package com.buildabitemvc.buildabitemvc.data;


import com.buildabitemvc.buildabitemvc.models.RecipeIngredient;
import org.springframework.data.repository.CrudRepository;

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Integer> {
}
