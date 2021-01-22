package Data;

import java.util.ArrayList;

public class AdvancedCoffeeMachineController extends CoffeeMachineController2{

    public AdvancedCoffeeMachineController(int id, String type, int status, Address address) {
        super(id, type, status, address);
        this.ocb = new AddCondiment();
        this.odb = new OrderDrink();

    }

    @Override
    void produceDrink(String drink) {
        this.odb.produceDrink(drink);
    }

    @Override
    void addCondiments(String condiment) {
        this.ocb.addCondiment(condiment);
    }

}
