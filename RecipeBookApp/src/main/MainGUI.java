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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Welcome title
        JLabel titleLabel = new JLabel("Welcome to Your Recipe Book!");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set Arial font and larger size
        contentPane.add(titleLabel, BorderLayout.NORTH);

        // Center panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Stack buttons vertically
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        // Add spacing between title and buttons
        buttonPanel.add(Box.createVerticalStrut(20));

        // Search Recipe button
        JButton searchButton = new JButton("Search for a Recipe");
        searchButton.setAlignmentX(CENTER_ALIGNMENT);
        buttonPanel.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement search functionality here
            }
        });

        // Add spacing between buttons
        buttonPanel.add(Box.createVerticalStrut(10));

        // Browse Recipes button
        JButton browseButton = new JButton("Browse All Recipes");
        browseButton.setAlignmentX(CENTER_ALIGNMENT);
        buttonPanel.add(browseButton);
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement browse functionality here
            }
        });

        // Add spacing between buttons
        buttonPanel.add(Box.createVerticalStrut(10));

        // Add New Recipe button
        JButton addButton = new JButton("Add a New Recipe");
        addButton.setAlignmentX(CENTER_ALIGNMENT);
        buttonPanel.add(addButton);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement add functionality here
            }
        });
    }
}
