package Model;

import java.util.ArrayList;

public class OrderManager {
    private CoffeeMachineController orderedCoffeeMachine;
    private Order order;
    private final ArrayList<CoffeeMachineController> coffeeMachineControllerDB;
    private AppResponse appResponse;
    private ControllerResponse conResponse;

    public OrderManager() {
        this.coffeeMachineControllerDB = new ArrayList<>();
        Address addr1 = new Address("200 N. Main", "47803");
        Address addr2 = new Address("3 S. Walnut", "60601");
        Address addr3 = new Address("875 Champlain Ct.", "47803");
        Address addr4 = new Address("18 Cana Ct.", "47804");
        this.coffeeMachineControllerDB.add(new CoffeeMachineController(1, "simple", 0, addr1));
        this.coffeeMachineControllerDB.add(new CoffeeMachineController(2, "advanced", 0, addr1));
        this.coffeeMachineControllerDB.add(new CoffeeMachineController(3, "advanced", 1, addr2));
        this.coffeeMachineControllerDB.add(new CoffeeMachineController(4, "simple", 0, addr2));
        this.coffeeMachineControllerDB.add(new CoffeeMachineController(5, "advanced", 0, addr3));
        this.coffeeMachineControllerDB.add(new CoffeeMachineController(6, "simple", 1, addr4));


    }

    public void startOrder(Order order) {
        this.order = order;
        findCoffeeMachine(order.getAddress());
    }

    private void findCoffeeMachine(Address address) {
        for (CoffeeMachineController cm : coffeeMachineControllerDB) {
            if (cm.getAddress().equals(address)) {
                this.orderedCoffeeMachine = cm;
                break;
            }
        }
        if (this.orderedCoffeeMachine == null) {
            this.conResponse = null;
            this.appResponse = new AppResponse(order.getOrderId());
        } else {
            getControllerResponse();
            generateAppResponse();
        }
    }

    private void getControllerResponse() {
        if (this.orderedCoffeeMachine.getStatus() == 0) {
            this.conResponse = new ControllerResponse(order.getOrderId(), orderedCoffeeMachine.getStatus());
        } else {
            this.conResponse = new ControllerResponse(order.getOrderId(), orderedCoffeeMachine.getStatus(), orderedCoffeeMachine.getErrorType());
        }

    }

    private void generateAppResponse() {
        switch (conResponse.getErrCode()) {
            case -1:
                this.appResponse = new AppResponse(order.getOrderId(), conResponse.getStatus(), orderedCoffeeMachine.getId());
                break;
            case 2:
            case 26:
                this.appResponse = new AppResponse(order.getOrderId(), conResponse.getStatus(), orderedCoffeeMachine.getId(), conResponse.getErrDesc());
                break;
            default:
                break;
        }
    }

    public AppResponse getAppResponse() {
        return this.appResponse;
    }

    public CoffeeMachineController getOrderedCoffeeMachine() {
        return orderedCoffeeMachine;
    }

    public Order getOrder() {
        return order;
    }

    public ArrayList<CoffeeMachineController> getCoffeeMachineControllerDB() {
        return coffeeMachineControllerDB;
    }

    public ControllerResponse getConResponse() {
        return conResponse;
    }
}
