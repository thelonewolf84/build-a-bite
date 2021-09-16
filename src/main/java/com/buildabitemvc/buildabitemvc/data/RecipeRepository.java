package com.buildabitemvc.buildabitemvc.data;

import com.buildabitemvc.buildabitemvc.models.Ingredient;
import com.buildabitemvc.buildabitemvc.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    Iterable <Recipe> getAllRecipesByUserId(int user_id);
}
