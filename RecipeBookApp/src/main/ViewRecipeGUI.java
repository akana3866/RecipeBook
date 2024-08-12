package main;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
    private RecipeManager recipeManager;
    private UUID recipeID;
    private JButton favoriteButton;
    private JScrollPane scrollPane;
    private JPanel buttonPanel;
    private JButton editButton;
    private JLabel lblNewLabel;
    private JButton btnBack;
    private JButton exportButton;
    private JButton deleteButton;
    
    public ViewRecipeGUI(RecipeManager recipeManager, UUID recipeID) {
        this.recipeManager = recipeManager;
        this.recipeID = recipeID;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Text area to show recipe details
        recipeDetailsArea = new JTextArea();
        recipeDetailsArea.setEditable(false);
        scrollPane = new JScrollPane(recipeDetailsArea);
        scrollPane.setBounds(5, 38, 440, 164);
        contentPane.add(scrollPane);

        // Bottom panel for buttons
        buttonPanel = new JPanel();
        buttonPanel.setBounds(5, 202, 440, 64);
        contentPane.add(buttonPanel);
        buttonPanel.setLayout(null);

        editButton = new JButton("Edit Recipe");
        editButton.setBounds(41, 5, 173, 29);
        buttonPanel.add(editButton);
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editRecipe();
            }
        });

        deleteButton = new JButton("Delete Recipe");
        deleteButton.setBounds(252, 5, 160, 29);
        buttonPanel.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteRecipe();
            }
        });

        exportButton = new JButton("Export to Text File");
        exportButton.setBounds(252, 30, 160, 29);
        buttonPanel.add(exportButton);
        exportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportRecipeToTextFile();
            }
        });

        // Favorite button to toggle favorite status
        Recipe recipe = recipeManager.getRecipe(recipeID);
        favoriteButton = new JButton(recipe.getIsFavorite() ? "Unmark Favorite" : "Mark as Favorite");
        favoriteButton.setBounds(41, 30, 173, 29);
        buttonPanel.add(favoriteButton);
        
        lblNewLabel = new JLabel("View Recipe");
        lblNewLabel.setBounds(182, 10, 80, 16);
        contentPane.add(lblNewLabel);
        
        btnBack = new JButton("Back");
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
            }
        });

        displayRecipeDetails();
    }

    private void displayRecipeDetails() {
        if (recipeID != null) {
            recipeDetailsArea.setText(recipeManager.displayRecipeDetails(this.recipeID));
        }
    }

    private void editRecipe() {
        // Create and show the EditRecipeGUI when the "Edit Recipe" button is clicked
        EditGUI editGUI = new EditGUI(recipeManager, recipeManager.getRecipe(this.recipeID));
        editGUI.setVisible(true);
    }

    private void deleteRecipe() {
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this recipe?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            recipeManager.deleteRecipe(recipeID);
            JOptionPane.showMessageDialog(this, "Recipe deleted successfully.");
            dispose();  // Close the current window after deletion
        }
    }

    private void exportRecipeToTextFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Recipe As");

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                recipeManager.exportRecipeToFile(recipeID, fileToSave);
                JOptionPane.showMessageDialog(this, "Recipe saved successfully to " + fileToSave.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "An error occurred while saving the recipe.", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    private void toggleFavorite() {
        recipeManager.toggleFavorite(recipeID);
    
        // Retrieve the updated recipe to check its new favorite status
        Recipe updatedRecipe = recipeManager.getRecipe(recipeID);
    
        // Display a message indicating the new status
        if (updatedRecipe.getIsFavorite()) {
            JOptionPane.showMessageDialog(this, "Recipe has been marked as a favorite.", "Favorite Status", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Recipe has been unmarked as a favorite.", "Favorite Status", JOptionPane.INFORMATION_MESSAGE);
        }
    
        // Update the button text to reflect the new status
        favoriteButton.setText(updatedRecipe.getIsFavorite() ? "Unmark Favorite" : "Mark as Favorite");
    }
    
    
    private void mnuBack_clk() {
        this.dispose();
    }
}
