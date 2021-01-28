package Java.Data;

import java.util.ArrayList;
import java.util.List;

public class Order {
    int orderID;
    Address address;
    String drink;
    List<Condiment> condiments = new ArrayList<>();

    public Order() {
//            this.orderID = 1;
//            this.address = new Address("wabsh",47803);
//            this.drink = "latte";
    }


    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public List<Condiment> getCondiments() {
        return condiments;
    }

    public void setCondiments(List<Condiment> condiments) {
        this.condiments = condiments;
    }

    public Order(int orderID, String drink) {
        this.orderID = orderID;
        this.address = new Address("200 N Main", 47803);
        this.drink = drink;
        this.condiments.add(new Condiment("Cream", 2));
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", address=" + address +
                ", drink='" + drink + '\'' +
                ", condiments=" + condiments +
                '}';
    }

}
