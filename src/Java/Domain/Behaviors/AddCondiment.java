package Java.Domain.Behaviors;

import Java.Data.Condiment;

import java.util.List;

public class AddCondiment implements OrderCondimentBehavior{
    @Override
    public void addCondiments(List<Condiment> condiments) {
        if (condiments.isEmpty()) {
            System.out.println("[CoffeeMachineController] No condiments added to the drink");
            return;
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < condiments.size(); i++) {
            Condiment c = condiments.get(i);
            sb.append("[");
            sb.append("name: ");
            sb.append(c.getName());
            sb.append(", quantity: ");
            sb.append(c.getQty());
            sb.append("]");
            if (i < condiments.size() - 1) {
                sb.append(", ");
            }
        }
        System.out.println("[CoffeeMachineController] Condiments " + sb.toString() + " added to the drink");
    }
}
