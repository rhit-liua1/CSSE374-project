package Java.Factories;

import Java.Data.Address;
import Java.Domain.*;

public class CoffeeControllerFactory {

    public CoffeeMachineController getCmc(String type) {
        switch (type) {
            case "simple":
                return new SimpleCoffeeMachineController();
            case "advanced":
                return new AdvancedCoffeeMachineController();
            case "programmable":
                return new ProgrammableCoffeeMachineController();
        }
        return null;
    }
}
