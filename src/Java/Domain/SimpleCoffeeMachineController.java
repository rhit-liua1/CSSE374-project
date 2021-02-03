package Java.Domain;

import Java.Data.Address;
import Java.Domain.Behaviors.AddNoCondiment;
import Java.Domain.Behaviors.AddNoRecipe;
import Java.Domain.Behaviors.OrderDrink;

public class SimpleCoffeeMachineController extends CoffeeMachineController {

    public SimpleCoffeeMachineController(int id, int status, Address address, Subject subject) {
        super(id, status, address, subject);
        type = "simple";
        this.ocb = new AddNoCondiment();
        this.odb = new OrderDrink();
        this.orb = new AddNoRecipe();
    }

    public SimpleCoffeeMachineController() {
        type = "simple";
        this.ocb = new AddNoCondiment();
        this.odb = new OrderDrink();
        this.orb = new AddNoRecipe();
    }
}
