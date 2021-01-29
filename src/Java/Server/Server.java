package Java.Server;

import Java.Data.Address;
import Java.Data.Condiment;
import Java.Data.Order;
import Java.Beans.OrderBean;
import Java.Data.Responses.UserResponse;
import Java.Beans.UserResponseBean;
import Java.Domain.CoffeeMachineController;
import Java.Domain.OrderManager;
import Java.GsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Server {

    private static OrderManager orderManager;
    private boolean isServerOn;
    private boolean flag = false;

    public Server() {
        isServerOn = true;
        orderManager = new OrderManager();
    }

    public void run() {
        System.out.println("[Server] Welcome to the Coffee Machine Management Platform!");
        int globalOrderCounter = 1;
        Scanner commandScanner = new Scanner(System.in);

        while (isServerOn) {
            System.out.println("[Server] command list: \n" + "> shutdown -- will shut down the server;\n"
                    + "> neworder -- will create a new player and game;\n" + "Now, please enter a server command: ");
            String command = commandScanner.nextLine();

            switch (command) { // pave future stories for command pattern
                case "shutdown":
                    System.out.println("[Server] Command received: shutdown");
                    System.out.println("[Server] Shutdown received...server shut down. Bye.");
                    isServerOn = false;
                    break;

                case "neworder":
                    System.out.println("[Server] Command received: neworder");

                    //Create order input receiver
                    OrderBean ob = new OrderBean();
                    Order inputOrder = new Order();

                    StringBuilder sb = new StringBuilder();
                    //	JSONObject order = new JSONObject();
                    System.out.println();
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
                    //TODO
                    int orderInputMachineNumber = Integer.parseInt(commandScanner.nextLine());

                    if (orderInputMachineNumber < 0
                            || orderInputMachineNumber >= orderManager.getCoffeeMachineControllerDB().size()) {
                        System.out.println("[Client] Coffee machine doesn't exist. Please try again");
                        System.out.println();
                        break;
                    }
                    System.out.println();
                    System.out.println("[Client] Choose a drink from below: ");
                    System.out.println("> Americano : Regular caffeinated coffee");
                    System.out.println("> Latte : Coffee drink with milk and whipped cream");
                    System.out.println("> Decaff : Regular decaffeinated coffee");
                    System.out.println("> Espresso : Coffee concentrated");
                    System.out.println("> Colombia Dark : Stronger roast with Colombian beans");
                    System.out.println("> Pumpkin Spice : Seasonal drink with Pumpkin");
                    System.out.println("Please Enter the name of the drink");
                    String orderInputDrink = commandScanner.nextLine().toLowerCase();
                    //set drink to current order (for json use)
                    inputOrder.setDrink(orderInputDrink);

                    if (!orderManager.getCoffeeTypes().contains(orderInputDrink)) {
                        System.out.println("[Client] Coffee type doesn't exist. Please try again");
                        System.out.println();
                        break;
                    }

                    System.out.println();
                    System.out.println("[Client] Select condiments from below: ");
                    System.out.println("> Cream : Diary based individual serving");
                    System.out.println("> Sugar : Case sugar teaspoon");
                    System.out.println("> NutraSweet : Individual serving");
                    System.out.println("Please Enter the name of the condiments, separate with spaces");
                    String orderInputCondiments = commandScanner.nextLine().toLowerCase();

                    List<Condiment> condiments = new ArrayList<>();
                    if (!orderInputCondiments.isEmpty()) {
                        String[] tmpCondiments = orderInputCondiments.trim().split(" ");
                        boolean invalidCondiment = false;
                        for (String condiment : tmpCondiments) {
                            if (!orderManager.getCoffeeCondiments().contains(condiment)) {
                                System.out.println("[Client] Condiment type doesn't exist. Please try again");
                                System.out.println();
                                invalidCondiment = true;
                                break;
                            }
                        }
                        if (invalidCondiment) {
                            continue;
                        }

                        for (String condiment : tmpCondiments) {
                            System.out.println("Please Enter the quantity you want for [" + condiment + "]: ");
                            int tmpInputQty = commandScanner.nextInt();
                            Condiment cd = new Condiment(condiment, tmpInputQty);
                            condiments.add(cd);
                        }
                    }
                    //set condiments to current order (for json use)
                    inputOrder.setCondiments(condiments);

                    Address address = orderManager.findAddressById(orderInputMachineNumber);
                    //set address to current order json
                    inputOrder.setAddress(address);
                    //set orderID to current order json
                    inputOrder.setOrderID(globalOrderCounter++);

                    ob.setOrder(inputOrder);
                    String orderJson = GsonUtil.serializeWithGson(ob);
                    System.out.println("[Client] order-input JSON object Created: \n" + orderJson + "\n");
                    System.out.println("[Client] Sending order to system...");
                    System.out.println();

                    orderManager.processOrderWithJson(orderJson);
                    String arJson = orderManager.getResponse();
                    UserResponseBean ub = GsonUtil.parseJsonWithGson(arJson, UserResponseBean.class);
                    UserResponse ur = ub.getUser_response();

                    System.out.println("[Client] Received response from the system: ");
                    System.out.println("--------------------------------------------------");
                    System.out.println("> Order ID: " + ur.getOrderID());
                    System.out.println("> Machine ID: " + ur.getCoffee_machine_id());
                    if (ur.getStatus() == 1) {
                        System.out.println("> Order Status: " + ur.getStatus_message());
                        System.out.println("> Error: " + ur.getError_message());
                    } else {
                        System.out.println("> Order Status: " + ur.getStatus_message());
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
