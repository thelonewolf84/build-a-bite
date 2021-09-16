package com.buildabitemvc.buildabitemvc.controllers;

import com.buildabitemvc.buildabitemvc.data.IngredientRepository;
import com.buildabitemvc.buildabitemvc.data.RecipeRepository;
import com.buildabitemvc.buildabitemvc.data.UserRepository;
import com.buildabitemvc.buildabitemvc.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping
public class IngredientController {

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    UserRepository userRepository;

    @GetMapping("ingredients")
    public String displayIngredients(Model model, HttpServletRequest request){

        HttpSession session = request.getSession();
        User sessionUser = authenticationController.getUserFromSession(session);
        int userId = sessionUser.getId();

        Map<String,List<Ingredient>> ingredientListMap = new HashMap<>();

        for (IngredientType ingredientType : IngredientType.values()){
            ingredientListMap.put(ingredientType.getDisplayName(), new ArrayList<>());
            System.out.println(ingredientListMap);
        }

        for(Ingredient ingredient : ingredientRepository.findAll()){
            ingredientListMap.get(ingredient.getType().getDisplayName()).add(ingredient);
        }

        model.addAttribute("profile", userRepository.findById(userId).get());
        model.addAttribute("ingredients", ingredientListMap);

        return "ingredients/ingredients";
    }

    @PostMapping("ingredients")
    public String displayRecipeSearch(@RequestParam(required = false) int [] ingredientIds, Model model, HttpServletRequest request){

        HttpSession session = request.getSession();
        User sessionUser = authenticationController.getUserFromSession(session);
        int userId = sessionUser.getId();

        model.addAttribute("profile", userRepository.findById(userId).get());

        List<Ingredient> searchIngredients = new ArrayList<>();

        List<Recipe> searchRecipes = new ArrayList<>();

        for(int id : ingredientIds){
            searchIngredients.add(ingredientRepository.findById(id));
        }
        Collections.sort(searchIngredients);

        Iterable<Recipe> recipes = recipeRepository.findAll();

        for (Recipe recipe : recipes){

            List<RecipeIngredient> recipeIngredients = recipe.getRecipeIngredients();
            List<Ingredient> ingredients = new ArrayList<>();

            for(RecipeIngredient recipeIngredient : recipeIngredients){
                ingredients.add(recipeIngredient.getIngredient());
            }

            Collections.sort(ingredients);

            if(ingredients.equals(searchIngredients)){
                searchRecipes.add(recipe);
            }else if(ingredients.contains(searchIngredients)){

            }
            ingredients.clear();
        }

        if(searchRecipes.isEmpty()){
            model.addAttribute("recipesFound", "No recipes found");
        }else{
            model.addAttribute("recipesFound", searchRecipes.size() + " Recipes Found");
            model.addAttribute("recipes", searchRecipes);
        }

        return "ingredients/ingredient-recipe-search";
    }

    @GetMapping("ingredients/add")
    public String renderAddIngredient(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User sessionUser = authenticationController.getUserFromSession(session);
        int userId = sessionUser.getId();

        model.addAttribute("profile", userRepository.findById(userId).get());
        model.addAttribute("title", "Add an Ingredient");
        model.addAttribute(new Ingredient());
        model.addAttribute("ingredientTypes", IngredientType.values());
        return "ingredients/add";
    }

    @PostMapping("ingredients/add")
    public String addIngredient(@ModelAttribute Ingredient newIngredient, Model model, Errors errors, HttpServletRequest request){
        HttpSession session = request.getSession();
        User sessionUser = authenticationController.getUserFromSession(session);
        int userId = sessionUser.getId();

        model.addAttribute("profile", userRepository.findById(userId).get());

        if(errors.hasErrors()){
            model.addAttribute("title", "Add an Ingredient");
            return "ingredients/add";
        }

        ingredientRepository.save(newIngredient);
        return "redirect:";
    }
}
