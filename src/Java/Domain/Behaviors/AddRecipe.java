package Java.Domain.Behaviors;

import Java.Data.Condiment;
import Java.Data.Recipe;

import java.util.List;

public class AddRecipe implements OrderRecipeBehavior{
    @Override
    public void handleRecipe(List<Recipe> recipes) {
//        if (recipes.get(0).equals("no condiment")) {
//            System.out.println("[CoffeeMachineController] No Recipes added to the drink");
//            return;
//        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < recipes.size(); i++) {
            Recipe r = recipes.get(i);
            sb.append("[");
            sb.append("name: ");
            sb.append(r.getCommandstep());
            sb.append(", quantity: ");
            sb.append(r.getObject());
            sb.append("]");
            if (i < recipes.size() - 1) {
                sb.append(", ");
            }
        }
        System.out.println("[CoffeeMachineController] Recipes " + sb.toString() + " added to the drink");
    }
}
