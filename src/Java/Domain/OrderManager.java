package Java.Domain;

import Java.Beans.*;
import Java.Beans.OrderBean;
import Java.Data.*;

import Java.GsonUtil;

import Java.Data.Responses.AppResponse;
import Java.Data.Responses.ControllerResponse;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class OrderManager implements Subject {
	private ArrayList<Order> orders;
	private final ArrayList<CoffeeMachineController> coffeeMachineControllerDB;
	private final ArrayList<String> coffeeTypes;
	private final ArrayList<String> coffeeCondiments;

	private ArrayList<JSONObject> jorders;

	public OrderManager() {
		this.coffeeMachineControllerDB = new ArrayList<>();
		this.orders = new ArrayList<>();
		this.coffeeTypes = new ArrayList<>();
		this.coffeeCondiments = new ArrayList<>();
		Address addr1 = new Address("200 N. Main", "47803");
		Address addr2 = new Address("3 S. Walnut", "60601");
		Address addr3 = new Address("875 Champlain Ct.", "47803");
		Address addr4 = new Address("18 Cana Ct.", "47804");
		Address addr5 = new Address("500 Pen Street", "00001");
		Address addr6 = new Address("5500 Wabash Ave", "47803");
		this.coffeeMachineControllerDB.add(0, new SimpleCoffeeMachineController(0, "simple", 1, addr1));
		this.coffeeMachineControllerDB.add(1, new SimpleCoffeeMachineController(1, "simple", 0, addr2));
		this.coffeeMachineControllerDB.add(2, new AdvancedCoffeeMachineController(2, "advanced", 0, addr3));
		this.coffeeMachineControllerDB.add(3, new AdvancedCoffeeMachineController(3, "advanced", 1, addr4));
		this.coffeeMachineControllerDB.add(4, new SimpleCoffeeMachineController(4, "simple", 0, addr5));
		this.coffeeMachineControllerDB.add(5, new AdvancedCoffeeMachineController(5, "advanced", 0, addr6));
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

	public String processOrderWithJson(String json) {
		try{
			//order json obj
			OrderBean ob = GsonUtil.parseJsonWithGson(json,OrderBean.class);
			System.out.println("ob: "+ob.toString());
			OrderBean.Order od = ob.getOrder();
			System.out.println("od: "+od.toString());
			//command json obj
			CommandBean cb = new CommandBean();
			CommandBean.Command cmd = cb.getCommand();
			//find coffee machine controller
			CoffeeMachineController cmc = findCoffeeMachineByAddress(od.getAddress());
			cmd.setController_id(cmc.getId());
			cmd.setCoffee_machine_id(cmc.getId());
			cmd.setOrderID(od.getOrderID());
			cmd.setDrinkName(od.getDrink());
			cmd.setRequesttype(cmc.getType());
			List<CommandBean.Command.Option> optList = cmd.getOptions();
			for (OrderBean.Order.Condiment condiment : od.getCondiments()) {
				CommandBean.Command.Option tmpOpt = new CommandBean.Command.Option(condiment.getName(),condiment.getQty());
				optList.add(tmpOpt);
			}

			//deserialize cmd
			String cmdJson = GsonUtil.serializeWithGson(cmd);
			System.out.println("controller response: "+cmdJson);
			//get controller response
			String drJson = cmc.processCommandStream(cmdJson);;
			//get drJson
			DrinkResponseBean db = GsonUtil.parseJsonWithGson(drJson,DrinkResponseBean.class);
			DrinkResponseBean.DrinkResponse dr = db.getDrinkresponse();
			//new Userresponse
			UserResponseBean ub = new UserResponseBean();
			UserResponseBean.UserResponse ur = ub.getUser_response();
			ur.setOrderID(dr.getOrderID());
			ur.setCoffee_machine_id(cmc.getId());
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
			String urJson = GsonUtil.serializeWithGson(ub);
			System.out.println("user response: "+urJson);
			return urJson;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return "";
	}

	private CoffeeMachineController findCoffeeMachineByAddress(OrderBean.Order.Address address) {
		if (address == null) return null;
		for (CoffeeMachineController cmc : coffeeMachineControllerDB) {
			if (cmc.getAddress().getStreet().equals(address.getStreet())) {
				return cmc;
			}
		}
		System.out.println("[Client]> machine not found at address");
		return null;
	}

	public AppResponse processOrder(int orderId, String drink, Address address, int machineId, String[] condiments) {
		CoffeeMachineController cm = findCoffeeMachineById(machineId);
		Order order = new Order(orderId, drink, address, cm, condiments);
		this.orders.add(order);
		return generateAppResponse(order.getOrderId(), machineId);
	}

	public AppResponse processOrder1(JSONObject order) {
		Address addr = (Address) order.get("address");
		System.out.println(addr.toString());
		CoffeeMachineController cm = findCoffeeMachineByAddress(addr.toString());
//		this.jorders.add(order);
		return generateAppResponse(order.getInt("orderID"), cm.id);
	}

	// find coffee machine based on provided id
	private CoffeeMachineController findCoffeeMachineById(int id) {
		for (CoffeeMachineController cm : coffeeMachineControllerDB) {
			if (cm.getId() == id) {
				return cm;
			}
		}
		return null;
	}

	private CoffeeMachineController findCoffeeMachineByAddress(String addr) {
		for (CoffeeMachineController cm : coffeeMachineControllerDB) {
			if (addr.equals(cm.getAddress().toString())) {
				return cm;
			}
		}
		return null;
	}

	private AppResponse generateAppResponse(int orderId, int machineId) {
		System.out.println("[System] Generating response for customer...");
		Order order = getOrderById(orderId);
		ControllerResponse cr = order.getCR();
		CoffeeMachineController cm = findCoffeeMachineById(machineId);
		if (cm == null) {
			return new AppResponse(orderId);
		}
		AppResponse ar = null;
		switch (cr.getErrCode()) {
		case -1:
			ar = new AppResponse(orderId, cr.getStatus(), cm.getId());
			break;
		case 2:
		case 26:
			ar = new AppResponse(orderId, cr.getStatus(), cm.getId(), cr.getErrDesc());
			break;
		default:
			ar = new AppResponse(orderId);
			break;
		}
		System.out.println("[System] Replying to customer with status: " + ar.getStatusMsg());
		return ar;
	}

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
	public void registerObserver(CoffeeMachineController cm) {
		this.coffeeMachineControllerDB.add(cm);
	}

	@Override
	public void removeObserver(CoffeeMachineController cm) {
		this.coffeeMachineControllerDB.remove(cm);
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub

	}

	public AppResponse processOrder(JSONObject order) {
		// TODO Auto-generated method stub
		return null;
	}
}
