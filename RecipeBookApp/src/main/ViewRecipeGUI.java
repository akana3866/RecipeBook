package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewRecipeGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea recipeDetailsArea;
    private Recipe recipe;
    private RecipeManager recipeManager;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Creating a sample Recipe object for demonstration
                    ArrayList<String> ingredients = new ArrayList<>();
                    ingredients.add("Ingredient 1");
                    ingredients.add("Ingredient 2");

                    ArrayList<String> instructions = new ArrayList<>();
                    instructions.add("Step 1");
                    instructions.add("Step 2");

                    Recipe sampleRecipe = new Recipe(1, "Sample Recipe", "A delicious meal", Meal.BREAKFAST, 
                            ingredients, instructions, false);
                    
                    // Dummy RecipeManager for demonstration
                    RecipeManager recipeManager = new RecipeManager();

                    // Instantiating the ViewRecipeGUI with the sample recipe
                    ViewRecipeGUI frame = new ViewRecipeGUI(recipeManager, sampleRecipe);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();  // Print stack trace for debugging
                }
            }
        });
    }

    public ViewRecipeGUI(RecipeManager recipeManager, Recipe recipe) {
        this.recipeManager = recipeManager;
        this.recipe = recipe;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Text area to show recipe details
        recipeDetailsArea = new JTextArea();
        recipeDetailsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(recipeDetailsArea);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Bottom panel for buttons
        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JButton editButton = new JButton("Edit Recipe");
        buttonPanel.add(editButton);
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editRecipe();
            }
        });

        JButton deleteButton = new JButton("Delete Recipe");
        buttonPanel.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteRecipe();
            }
        });

        JButton exportButton = new JButton("Export to Text File");
        buttonPanel.add(exportButton);
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportRecipeToTextFile();
            }
        });

        JButton favoriteButton = new JButton(recipe.getIsFavorite() ? "Unmark Favorite" : "Mark as Favorite");
        buttonPanel.add(favoriteButton);
        favoriteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleFavorite();
                favoriteButton.setText(recipe.getIsFavorite() ? "Unmark Favorite" : "Mark as Favorite");
            }
        });

        displayRecipeDetails();
    }

    private void displayRecipeDetails() {
        recipeDetailsArea.setText("Recipe Name: " + recipe.getName() + "\n");
        recipeDetailsArea.append("Meal Type: " + recipe.getMeal() + "\n");
        recipeDetailsArea.append("Description: " + recipe.getDescription() + "\n\n");

        recipeDetailsArea.append("Ingredients:\n");
        for (String ingredient : recipe.getIngredients()) {
            recipeDetailsArea.append("- " + ingredient + "\n");
        }

        recipeDetailsArea.append("\nInstructions:\n");
        for (String instruction : recipe.getInstructions()) {
            recipeDetailsArea.append("- " + instruction + "\n");
        }
    }

    private void editRecipe() {
        // Create and show the EditRecipeGUI when the "Edit Recipe" button is clicked
        EditGUI editGUI = new EditGUI(recipeManager, recipe);
        editGUI.setVisible(true);
    }

    private void deleteRecipe() {
        // Implement the delete functionality
        // For example, remove the recipe from the list or database
    }

    private void exportRecipeToTextFile() {
        // Show a file chooser dialog to select the location to save the file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Recipe As");
        
        int userSelection = fileChooser.showSaveDialog(this);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            
            try (FileWriter writer = new FileWriter(fileToSave)) {
                writer.write("Recipe Name: " + recipe.getName() + "\n");
                writer.write("Meal Type: " + recipe.getMeal() + "\n");
                writer.write("Description: " + recipe.getDescription() + "\n\n");
                
                writer.write("Ingredients:\n");
                for (String ingredient : recipe.getIngredients()) {
                    writer.write("- " + ingredient + "\n");
                }

                writer.write("\nInstructions:\n");
                for (String instruction : recipe.getInstructions()) {
                    writer.write("- " + instruction + "\n");
                }

                JOptionPane.showMessageDialog(this, "Recipe saved successfully to " + fileToSave.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "An error occurred while saving the recipe.", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    private void toggleFavorite() {
        // Implement the favorite functionality
        recipe.setIsFavorite(!recipe.getIsFavorite());
    }
}
