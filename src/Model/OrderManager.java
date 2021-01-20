package Model;

import java.util.ArrayList;

public class OrderManager {
    private CoffeeMachineController orderedCoffeeMachine;
    private Order order;
    private ArrayList<CoffeeMachineController> coffeeMachineControllerDB;
    private AppResponse appResponse;
    private ControllerResponse conResponse;

    public OrderManager(Order order) {
        this.order = order;
        this.coffeeMachineControllerDB = new ArrayList<>();

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

}
