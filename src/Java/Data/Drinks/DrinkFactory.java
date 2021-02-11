package Java.Data.Drinks;

import Java.Data.Ingredients.*;

public class DrinkFactory {
    public static Drink getDrink(String drinkType) {
        Drink drink;
        switch (drinkType) {
            case "americano":
                return new Americano();
            case "colombia dark":
                return new ColombiaDark();
            case "decaff":
                return new Decaff();
            case "espresso":
                return new Espresso();
            case "latte":
                //Add steps
                drink = new Espresso();
                drink = new Milk(drink);
                drink = new WhippedCream(drink);
                drink.setName("Latte");
                return drink;
            case "pumpkin spice":
                //Add steps
                drink = new Coffee();
                drink = new PumpkinSpiceIng(drink);
                drink = new WhippedCream(drink);
                drink = new Mix(drink);
                drink = new Nutmeg(drink);
                drink.setName("Pumpkin Spice");
                return drink;
        }
        return null;
    }
}
