package Java.Data.Ingredients;

import Java.Data.Drinks.Drink;

public class Sugar extends IngredientDecorator {
    public Sugar() {
        this.name = "Sugar";
        this.description = "Regular sugar cane sugar";
        this.actualDescription = "Add Sugar";
    }

    public Sugar (Drink drink) {
        this.name = "Sugar";
        this.description = "Regular sugar cane sugar";
        this.actualDescription = "Add Sugar";
        this.drink = drink;
    }
}
