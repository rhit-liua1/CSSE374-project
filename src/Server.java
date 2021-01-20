import Model.*;

import java.util.Scanner;

public class Server {

    private boolean isServerOn;
    private static OrderManager orderManager;

    public Server() {
        isServerOn = true;
        orderManager = new OrderManager();
    }

    public void run() {
        System.out.println("[Server] Welcome to the Coffee Machine Management Platform!");
        int globalOrderCounter = 1;
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
                    sb.append("[Client] Select a coffee machine: \n");
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
                    sb.append("[Client] Enter a number to choose a coffee machine: ");
                    int orderInputMachineNumber = Integer.parseInt(commandScanner.nextLine());
                    if (orderInputMachineNumber < 0 || orderInputMachineNumber >= orderManager.getCoffeeMachineControllerDB().size()) {
                        System.out.println("[Client] Coffee machine doesn't exist. Please try again");
                        break;
                    }
                    System.out.println("Choose a drink from below: ");
                    System.out.println("> Americano : Regular caffeinated coffee");
                    System.out.println("> Latte : Coffee drink with milk and whipped cream");
                    System.out.println("> Decaff : Regular decaffeinated coffee");
                    System.out.println("> Espresso : Coffee concentrated");
                    System.out.println("> Colombia Dark : Stronger roast with Colombian beans");
                    System.out.println("> Pumpkin Spice : Seasonal drink with Pumpkin");
                    System.out.println("Please Enter the name of the drink");
                    String orderInputDrink = commandScanner.nextLine().toLowerCase();
                    if (!orderManager.getCoffeeTypes().contains(orderInputDrink)) {
                        System.out.println("[Client] Coffee type doesn't exist. Please try again");
                        break;
                    }
//                    String orderInputCondiments = commandScanner.nextLine();
//                    System.out.println("order machine num "+orderInputMachineNumber);
//                    System.out.println("machine "+orderManager.getCoffeeMachineControllerDB().get(orderInputMachineNumber).toString());
                    Address address = orderManager.getCoffeeMachineControllerDB().get(orderInputMachineNumber).getAddress();
                    // Create Order
                    Order order = new Order(globalOrderCounter++, orderInputDrink, address);
                    System.out.println("[Client] Order generated");
                    System.out.println("[Client] Sending order to system");
                    System.out.println();
                    orderManager.startOrder(order, orderInputMachineNumber);

                    AppResponse ar = orderManager.getAppResponse();
                    System.out.println();
                    System.out.println("[Client] Received response from the system: ");
                    System.out.println("--------------------------------------------------");
                    System.out.println("> Order ID: " + ar.getOrderId());
                    System.out.println("> Machine ID: " + ar.getMachineId());
                    if (ar.getStatus() == 1) {
                        System.out.println("> Order Status: " + ar.getStatusMsg());
                        System.out.println("> Error: " + ar.getErrMsg());
                    } else {
                        System.out.println("> Order Status: " +  ar.getStatusMsg());
                    }
                    System.out.println("--------------------------------------------------");
                    System.out.println();
                    System.out.println();
                    break;
                default:
                    System.out.println("[Server] Command not recognized.");
                    break;
            }
        }
        commandScanner.close();
    }
}
