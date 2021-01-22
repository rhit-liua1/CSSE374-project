package Data;

public class SimpleCoffeeMachineController extends CoffeeMachineController2{

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
