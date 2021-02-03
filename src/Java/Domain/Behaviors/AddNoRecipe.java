package Java.Domain.Behaviors;

import Java.Data.Recipe;

import java.util.List;

public class AddNoRecipe implements OrderRecipeBehavior{

    @Override
    public void handleRecipe(List<Recipe> recipes) {
        System.out.println("[CoffeeMachineController] This machine doesn't support adding recipes. Please find barista.");
    }
}
