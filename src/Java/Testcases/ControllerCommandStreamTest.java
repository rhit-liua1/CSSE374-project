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
import Java.Factories.CoffeeControllerFactory;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.ArrayList;


public class ControllerCommandStreamTest {

    @Test
    public void testSimpleController(){
        System.out.println();
        System.out.println();
        System.out.println("Initializing some fake simple coffee machine controllers...");

        CoffeeControllerFactory ccf = new CoffeeControllerFactory();
        Address addr1 = new Address("200 N. Main", 47803);
        Address addr2 = new Address("3 S. Walnut", 60601);
        CoffeeMachineController cm1 = ccf.getCmc("simple");
        cm1.setFields(0,1, addr1);

        CoffeeMachineController cm2 = ccf.getCmc("simple");
        //programmable
        cm2.setFields(1, 0, addr2);

        String cmd1 = "{\"commandStream\":{\"controller_id\":0,\"coffee_machine_id\":0,\"orderID\":1,\"DrinkName\":\"Latte\",\"Requesttype\":\"simple\",\"Options\":[{\"Name\":\"cream\",\"qty\":2},{\"Name\":\"sugar\",\"qty\":1}],\"Recipe\":[]}}";
        String cmd2 = "{\"commandStream\":{\"controller_id\":1,\"coffee_machine_id\":1,\"orderID\":2,\"DrinkName\":\"Americano\",\"Requesttype\":\"simple\",\"Options\":[{\"Name\":\"hazelnut\",\"qty\":4}],\"Recipe\":[]}}";


        System.out.println();
        System.out.println("Testing with SimpleController 1: ");
        System.out.println(cm1.toString());
        System.out.println("Command Stream JSON: " + cmd1);
        cm1.processCommandStream(cmd1);

        System.out.println();
        System.out.println("Testing with SimpleController 2: ");
        System.out.println(cm2.toString());
        System.out.println("Command Stream JSON: " + cmd2);
        cm2.processCommandStream(cmd2);
    }

    @Test
    public void testAdvancedController(){
        System.out.println();
        System.out.println();
        System.out.println("Initializing some fake advanced coffee machine controllers...");

        CoffeeControllerFactory ccf = new CoffeeControllerFactory();
        Address addr1 = new Address("200 N. Main", 47803);
        Address addr2 = new Address("3 S. Walnut", 60601);
        CoffeeMachineController cm1 = ccf.getCmc("advanced");
        cm1.setFields(0,1, addr1);

        CoffeeMachineController cm2 = ccf.getCmc("advanced");
        //programmable
        cm2.setFields(1, 0, addr2);

        String cmd1 = "{\"commandStream\":{\"controller_id\":0,\"coffee_machine_id\":0,\"orderID\":1,\"DrinkName\":\"Latte\",\"Requesttype\":\"advanced\",\"Options\":[{\"Name\":\"cream\",\"qty\":2},{\"Name\":\"sugar\",\"qty\":1}],\"Recipe\":[]}}";
        String cmd2 = "{\"commandStream\":{\"controller_id\":1,\"coffee_machine_id\":1,\"orderID\":2,\"DrinkName\":\"Americano\",\"Requesttype\":\"advanced\",\"Options\":[{\"Name\":\"hazelnut\",\"qty\":4}],\"Recipe\":[]}}";


        System.out.println();
        System.out.println("Testing with AdvancedController 1: ");
        System.out.println(cm1.toString());
        System.out.println("Command Stream JSON: " + cmd1);
        cm1.processCommandStream(cmd1);

        System.out.println();
        System.out.println("Testing with AdvancedController 2: ");
        System.out.println(cm2.toString());
        System.out.println("Command Stream JSON: " + cmd2);
        cm2.processCommandStream(cmd2);
    }

    @Test
    public void testProgrammableController(){
        System.out.println();
        System.out.println();
        System.out.println("Initializing some fake programmable coffee machine controllers...");

        CoffeeControllerFactory ccf = new CoffeeControllerFactory();
        Address addr1 = new Address("200 N. Main", 47803);
        Address addr2 = new Address("3 S. Walnut", 60601);
        CoffeeMachineController cm1 = ccf.getCmc("programmable");
        cm1.setFields(0,1, addr1);

        CoffeeMachineController cm2 = ccf.getCmc("programmable");
        cm2.setFields(1, 0, addr2);

        String cmd1 = "{\"commandStream\":{\"controller_id\":0,\"coffee_machine_id\":0,\"orderID\":1,\"DrinkName\":\"Latte\",\"Requesttype\":\"programmable\",\"Options\":[{\"Name\":\"cream\",\"qty\":2},{\"Name\":\"sugar\",\"qty\":1}],\"Recipe\":[]}}";
        String cmd2 = "{\"commandStream\":{\"controller_id\":1,\"coffee_machine_id\":1,\"orderID\":2,\"DrinkName\":\"Americano\",\"Requesttype\":\"programmable\",\"Options\":[{\"Name\":\"sugar\",\"qty\":4}],\"Recipe\":[]}}";

        System.out.println();
        System.out.println("Test ProgrammableController 1: ");
        System.out.println(cm1.toString());
        System.out.println("Command Stream JSON: " + cmd2);
        cm1.processCommandStream(cmd2);

        System.out.println();
        System.out.println("Test ProgrammableController 2: ");
        System.out.println(cm2.toString());
        System.out.println("Command Stream JSON: " + cmd1);
        cm2.processCommandStream(cmd1);
    }
}
