package Java.Domain.Behaviors;

import Java.Data.Drinks.Drink;
import Java.Data.Drinks.DrinkFactory;

import java.util.Locale;

public class OrderDrink implements OrderDrinkBehavior{
    @Override
    public void produceDrink(String drinkName) {
        drinkName = drinkName.toLowerCase();
        Drink drink = DrinkFactory.getDrink(drinkName);
        System.out.println(drinkName);
        assert drink != null;
        System.out.println("[CoffeeMachineController] Drink: " + drink.getName() + " produced.");
        System.out.println("[CoffeeMachineController] Recipe for this drink: " + drink.getActualDescription() + " produced.");
    }
}
