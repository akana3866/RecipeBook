package main;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;  // Serialization identifier

    private int recipeID;
    private String name;
    private String description;
    private Meal meal;
    private ArrayList<String> ingredients;
    private ArrayList<String> instructions;
    private Boolean isFavorite;

    public Recipe(int recipeID, String name, String description, Meal meal, ArrayList<String> ingredients,
            ArrayList<String> instructions, boolean isFavorite) {
        this.recipeID = recipeID;
        this.name = name;
        this.description = description;
        this.meal = meal;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.isFavorite = isFavorite;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<String> instructions) {
        this.instructions = instructions;
    }

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
}
