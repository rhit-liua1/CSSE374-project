package Java.Testcases;

import Java.Data.Address;
import Java.Data.Condiment;
import Java.Data.Drinks.*;
import Java.Data.Ingredients.*;
import Java.Domain.AdvancedCoffeeMachineController;
import Java.Domain.CoffeeMachineController;
import Java.Domain.OrderManager;
import Java.Domain.SimpleCoffeeMachineController;
import org.junit.Test;

import java.util.ArrayList;

public class DrinkDecoratorTest {

    @Test
    public void testSimpleDrinks(){
        System.out.println();
        System.out.println();

        System.out.println("Test Drink Americano: ");
        Drink d1 = DrinkFactory.getDrink("americano");
        System.out.println("Name: " + d1.getName());
        System.out.println("Description: " + d1.getDescription());

        System.out.println();

        System.out.println("Test Drink Colombia Dark: ");
        Drink d2 = DrinkFactory.getDrink("colombia dark");
        System.out.println("Name: " + d2.getName());
        System.out.println("Description: " + d2.getDescription());

        System.out.println();
    }

    @Test
    public void testMoreDrinks(){
        System.out.println();
        System.out.println();

        System.out.println("Test Drink Latte: ");
        Drink d3 = DrinkFactory.getDrink("latte");
        System.out.println("Name: " + d3.getName());
        System.out.println("Description: " + d3.getDescription());
        System.out.println("Recipe Steps: " + d3.getActualDescription());

        System.out.println();

        System.out.println("Test Drink Pumpkin Spice: ");
        Drink d4 = DrinkFactory.getDrink("pumpkin spice");
        System.out.println("Name: " + d4.getName());
        System.out.println("Description: " + d4.getDescription());
        System.out.println("Recipe Needed: " + d4.getRecipeNeeded());
        System.out.println("Recipe Steps: " + d4.getActualDescription());

        System.out.println();
    }


    @Test
    public void testDrinkWithExtraRecipeSteps(){
        System.out.println();
        System.out.println();

        System.out.println("Test Drink Americano with extra ingredients: ");
        Drink d5 = DrinkFactory.getDrink("americano");
        d5 = new Hazelnut(d5);
        d5 = new Milk(d5);
        d5 = new Mix(d5);
        System.out.println("Name: " + d5.getName());
        System.out.println("Description: " + d5.getDescription());
        System.out.println("Recipe Steps: " + d5.getActualDescription());

        System.out.println();

        System.out.println("Test Drink Pumpkin Spice with extra ingredients: ");
        Drink d6 = DrinkFactory.getDrink("pumpkin spice");
        d6 = new Coffee(d6);
        d6 = new Nutmeg(d6);
        d6 = new SoyMilk(d6);
        System.out.println("Name: " + d6.getName());
        System.out.println("Description: " + d6.getDescription());
        System.out.println("Recipe Steps: " + d6.getActualDescription());

        System.out.println();
    }
}
