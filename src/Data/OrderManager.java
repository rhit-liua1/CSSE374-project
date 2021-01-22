package Data;

import java.util.ArrayList;

public class OrderManager {
    private CoffeeMachineController orderedCoffeeMachine;
    private ArrayList<Order> orders;
    private Order order;
    private final ArrayList<CoffeeMachineController> coffeeMachineControllerDB;
    private AppResponse appResponse;
    private ControllerResponse conResponse;
    private final ArrayList<String> coffeeTypes;

    public OrderManager() {
        this.coffeeMachineControllerDB = new ArrayList<>();
        this.coffeeTypes = new ArrayList<>();
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
        this.coffeeTypes.add("americano");
        this.coffeeTypes.add("latte");
        this.coffeeTypes.add("decaff");
        this.coffeeTypes.add("espresso");
        this.coffeeTypes.add("colombia dark");
        this.coffeeTypes.add("pumpkin spice");
    }

    public void startOrder(Order order, int machineId) {
        this.orders.add(order);
        findCoffeeMachine(machineId);

        if (this.orderedCoffeeMachine == null) {
            this.conResponse = null;
            this.appResponse = new AppResponse(order.getOrderId());
        } else {
            //getControllerResponse();
            generateAppResponse();
        }
    }

    private void findCoffeeMachine(int id) {
        for (CoffeeMachineController cm : coffeeMachineControllerDB) {
            if (cm.getId() == id) {
                this.orderedCoffeeMachine = cm;
                break;
            }
        }
    }

    private void getControllerResponse(int orderID) {
        System.out.println("[System] Sending Order to coffee machine controller no." + orderedCoffeeMachine.getId());
        int status = orderedCoffeeMachine.getStatus();
        if (status == 0) {
            this.conResponse = new ControllerResponse(order.getOrderId(), status);
            System.out.println("[System] Controller Response received. Coffee machine status=" + status);
        } else {
            this.conResponse = new ControllerResponse(order.getOrderId(), status, orderedCoffeeMachine.getErrorType());
            System.out.println("[System] Controller Response received. Coffee machine status=" + status + ", Error=" + orderedCoffeeMachine.getErrorType());
        }

    }

    private void generateAppResponse() {
        System.out.println("[System] Generating response for customer...");
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
        System.out.println("[System] Replying to customer with status: " + this.appResponse.getStatusMsg());
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
    public ArrayList<String> getCoffeeTypes() {
        return coffeeTypes;
    }

    public ControllerResponse getConResponse() {
        return conResponse;
    }
}
