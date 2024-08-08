package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BrowseGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea recipeListArea;
    private JComboBox<String> sortComboBox;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BrowseGUI frame = new BrowseGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public BrowseGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
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

        // Center panel for the recipe list
        recipeListArea = new JTextArea();
        recipeListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(recipeListArea);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

    private void sortRecipes(String criteria) {
        // Placeholder for sorting functionality
        // This is where you'd sort and display recipes based on the selected criteria
        recipeListArea.setText("Sorted by: " + criteria + "\n\n");
        // Example of displaying sorted recipes:
        // if (criteria.equals("Breakfast")) {
        //     recipeListArea.append("Breakfast Recipe 1\n");
        //     recipeListArea.append("Breakfast Recipe 2\n");
        // } else if (criteria.equals("Favorites")) {
        //     recipeListArea.append("Favorite Recipe 1\n");
        //     recipeListArea.append("Favorite Recipe 2\n");
        // }
    }
}
