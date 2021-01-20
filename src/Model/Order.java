package Model;

import java.util.HashMap;

public class Order {

    private int orderId;
    private HashMap<Condiment, Integer> condimentMap;//?

    public Order(int orderId, HashMap<Condiment, Integer> condimentMap) {
        this.orderId = orderId;
        this.condimentMap = condimentMap;
    }



    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public HashMap<Condiment, Integer> getCondimentMap() {
        return condimentMap;
    }

    public void setCondimentMap(HashMap<Condiment, Integer> condimentMap) {
        this.condimentMap = condimentMap;
    }
}
