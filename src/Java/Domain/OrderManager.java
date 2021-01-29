package Java.Domain;

import Java.Beans.CommandBean;
import Java.Beans.OrderBean;
import Java.Data.*;

import Java.Data.Responses.DrinkResponse;
import Java.Beans.DrinkResponseBean;
import Java.Data.Responses.UserResponse;
import Java.Beans.UserResponseBean;
import Java.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class OrderManager implements Subject {
	private ArrayList<Order> orders;
	private String response;
	private int currentMachine;
	private ArrayList<Observer> observers;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		//Receive Drink Response and Translate into User Response
		DrinkResponseBean db = GsonUtil.parseJsonWithGson(response,DrinkResponseBean.class);
		DrinkResponse dr = db.getDrinkResponse();
		String urJson = generateUserResponseJson(dr, currentMachine);//cmd.getCoffee_machine_id());
		this.response = urJson;
	}

	private final ArrayList<CoffeeMachineController> coffeeMachineControllerDB;
	private final ArrayList<String> coffeeTypes;
	private final ArrayList<String> coffeeCondiments;

	private ArrayList<JSONObject> jorders;

	public OrderManager() {
		this.observers = new ArrayList<>();
		this.coffeeMachineControllerDB = new ArrayList<>();
		this.orders = new ArrayList<>();
		this.coffeeTypes = new ArrayList<>();
		this.coffeeCondiments = new ArrayList<>();
		Address addr1 = new Address("200 N. Main", 47803);
		Address addr2 = new Address("3 S. Walnut", 60601);
		Address addr3 = new Address("875 Champlain Ct.", 47803);
		Address addr4 = new Address("18 Cana Ct.", 47804);
		Address addr5 = new Address("500 Pen Street", 00001);
		Address addr6 = new Address("5500 Wabash Ave", 47803);
		this.coffeeMachineControllerDB.add(0, new SimpleCoffeeMachineController(0, "simple", 1, addr1, this));
		this.coffeeMachineControllerDB.add(1, new SimpleCoffeeMachineController(1, "simple", 0, addr2, this));
		this.coffeeMachineControllerDB.add(2, new AdvancedCoffeeMachineController(2, "advanced", 0, addr3, this));
		this.coffeeMachineControllerDB.add(3, new AdvancedCoffeeMachineController(3, "advanced", 1, addr4, this));
		this.coffeeMachineControllerDB.add(4, new SimpleCoffeeMachineController(4, "simple", 0, addr5, this));
		this.coffeeMachineControllerDB.add(5, new AdvancedCoffeeMachineController(5, "advanced", 0, addr6, this));
		this.coffeeTypes.add("americano");
		this.coffeeTypes.add("latte");
		this.coffeeTypes.add("decaff");
		this.coffeeTypes.add("espresso");
		this.coffeeTypes.add("colombia dark");
		this.coffeeTypes.add("pumpkin spice");
		this.coffeeCondiments.add("sugar");
		this.coffeeCondiments.add("cream");
		this.coffeeCondiments.add("nutrasweet");
	}

	public void processOrderWithJson(String orderInputJson) {
		try{
			//receive a order-input json object
			System.out.println(orderInputJson);
			OrderBean ob = GsonUtil.parseJsonWithGson(orderInputJson,OrderBean.class);
			Java.Data.Order od = ob.getOrder();

			//create a command-stream json object
			CommandBean cb = new CommandBean();
			CommandStream cmd = cb.getCommand();

			//find the right coffee machine and create command stream
			CoffeeMachineController cmc = findCoffeeMachineByAddress(od.getAddress());
			cmd.setController_id(cmc.getId());
			cmd.setCoffee_machine_id(cmc.getId());
			cmd.setOrderID(od.getOrderID());
			cmd.setDrinkName(od.getDrink());
			cmd.setRequesttype(cmc.getType());
			List<Option> optList = cmd.getOptions();
			for (Condiment condiment : od.getCondiments()) {
				Option tmpOpt = new Option(condiment.getName(),condiment.getQty());
				optList.add(tmpOpt);
			}

			//send command-stream json object to coffee machine
			cb.setCommand(cmd);
			String cmdJson = GsonUtil.serializeWithGson(cb);
			System.out.println("[System] Sending command-stream JSON object to coffee machine: \n"+cmdJson + "\n");
			currentMachine = cmd.getCoffee_machine_id();
			notifyObserver(cmdJson);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public String generateUserResponseJson(DrinkResponse dr, int machineID) {
		//new user-response from drink-reponse
		UserResponseBean ub = new UserResponseBean();
		UserResponse ur = ub.getUser_response();
		ur.setOrderID(dr.getOrderID());
		ur.setCoffee_machine_id(machineID);
		ur.setStatus(dr.getStatus());
		switch (dr.getStatus()) {
			case 0:
				ur.setStatus_message("Your coffee has been prepared with your desired options");
				break;
			case 1:
				ur.setStatus_message("Your coffee order has been cancelled");
				break;
		}
		ur.setError_message(dr.getErrordesc());
		ub.setUser_response(ur);
		String urJson = GsonUtil.serializeWithGson(ub);
		System.out.println("[System] Sending user-response JSON object to user: \n" + urJson + "\n");
		return urJson;
	}

	private CoffeeMachineController findCoffeeMachineByAddress(Address address) {
		if (address == null) return null;
		for (CoffeeMachineController cmc : coffeeMachineControllerDB) {
			if (cmc.getAddress().getStreet().equals(address.getStreet())) {
				return cmc;
			}
		}
		System.out.println("[Client]> machine not found at address");
		return null;
	}

//	public AppResponse processOrder(int orderId, String drink, Address address, int machineId, String[] condiments) {
//		CoffeeMachineController cm = findCoffeeMachineById(machineId);
//		Order order = new Order(orderId, drink, address, cm, condiments);
//		this.orders.add(order);
//		return generateAppResponse(order.getOrderId(), machineId);
//	}
//
//	public AppResponse processOrder1(JSONObject order) {
//		Address addr = (Address) order.get("address");
//		System.out.println(addr.toString());
//		CoffeeMachineController cm = findCoffeeMachineByAddress(addr.toString());
////		this.jorders.add(order);
//		return generateAppResponse(order.getInt("orderID"), cm.id);
//	}

	// find coffee machine based on provided id
	public CoffeeMachineController findCoffeeMachineById(int id) {
		for (CoffeeMachineController cm : coffeeMachineControllerDB) {
			if (cm.getId() == id) {
				return cm;
			}
		}
		return null;
	}

	public Address findAddressById(int id) {
		return findCoffeeMachineById(id).getAddress();
	}


//	private CoffeeMachineController findCoffeeMachineByAddress(String addr) {
//		for (CoffeeMachineController cm : coffeeMachineControllerDB) {
//			if (addr.equals(cm.getAddress().toString())) {
//				return cm;
//			}
//		}
//		return null;
//	}

//	private AppResponse generateAppResponse(int orderId, int machineId) {
//		System.out.println("[System] Generating response for customer...");
//		Order order = getOrderById(orderId);
//		ControllerResponse cr = order.getCR();
//		CoffeeMachineController cm = findCoffeeMachineById(machineId);
//		if (cm == null) {
//			return new AppResponse(orderId);
//		}
//		AppResponse ar = null;
//		switch (cr.getErrCode()) {
//		case -1:
//			ar = new AppResponse(orderId, cr.getStatus(), cm.getId());
//			break;
//		case 2:
//		case 26:
//			ar = new AppResponse(orderId, cr.getStatus(), cm.getId(), cr.getErrDesc());
//			break;
//		default:
//			ar = new AppResponse(orderId);
//			break;
//		}
//		System.out.println("[System] Replying to customer with status: " + ar.getStatusMsg());
//		return ar;
//	}

	public ArrayList<String> getCoffeeTypes() {
		return coffeeTypes;
	}

	public ArrayList<CoffeeMachineController> getCoffeeMachineControllerDB() {
		return coffeeMachineControllerDB;
	}

	public Order getOrderById(int orderId) {
		for (Order order : orders) {
			if (order.getOrderId() == orderId) {
				return order;
			}
		}
		return null;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public ArrayList<String> getCoffeeCondiments() {
		return coffeeCondiments;
	}

	public void orderParse() {

	}

	@Override
	public void registerObserver(Observer cm) {
		observers.add(cm);
	}

	@Override
	public void removeObserver(Observer cm) {
		observers.add(cm);
	}

	@Override
	public void notifyObserver(String command) {
		for(Observer observer: observers){
			observer.update(command, this);
		}

	}

	public UserResponse processOrder(JSONObject order) {
		// TODO Auto-generated method stub
		return null;
	}
}
