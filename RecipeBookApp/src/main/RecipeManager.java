package main;

import java.util.ArrayList;

public class RecipeManager {

    ArrayList<Recipe> recipeList;
    private int recipeCounter;

    public RecipeManager() {
        recipeList = new ArrayList<>();
        recipeCounter = 0;  
    }
    
    // This should handle all of the functionality to manage the recipe

    void loadAllRecipes() {
        
    }
    
    void saveAllRecipes() {
        
    }
    
    void editRecipe(int recipeID) {
        
    }
    
    void deletesRecipe(int recipeID) {
        
    }

    public void addRecipe(Recipe recipe) {
        recipe.setRecipeID(++recipeCounter);
        recipeList.add(recipe);
        saveAllRecipes();
    }

    public ArrayList<Recipe> getRecipes() {
        return recipeList;
    }

    public Recipe getRecipe(int recipeID) {
        for (Recipe recipe : recipeList) {
            if (recipe.getRecipeID() == recipeID) {
                return recipe;
            }
        }
        return null;
    }
}
