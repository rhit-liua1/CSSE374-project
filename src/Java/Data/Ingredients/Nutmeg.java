package Java.Data.Ingredients;

import Java.Data.Drinks.Drink;

public class Nutmeg extends IngredientDecorator {
    public Nutmeg() {
        this.name = "Nutmeg";
        this.description = "Spice additive";
        this.actualDescription = "Top with Nutmeg";
    }

    public Nutmeg(Drink drink) {
        this.name = "Nutmeg";
        this.description = "Spice additive";
        this.actualDescription = "Top with Nutmeg";
        this.drink = drink;
    }
}
