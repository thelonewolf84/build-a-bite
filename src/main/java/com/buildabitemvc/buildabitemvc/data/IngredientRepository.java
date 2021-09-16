package com.buildabitemvc.buildabitemvc.data;

import com.buildabitemvc.buildabitemvc.models.Ingredient;
import com.buildabitemvc.buildabitemvc.models.IngredientType;
import com.buildabitemvc.buildabitemvc.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
    Ingredient findByType(IngredientType type);

    List<Ingredient> findAllByType(IngredientType type);

    Optional<Ingredient> findByIngredientName(String ingredientName);

    Ingredient findById(int id);
}
