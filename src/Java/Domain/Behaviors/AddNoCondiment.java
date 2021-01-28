package Java.Domain.Behaviors;

import Java.Data.Condiment;

import java.util.List;

public class AddNoCondiment implements OrderCondimentBehavior{
    @Override
    public void addCondiments(List<Condiment> condiments) {
        System.out.println("[CoffeeMachineController] No condiments available on this machine. Please find barista.");
    }
}
