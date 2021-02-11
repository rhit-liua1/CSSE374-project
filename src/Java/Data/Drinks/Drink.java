package Java.Data.Drinks;

import Java.Data.Ingredients.*;

public abstract class Drink {
    String name;
    String description;
    String recipeNeeded;
    String actualDescription;

    public Drink(String name, String description, String recipeNeeded, String actualDescription) {
        this.name = name;
        this.description = description;
        this.recipeNeeded = recipeNeeded;
        this.actualDescription = actualDescription;
    }

    public Drink() {
        this.name = "Drink Doesn't exist";
        this.description = "";
        this.recipeNeeded = "";
        this.actualDescription = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getActualDescription() {
        return "Add " + actualDescription;
    }

    public String getRecipeNeeded() {
        return recipeNeeded;
    }
}
