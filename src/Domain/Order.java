package Domain;

import Data.Address;
import Data.Responses.ControllerResponse;

public class Order implements Observer{

    private int orderId;
    private String drink;
    private Address address;
    private CoffeeMachineController cm;
    private ControllerResponse cr;
    private String[] condiments;

    public Order(int orderId, String drink, Address address, CoffeeMachineController cm, String[] condiments) {
        this.orderId = orderId;
        this.drink = drink;
        this.address = address;
        this.cm = cm;
        this.condiments = condiments;
        generateCR();
    }

    @Override
    public void update(CoffeeMachineController cm) {
        this.cm = cm;
        generateCR();
    }

//    private void generateCR() {
//        this.cr = new ControllerResponse(orderId, cm.getStatus(), cm.getErrorType());
//    }

    private void generateCR() {
        System.out.println("[System] Sending Order to coffee machine controller no." + cm.getId());
        int status = cm.getStatus();
        cm.addCondiments(condiments);
        if (status == 0) {
            this.cr = new ControllerResponse(this.getOrderId(), status);
            cm.produceDrink(drink);
            System.out.println("[System] Controller Response received. Coffee machine status=" + status);
        } else {
            this.cr = new ControllerResponse(this.getOrderId(), status, cm.getErrorType());
            System.out.println("[System] Controller Response received. Coffee machine status=" + status + ", Error=" + cm.getErrorType());
        }
    }

    public ControllerResponse getCR() {
        return this.cr;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
