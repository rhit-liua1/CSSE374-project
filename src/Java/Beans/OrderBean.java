package Java.Beans;

import Java.Data.Order;

public class OrderBean {

    Order order;

    public OrderBean() {
        this.order = new Order();
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "order=" + order +
                '}';
    }

}
