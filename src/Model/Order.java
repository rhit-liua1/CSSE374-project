package Model;

public class Order {

    private int orderId;
    private String drink;
    private Address address;

    public Order(int orderId, String drink, Address address) {
        this.orderId = orderId;
        this.drink = drink;
        this.address = address;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
