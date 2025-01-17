package main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class EditGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nameField;
    private JTextArea descriptionArea;
    private JTextArea ingredientsArea;
    private JTextArea instructionsArea;
    private JComboBox<Meal> mealComboBox;
    private JLabel lblMeal;
    private JButton btnSave;
    private JLabel lblInstructions;
    private JLabel lblIngredients;
    private JLabel lblDescription;
    private JLabel lblName;
    private JCheckBox chckbxFavorite;

    private RecipeManager recipeManager;
    private UUID recipeID;

    public EditGUI(RecipeManager recipeManager, UUID recipeID) {
        this.recipeManager = recipeManager;
        this.recipeID = recipeID;

        Recipe recipe = recipeManager.getRecipe(recipeID);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        lblName = new JLabel("Name:");
        lblName.setBounds(10, 10, 80, 25);
        contentPane.add(lblName);

        nameField = new JTextField(recipe.getName());
        nameField.setBounds(100, 10, 300, 25);
        contentPane.add(nameField);

        lblDescription = new JLabel("Description:");
        lblDescription.setBounds(10, 45, 80, 25);
        contentPane.add(lblDescription);

        descriptionArea = new JTextArea(recipe.getDescription());
        descriptionArea.setBounds(100, 45, 300, 50);
        contentPane.add(descriptionArea);

        lblIngredients = new JLabel("Ingredients:");
        lblIngredients.setBounds(10, 110, 80, 25);
        contentPane.add(lblIngredients);

        ingredientsArea = new JTextArea(String.join("\n", recipe.getIngredients()));
        ingredientsArea.setBounds(100, 110, 300, 50);
        contentPane.add(ingredientsArea);

        lblInstructions = new JLabel("Instructions:");
        lblInstructions.setBounds(10, 175, 80, 25);
        contentPane.add(lblInstructions);

        instructionsArea = new JTextArea(String.join("\n", recipe.getInstructions()));
        instructionsArea.setBounds(100, 175, 300, 50);
        contentPane.add(instructionsArea);

        lblMeal = new JLabel("Meal:");
        lblMeal.setBounds(10, 240, 80, 25);
        contentPane.add(lblMeal);

        mealComboBox = new JComboBox<>(Meal.values());
        mealComboBox.setSelectedItem(recipe.getMeal());
        mealComboBox.setBounds(100, 240, 300, 25);
        contentPane.add(mealComboBox);

        chckbxFavorite = new JCheckBox("Favorite Recipe");
        chckbxFavorite.setBounds(100, 277, 128, 23);
        chckbxFavorite.setSelected(recipe.getIsFavorite());
        contentPane.add(chckbxFavorite);

        btnSave = new JButton("Save Changes");
        btnSave.setBounds(150, 315, 150, 25);
        contentPane.add(btnSave);

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveRecipeChanges();
            }
        });
    }

    private void saveRecipeChanges() {
        String newName = nameField.getText();
        String newDescription = descriptionArea.getText();

        ArrayList<String> newIngredients = new ArrayList<>(Arrays.asList(ingredientsArea.getText().split("\n")));
        ArrayList<String> newInstructions = new ArrayList<>(Arrays.asList(instructionsArea.getText().split("\n")));
        Meal newMeal = (Meal) mealComboBox.getSelectedItem();
        boolean newIsFavorite = chckbxFavorite.isSelected();

        // Use RecipeManager to update the recipe, including the favorite status
        recipeManager.editRecipe(recipeID, newName, newDescription, newMeal, newIngredients, newInstructions, newIsFavorite);

        JOptionPane.showMessageDialog(this, "Recipe updated successfully!");

        dispose();
    }
}
