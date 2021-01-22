package Domain.Behaviors;

public class AddCondiment implements OrderCondimentBehavior{
    @Override
    public void addCondiments(String[] condiments) {
        if (condiments[0].equals("no condiment")) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < condiments.length; i++) {
            sb.append(condiments[i]);
            if (i < condiments.length - 1) {
                sb.append(", ");
            }
        }
        System.out.println("[CoffeeMachineController] " + sb.toString() + " added to the drink");
    }
}
