package Java.Factories;

import Java.Data.Address;
import Java.Domain.*;

public class CoffeeControllerFactory {

    CoffeeMachineController cmc;

    public CoffeeMachineController getCmc(String type) {
        switch (type) {
            case "simple":
                cmc = new SimpleCoffeeMachineController();
                break;
            case "advance":
                cmc = new AdvancedCoffeeMachineController();
                break;
            case "programmable":
                cmc = new ProgrammableCoffeeMachineController();
                break;
        }
        return cmc;
    }
}
