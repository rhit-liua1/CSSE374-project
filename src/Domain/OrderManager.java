package Domain;

import Data.*;
import Data.Responses.AppResponse;
import Data.Responses.ControllerResponse;

import java.util.ArrayList;

public class OrderManager {
    private ArrayList<Order> orders;
    private final ArrayList<CoffeeMachineController> coffeeMachineControllerDB;
    private final ArrayList<String> coffeeTypes;
    private final ArrayList<String> coffeeCondiments;

    public OrderManager() {
        this.coffeeMachineControllerDB = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.coffeeTypes = new ArrayList<>();
        this.coffeeCondiments = new ArrayList<>();
        Address addr1 = new Address("200 N. Main", "47803");
        Address addr2 = new Address("3 S. Walnut", "60601");
        Address addr3 = new Address("875 Champlain Ct.", "47803");
        Address addr4 = new Address("18 Cana Ct.", "47804");
        this.coffeeMachineControllerDB.add(0, new SimpleCoffeeMachineController(0, "simple", 1, addr4));
        this.coffeeMachineControllerDB.add(1, new SimpleCoffeeMachineController(1, "simple", 0, addr1));
        this.coffeeMachineControllerDB.add(2, new AdvancedCoffeeMachineController(2, "advanced", 0, addr1));
        this.coffeeMachineControllerDB.add(3, new AdvancedCoffeeMachineController(3, "advanced", 1, addr2));
        this.coffeeMachineControllerDB.add(4, new SimpleCoffeeMachineController(4, "simple", 0, addr2));
        this.coffeeMachineControllerDB.add(5, new AdvancedCoffeeMachineController(5, "advanced", 0, addr3));
        this.coffeeTypes.add("americano");
        this.coffeeTypes.add("latte");
        this.coffeeTypes.add("decaff");
        this.coffeeTypes.add("espresso");
        this.coffeeTypes.add("colombia dark");
        this.coffeeTypes.add("pumpkin spice");
        this.coffeeCondiments.add("sugar");
        this.coffeeCondiments.add("cream");
        this.coffeeCondiments.add("nutrasweet");
    }

    public AppResponse processOrder(int orderId, String drink, Address address, int machineId, String[] condiments) {
        CoffeeMachineController cm = findCoffeeMachineById(machineId);
        Order order = new Order(orderId, drink, address, cm, condiments);
        this.orders.add(order);
        return generateAppResponse(order.getOrderId(), machineId);
    }

    // find coffee machine based on provided id
    private CoffeeMachineController findCoffeeMachineById(int id) {
        for (CoffeeMachineController cm : coffeeMachineControllerDB) {
            if (cm.getId() == id) {
                return cm;
            }
        }
        return null;
    }

    private AppResponse generateAppResponse(int orderId, int machineId) {
        System.out.println("[System] Generating response for customer...");
        Order order = getOrderById(orderId);
        ControllerResponse cr = order.getCR();
        CoffeeMachineController cm = findCoffeeMachineById(machineId);
        if (cm == null) {
            return new AppResponse(orderId);
        }
        AppResponse ar = null;
        switch (cr.getErrCode()) {
            case -1:
                ar = new AppResponse(orderId, cr.getStatus(), cm.getId());
                break;
            case 2:
            case 26:
                ar = new AppResponse(orderId, cr.getStatus(), cm.getId(), cr.getErrDesc());
                break;
            default:
                ar = new AppResponse(orderId);
                break;
        }
        System.out.println("[System] Replying to customer with status: " + ar.getStatusMsg());
        return ar;
    }

    public ArrayList<String> getCoffeeTypes() {
        return coffeeTypes;
    }

    public ArrayList<CoffeeMachineController> getCoffeeMachineControllerDB() {
        return coffeeMachineControllerDB;
    }

    public Order getOrderById(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public ArrayList<String> getCoffeeCondiments() {
        return coffeeCondiments;
    }
}
