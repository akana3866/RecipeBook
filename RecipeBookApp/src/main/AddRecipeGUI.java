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

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(10, 10, 80, 25);
        contentPane.add(lblName);

        nameField = new JTextField();
        nameField.setBounds(100, 10, 300, 25);
        contentPane.add(nameField);

        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setBounds(10, 45, 80, 25);
        contentPane.add(lblDescription);

        descriptionArea = new JTextArea();
        descriptionArea.setBounds(100, 45, 300, 50);
        contentPane.add(descriptionArea);

        JLabel lblIngredients = new JLabel("Ingredients:");
        lblIngredients.setBounds(10, 110, 80, 25);
        contentPane.add(lblIngredients);

        ingredientsArea = new JTextArea();
        ingredientsArea.setBounds(100, 110, 300, 50);
        contentPane.add(ingredientsArea);

        JLabel lblInstructions = new JLabel("Instructions:");
        lblInstructions.setBounds(10, 175, 80, 25);
        contentPane.add(lblInstructions);

        instructionsArea = new JTextArea();
        instructionsArea.setBounds(100, 175, 300, 50);
        contentPane.add(instructionsArea);

        JLabel lblMeal = new JLabel("Meal:");
        lblMeal.setBounds(10, 240, 80, 25);
        contentPane.add(lblMeal);

        mealComboBox = new JComboBox<>(Meal.values());
        mealComboBox.setBounds(100, 240, 300, 25);
        contentPane.add(mealComboBox);

        JButton btnSave = new JButton("Save Recipe");
        btnSave.setBounds(150, 300, 150, 25);
        contentPane.add(btnSave);

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveRecipe();
            }
        });
    }

    private void saveRecipe() {
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

        Recipe recipe = new Recipe(0, name, description, meal, ingredients, instructions, false);
        recipeManager.addRecipe(recipe);  // Using the addRecipe method

        JOptionPane.showMessageDialog(this, "Recipe added successfully!");
        dispose();
    }
}
