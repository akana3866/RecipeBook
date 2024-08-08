package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField searchField;
    private JTextArea recipeListArea;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SearchGUI frame = new SearchGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SearchGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Top panel for the search bar
        JPanel searchPanel = new JPanel();
        contentPane.add(searchPanel, BorderLayout.NORTH);

        JLabel searchLabel = new JLabel("Search Recipes:");
        searchPanel.add(searchLabel);

        searchField = new JTextField();
        searchPanel.add(searchField);
        searchField.setColumns(20);

        JButton searchButton = new JButton("Search");
        searchPanel.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement search functionality here
                String searchText = searchField.getText();
                searchRecipes(searchText);
            }
        });

        // Center panel for the recipe list
        recipeListArea = new JTextArea();
        recipeListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(recipeListArea);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

    private void searchRecipes(String searchText) {
        // Placeholder for the search functionality
        // This is where you'd search through your recipes and update the recipeListArea
        recipeListArea.setText("Search results for: " + searchText + "\n\n");
        // Add found recipes to recipeListArea
        // Example:
        // recipeListArea.append("Recipe 1\n");
        // recipeListArea.append("Recipe 2\n");
    }
}
