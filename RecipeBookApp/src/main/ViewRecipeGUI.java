package main;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import javax.swing.JLabel;

public class ViewRecipeGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea recipeDetailsArea;
    private Recipe recipe;
    private RecipeManager recipeManager;

    public ViewRecipeGUI(RecipeManager recipeManager, Recipe recipe) {
        this.recipeManager = recipeManager;
        this.recipe = recipe;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Text area to show recipe details
        recipeDetailsArea = new JTextArea();
        recipeDetailsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(recipeDetailsArea);
        scrollPane.setBounds(5, 38, 440, 164);
        contentPane.add(scrollPane);

        // Bottom panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(5, 202, 440, 64);
        contentPane.add(buttonPanel);
        buttonPanel.setLayout(null);

        JButton editButton = new JButton("Edit Recipe");
        editButton.setBounds(41, 5, 173, 29);
        buttonPanel.add(editButton);
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editRecipe();
            }
        });

        JButton deleteButton = new JButton("Delete Recipe");
        deleteButton.setBounds(252, 5, 160, 29);
        buttonPanel.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteRecipe();
            }
        });

        JButton exportButton = new JButton("Export to Text File");
        exportButton.setBounds(252, 30, 160, 29);
        buttonPanel.add(exportButton);
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportRecipeToTextFile();
            }
        });

        // Favorite button to toggle favorite status
        JButton favoriteButton = new JButton(recipe.getIsFavorite() ? "Unmark Favorite" : "Mark as Favorite");
        favoriteButton.setBounds(41, 30, 173, 29);
        buttonPanel.add(favoriteButton);
        
        JLabel lblNewLabel = new JLabel("View Recipe");
        lblNewLabel.setBounds(182, 10, 80, 16);
        contentPane.add(lblNewLabel);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mnuBack_clk();
        	}
        });
        btnBack.setBounds(5, 5, 117, 29);
        contentPane.add(btnBack);
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
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this recipe?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            recipeManager.deleteRecipe(recipe.getRecipeID());
            JOptionPane.showMessageDialog(this, "Recipe deleted successfully.");
            dispose();  // Close the current window after deletion
        }
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
        recipeManager.editRecipe(recipe.getRecipeID(), null, null, null, null, null, !recipe.getIsFavorite());
        recipe.setIsFavorite(!recipe.getIsFavorite());
    }
    
    private void mnuBack_clk() {
    	this.dispose();
    }
    
}
