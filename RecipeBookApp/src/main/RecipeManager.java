package main;

import java.io.*;
import java.util.ArrayList;

public class RecipeManager {

    private ArrayList<Recipe> recipeList;
    private int recipeCounter;
    private static final String FILE_NAME = "recipes.dat";

    public RecipeManager() {
        recipeList = new ArrayList<>();
        recipeCounter = 0;
        loadAllRecipes();  // Load recipes from file at startup
    }

    // Load recipes from the binary file
    @SuppressWarnings("unchecked")
    void loadAllRecipes() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                recipeList = (ArrayList<Recipe>) ois.readObject();
                recipeCounter = recipeList.size();  // Ensure the counter reflects the number of recipes
                System.out.println("Recipes loaded from file.");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Error loading recipes. Starting with an empty recipe list.");
                recipeList = new ArrayList<>();  // Reset to empty if loading fails
            }
        } else {
            System.out.println("No saved recipes found. Starting with an empty recipe list.");
        }
    }

    // Save recipes to the binary file
    void saveAllRecipes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(recipeList);
            System.out.println("Recipes saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving recipes.");
        }
    }

    // Add a new recipe and save to binary file
    public void addRecipe(Recipe recipe) {
        recipe.setRecipeID(++recipeCounter);
        recipeList.add(recipe);
        saveAllRecipes();  // Save to file after adding the recipe
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

    void editRecipe(int recipeID) {
        // Implementation for editing a recipe
    }

    void deletesRecipe(int recipeID) {
        // Implementation for deleting a recipe
    }
}
