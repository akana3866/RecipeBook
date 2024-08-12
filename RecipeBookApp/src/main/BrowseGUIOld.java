package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class BrowseGUIOld extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JList<String> recipeList;
    private JTextArea recipeDetailArea;
    private JComboBox<String> sortComboBox;
    private RecipeManager recipeManager;
    private DefaultListModel<String> listModel;
    private List<Recipe> currentDisplayedRecipes;
    
    private JPanel sortPanel;
    private JButton sortButton;
    private JSplitPane splitPane;
    private JScrollPane listScrollPane;
    private JScrollPane detailScrollPane;
    private JButton btnBack;
    
    public BrowseGUIOld(RecipeManager recipeManager) {
        this.recipeManager = recipeManager;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Top panel for the sorting options
        sortPanel = new JPanel();
        sortPanel.setBounds(167, 6, 240, 39);
        contentPane.add(sortPanel);

        // Combo box for sorting options
        String[] sortOptions = { "All Recipes", "Breakfast", "Lunch", "Dinner", "Favorites" };
        sortComboBox = new JComboBox<>(sortOptions);
        sortPanel.add(sortComboBox);

        sortButton = new JButton("Sort");
        sortPanel.add(sortButton);
        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) sortComboBox.getSelectedItem();
                sortRecipes(selectedOption);
            }
        });

        // Center panel with a split layout for list and details
        splitPane = new JSplitPane();
        splitPane.setBounds(5, 47, 590, 320);
        contentPane.add(splitPane);

        // List of recipes
        listModel = new DefaultListModel<>();
        recipeList = new JList<>(listModel);
        recipeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        recipeList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = recipeList.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        displayRecipeDetails(currentDisplayedRecipes.get(index));
                    }
                }
            }
        });
        listScrollPane = new JScrollPane(recipeList);
        splitPane.setLeftComponent(listScrollPane);

        // Text area to display the selected recipe details
        recipeDetailArea = new JTextArea();
        recipeDetailArea.setEditable(false);
        detailScrollPane = new JScrollPane(recipeDetailArea);
        splitPane.setRightComponent(detailScrollPane);
        
        btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mnuBack_clk();
        	}
        });
        btnBack.setBounds(6, 6, 117, 29);
        contentPane.add(btnBack);

        // Load and display all recipes initially
        sortRecipes("All Recipes");
    }

    private void sortRecipes(String criteria) {
        List<Recipe> recipesToDisplay = new ArrayList<>();

        switch (criteria) {
            case "Breakfast":
                for (Recipe recipe : recipeManager.getRecipes()) {
                    if (recipe.getMeal() == Meal.Breakfast) {
                        recipesToDisplay.add(recipe);
                    }
                }
                break;
            case "Lunch":
                for (Recipe recipe : recipeManager.getRecipes()) {
                    if (recipe.getMeal() == Meal.Lunch) {
                        recipesToDisplay.add(recipe);
                    }
                }
                break;
            case "Dinner":
                for (Recipe recipe : recipeManager.getRecipes()) {
                    if (recipe.getMeal() == Meal.Dinner) {
                        recipesToDisplay.add(recipe);
                    }
                }
                break;
            case "Favorites":
                for (Recipe recipe : recipeManager.getRecipes()) {
                    if (recipe.getIsFavorite()) {
                        recipesToDisplay.add(recipe);
                    }
                }
                break;
            default: // "All Recipes"
                recipesToDisplay = recipeManager.getRecipes();
                break;
        }

        displayRecipeNames(recipesToDisplay);
        currentDisplayedRecipes = recipesToDisplay;
    }

    private void displayRecipeNames(List<Recipe> recipes) {
        listModel.clear();  // Clear the current list
        for (Recipe recipe : recipes) {
            listModel.addElement(recipe.getName());  // Add the recipe name to the list
        }
    }
    
    private void displayRecipeDetails(Recipe recipe) {
        recipeDetailArea.setText("");  // Clear the text area
        recipeDetailArea.append("Recipe ID: " + recipe.getRecipeID() + "\n");
        recipeDetailArea.append("Name: " + recipe.getName() + "\n");
        recipeDetailArea.append("Description: " + recipe.getDescription() + "\n");
        recipeDetailArea.append("Meal: " + recipe.getMeal() + "\n");

        recipeDetailArea.append("Ingredients:\n");
        for (String ingredient : recipe.getIngredients()) {
            recipeDetailArea.append(" - " + ingredient + "\n");
        }

        recipeDetailArea.append("Instructions:\n");
        for (String instruction : recipe.getInstructions()) {
            recipeDetailArea.append(" - " + instruction + "\n");
        }

        recipeDetailArea.append("Favorite: " + recipe.getIsFavorite() + "\n");
    }
    private void mnuBack_clk() {
    	this.dispose();
    }
    
    
}
