import Model.Address;
import Model.CoffeeMachineController;
import Model.Order;
import Model.OrderManager;

import java.util.Scanner;

public class Server {

    private boolean isServerOn;
    private static OrderManager orderManager;

    public Server() {
        isServerOn = true;
        // Initialize Addresses
        // Initialize Coffee Machines
        orderManager = new OrderManager();
    }

    public void run() {
        System.out.println("[Server] Welcome to the Coffee Machine Management Platform!");

        int globalOrderCounter = 0;
        Scanner commandScanner = new Scanner(System.in);
        while(isServerOn){
            System.out.println("[Server] command list: \n" +
                    "> shutdown -- will shut down the server;\n" +
                    "> neworder -- will create a new player and game;\n" +
                    "Now, please enter a server command: ");
            String command = commandScanner.nextLine();

            switch(command){  // pave future stories for command pattern
                case "shutdown":
                    System.out.println("[Server] Command received: shutdown");
                    System.out.println("[Server] Shutdown received...server shut down. Bye.");
                    isServerOn = false;
                    break;

                case "neworder":
                    System.out.println("[Server] Command received: neworder");
                    StringBuilder sb = new StringBuilder();
                    sb.append("[Server] Select a coffee machine: \n");
                    for (CoffeeMachineController cm : orderManager.getCoffeeMachineControllerDB()) {
                        sb.append("> (");
                        sb.append(cm.getId());
                        sb.append(") -- Type: ");
                        sb.append(cm.getType());
                        sb.append(" -- Address: ");
                        sb.append(cm.getAddress().toString());
                        sb.append("\n");
                    }
                    System.out.println(sb.toString());
                    sb.append("Now, please enter a number to choose a coffee machine: ");
                    int orderInputAddressNumber = Integer.parseInt(commandScanner.nextLine());
                    System.out.println("Choose a drink from below: ");
                    System.out.println("> Americano : Regular caffeinated coffee");
                    System.out.println("> Latte : Coffee drink with milk and whipped cream");
                    System.out.println("> Decaff : Regular decaffeinated coffee");
                    System.out.println("> Expresso : Coffee concentrated");
                    System.out.println("> Colombia Dark : Stronger roast with Colombian beans");
                    System.out.println("> Pumpkin Spice : Seasonal drink with Pumpkin");
                    System.out.println("Please Enter the name of the drink");
                    String orderInputDrink = commandScanner.nextLine().toLowerCase();
                    //String orderInputCondiments = commandScanner.nextLine();

                    // Create Order
                    //Order order = new Order(globalOrderCounter++, orderInputDrink, address);
                    //orderManager.startOrder(order);
                    break;

                default:
                    System.out.println("[Server] Command not recognized.");
                    break;
            }
        }
        commandScanner.close();
    }
}
