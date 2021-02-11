package Java.Domain;

import Java.Data.*;
import Java.Beans.CommandBean;
import Java.Data.Responses.DrinkResponse;
import Java.Beans.DrinkResponseBean;
import Java.Domain.Behaviors.OrderCondimentBehavior;
import Java.Domain.Behaviors.OrderDrinkBehavior;
import Java.Domain.Behaviors.OrderRecipeBehavior;
import Java.GsonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class CoffeeMachineController implements Observer {
	protected OrderCondimentBehavior ocb;
	protected OrderDrinkBehavior odb;
	protected OrderRecipeBehavior orb;
	protected String type;

	protected int id;
	protected int status;
	protected Address address;
	private int errCode;

	public CoffeeMachineController() {}

	public CoffeeMachineController(int id, int status, Address address, Subject subject) {
		this.id = id;
		this.status = status;
		this.address = address;
		if (this.status == 1) {
			// Random error code if error
			this.errCode = this.getErrorType();
		}

	}

	public void setFields(int id, int status, Address address) {
		this.id = id;
		this.status = status;
		this.address = address;
		if (this.status == 1) {
			// Random error code if error
			this.errCode = this.getErrorType();
		}
	}

	@Override
	public void update(String command, OrderManager orderManager, int id) {
		if (id == this.id) {
			String drResponse = processCommandStream(command);
			orderManager.setResponse(drResponse);
		}
	}

	public String processCommandStream(String commandStreamJson) {
		try {
			// receive command json obj
			CommandBean cb = GsonUtil.parseJsonWithGson(commandStreamJson, CommandBean.class);
			CommandStream cmd = cb.getCommand();
			System.out.println("[CoffeeMachineController] Command Stream Received. Checking machine status...");
			processDrink(cmd);
			return generateDRJson(cmd);
		} catch (Exception e) {
			System.out.println(Arrays.toString(e.getStackTrace()));
		}
		return "";
	}

	private void processDrink(CommandStream cmd) {
		String drink = cmd.getDrinkName();
		List<Option> options = cmd.getOptions();
		List<Recipe> recipes = cmd.getRecipe();

		// invoke drink behavior
		this.produceDrink(drink);

		// invoke condiment behavior
		List<Condiment> condiments = new ArrayList<>();
		for (Option o: options) {
			condiments.add(new Condiment(o.getName(), o.getQty()));
		}
		this.addCondiments(condiments);

		// invoke recipe behavior
		this.handleRecipes(recipes);
	}

	private String generateDRJson(CommandStream cmd) {
		// create new drink response
		DrinkResponseBean db = new DrinkResponseBean();
		DrinkResponse dr = db.getDrinkResponse();
		dr.setOrderID(cmd.getOrderID());
		dr.setStatus(this.status);
		if (status == 0) {
			System.out.println("[CoffeeMachine] Coffee machine status = " + status);
			dr.setErrorcode(0);
			dr.setErrordesc("");
		} else {

			System.out.println("[CoffeeMachine] Coffee machine status = " + status + ", Error = " + errCode);
			dr.setErrorcode(this.errCode);
			dr.setErrordesc(this.getErrorMsg());
		}
		db.setDrinkresponse(dr);
		String drJson = GsonUtil.serializeWithGson(db);
		System.out.println("[CoffeeMachine] Sending back drink-response JSON object: \n			" + drJson + "\n");
		return drJson;
	}
	ArrayList<String> condiments = new ArrayList<>();

	public void produceDrink(String drink) {
		if (this.status == 1) {
			System.out.println("[CoffeeMachine] Error. Cannot produce drink. errorCode = " + errCode
					+ " errorMessage = " + this.getErrorMsg());
			return;
		}
		this.odb.produceDrink(drink);
	};

	public void handleRecipes(List<Recipe> recipes) {
		if (this.status == 1) {
			System.out.println("[CoffeeMachine] Error. Cannot handle recipes. errorCode = " + errCode
					+ " errorMessage = " + this.getErrorMsg());
			return;
		}
		this.orb.handleRecipe(recipes);
	}

	public void addCondiments(List<Condiment> condiments) {
		if (this.status == 1) {
			//print err msg
            System.out.println("[CoffeeMachine] Error. Cannot add condiments. errorCode = " + errCode + " errorMessage = " + this.getErrorMsg(errCode));
			return;
		}
		this.ocb.addCondiments(condiments);
	};

	@Override
	public String toString() {
		return "CoffeeMachineController{" + "id=" + id + ", type='" + type + '\'' + ", status=" + status + ", address="
				+ address + '}';
	}

	public int getErrorType() {
		Random r = new Random();
		return r.nextInt(26 - 2) > 12 ? 26 : 2;
	}

	public String getErrorMsg(int errCode) {
		switch (errCode) {
		case 2:
			return "Out of milk, drink canceled";
		case 26:
			return "Machine jammed";
		default:
			return "";
		}
	}

	public String getErrorMsg() {
		switch (this.errCode) {
			case 2:
				return "Out of milk, drink canceled";
			case 26:
				return "Machine jammed";
			default:
				return "";
		}
	}

	public OrderCondimentBehavior getOcb() {
		return ocb;
	}

	public void setOcb(OrderCondimentBehavior ocb) {
		this.ocb = ocb;
	}

	public OrderDrinkBehavior getOdb() {
		return odb;
	}

	public void setOdb(OrderDrinkBehavior odb) {
		this.odb = odb;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ArrayList<String> getCondiments() {
		return condiments;
	}

	public void setCondiments(ArrayList<String> condiments) {
		this.condiments = condiments;
	}
}
