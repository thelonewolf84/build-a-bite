package com.buildabitemvc.buildabitemvc.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;


@Entity
public class RecipeIngredient extends AbstractEntity{

    @OneToOne(cascade = CascadeType.ALL)
    private Ingredient ingredient;

    @NotBlank(message = "Measurement can not be blank!")
    private String measurement;

    @ManyToOne
    private Recipe recipe;

    public RecipeIngredient() {
    }

    public RecipeIngredient(Ingredient ingredient, String measurement) {
        this.ingredient = ingredient;
        this.measurement = measurement;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return measurement + " " + ingredient.getIngredientName();
    }
}
