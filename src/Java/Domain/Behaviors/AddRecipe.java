package Java.Domain.Behaviors;

import Java.Data.Condiment;
import Java.Data.Recipe;

import java.util.List;

public class AddRecipe implements OrderRecipeBehavior{
    @Override
    public void handleRecipe(List<Recipe> recipes) {
        if (recipes.isEmpty()) {
            System.out.println("[CoffeeMachineController] No recipe available");
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= recipes.size(); i++) {
            Recipe r = recipes.get((i-1));
            sb.append(i);
            sb.append(". ");
            sb.append(r.getCommandstep());
            sb.append(" ");
            sb.append(r.getObject());
            if ((i-1) < recipes.size() - 1) {
                sb.append(", ");
            }
        }
//
        System.out.println("[CoffeeMachineController] Drink prepared with recipe steps: " + sb.toString());
    }
}
