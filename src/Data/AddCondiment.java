package Data;

public class AddCondiment implements OrderCondimentBehavior{
    @Override
    public void addCondiment(String condiment) {
        System.out.println("[CoffeeMachineController] " + condiment + " added to the drink");
    }
}
