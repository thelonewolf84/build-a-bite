package com.buildabitemvc.buildabitemvc.models.dto;

import com.buildabitemvc.buildabitemvc.models.Ingredient;

import java.util.List;

public class IngredientDTO {

    private String ingredientName;

    private String type;

    private String measurement;

    public IngredientDTO() {
    }

    public IngredientDTO(String ingredientName, String type, String measurement) {
        this.ingredientName = ingredientName;
        this.type = type;
        this.measurement = measurement;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
}
