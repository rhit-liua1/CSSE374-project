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
        this.coffeeMachineControllerDB.add(0, new CoffeeMachineController(0, "simple", 1, addr4));
        this.coffeeMachineControllerDB.add(1, new CoffeeMachineController(1, "simple", 0, addr1));
        this.coffeeMachineControllerDB.add(2, new CoffeeMachineController(2, "advanced", 0, addr1));
        this.coffeeMachineControllerDB.add(3, new CoffeeMachineController(3, "advanced", 1, addr2));
        this.coffeeMachineControllerDB.add(4, new CoffeeMachineController(4, "simple", 0, addr2));
        this.coffeeMachineControllerDB.add(5, new CoffeeMachineController(5, "advanced", 0, addr3));
    }

    public void startOrder(Order order, int machineId) {
        this.order = order;
        findCoffeeMachine(machineId);
    }

    private void findCoffeeMachine(int id) {
        for (CoffeeMachineController cm : coffeeMachineControllerDB) {
            if (cm.getId() == id) {
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
        System.out.println("[System]> Validating coffee machine controller");
        if (this.orderedCoffeeMachine.getStatus() == 0) {
            this.conResponse = new ControllerResponse(order.getOrderId(), orderedCoffeeMachine.getStatus());
        } else {
            this.conResponse = new ControllerResponse(order.getOrderId(), orderedCoffeeMachine.getStatus(), orderedCoffeeMachine.getErrorType());
        }

    }

    private void generateAppResponse() {
        System.out.println("[System]> Generating response for customer");
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
