package main;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RecipeManager {

    private ArrayList<Recipe> recipeList;
    private static final String FILE_NAME = "recipes.dat";

    public RecipeManager() {
        recipeList = new ArrayList<>();
        loadAllRecipes();  // Load recipes from file at startup
    }

    // Load recipes from the binary file
    @SuppressWarnings("unchecked")
    void loadAllRecipes() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                recipeList = (ArrayList<Recipe>) ois.readObject();
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
    public void addRecipe(String name, String description, Meal meal, ArrayList<String> ingredients, ArrayList<String> instructions, Boolean isFavorite) {
        // Provide default values for null fields
        name = name != null ? name : "Unnamed Recipe";
        description = description != null ? description : "No description";
        meal = meal != null ? meal : Meal.Lunch; // Assuming Meal.LUNCH is a valid default
        ingredients = ingredients != null ? ingredients : new ArrayList<>();
        instructions = instructions != null ? instructions : new ArrayList<>();
        isFavorite = isFavorite != null ? isFavorite : false;

        Recipe recipe = new Recipe(name, description, meal, ingredients, instructions, isFavorite);

        recipeList.add(recipe);
        saveAllRecipes();  // Save to file after adding the recipe
    }

    public List<Recipe> searchRecipesByName(String name) {
        List<Recipe> results = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            if (recipe.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(recipe);
            }
        }
        return results;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipeList;
    }

    public Recipe getRecipe(UUID recipeID) {
        for (Recipe recipe : recipeList) {
            if (recipe.getRecipeID().equals(recipeID)) {
                return recipe;
            }
        }
        return null;
    }

    // Edit a recipe by its ID
    public void editRecipe(UUID recipeID, String newName, String newDescription, Meal newMeal,
                           ArrayList<String> newIngredients, ArrayList<String> newInstructions, boolean newIsFavorite) {
        Recipe recipe = getRecipe(recipeID);
        if (recipe != null) {
            recipe.setName(newName != null ? newName : recipe.getName());
            recipe.setDescription(newDescription != null ? newDescription : recipe.getDescription());
            recipe.setMeal(newMeal != null ? newMeal : recipe.getMeal());
            recipe.setIngredients(newIngredients != null ? newIngredients : recipe.getIngredients());
            recipe.setInstructions(newInstructions != null ? newInstructions : recipe.getInstructions());
            recipe.setIsFavorite(newIsFavorite);
            saveAllRecipes();  // Save changes to file after editing
        }
    }

    // Delete a recipe by its ID
    public void deleteRecipe(UUID recipeID) {
        Recipe recipe = getRecipe(recipeID);
        if (recipe != null) {
            recipeList.remove(recipe);
            saveAllRecipes();  // Save changes to file after deleting
        }
    }

    // Sort recipes by criteria
    public List<Recipe> sortRecipes(String criteria) {
        List<Recipe> sortedRecipes = new ArrayList<>();
        
        switch (criteria) {
            case "Breakfast":
                for (Recipe recipe : recipeList) {
                    if (recipe.getMeal() == Meal.Breakfast) {
                        sortedRecipes.add(recipe);
                    }
                }
                break;
            case "Lunch":
                for (Recipe recipe : recipeList) {
                    if (recipe.getMeal() == Meal.Lunch) {
                        sortedRecipes.add(recipe);
                    }
                }
                break;
            case "Dinner":
                for (Recipe recipe : recipeList) {
                    if (recipe.getMeal() == Meal.Dinner) {
                        sortedRecipes.add(recipe);
                    }
                }
                break;
            case "Snack":
                for (Recipe recipe : recipeList) {
                    if (recipe.getMeal() == Meal.Snack) {
                        sortedRecipes.add(recipe);
                    }
                }
                break;
            case "Dessert":
                for (Recipe recipe : recipeList) {
                    if (recipe.getMeal() == Meal.Dessert) {
                        sortedRecipes.add(recipe);
                    }
                }
                break;
            case "Favorites":
                for (Recipe recipe : recipeList) {
                    if (recipe.getIsFavorite()) {
                        sortedRecipes.add(recipe);
                    }
                }
                break;
            default: // "All Recipes"
                sortedRecipes = new ArrayList<>(recipeList);
                break;
        }

        return sortedRecipes;
    }

    public void exportRecipeToFile(UUID recipeID, File file) throws IOException {
        if (recipeID != null && file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(displayRecipeDetails(recipeID));
            }
        }
    }
    
    public void toggleFavorite(UUID recipeID) {
        Recipe recipe = getRecipe(recipeID);
        if (recipe != null) {
            recipe.setIsFavorite(!recipe.getIsFavorite());
            saveAllRecipes();  // Save changes to file after toggling favorite
        }
    }
    
    public Map<String, UUID> getRecipeNamesAndIDs() {
        Map<String, UUID> recipeMap = new HashMap<>();
        for (Recipe recipe : recipeList) {
            recipeMap.put(recipe.getName(), recipe.getRecipeID());
        }
        return recipeMap;
    }
    
    // Display recipe details
	public String displayRecipeDetails(UUID recipeID) {
    	Recipe recipe = getRecipe(recipeID);
        StringBuilder details = new StringBuilder();
        
        details.append("Recipe ID: ").append(recipe.getRecipeID()).append("\n");
        details.append("Name: ").append(recipe.getName()).append("\n");
        details.append("Description: ").append(recipe.getDescription()).append("\n");
        details.append("Meal: ").append(recipe.getMeal()).append("\n");

        details.append("Ingredients:\n");
        for (String ingredient : recipe.getIngredients()) {
            details.append(" - ").append(ingredient).append("\n");
        }

        details.append("Instructions:\n");
        for (String instruction : recipe.getInstructions()) {
            details.append(" - ").append(instruction).append("\n");
        }

        details.append("Favorite: ").append(recipe.getIsFavorite() ? "Yes" : "No").append("\n");
        
        return details.toString();
    }
}
