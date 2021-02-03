package Java.Domain.Behaviors;

import Java.Data.Condiment;
import Java.Data.Recipe;

import java.util.List;

public class AddRecipe implements OrderRecipeBehavior{
    @Override
    public void handleRecipe(List<Recipe> recipes) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < recipes.size(); i++) {
            Recipe r = recipes.get(i);
            sb.append("[");
            sb.append("command step: ");
            sb.append(r.getCommandstep());
            sb.append(", object: ");
            sb.append(r.getObject());
            sb.append("]");
            if (i < recipes.size() - 1) {
                sb.append(", ");
            }
        }
        System.out.println("[CoffeeMachineController] Drink prepared with recipe steps " + sb.toString());
    }
}
