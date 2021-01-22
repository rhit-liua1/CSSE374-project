package Data;

public class OrderDrink implements OrderDrinkBehavior{
    @Override
    public void produceDrink(String drink) {
        System.out.println("[CoffeeMachineController] " + drink + " produced.");
    }
}
