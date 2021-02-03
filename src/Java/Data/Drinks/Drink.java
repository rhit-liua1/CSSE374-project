package Java.Data.Drinks;

import Java.Data.Ingredients.*;

public abstract class Drink {
    String name;
    String description;
    String recipeNeeded;
    String actualDescription;

    public Drink(String name, String description, String recipeNeeded, String actualDescription) {
        this.name = name;
        this.description = description;
        this.recipeNeeded = recipeNeeded;
        this.actualDescription = actualDescription;
    }

    public Drink() {
        this.name = "Drink Doesn't exist";
        this.description = "";
        this.recipeNeeded = "";
        this.actualDescription = "";
    }

    public static Drink getDrink(String drinkType) {
        switch (drinkType) {
            case "americano":
                //System.out.println();
                Drink am = new Americano();
                System.out.println(am.getActualDescription());
                return new Americano();
            case "colombia dark":
                return new ColombiaDark();
            case "decaff":
                return new Decaff();
            case "espresso":
                return new Espresso();
            case "latte":
                //Add steps
                Drink drink = new Espresso();
                drink = new Milk(drink);
                drink = new WhippedCream(drink);
                System.out.println(drink.getActualDescription());
                return drink;
            case "pumpkin spice":
                //Add steps
                Drink drink2 = new Coffee();
                drink2 = new PumpkinSpiceIng(drink2);
                drink2 = new WhippedCream(drink2);
                drink2 = new Mix(drink2);
                drink2 = new Nutmeg(drink2);
                System.out.println(drink2.getActualDescription());
                return new PumpkinSpice();
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getActualDescription() {
        return actualDescription;
    }

    public String getRecipeNeeded() {
        return recipeNeeded;
    }
}
