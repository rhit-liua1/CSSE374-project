package Java.Testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import Java.Data.Address;
import Java.Data.Condiment;
import Java.Domain.AdvancedCoffeeMachineController;
import Java.Domain.CoffeeMachineController;
import Java.Domain.OrderManager;
import Java.Domain.SimpleCoffeeMachineController;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.ArrayList;

public class CoffeeMachineStrategyBehaviorTest {
    @Test
    public void testSimpleController(){
        System.out.println();
        System.out.println();
        System.out.println("Initializing some fake simple coffee machine controllers...");
        Address addr1 = new Address("200 N. Main", 47803);
        Address addr2 = new Address("3 S. Walnut", 60601);
        ArrayList<Condiment> condiments = new ArrayList<>();
        condiments.add(new Condiment("cream", 1));
        condiments.add(new Condiment("sugar", 2));

        System.out.println();
        System.out.println("Testing with SimpleController 1: ");
        CoffeeMachineController s1 = new SimpleCoffeeMachineController(0, 0, addr1, new OrderManager());
        System.out.println(s1.toString());
        s1.produceDrink("Latte");
        s1.addCondiments(condiments);

        System.out.println();
        System.out.println("Testing with SimpleController 2: ");
        CoffeeMachineController s2 = new SimpleCoffeeMachineController(1, 1, addr2, new OrderManager());
        System.out.println(s2.toString());
        s2.produceDrink("Americano");
        s2.addCondiments(condiments);
    }

    @Test
    public void testAdvancedController(){
        System.out.println();
        System.out.println();
        System.out.println("Initializing some fake advanced coffee machine controllers...");
        Address addr3 = new Address("18 Cana Ct.", 47804);
        Address addr4 = new Address("5500 Wabash Ave", 47803);
        ArrayList<Condiment> condiments = new ArrayList<>();
        condiments.add(new Condiment("nutrasweet", 3));
        condiments.add(new Condiment("cream", 1));

        System.out.println();
        System.out.println("Testing with AdvancedController 1: ");
        //TODO:USE FACTORY
        CoffeeMachineController a1 = new AdvancedCoffeeMachineController(2, 0, addr3, new OrderManager());
        System.out.println(a1.toString());
        a1.produceDrink("Pumpkin Spice");
        a1.addCondiments(condiments);

        System.out.println();
        System.out.println("Testing with AdvancedController 1: ");
        //TODO:USE FACTORY
        CoffeeMachineController a2 = new AdvancedCoffeeMachineController(3, 1, addr4, new OrderManager());
        System.out.println(a2.toString());
        a2.produceDrink("Espresso");
        a2.addCondiments(condiments);
    }
}
