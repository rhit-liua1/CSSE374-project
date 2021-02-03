package Java.Data.Ingredients;

import Java.Data.Drinks.Drink;

public class Hazelnut extends IngredientDecorator {

    public Hazelnut() {
        this.name = "Hazelnut";
        this.description = "Syrup";
        this.actualDescription = "Add Hazelnut";
    }

    public Hazelnut(Drink drink) {
        this.drink = drink;
        this.name = "Hazelnut";
        this.description = "Syrup";
        this.actualDescription = "Add Hazelnut";
    }
}
