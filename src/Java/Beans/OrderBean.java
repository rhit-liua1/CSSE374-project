package Java.Beans;

import Java.Data.Address;

import java.util.ArrayList;
import java.util.List;

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

    public static class Order{
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
            this.condiments.add(new Condiment("Cream",2));
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

        public static class Address {
            private String street;
            private int ZIP;

            public Address(String street, int ZIP) {
                this.street = street;
                this.ZIP = ZIP;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public int getZIP() {
                return ZIP;
            }

            public void setZIP(int ZIP) {
                this.ZIP = ZIP;
            }

            @Override
            public String toString() {
                return "Address{" +
                        "street='" + street + '\'' +
                        ", ZIP='" + ZIP + '\'' +
                        '}';
            }
        }

        public static class Condiment {
            private String Name;
            private int qty;

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public int getQty() {
                return qty;
            }

            public void setQty(int qty) {
                this.qty = qty;
            }

            public Condiment(String name, int qty) {
                Name = name;
                this.qty = qty;
            }

            @Override
            public String toString() {
                return "Condiment{" +
                        "Name='" + Name + '\'' +
                        ", qty=" + qty +
                        '}';
            }
        }
    }

}
