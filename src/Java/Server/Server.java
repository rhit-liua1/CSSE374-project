package Java.Server;

import Java.Beans.*;
import Java.Data.*;
import Java.Data.Responses.AppResponse;
import Java.Domain.CoffeeMachineController;
import Java.Domain.OrderManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Java.GsonUtil;
import org.json.JSONObject;


public class Server {

	private boolean isServerOn;
	private static OrderManager orderManager;
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
				StringBuilder sb = new StringBuilder();

//				JSONObject order = new JSONObject();

				OrderBean ob = new OrderBean();
				OrderBean.Order inputOrder = new OrderBean.Order();

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
				//set drink to current order (for json use)
				inputOrder.setDrink(orderInputDrink);
				System.out.println("");

				if (!orderManager.getCoffeeTypes().contains(orderInputDrink)) {
					System.out.println("[Client] Coffee type doesn't exist. Please try again");
					break;
				}

				System.out.println("Select condiments from below: ");
				System.out.println("> Cream : Diary based individual serving");
				System.out.println("> Sugar : Case sugar teaspoon");
				System.out.println("> NutraSweet : Individual serving");
				System.out.println("Please Enter the name of the condiments, separate with spaces");
				String orderInputCondiments = commandScanner.nextLine().toLowerCase();
				System.out.println("");
//				String[] condiments = null;
//				if (orderInputCondiments.isEmpty()) {
//					condiments = new String[] { "no condiment" };
//				} else {
//					condiments = orderInputCondiments.trim().split(" ");
//					boolean flag = false;
//					for (String condiment : condiments) {
//						if (!orderManager.getCoffeeCondiments().contains(condiment)) {
//							System.out.println("[Client] Condiment type doesn't exist. Please try again");
//							flag = true;
//							break;
//						}
//					}
//					if (flag)
//						continue;
//				}

				List<OrderBean.Order.Condiment> condiments = new ArrayList<>();
				if (!orderInputCondiments.isEmpty()) {
					String[] tmpCondiments = orderInputCondiments.trim().split(" ");
					boolean invalidCondiment = false;
					for (String condiment : tmpCondiments) {
						if (!orderManager.getCoffeeCondiments().contains(condiment)) {
							System.out.println("[Client] Condiment type doesn't exist. Please try again");
							invalidCondiment = true;
							break;
						}
					}
					if (invalidCondiment)
						continue;
					for (String condiment : tmpCondiments) {
						System.out.println("Please Enter the quantity you want for "+condiment+": ");
						int tmpInputQty = commandScanner.nextInt();
						OrderBean.Order.Condiment cd = new OrderBean.Order.Condiment(condiment,tmpInputQty);
						condiments.add(cd);
					}
				}
				//set condiments to current order (for json use)
				inputOrder.setCondiments(condiments);
//				System.out.println(order.toJson(condiments));

				//TODO: ADDRESS TO BE FIXED
				Address address = orderManager.getCoffeeMachineControllerDB().get(orderInputMachineNumber).getAddress();
				if (address == null) {
					System.out.println("--------------------");
					break;
				}
				OrderBean.Order.Address ad = new OrderBean.Order.Address(address.getStreet(), Integer.parseInt(address.getZip()));
				//set address to current order (for json use)
				inputOrder.setAddress(ad);
				//set orderID to current order (for json use)
				inputOrder.setOrderID(globalOrderCounter++);
//				System.out.println("input order: "+inputOrder.toString());
//				System.out.println("input json: "+GsonUtil.serializeWithGson(inputOrder));

				System.out.println("input order:" +inputOrder);
				ob.setOrder(inputOrder);
				System.out.println("order b:" +ob.toString());
				String orderJson = GsonUtil.serializeWithGson(ob);
				System.out.println("order json: "+orderJson);
				String arJson = orderManager.processOrderWithJson(orderJson);
				UserResponseBean ub = GsonUtil.parseJsonWithGson(arJson,UserResponseBean.class);
				UserResponseBean.UserResponse ur = ub.getUser_response();

//				order.put("orderID", globalOrderCounter++);
				System.out.println("[Client] Order generated");
				System.out.println("[Client] Sending order to system");
				System.out.println();
//				AppResponse ar = orderManager.processOrder(globalOrderCounter++, orderInputDrink, address,
//						orderInputMachineNumber, condiments);

				System.out.println();
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

//				if (flag) {
//					order.put("condiments", condiments);
//					order.put("address", address);
//					AppResponse ar1 = orderManager.processOrder1(order);
//					System.out.println();
//					System.out.println("[Client] Received response from the system: ");
//					System.out.println("--------------------------------------------------");
//					System.out.println("> Order ID: " + ar1.getOrderId());
//					System.out.println("> Machine ID: " + ar1.getMachineId());
//					if (ar1.getStatus() == 1) {
//						System.out.println("> Order Status: " + ar1.getStatusMsg());
//						System.out.println("> Error: " + ar1.getErrMsg());
//					} else {
//						System.out.println("> Order Status: " + ar1.getStatusMsg());
//					}
//					System.out.println("--------------------------------------------------");
//					System.out.println();
//					System.out.println();
//				}

				break;
				default:
				System.out.println("[Server] Command not recognized.");
				break;
			}
		}
		commandScanner.close();
	}
}
