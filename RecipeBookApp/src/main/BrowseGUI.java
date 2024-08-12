package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.UUID;
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
    private List<Recipe> currentBrowseResults;  // Store the browse results

    private JPanel sortPanel;
    private JButton filterButton;
    private JSplitPane splitPane;
    private JScrollPane listScrollPane;
    private JScrollPane detailScrollPane;
    private JButton btnBack;
    private JLabel lblClickInstr;
    private JLabel lblDblClickInstr;
    private JLabel lblFilterInstr;
    
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
        String[] sortOptions = { "All Recipes", "Breakfast", "Lunch", "Dinner", "Snack", " Dessert", "Favorites" };
        sortComboBox = new JComboBox<>(sortOptions);
        sortPanel.add(sortComboBox);

        filterButton = new JButton("Filter");
        sortPanel.add(filterButton);
        filterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) sortComboBox.getSelectedItem();
                sortAndDisplayRecipes(selectedOption);
            }
        });

        // Center panel with a split layout for list and details
        splitPane = new JSplitPane();
        splitPane.setBounds(5, 112, 590, 255);
        contentPane.add(splitPane);

        // List of recipes
        listModel = new DefaultListModel<>();
        recipeList = new JList<>(listModel);
        recipeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        recipeList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = recipeList.locationToIndex(e.getPoint());
                if (index >= 0 && index <= currentBrowseResults.size()) {
                    Recipe selectedRecipe = currentBrowseResults.get(index);
                    if (e.getClickCount() == 1) {
                        // Single-click detected, display the recipe details
                        displayRecipeDetails(selectedRecipe.getRecipeID());
                    } else if (e.getClickCount() == 2) {
                        // Double-click detected, open the ViewRecipeGUI
                        openViewRecipeGUI(selectedRecipe.getRecipeID());
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
        
        lblClickInstr = new JLabel("Click on the recipe name in the list on the bottom left pane to view the recipe.");
        lblClickInstr.setHorizontalAlignment(SwingConstants.CENTER);
        lblClickInstr.setBounds(39, 75, 522, 16);
        contentPane.add(lblClickInstr);
        
        lblDblClickInstr = new JLabel("Double click on the recipe name to view the complete recipe in a new window.");
        lblDblClickInstr.setHorizontalAlignment(SwingConstants.CENTER);
        lblDblClickInstr.setBounds(39, 96, 522, 16);
        contentPane.add(lblDblClickInstr);
        
        lblFilterInstr = new JLabel("To filter your recipes, select a filter parameter above and click Filter.");
        lblFilterInstr.setHorizontalAlignment(SwingConstants.CENTER);
        lblFilterInstr.setBounds(39, 47, 522, 16);
        contentPane.add(lblFilterInstr);

        // Load and display all recipes initially
        sortAndDisplayRecipes("All Recipes");
    }

    private void sortAndDisplayRecipes(String criteria) {
        currentBrowseResults = recipeManager.sortRecipes(criteria);
        listModel.clear();  // Clear the current list
        for (Recipe recipe : currentBrowseResults) {
            listModel.addElement(recipe.getName());  // Add the recipe name to the list
        }
    }

    private void displayRecipeDetails(UUID recipeID) {
        String details = recipeManager.displayRecipeDetails(recipeID);
        recipeDetailArea.setText(details);  // Display the recipe details in the text area
    }

    private void openViewRecipeGUI(UUID recipeID) {
        ViewRecipeGUI viewRecipeGUI = new ViewRecipeGUI(recipeManager, recipeID);
        viewRecipeGUI.setVisible(true);
    }

    private void mnuBack_clk() {
        this.dispose();
    }
}
