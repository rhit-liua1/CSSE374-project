package Java.Data.Ingredients;

import Java.Data.Drinks.Drink;

public class PumpkinSpiceIng extends IngredientDecorator {
    public PumpkinSpiceIng() {
        this.name = "Pumpkin Spice";
        this.description = "Spice additive";
        this.actualDescription = "Add Pumpkin Spice";
    }

    public PumpkinSpiceIng(Drink drink) {
        this.name = "Pumpkin Spice";
        this.description = "Spice additive";
        this.actualDescription = "Add Pumpkin Spice";
        this.drink = drink;
    }
}
