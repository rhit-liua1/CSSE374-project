package Java.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CommandStream implements Serializable {
	int controller_id;
	int coffee_machine_id;
	int orderID;
	String DrinkName;
	String Requesttype;
	List<Option> Options = new ArrayList<>();
	List<Recipe> Recipe = new ArrayList<>();

	@Override
	public String toString() {
		return "Command{" + "controller_id=" + controller_id + ", coffee_machine_id=" + coffee_machine_id + ", orderID="
				+ orderID + ", DrinkName='" + DrinkName + '\'' + ", RequestType='" + Requesttype + '\'' + ", Options="
				+ (Options == null ? "[]" : Options.toString()) + '}';
	}

	public CommandStream() {
	}

	public CommandStream(int controller_id, int coffee_machine_id, int orderID, String drinkName, String Requesttype) {
		this.controller_id = controller_id;
		this.coffee_machine_id = coffee_machine_id;
		this.orderID = orderID;
		this.DrinkName = drinkName;
		this.Requesttype = Requesttype;
//            this.Options.add(new Option("Cream", 2));
	}

	public List<Java.Data.Recipe> getRecipe() {
		return Recipe;
	}

	public void setRecipe(List<Java.Data.Recipe> recipe) {
		Recipe = recipe;
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
