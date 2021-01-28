package Java.Domain;

import Java.Data.Address;
import Java.Domain.Behaviors.AddNoCondiment;
import Java.Domain.Behaviors.OrderDrink;

public class SimpleCoffeeMachineController extends CoffeeMachineController {

    public SimpleCoffeeMachineController(int id, String type, int status, Address address) {
        super(id, type, status, address);
        this.ocb = new AddNoCondiment();
        this.odb = new OrderDrink();

    }

    @Override
    void produceDrink(String drink) {
        this.odb.produceDrink(drink);
    }

    @Override
    void addCondiments(String[] condiments) {
        this.ocb.addCondiments(condiments);
    }
}
