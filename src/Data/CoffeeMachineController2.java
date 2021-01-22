package Data;

import java.util.ArrayList;

public abstract class CoffeeMachineController2 {
    OrderCondimentBehavior ocb;
    OrderDrinkBehavior odb;
    int id;
    String type;
    int status;
    Address address;
    ArrayList<Order> observers = new ArrayList<>();



    public CoffeeMachineController2(int id, String type, int status, Address address) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.address = address;
    }

    ArrayList<String> condiments = new ArrayList<>();

    abstract void produceDrink(String drink);
    abstract void addCondiments(String condiment);

    void registerObserver(Order o) {
        this.observers.add(o);
    };
    void removeObserver(Order o) {
        this.observers.remove(o);
    };
    void notifyObserver() {
        //Todo
    };

}
