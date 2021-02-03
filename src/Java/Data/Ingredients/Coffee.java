package Java.Data.Ingredients;

import Java.Data.Drinks.Drink;

public class Coffee extends IngredientDecorator {
    public Coffee() {
        this.name = "Coffee";
        this.description = "Reg. Coffee beans, ground";
        this.actualDescription = "Add Coffee";
    }

    public Coffee(Drink drink) {
        this.name = "Coffee";
        this.description = "Reg. Coffee beans, ground";
        this.actualDescription = "Add Coffee";
        this.drink = drink;
    }

}
