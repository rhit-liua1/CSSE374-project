package Java.Data.Ingredients;

import Java.Data.Drinks.Drink;

public class WhippedCream extends IngredientDecorator {
    public WhippedCream() {
        this.name = "Whipped Cream";
        this.description = "Dairy-based heavy whipped cream";
        this.actualDescription = "Top with whipped cream";
    }

    public WhippedCream (Drink drink) {
        this.name = "Whipped Cream";
        this.description = "Dairy-based heavy whipped cream";
        this.actualDescription = "Top with whipped cream";
        this.drink = drink;
    }
}
