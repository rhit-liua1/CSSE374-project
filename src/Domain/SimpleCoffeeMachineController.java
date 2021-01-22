package Domain;

import Data.Address;
import Domain.Behaviors.AddNoCondiment;
import Domain.Behaviors.OrderDrink;

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
