package Java.Domain;

import Java.Data.Address;
import Java.Data.Responses.ControllerResponse;

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
        cm.registerObserver(this);
        this.condiments = condiments;
//        generateCR();
        this.cr = cm.generateCR(this);
    }

    @Override
    public void update(CoffeeMachineController cm) {
        this.cm = cm;
        cm.generateCR(this);
//        generateCR();
    }


//    private void generateCR() {
//        this.cr = new ControllerResponse(orderId, cm.getStatus(), cm.getErrorType());
//    }
//
//    private void generateCR() {
//        System.out.println("[System] Sending Order to coffee machine controller no." + cm.getId());
//        int status = cm.getStatus();
//        cm.addCondiments(condiments);
//        if (status == 0) {
//            this.cr = new ControllerResponse(this.getOrderId(), status);
//            cm.produceDrink(drink);
//            System.out.println("[System] Controller Response received. Coffee machine status=" + status);
//        } else {
//            this.cr = new ControllerResponse(this.getOrderId(), status, cm.getErrorType());
//            System.out.println("[System] Controller Response received. Coffee machine status=" + status + ", Error=" + cm.getErrorType());
//        }
//    }

    public String[] getCondiments() {
        return condiments;
    }

    public void setCondiments(String[] condiments) {
        this.condiments = condiments;
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
