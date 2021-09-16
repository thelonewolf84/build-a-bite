package com.buildabitemvc.buildabitemvc.models.dto;

import com.buildabitemvc.buildabitemvc.models.Ingredient;
import com.buildabitemvc.buildabitemvc.models.Recipe;
import com.buildabitemvc.buildabitemvc.models.Tag;

import javax.validation.constraints.NotNull;

public class RecipeIngredientDTO {
    @NotNull
    private Recipe recipe;

    @NotNull
    private Ingredient ingredient;

    @NotNull
    private Tag tag;

    public RecipeIngredientDTO() {
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
