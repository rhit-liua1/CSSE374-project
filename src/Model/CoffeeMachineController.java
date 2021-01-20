package Model;

import Model.Address;

import java.util.Random;

public class CoffeeMachineController {
    private int id;
    private Address address;
    private CoffeeMachineType type;
    private CoffeeMachineStatus status;
    private int lastestOrderID;

    public CoffeeMachineController(int id, String type, int status, Address address) {
        this.address = address;
        this.id = id;
        this.type = type.equals("simple") ? CoffeeMachineType.SIMPLE : CoffeeMachineType.ADVANCED;
        this.status = status == 0 ? CoffeeMachineStatus.AVAILABLE : CoffeeMachineStatus.UNAVAILABLE;
    }

    @Override
    public String toString() {
        return "CoffeeMachineController{" +
                "id=" + id +
                ", address=" + address +
                ", type=" + type +
                ", status=" + status +
                ", lastestOrderID=" + lastestOrderID +
                '}';
    }

    enum CoffeeMachineStatus{
        AVAILABLE,
        UNAVAILABLE;
    }

    enum CoffeeMachineType {
        SIMPLE,
        ADVANCED
    }

//    public ControllerResponse initMsg() {
//        ControllerResponse response = new ControllerResponse(orderID, status, errCode);
//        return response;
//    }

    public void setType(CoffeeMachineType type) {
        this.type = type;
    }

    public int getStatus() {
        System.out.println("[Controller] Replying with status: " + status);
        return status == CoffeeMachineStatus.AVAILABLE ? 0 : 1;
    }

    public void setStatus(int status) {
        this.status = status == 0 ? CoffeeMachineStatus.AVAILABLE : CoffeeMachineStatus.UNAVAILABLE;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type == CoffeeMachineType.SIMPLE ? "simple" : "advanced";
    }

    public void setType(String type) {
        this.type = type.equals("simple") ? CoffeeMachineType.SIMPLE : CoffeeMachineType.ADVANCED;
    }

    public int getErrorType() {
        Random r = new Random();
        return r.nextInt(26-2) > 12 ? 26 : 2;
    }
}
