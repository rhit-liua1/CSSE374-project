package Java.Domain;

import Java.Data.Address;
import Java.Domain.Behaviors.AddNoCondiment;
import Java.Domain.Behaviors.OrderDrink;

public class SimpleCoffeeMachineController extends CoffeeMachineController {

    public SimpleCoffeeMachineController(int id, String type, int status, Address address, Subject subject) {
        super(id, type, status, address, subject);
        this.ocb = new AddNoCondiment();
        this.odb = new OrderDrink();

    }

//    @Override
//    public void produceDrink(String drink) {
//        this.odb.produceDrink(drink);
//    }
//
//    @Override
//    public void addCondiments(String[] condiments) {
//        this.ocb.addCondiments(condiments);
//    }
}
