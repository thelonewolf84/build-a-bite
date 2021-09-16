package com.buildabitemvc.buildabitemvc.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingredient extends AbstractEntity implements Comparable<Ingredient>{

    @NotBlank(message = "Ingredient name can not be blank!")
    @Size(min = 1, max = 50, message = "Ingredient name must be between 1 and 50 characters in length!")
    private String ingredientName;

    private IngredientType type;

    public Ingredient(String ingredientName, IngredientType type) {
        this.ingredientName = ingredientName;
        this.type = type;
    }

    public Ingredient(){}

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public IngredientType getType() {
        return type;
    }

    public void setType(IngredientType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.ingredientName;
    }

    @Override
    public int compareTo(Ingredient ingredient) {

        if(this.getId() == ingredient.getId()){
            return 0;
        }else if(this.getId() > ingredient.getId()){
            return 1;
        }else{
            return -1;
        }
    }
}
