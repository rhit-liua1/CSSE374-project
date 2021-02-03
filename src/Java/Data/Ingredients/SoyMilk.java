package Java.Data.Ingredients;

import Java.Data.Drinks.Drink;

public class SoyMilk extends IngredientDecorator {
    public SoyMilk() {
        this.name = "Soy Milk";
        this.description = "Milk-like but from soybeans";
        this.actualDescription = "Add Soymilk";
    }

    public SoyMilk(Drink drink) {
        this.name = "Soy Milk";
        this.description = "Milk-like but from soybeans";
        this.actualDescription = "Add Soymilk";
        this.drink = drink;
    }
}
