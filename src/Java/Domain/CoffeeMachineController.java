package Java.Domain;

import Java.Beans.CommandBean;
import Java.Beans.DrinkResponseBean;
import Java.Data.Address;
import Java.Data.Responses.ControllerResponse;
import Java.Domain.Behaviors.OrderCondimentBehavior;
import Java.Domain.Behaviors.OrderDrinkBehavior;
import Java.GsonUtil;

import java.util.ArrayList;
import java.util.Random;

public abstract class CoffeeMachineController {
    protected OrderCondimentBehavior ocb;
    protected OrderDrinkBehavior odb;
    protected int id;
    protected String type;
    protected int status;
    protected Address address;
    protected ArrayList<Order> observers = new ArrayList<>();

    public CoffeeMachineController(int id, String type, int status, Address address) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.address = address;
    }

    public String processCommandStream(String json) {
        try {
        	//command json obj
			CommandBean cb = GsonUtil.parseJsonWithGson(json, CommandBean.class);
			CommandBean.Command cmd = cb.getCommand();
			//drink response bean
			DrinkResponseBean db = new DrinkResponseBean();
			DrinkResponseBean.DrinkResponse dr = db.getDrinkresponse();
			dr.setOrderID(cmd.getOrderID());
			dr.setStatus(this.status);
			if (status == 0) {
				System.out.println("[System] Controller Response received. Coffee machine status=" + status);
				dr.setErrorcode(0);
				dr.setErrordesc("");
			} else {
				int errCode = this.getErrorType();
				System.out.println("[System] Controller Response received. Coffee machine status=" + status + ", Error="
						+ errCode);
				dr.setErrorcode(errCode);
				switch (errCode) {
					case 2:
						dr.setErrordesc("Out of milk, drink canceled");
						break;
					case 26:
						dr.setErrordesc("Machine jammed");
						break;
					default:
						break;
				}
			}
			String drJson = GsonUtil.serializeWithGson(db);
			System.out.println("controller response"+drJson);
			return drJson;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return "";
    }

    public ControllerResponse generateCR(Order order) {
        System.out.println("[System] Sending Order to coffee machine controller no." + this.getId());
        int status = this.getStatus();
        this.addCondiments(order.getCondiments());
        if (status == 0) {
            System.out.println("[System] Controller Response received. Coffee machine status=" + status);
            this.produceDrink(order.getDrink());
            return new ControllerResponse(order.getOrderId(), status);
        } else {
            System.out.println("[System] Controller Response received. Coffee machine status=" + status + ", Error="
                    + this.getErrorType());
            return new ControllerResponse(order.getOrderId(), status, this.getErrorType());
        }
    }

    ArrayList<String> condiments = new ArrayList<>();

    abstract void produceDrink(String drink);

    abstract void addCondiments(String[] condiments);

    public void registerObserver(Order o) {
        this.observers.add(o);
    }

    public void removeObserver(Order o) {
        this.observers.remove(o);
    }

    public void notifyObserver() {
        for (Order order : observers) {
            order.update(this);
        }
    }

    public int getErrorType() {
        Random r = new Random();
        return r.nextInt(26 - 2) > 12 ? 26 : 2;
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

    public void setType(String type) {
        this.type = type;
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

    public ArrayList<Order> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<Order> observers) {
        this.observers = observers;
    }

    public ArrayList<String> getCondiments() {
        return condiments;
    }

    public void setCondiments(ArrayList<String> condiments) {
        this.condiments = condiments;
    }

}
