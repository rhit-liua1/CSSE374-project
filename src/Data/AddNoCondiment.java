package Data;

public class AddNoCondiment implements OrderCondimentBehavior{
    @Override
    public void addCondiments(String[] condiments) {
        System.out.println("[CoffeeMachineController] " + "No condiments available.");
    }
}
