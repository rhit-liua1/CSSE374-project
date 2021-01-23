package Domain;

import Data.Address;
import Data.Responses.ControllerResponse;
import Domain.Behaviors.OrderCondimentBehavior;
import Domain.Behaviors.OrderDrinkBehavior;

import java.util.ArrayList;
import java.util.Random;

public abstract class CoffeeMachineController implements Subject{
    OrderCondimentBehavior ocb;
    OrderDrinkBehavior odb;
    int id;
    String type;
    int status;
    Address address;
    ArrayList<Order> observers = new ArrayList<>();

    public CoffeeMachineController(int id, String type, int status, Address address) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.address = address;
    }

    public ControllerResponse generateCR(Order order) {
        this.registerObserver(order);
        System.out.println("[System] Sending Order to coffee machine controller no." + this.getId());
        int status = this.getStatus();
        this.addCondiments(order.getCondiments());
        if (status == 0) {
            System.out.println("[System] Controller Response received. Coffee machine status=" + status);
            this.produceDrink(order.getDrink());
            return new ControllerResponse(order.getOrderId(), status);
        } else {
            System.out.println("[System] Controller Response received. Coffee machine status=" + status + ", Error=" + this.getErrorType());
            return new ControllerResponse(order.getOrderId(), status, this.getErrorType());
        }
    }

    ArrayList<String> condiments = new ArrayList<>();

    abstract void produceDrink(String drink);
    abstract void addCondiments(String[] condiments);

    public void registerObserver(Order o) {
        this.observers.add(o);
    }

    public void removeObserver(Order o) {
        this.observers.remove(o);
    }

    public void notifyObserver() {
        for (Order order : observers) {
            order.update(this);
        }
    }

    public int getErrorType() {
        Random r = new Random();
        return r.nextInt(26-2) > 12 ? 26 : 2;
    }

    public OrderCondimentBehavior getOcb() {
        return ocb;
    }

    public void setOcb(OrderCondimentBehavior ocb) {
        this.ocb = ocb;
    }

    public OrderDrinkBehavior getOdb() {
        return odb;
    }

    public void setOdb(OrderDrinkBehavior odb) {
        this.odb = odb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<Order> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<Order> observers) {
        this.observers = observers;
    }

    public ArrayList<String> getCondiments() {
        return condiments;
    }

    public void setCondiments(ArrayList<String> condiments) {
        this.condiments = condiments;
    }

}
