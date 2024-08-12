package main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddRecipeGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nameField;
    private JTextArea descriptionArea;
    private JTextArea ingredientsArea;
    private JTextArea instructionsArea;
    
    private JScrollPane ingredientsScrollPane;
    private JScrollPane instructionsScrollPane;
    
    private JLabel lblName;
    private JLabel lblDescription;
    private JLabel lblIngredients;
    private JLabel lblInstructions;
    private JLabel lblMeal;
    
    private JButton btnSave;
    private JButton btnCancel;
    private JLabel lblAddNewRecipe;
    
    private JComboBox<Meal> mealComboBox;

    private RecipeManager recipeManager;

    public AddRecipeGUI(RecipeManager recipeManager) {
        this.recipeManager = recipeManager;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        lblName = new JLabel("Name:");
        lblName.setBounds(24, 34, 80, 25);
        contentPane.add(lblName);

        nameField = new JTextField();
        nameField.setBounds(114, 34, 300, 25);
        contentPane.add(nameField);

        lblDescription = new JLabel("Description:");
        lblDescription.setBounds(24, 71, 80, 25);
        contentPane.add(lblDescription);

        descriptionArea = new JTextArea();
        descriptionArea.setBounds(114, 71, 300, 50);
        contentPane.add(descriptionArea);

        lblIngredients = new JLabel("Ingredients:");
        lblIngredients.setVerticalAlignment(SwingConstants.TOP);
        lblIngredients.setBounds(24, 135, 80, 25);
        contentPane.add(lblIngredients);
        
        ingredientsArea = new JTextArea();
        ingredientsScrollPane = new JScrollPane(ingredientsArea);
        ingredientsScrollPane.setBounds(114, 133, 300, 64);
        contentPane.add(ingredientsScrollPane);
        
        
        lblInstructions = new JLabel("Instructions:");
        lblInstructions.setBounds(24, 237, 80, 25);
        contentPane.add(lblInstructions);

        instructionsArea = new JTextArea();
        instructionsScrollPane = new JScrollPane(instructionsArea);
                
        instructionsScrollPane.setBounds(114, 212, 300, 76);
        contentPane.add(instructionsScrollPane);

        lblMeal = new JLabel("Meal:");
        lblMeal.setBounds(24, 299, 80, 25);
        contentPane.add(lblMeal);

        mealComboBox = new JComboBox<>(Meal.values());
        mealComboBox.setBounds(114, 300, 300, 25);
        contentPane.add(mealComboBox);

        btnSave = new JButton("Save Recipe");
        btnSave.setBounds(264, 339, 150, 25);
        contentPane.add(btnSave);
        
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mnuBack_clk();
        	}
        });
        btnCancel.setBounds(24, 337, 117, 29);
        contentPane.add(btnCancel);
        
        lblAddNewRecipe = new JLabel("Add New Recipe");
        lblAddNewRecipe.setHorizontalAlignment(SwingConstants.CENTER);
        lblAddNewRecipe.setBounds(168, 9, 110, 25);
        contentPane.add(lblAddNewRecipe);

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	mnuSave_clk();
            }
        });
    }

    private void mnuSave_clk() {
        String name = nameField.getText();
        String description = descriptionArea.getText();
        ArrayList<String> ingredients = new ArrayList<>();
        for (String ingredient : ingredientsArea.getText().split("\n")) {
            ingredients.add(ingredient);
        }
        ArrayList<String> instructions = new ArrayList<>();
        for (String instruction : instructionsArea.getText().split("\n")) {
            instructions.add(instruction);
        }
        Meal meal = (Meal) mealComboBox.getSelectedItem();

        recipeManager.addRecipe(name, description, meal, ingredients, instructions, false);

        JOptionPane.showMessageDialog(this, "Recipe added successfully!");
        dispose();
    }
    
    private void mnuBack_clk() {
    	this.dispose();
    }
}
