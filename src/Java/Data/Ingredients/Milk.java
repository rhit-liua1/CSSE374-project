package Java.Data.Ingredients;

import Java.Data.Drinks.Drink;

public class Milk extends IngredientDecorator {
    public Milk() {
        this.name = "Milk";
        this.description = "Regular dairy milk";
        this.actualDescription = "Steam Milk";
    }

    public Milk(Drink drink) {
        this.drink = drink;
        this.name = "Milk";
        this.description = "Regular dairy milk";
        this.actualDescription = "Steam Milk";
    }
}
