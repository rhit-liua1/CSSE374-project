package Java.Data.Ingredients;

import Java.Data.Drinks.Drink;

public class Mix extends IngredientDecorator {
    public Mix() {
        this.name = "Mix";
        this.description = "";
        this.actualDescription = "Mix";
    }

    public Mix(Drink drink) {
        this.name = "Mix";
        this.description = "";
        this.actualDescription = "Mix";
        this.drink = drink;
    }
}
