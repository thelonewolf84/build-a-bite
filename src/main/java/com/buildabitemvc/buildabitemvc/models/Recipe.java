package com.buildabitemvc.buildabitemvc.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Recipe extends AbstractEntity{
    @NotBlank(message = "Name can not be blank!")
    @Size(min = 1, max = 50, message = "Recipe name must be between 1 and 50 characters in length!")
    private String recipeName;

    @NotBlank(message = "Please enter directions for your recipe!")
    @Size(max = 1000)
    private String directions;

    private String urlImage;

    @NotBlank(message = "Name can not be blank!")
    @Size(min = 1, max = 15, message = "Prep time name must be between 1 and 50 characters in length!")
    private String prepTime;

    @NotBlank(message = "Name can not be blank!")
    @Size(min = 1, max = 15, message = "Prep time name must be between 1 and 50 characters in length!")
    private String cookTime;

    private short servers;

    private Date dateCreated;

    private Date dateUpdated;

    private boolean created = false;

    @OneToMany
    @JoinColumn
    private final List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    public Recipe(){}

    public Recipe(String recipeName, String directions, String urlImage, String prepTime, String cookTime, short servers, Date dateCreated) {
        this.recipeName = recipeName;
        this.directions = directions;
        this.urlImage = urlImage;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servers = servers;
        this.dateCreated = new Date();
    }


    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public short getServers() {
        return servers;
    }

    public void setServers(short servers) {
        this.servers = servers;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void addRecipeIngredient(RecipeIngredient recipeIngredient) {
        this.recipeIngredients.add(recipeIngredient);
    }

    public void addRecipeIngredients(List<RecipeIngredient> recipeIngredients){
        for(RecipeIngredient recipeIngredient : recipeIngredients) {
            this.addRecipeIngredient(recipeIngredient);
        }
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void addTags(List<Tag> tags){
        for(Tag tag : tags) {
            this.addTag(tag);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return recipeName;
    }

}
