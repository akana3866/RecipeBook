package main;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private RecipeManager recipeManager;

    JLabel titleLabel;
    JPanel buttonPanel;
    JButton browseButton;
    JButton addButton;
    JButton searchButton;
    
    public MainGUI() {
        recipeManager = new RecipeManager();  // Initialize RecipeManager

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        titleLabel = new JLabel("Welcome to Your Recipe Book!");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        contentPane.add(titleLabel, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        buttonPanel.add(Box.createVerticalStrut(20));

        searchButton = new JButton("Search for a Recipe");
        searchButton.setAlignmentX(CENTER_ALIGNMENT);
        buttonPanel.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSearchGUI();
            }
        });

        buttonPanel.add(Box.createVerticalStrut(10));

        browseButton = new JButton("Browse All Recipes");
        browseButton.setAlignmentX(CENTER_ALIGNMENT);
        buttonPanel.add(browseButton);
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openBrowseGUI();
            }
        });

        buttonPanel.add(Box.createVerticalStrut(10));

        addButton = new JButton("Add a New Recipe");
        addButton.setAlignmentX(CENTER_ALIGNMENT);
        buttonPanel.add(addButton);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddRecipeGUI();
            }
        });
    }

    // Method to open SearchGUI
    private void openSearchGUI() {
        SearchGUI searchGUI = new SearchGUI(recipeManager);
        searchGUI.setVisible(true);
    }

    // Method to open BrowseGUI
    private void openBrowseGUI() {
        BrowseGUI browseGUI = new BrowseGUI(recipeManager);
        browseGUI.setVisible(true);
    }

    // Method to open AddRecipeGUI
    private void openAddRecipeGUI() {
        AddRecipeGUI addRecipeGUI = new AddRecipeGUI(recipeManager);
        addRecipeGUI.setVisible(true);
    }

    public static void main(String[] args) {
        MainGUI frame = new MainGUI();
        frame.setVisible(true);
    }
}
