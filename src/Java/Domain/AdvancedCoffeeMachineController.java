package Java.Domain;

import Java.Data.Address;
import Java.Domain.Behaviors.AddCondiment;
import Java.Domain.Behaviors.AddNoRecipe;
import Java.Domain.Behaviors.OrderDrink;

public class AdvancedCoffeeMachineController extends CoffeeMachineController {

    public AdvancedCoffeeMachineController(int id, int status, Address address, Subject subject) {
        super(id, status, address, subject);
        type = "advanced";
        this.ocb = new AddCondiment();
        this.odb = new OrderDrink();
        this.orb = new AddNoRecipe();
    }

    public AdvancedCoffeeMachineController() {
        type = "advanced";
        this.ocb = new AddCondiment();
        this.odb = new OrderDrink();
        this.orb = new AddNoRecipe();
    }
}
