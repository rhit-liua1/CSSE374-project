package Data;

public class AddNoCondiment implements OrderCondimentBehavior{
    @Override
    public void addCondiment(String condiment) {
        System.out.println("[CoffeeMachineController] " + "No condiments available.");
    }
}
