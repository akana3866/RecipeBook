package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BrowseGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JList<String> recipeList;
    private JTextArea recipeDetailArea;
    private JComboBox<String> sortComboBox;
    private RecipeManager recipeManager;
    private DefaultListModel<String> listModel;

    private JPanel sortPanel;
    private JButton sortButton;
    private JSplitPane splitPane;
    private JScrollPane listScrollPane;
    private JScrollPane detailScrollPane;
    private JButton btnBack;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    
    public BrowseGUI(RecipeManager recipeManager) {
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
        String[] sortOptions = { "All Recipes", "Breakfast", "Lunch", "Dinner", "Snack", "Favorites" };
        sortComboBox = new JComboBox<>(sortOptions);
        sortPanel.add(sortComboBox);

        sortButton = new JButton("Sort");
        sortPanel.add(sortButton);
        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) sortComboBox.getSelectedItem();
                sortAndDisplayRecipes(selectedOption);
            }
        });

        // Center panel with a split layout for list and details
        splitPane = new JSplitPane();
        splitPane.setBounds(5, 89, 590, 278);
        contentPane.add(splitPane);

        // List of recipes
        listModel = new DefaultListModel<>();
        recipeList = new JList<>(listModel);
        recipeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        recipeList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = recipeList.locationToIndex(e.getPoint());
                if (index >= 0) {
                    Recipe selectedRecipe = recipeManager.getRecipes().get(index);
                    if (e.getClickCount() == 1) {
                        // Single-click detected, display the recipe details
                        displayRecipeDetails(selectedRecipe);
                    } else if (e.getClickCount() == 2) {
                        // Double-click detected, open the ViewRecipeGUI
                        openViewRecipeGUI(selectedRecipe);
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
        
        lblNewLabel = new JLabel("Click on the recipe name in the list on the left to view the recipe.");
        lblNewLabel.setBounds(16, 47, 560, 16);
        contentPane.add(lblNewLabel);
        
        lblNewLabel_1 = new JLabel("Double click on the recipe name to view the complete recipe in a new window.");
        lblNewLabel_1.setBounds(17, 65, 501, 16);
        contentPane.add(lblNewLabel_1);

        // Load and display all recipes initially
        sortAndDisplayRecipes("All Recipes");
    }

    private void sortAndDisplayRecipes(String criteria) {
        List<Recipe> recipes = recipeManager.sortRecipes(criteria);
        displayRecipeNames(recipes);
    }

    private void displayRecipeNames(List<Recipe> recipes) {
        listModel.clear();  // Clear the current list
        for (Recipe recipe : recipes) {
            listModel.addElement(recipe.getName());  // Add the recipe name to the list
        }
    }
    
    private void displayRecipeDetails(Recipe recipe) {
        String details = recipeManager.displayRecipeDetails(recipe);
        recipeDetailArea.setText(details);  // Display the recipe details in the text area
    }

    private void openViewRecipeGUI(Recipe recipe) {
        ViewRecipeGUI viewRecipeGUI = new ViewRecipeGUI(recipeManager, recipe);
        viewRecipeGUI.setVisible(true);
    }

    private void mnuBack_clk() {
        this.dispose();
    }
}
