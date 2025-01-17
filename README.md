# RecipeBook
OOP Recipe Book System

### Quick Disclaimers
We used VSCode to work on, implement, and test this project. For all group members of the project right before submission, we used VSCode to check if our implementation was working and it was working across the board. We have not checked on other IDEs, so we are unsure if the implementation or code works there. But it should. Should any problems arise in pathing we have referenced potential fixes below:

*Right-click on the src folder in your IDE's file explorer.
*Select "Mark Directory as" (or "Build Path" > "Use as Source Folder").
*Ensure that src/main is marked as a source folder. This tells the IDE to treat this directory as containing Java source files.

OR

Right-click on the RecipeBookApp project and select "Properties."
Go to "Java Build Path" (or "Build Path" > "Configure Build Path").
Under the "Source" tab, ensure that src/main (or src/main/java if you have a java subfolder) is listed as a source folder.
If it’s missing, click "Add Folder" and navigate to select the src/main folder.

### Overview
The project that we as a group wanted to implement is a recipe book, designed to help users manage and organize their favorite recipes. The application will allow users to add new recipes, browse through their collection, search for specific recipes, edit recipes, and favorite recipes, export them to a .txt file, and delete recipes they no longer need. The application will have a user-friendly Java GUI and store recipes in a text file for easy retrieval and management.

### Key Features
The key features/use cases that we are thinking about implementing in this project are: adding a new recipe, viewing a recipe, editing a recipe, and searching for recipes

* **Adding a new recipe:** Users can add a new recipe by entering the recipe name, recipe type, ingredients, and instructions. The recipe is saved to a new text file.
* **Browsing recipes:** Users can view all the recipes they have added. The application displays a list of recipe names that the user can browse through and then click on to open the recipe
* **Searching recipe:** Users can search for a recipe by entering a recipe name. The application searches the text file for recipes that match the recipe name and displays the results.
* **Edit recipe:** Users can edit a recipe from their collection after searching for it.
* **Delete Recipe:** Users can delete a recipe from their collection. The selected recipe is removed from the text file.
* **Sort by type:** This is similar to search and browse except this sorts the recipes by type breakfast, lunch, and dinner.
* **Mark recipe as favorite:** The user can mark their recipes as favorites and view their favorite recipes.
* **Export recipe to a text file:** The user will be able to export their recipe to a text file that will be stored on their machine.

