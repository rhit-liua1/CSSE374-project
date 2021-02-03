package Java.Domain;

import Java.Data.Address;
import Java.Domain.Behaviors.AddCondiment;
import Java.Domain.Behaviors.AddRecipe;
import Java.Domain.Behaviors.OrderDrink;

public class ProgrammableCoffeeMachineController extends CoffeeMachineController {

    public ProgrammableCoffeeMachineController(int id, int status, Address address, Subject subject) {
        super(id, status, address, subject);
        type = "programmable";
        this.ocb = new AddCondiment();
        this.odb = new OrderDrink();
        this.orb = new AddRecipe();
    }

    public ProgrammableCoffeeMachineController() {
        type = "programmable";
        this.ocb = new AddCondiment();
        this.odb = new OrderDrink();
        this.orb = new AddRecipe();
    }
}
