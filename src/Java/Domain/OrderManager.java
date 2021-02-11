package Java.Domain;

import Java.Beans.CommandBean;
import Java.Beans.OrderBean;
import Java.Data.*;

import Java.Data.Responses.DrinkResponse;
import Java.Beans.DrinkResponseBean;
import Java.Data.Responses.UserResponse;
import Java.Beans.UserResponseBean;
import Java.Factories.CoffeeControllerFactory;
import Java.GsonUtil;

import java.util.ArrayList;
import java.util.List;

public class OrderManager implements Subject {
	private ArrayList<Order> orders;
	private String response;
	private int currentMachine;
	private ArrayList<Observer> observers;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		// Receive Drink Response and Translate into User Response
		DrinkResponseBean db = GsonUtil.parseJsonWithGson(response, DrinkResponseBean.class);
		DrinkResponse dr = db.getDrinkResponse();
		String urJson = generateUserResponseJson(dr, currentMachine);// cmd.getCoffee_machine_id());
		this.response = urJson;
	}

//	private final ArrayList<CoffeeMachineController> coffeeMachineControllerDB;
	private final ArrayList<String> coffeeTypes;
	private final ArrayList<String> coffeeCondiments;

	public OrderManager() {
		this.observers = new ArrayList<>();
		this.orders = new ArrayList<>();
		this.coffeeTypes = new ArrayList<>();
		this.coffeeCondiments = new ArrayList<>();
		this.coffeeTypes.add("americano");
		this.coffeeTypes.add("latte");
		this.coffeeTypes.add("decaff");
		this.coffeeTypes.add("espresso");
		this.coffeeTypes.add("colombia dark");
		this.coffeeTypes.add("pumpkin spice");
		this.coffeeCondiments.add("sugar");
		this.coffeeCondiments.add("cream");
		this.coffeeCondiments.add("nutrasweet");
		this.coffeeCondiments.add("hazelnut");

		CoffeeControllerFactory ccf = new CoffeeControllerFactory();
		CoffeeMachineController cm1 = ccf.getCmc("simple");
		cm1.setFields(0,1,new Address("200 N. Main", 47803));

		CoffeeMachineController cm2 = ccf.getCmc("simple");
		cm2.setFields(1, 0, new Address("3 S. Walnut", 60601));

		CoffeeMachineController cm3 = ccf.getCmc("advanced");
		cm3.setFields(2, 0, new Address("875 Champlain Ct.", 47803));

		CoffeeMachineController cm4 = ccf.getCmc("advanced");
		cm4.setFields(3, 1, new Address("18 Cana Ct.", 47804));

		CoffeeMachineController cm5 = ccf.getCmc("simple");
		cm5.setFields(4,  0, new Address("500 Pen Street", 10001));

		CoffeeMachineController cm6 = ccf.getCmc("advanced");
		cm6.setFields(5, 0, new Address("5500 Wabash Ave", 47803));

		CoffeeMachineController cm7 = ccf.getCmc("programmable");
		cm7.setFields(6, 1, new Address("6600 Fab Ave", 57044));

		CoffeeMachineController cm8 = ccf.getCmc("programmable");
		cm8.setFields(7, 0, new Address("20 Git Ave", 32900));

		this.registerObserver(cm1);
		this.registerObserver(cm2);
		this.registerObserver(cm3);
		this.registerObserver(cm4);
		this.registerObserver(cm5);
		this.registerObserver(cm6);
		this.registerObserver(cm7);
		this.registerObserver(cm8);
	}

	public void processOrderWithJson(String orderInputJson) {
		try {
			// receive a order-input json object
			OrderBean ob = GsonUtil.parseJsonWithGson(orderInputJson, OrderBean.class);
			Java.Data.Order od = ob.getOrder();
			// create a command-stream json object
			CommandBean cb = new CommandBean();
			CommandStream cmd = cb.getCommand();
			// find the right coffee machine and create command stream
			CoffeeMachineController cmc = findCoffeeMachineByAddress(od.getAddress());
			cmd.setController_id(cmc.getId());
			cmd.setCoffee_machine_id(cmc.getId());
			cmd.setOrderID(od.getOrderID());
			cmd.setDrinkName(od.getDrink());
			cmd.setRequesttype(cmc.getType());
			List<Option> optList = cmd.getOptions();
			for (Condiment condiment : od.getCondiments()) {
				Option tmpOpt = new Option(condiment.getName(), condiment.getQty());
				optList.add(tmpOpt);
			}
			// send command-stream json object to coffee machine
			cb.setCommand(cmd);
			String cmdJson = GsonUtil.serializeWithGson(cb);
			System.out.println("[System] Sending command-stream JSON object to coffee machine: \n			" + cmdJson + "\n");
			currentMachine = cmd.getCoffee_machine_id();
			notifyObserver(cmdJson, currentMachine);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public String generateUserResponseJson(DrinkResponse dr, int machineID) {
		// new user-response from drink-reponse
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
		System.out.println("[System] Sending user-response JSON object to user: \n			" + urJson + "\n");
		return urJson;
	}

	private CoffeeMachineController findCoffeeMachineByAddress(Address address) {
		if (address == null)
			return null;
		for (Observer cmc : observers) {
			if (((CoffeeMachineController) cmc).getAddress().getStreet().equals(address.getStreet())) {
				return (CoffeeMachineController) cmc;
			}
		}
		System.out.println("[Client]> machine not found at address");
		return null;
	}

	// find coffee machine based on provided id
	public CoffeeMachineController findCoffeeMachineById(int id) {
		for (Observer cm : observers) {
			if (((CoffeeMachineController) cm).getId() == id) {
				return (CoffeeMachineController) cm;
			}
		}
		return null;
	}

	public Address findAddressById(int id) {
		return findCoffeeMachineById(id).getAddress();
	}

	public ArrayList<String> getDrinkTypes() {
		return coffeeTypes;
	}

	public ArrayList<Observer> getCoffeeMachineControllerDB() {
		return observers;
	}

	public Order getOrderById(int orderId) {
		for (Order order : orders) {
			if (order.getOrderID() == orderId) {
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

	@Override
	public void registerObserver(Observer cm) {
		observers.add(cm);
	}

	@Override
	public void removeObserver(Observer cm) {
		observers.remove(cm);
	}

	@Override
	public void notifyObserver(String command, int id) {
		for (Observer observer : observers) {
			observer.update(command, this, id);
		}
	}
}
