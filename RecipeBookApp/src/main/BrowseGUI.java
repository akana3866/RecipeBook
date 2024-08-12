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

public class BrowseGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JList<String> recipeList;
    private JTextArea recipeDetailArea;
    private JComboBox<String> sortComboBox;
    private RecipeManager recipeManager;
    private DefaultListModel<String> listModel;
    private List<Recipe> currentDisplayedRecipes;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Initialize RecipeManager and load recipes
                    RecipeManager recipeManager = new RecipeManager();

                    BrowseGUI frame = new BrowseGUI(recipeManager);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public BrowseGUI(RecipeManager recipeManager) {
        this.recipeManager = recipeManager;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Top panel for the sorting options
        JPanel sortPanel = new JPanel();
        contentPane.add(sortPanel, BorderLayout.NORTH);

        // Combo box for sorting options
        String[] sortOptions = { "All Recipes", "Breakfast", "Lunch", "Dinner", "Favorites" };
        sortComboBox = new JComboBox<>(sortOptions);
        sortPanel.add(sortComboBox);

        JButton sortButton = new JButton("Sort");
        sortPanel.add(sortButton);
        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) sortComboBox.getSelectedItem();
                sortRecipes(selectedOption);
            }
        });

        // Center panel with a split layout for list and details
        JSplitPane splitPane = new JSplitPane();
        contentPane.add(splitPane, BorderLayout.CENTER);

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
        JScrollPane listScrollPane = new JScrollPane(recipeList);
        splitPane.setLeftComponent(listScrollPane);

        // Text area to display the selected recipe details
        recipeDetailArea = new JTextArea();
        recipeDetailArea.setEditable(false);
        JScrollPane detailScrollPane = new JScrollPane(recipeDetailArea);
        splitPane.setRightComponent(detailScrollPane);

        // Load and display all recipes initially
        sortRecipes("All Recipes");
    }

    private void sortRecipes(String criteria) {
        List<Recipe> recipesToDisplay = new ArrayList<>();

        switch (criteria) {
            case "Breakfast":
                for (Recipe recipe : recipeManager.getRecipes()) {
                    if (recipe.getMeal() == Meal.BREAKFAST) {
                        recipesToDisplay.add(recipe);
                    }
                }
                break;
            case "Lunch":
                for (Recipe recipe : recipeManager.getRecipes()) {
                    if (recipe.getMeal() == Meal.LUNCH) {
                        recipesToDisplay.add(recipe);
                    }
                }
                break;
            case "Dinner":
                for (Recipe recipe : recipeManager.getRecipes()) {
                    if (recipe.getMeal() == Meal.DINNER) {
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
    
}
