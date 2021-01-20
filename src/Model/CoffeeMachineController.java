package Model;

import Model.Address;

public class CoffeeMachineController {
    private int id;
    private Address address;
    private CoffeeMachineType type;
    private CoffeeMachineStatus status;

    public CoffeeMachineController(Address address, int id, String type, int status) {
        this.address = address;
        this.id = id;
        this.type = type.equals("simple") ? CoffeeMachineType.SIMPLE : CoffeeMachineType.ADVANCED;
        this.status = status == 0 ? CoffeeMachineStatus.AVAILABLE : CoffeeMachineStatus.UNAVAILABLE;
    }

    enum CoffeeMachineStatus{
        AVAILABLE,
        UNAVAILABLE;
    }

    enum CoffeeMachineType {
        SIMPLE,
        ADVANCED
    }

    public void setType(CoffeeMachineType type) {
        this.type = type;
    }

    public int getStatus() {
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
}
