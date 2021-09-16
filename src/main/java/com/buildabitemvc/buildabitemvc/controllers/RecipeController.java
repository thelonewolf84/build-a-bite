package com.buildabitemvc.buildabitemvc.controllers;


import com.buildabitemvc.buildabitemvc.data.*;
import com.buildabitemvc.buildabitemvc.models.*;
import com.buildabitemvc.buildabitemvc.models.dto.IngredientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("recipes")
public class RecipeController {

    Recipe addedRecipe;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;


    @GetMapping
    public String displayRecipe(@RequestParam(required = false) Integer recipeId, Model model, HttpServletRequest request){

        HttpSession session = request.getSession();
        User sessionUser = authenticationController.getUserFromSession(session);
        int userId = sessionUser.getId();

        model.addAttribute("profile", userRepository.findById(userId).get());

        if (recipeId == null) {
            model.addAttribute("recipes", recipeRepository.findAll());
            return "recipes/recipes";
        }else {
            Optional<Recipe> result = recipeRepository.findById(recipeId);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid Recipe ID: " + recipeId);
            } else {
                Recipe recipe = result.get();
                model.addAttribute("title", "Recipes in category: " + recipe.getRecipeName());
                model.addAttribute("recipe", recipe);
            }
            return "recipes/recipe-detail";
        }

    }

    @GetMapping("/added")
    public String displayAddRecipe(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User sessionUser = authenticationController.getUserFromSession(session);
        int userId = sessionUser.getId();

        model.addAttribute("profile", userRepository.findById(userId).get());
        model.addAttribute("recipe",addedRecipe);
        return "recipes/added";
    }

    @GetMapping("/saved")
    public String displaySavedRecipes(@RequestParam(required = false) Integer recipeId, Model model, HttpServletRequest request){

        HttpSession session = request.getSession();
        User sessionUser = authenticationController.getUserFromSession(session);
        User user = userRepository.findById(sessionUser.getId()).get();
        int userId = sessionUser.getId();

        model.addAttribute("profile", userRepository.findById(userId).get());
        //If a recipe id is received
        if(recipeId != null) {
            //find the recipe by id
            Optional<Recipe> result = recipeRepository.findById(recipeId);
            //if it's in the data base get the recipe
            Recipe recipe = result.get();
            //add the recipe to the users list of recipes
            user.addRecipe(recipe);
            userRepository.save(user);
            model.addAttribute("recipes", recipe);
            return "recipes/saved";

        }else{
            model.addAttribute("title", "You Have Saved " + user.getRecipes().size() + " Recipes.");
            model.addAttribute("recipes", user.getRecipes());
            return "recipes/saved";
        }
    }

    @GetMapping("/create")
    public String renderCreateRecipe(Model model, HttpServletRequest request){

        HttpSession session = request.getSession();
        User sessionUser = authenticationController.getUserFromSession(session);
        int userId = sessionUser.getId();

        model.addAttribute("ingredientTypes", IngredientType.values());
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("tags", tagRepository.findAll());
        model.addAttribute("profile", userRepository.findById(userId).get());
        return "recipes/create";
    }

    @PostMapping("/create")
    public String createRecipe(
            @ModelAttribute @Valid Recipe newRecipe,
            @RequestParam(required = false) int [] tagIds,
            @RequestParam String ingredientName1, @RequestParam String ingredientType1, @RequestParam String measurement1,
            @RequestParam(required = false)  String ingredientName2, @RequestParam(required = false) String ingredientType2, @RequestParam(required = false)  String measurement2,
            @RequestParam(required = false)  String ingredientName3, @RequestParam(required = false)  String ingredientType3, @RequestParam(required = false) String measurement3,
            @RequestParam(required = false)  String ingredientName4, @RequestParam(required = false)  String ingredientType4, @RequestParam(required = false)  String measurement4,
            @RequestParam(required = false)  String ingredientName5, @RequestParam(required = false)  String ingredientType5, @RequestParam(required = false)  String measurement5,
            @RequestParam(required = false)  String ingredientName6, @RequestParam(required = false)  String ingredientType6, @RequestParam(required = false)  String measurement6,
            @RequestParam(required = false)  String ingredientName7, @RequestParam(required = false)  String ingredientType7, @RequestParam(required = false)  String measurement7,
            @RequestParam(required = false)  String ingredientName8, @RequestParam(required = false)  String ingredientType8, @RequestParam(required = false)  String measurement8,
            @RequestParam(required = false)  String ingredientName9, @RequestParam(required = false)  String ingredientType9, @RequestParam(required = false)  String measurement9,
            @RequestParam(required = false)  String ingredientName10, @RequestParam(required = false)  String ingredientType10, @RequestParam(required = false)  String measurement10,
            HttpSession session, Model model, Errors errors){

        int currentUserId = (int) session.getAttribute("user");
        User user = userRepository.findById(currentUserId).get();

        List<IngredientDTO> ingredientDTOList = new ArrayList<>();

        List<RecipeIngredient> recipeIngredients = new ArrayList<>();

        List<Integer> recipeIngredientsId = new ArrayList<>();

        List<Tag> tags = new ArrayList<>();

        if(ingredientName1 != null) {
            ingredientDTOList.add(new IngredientDTO(ingredientName1, ingredientType1, measurement1));
        }

        if(ingredientName2 != null){
            ingredientDTOList.add(new IngredientDTO(ingredientName2, ingredientType2, measurement2));
        }
        if(ingredientName3 != null){
            ingredientDTOList.add(new IngredientDTO(ingredientName3, ingredientType3, measurement3));
        }
        if(ingredientName4 != null){
            ingredientDTOList.add(new IngredientDTO(ingredientName4, ingredientType4, measurement4));
        }
        if(ingredientName5 != null){
            ingredientDTOList.add(new IngredientDTO(ingredientName5, ingredientType5, measurement5));
        }
        if(ingredientName6 != null){
            ingredientDTOList.add(new IngredientDTO(ingredientName6, ingredientType6, measurement6));
        }
        if(ingredientName7 != null){
            ingredientDTOList.add(new IngredientDTO(ingredientName7, ingredientType7, measurement7));
        }
        if(ingredientName8 != null){
            ingredientDTOList.add(new IngredientDTO(ingredientName8, ingredientType8, measurement8));
        }
        if(ingredientName9 != null){
            ingredientDTOList.add(new IngredientDTO(ingredientName9, ingredientType9, measurement9));
        }
        if(ingredientName10 != null){
            ingredientDTOList.add(new IngredientDTO(ingredientName10, ingredientType10, measurement10));
        }

        for (IngredientDTO ingredientDTO : ingredientDTOList){
            Optional<Ingredient> result = ingredientRepository.findByIngredientName(ingredientDTO.getIngredientName());
            if(result.isPresent()){
                RecipeIngredient recipeIngredient = recipeIngredientRepository.save(new RecipeIngredient(result.get(), ingredientDTO.getMeasurement()));
                recipeIngredientsId.add(recipeIngredient.getId());
            }else{
                Ingredient ingredient = new Ingredient(ingredientDTO.getIngredientName(), IngredientType.valueOf(ingredientDTO.getType()));
                ingredientRepository.save(ingredient);

                RecipeIngredient recipeIngredient = recipeIngredientRepository.save(new RecipeIngredient(ingredient, ingredientDTO.getMeasurement()));
                recipeIngredientsId.add(recipeIngredient.getId());

            }
        }

        if(errors.hasErrors()){
            return "recipes/create";
        }

        for (int id : tagIds) {
            tags.add(tagRepository.findById(id));
        }

        for(Integer id : recipeIngredientsId){
            Optional<RecipeIngredient> result = recipeIngredientRepository.findById(id);
            recipeIngredients.add(result.get());
        }

        newRecipe.addTags(tags);
        newRecipe.addRecipeIngredients(recipeIngredients);
        newRecipe.setDateCreated(new Date());
        newRecipe.setCreated(true);

        recipeRepository.save(newRecipe);
        addedRecipe = newRecipe;

        //user.addRecipe(newRecipe);
        model.addAttribute("profile", userRepository.findById(currentUserId).get());
        model.addAttribute("user", user);
        return "recipes/added";
    }

    @GetMapping("/delete")
    public String deleteRecipe(@RequestParam(required = false) Integer recipeId, Model model, HttpServletRequest request){

        HttpSession session = request.getSession();
        User sessionUser = authenticationController.getUserFromSession(session);
        User user = userRepository.findById(sessionUser.getId()).get();
        int userId = sessionUser.getId();

        if(recipeId == null) {
            model.addAttribute("profile", userRepository.findById(userId).get());
            model.addAttribute("recipes", user.getRecipes());
            return "recipes/delete";
        }else{
            Optional<Recipe> recipe = recipeRepository.findById(recipeId);
            user.removeRecipe(recipe.get());
            userRepository.save(user);
            model.addAttribute("removed","You successfully removed " + recipe.get().getRecipeName() + " from your favorites.");
            model.addAttribute("title", "You Have Saved " + user.getRecipes().size() + " Recipes.");
            return "recipes/saved";
        }
    }

    @PostMapping("/delete")
    public String processDeleteRecipe(Model model, @RequestParam(required = false) int [] recipeIds,HttpServletRequest request){
        HttpSession session = request.getSession();
        User sessionUser = authenticationController.getUserFromSession(session);
        User user = userRepository.findById(sessionUser.getId()).get();
        int userId = sessionUser.getId();

        model.addAttribute("profile", userRepository.findById(userId).get());

        if(recipeIds != null) {
            for (int id : recipeIds) {
                Optional<Recipe> recipe = recipeRepository.findById(id);
                user.removeRecipe(recipe.get());
            }
        }

        userRepository.save(user);

        model.addAttribute("removed","You successfully removed " + recipeIds.length + " recipes from your favorites.");
        model.addAttribute("title", "You Have Saved " + user.getRecipes().size() + " Recipes.");

        return "recipes/saved";
    }

    @GetMapping("/search")
    public String renderRecipeSearch(Model model, HttpServletRequest request){

        HttpSession session = request.getSession();
        User sessionUser = authenticationController.getUserFromSession(session);
        int userId = sessionUser.getId();

        model.addAttribute("profile", userRepository.findById(userId).get());

        return "recipes/recipe-search";
    }

    @PostMapping("/search")
    public String displayRecipeSearch(@RequestParam String searchTerm, Model model, HttpServletRequest request){

        HttpSession session = request.getSession();
        User sessionUser = authenticationController.getUserFromSession(session);
        int userId = sessionUser.getId();

        model.addAttribute("profile", userRepository.findById(userId).get());

        if(searchTerm == null || searchTerm.equals("")){
            model.addAttribute("noSearchTerm",  "Please Enter A Valid Search Term.");
            return "recipes/recipe-search";
        }

        List<Recipe> recipes = new ArrayList<>();

        for (Recipe recipe : recipeRepository.findAll()){
            if (recipe.getRecipeName().toLowerCase().trim().contains(searchTerm.toLowerCase().trim())){
                recipes.add(recipe);
            }
        }

        if (recipes.isEmpty()){
            model.addAttribute("noRecipes", "No recipes with the search term " + searchTerm + ".");
            return "recipes/recipes";
        }
        model.addAttribute("noRecipes", recipes.size() + " recipes found with the search term " + searchTerm + ".");
        model.addAttribute("recipes", recipes);
        return "recipes/recipes";
    }

    @GetMapping("/update")
    public String renderUpdateRecipe(@RequestParam(required = false) Integer recipeId, Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User sessionUser = authenticationController.getUserFromSession(session);
        int userId = sessionUser.getId();

        model.addAttribute("profile", userRepository.findById(userId).get());

        if(recipeId == null){
            List<Recipe> recipes = new ArrayList<>();

            for(Recipe recipe : recipeRepository.findAll()){
                if(recipe.isCreated()){
                    recipes.add(recipe);
                }
            }

            model.addAttribute("recipes", recipes);
            model.addAttribute("noRecipes", "You have created " + recipes.size() + " recipes.");
            return "recipes/update";
        }

        model.addAttribute("recipe", recipeRepository.findById(recipeId));

        return "recipes/update-recipe";
    }
/*
    @GetMapping("add-tag")
    public String displayAddIngredientForm(@RequestParam Integer recipeId, Model model){
        Optional<Recipe> result = recipeRepository.findById(recipeId);
        Recipe recipe = result.get();
        model.addAttribute("title", "Add Tag to: " + recipe.getRecipeName());
        model.addAttribute("Ingredient", ingredientRepository.findAll());
        RecipeIngredientDTO recipeIngredient = new RecipeIngredientDTO();
        recipeIngredient.setRecipe(recipe);
        model.addAttribute("recipeIngredient", recipeIngredient);
        return "recipes/add-ingredient";
    }
 */
}


