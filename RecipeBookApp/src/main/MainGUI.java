package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private RecipeManager recipeManager;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainGUI frame = new MainGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainGUI() {
        recipeManager = new RecipeManager();  // Initialize RecipeManager

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel titleLabel = new JLabel("Welcome to Your Recipe Book!");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        contentPane.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        buttonPanel.add(Box.createVerticalStrut(20));

        JButton searchButton = new JButton("Search for a Recipe");
        searchButton.setAlignmentX(CENTER_ALIGNMENT);
        buttonPanel.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SearchGUI searchGUI = new SearchGUI();
                searchGUI.setVisible(true);
            }
        });

        buttonPanel.add(Box.createVerticalStrut(10));

        JButton browseButton = new JButton("Browse All Recipes");
        browseButton.setAlignmentX(CENTER_ALIGNMENT);
        buttonPanel.add(browseButton);
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BrowseGUI browseGUI = new BrowseGUI(recipeManager);
                browseGUI.setVisible(true);
            }
        });

        buttonPanel.add(Box.createVerticalStrut(10));

        JButton addButton = new JButton("Add a New Recipe");
        addButton.setAlignmentX(CENTER_ALIGNMENT);
        buttonPanel.add(addButton);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddRecipeGUI addRecipeGUI = new AddRecipeGUI(recipeManager);
                addRecipeGUI.setVisible(true);
            }
        });
    }
}
