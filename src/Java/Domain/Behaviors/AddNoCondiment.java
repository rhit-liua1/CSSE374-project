package Java.Domain.Behaviors;

public class AddNoCondiment implements OrderCondimentBehavior{
    @Override
    public void addCondiments(String[] condiments) {
        System.out.println("[CoffeeMachineController] No condiments available on this machine. Please find barista");
    }
}
