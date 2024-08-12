package main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SearchGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField searchField;
    private JList<String> recipeList;
    private DefaultListModel<String> listModel;
    private RecipeManager recipeManager;
    private JButton btnBack;
    private JLabel searchLabel;
    private JScrollPane scrollPane;
    private List<Recipe> currentSearchResults;  // Store the search results
    private JLabel lblInstructions;
    private JPanel searchPanel;
    private JButton searchButton;

    public SearchGUI(RecipeManager recipeManager) {
        this.recipeManager = recipeManager;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Top panel for the search bar
        searchPanel = new JPanel();
        searchPanel.setBounds(5, 34, 439, 37);
        contentPane.add(searchPanel);
        searchPanel.setLayout(null);

        searchField = new JTextField();
        searchField.setBounds(6, 5, 309, 26);
        searchPanel.add(searchField);
        searchField.setColumns(20);

        searchButton = new JButton("Search");
        searchButton.setBounds(327, 5, 106, 29);
        searchPanel.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                displaySearchResults(searchText);
            }
        });

        // List for displaying recipe names
        listModel = new DefaultListModel<>();
        recipeList = new JList<>(listModel);
        recipeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(recipeList);
        scrollPane.setBounds(5, 91, 440, 176);
        contentPane.add(scrollPane);

        recipeList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    // Double-click detected
                    int index = recipeList.locationToIndex(evt.getPoint());
                    if (index >= 0) {
                        Recipe selectedRecipe = currentSearchResults.get(index);
                        openViewRecipeGUI(selectedRecipe);
                    }
                }
            }
        });

        searchLabel = new JLabel("Search Recipes by Name:");
        searchLabel.setBounds(140, 6, 180, 16);
        contentPane.add(searchLabel);

        btnBack = new JButton("Back");
        btnBack.setBounds(6, 3, 84, 29);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mnuBack_clk();
            }
        });
        contentPane.add(btnBack);
        
        lblInstructions = new JLabel("Double click on the recipe name to view the recipe.");
        lblInstructions.setBounds(15, 74, 340, 16);
        contentPane.add(lblInstructions);
    }

    private void displaySearchResults(String searchText) {
        listModel.clear();  // Clear previous results
        currentSearchResults = recipeManager.searchRecipesByName(searchText);

        if (currentSearchResults.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No recipes found matching: " + searchText);
        } else {
            for (Recipe recipe : currentSearchResults) {
                listModel.addElement(recipe.getName());
            }
        }
    }

    private void openViewRecipeGUI(Recipe recipe) {
        ViewRecipeGUI viewRecipeGUI = new ViewRecipeGUI(recipeManager, recipe);
        viewRecipeGUI.setVisible(true);
    }

    private void mnuBack_clk() {
        this.dispose();
    }
}
