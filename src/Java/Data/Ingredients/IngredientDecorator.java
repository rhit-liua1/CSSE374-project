package Java.Data.Ingredients;

import Java.Data.Drinks.Drink;

public abstract class IngredientDecorator extends Drink {
    Drink drink;
    String name;
    String description;
    String actualDescription;

    public IngredientDecorator(String name, String description, String actualDescription) {
        this.name = name;
        this.description = description;
        this.actualDescription = actualDescription;
    }

    public IngredientDecorator() {
        this.name = "Ingredient Doesn't exist";
        this.description = "";
        this.actualDescription = "";
    }

    @Override
    public String getDescription() {
        return drink.getDescription();
    }

    public Drink getDrink() {
        return drink;
    }

    public String getName() {
        return name;
    }

    public String getActualDescription() {
        return drink == null? this.actualDescription : drink.getActualDescription() + ", " + this.actualDescription;
    }
}
