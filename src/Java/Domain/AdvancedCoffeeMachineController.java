package Java.Domain;

import Java.Data.Address;
import Java.Domain.Behaviors.AddCondiment;
import Java.Domain.Behaviors.OrderDrink;

public class AdvancedCoffeeMachineController extends CoffeeMachineController {

    public AdvancedCoffeeMachineController(int id, String type, int status, Address address, Subject subject) {
        super(id, type, status, address, subject);
        this.ocb = new AddCondiment();
        this.odb = new OrderDrink();
    }

    public AdvancedCoffeeMachineController() {}

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
