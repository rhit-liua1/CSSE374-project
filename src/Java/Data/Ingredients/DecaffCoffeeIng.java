package Java.Data.Ingredients;

import Java.Data.Drinks.Drink;

public class DecaffCoffeeIng extends IngredientDecorator {
    public DecaffCoffeeIng() {
        this.name = "Decaff Coffee";
        this.description = "Reg. Decaffeinated coffee beans, ground";
        this.actualDescription = "Add Decaff Coffee";
    }

    public DecaffCoffeeIng(Drink drink) {
        this.name = "Decaff Coffee";
        this.description = "Reg. Decaffeinated coffee beans, ground";
        this.actualDescription = "Add Decaff Coffee";
        this.drink = drink;
    }

}
