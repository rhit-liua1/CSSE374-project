package Java.Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CommandBean{

    private Command command;

    @Override
    public String toString() {
        return "CommandBean{" +
                "command=" + command +
                '}';
    }

    public static class Command implements Serializable {
        int controller_id;
        int coffee_machine_id;
        int orderID;
        String DrinkName;
        String Requesttype;
        List<Option> Options = new ArrayList<>();

        @Override
        public String toString() {
            return "Command{" +
                    "controller_id=" + controller_id +
                    ", coffee_machine_id=" + coffee_machine_id +
                    ", orderID=" + orderID +
                    ", DrinkName='" + DrinkName + '\'' +
                    ", Requesttype='" + Requesttype + '\'' +
                    ", Options=" + (Options == null ? "[]" : Options.toString()) +
                    '}';
        }

        public Command(int controller_id, int coffee_machine_id, int orderID, String drinkName, String Requesttype) {
            this.controller_id = controller_id;
            this.coffee_machine_id = coffee_machine_id;
            this.orderID = orderID;
            this.DrinkName = drinkName;
            this.Requesttype = Requesttype;
            this.Options.add(new Option("Cream", 2));
        }

        public class Option {
            private String Name;
            private int qty;

            @Override
            public String toString() {
                return "Option{" +
                        "Name='" + Name + '\'' +
                        ", qty=" + qty +
                        '}';
            }

            public Option(String name, int qty) {
                this.Name = name;
                this.qty = qty;
            }

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
        }

        public int getController_id() {
            return controller_id;
        }

        public void setController_id(int controller_id) {
            this.controller_id = controller_id;
        }

        public int getCoffee_machine_id() {
            return coffee_machine_id;
        }

        public void setCoffee_machine_id(int coffee_machine_id) {
            this.coffee_machine_id = coffee_machine_id;
        }

        public int getOrderID() {
            return orderID;
        }

        public void setOrderID(int orderID) {
            this.orderID = orderID;
        }

        public String getDrinkName() {
            return DrinkName;
        }

        public void setDrinkName(String drinkName) {
            DrinkName = drinkName;
        }

        public String getRequesttype() {
            return Requesttype;
        }

        public void setRequesttype(String requesttype) {
            Requesttype = requesttype;
        }

        public List<Option> getOptions() {
            return Options;
        }

        public void setOptions(List<Option> options) {
            Options = options;
        }
    }
}
