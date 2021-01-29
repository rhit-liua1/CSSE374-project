package Java.Domain;

import Java.Data.CommandStream;
import Java.Beans.CommandBean;
import Java.Data.Condiment;
import Java.Data.Responses.DrinkResponse;
import Java.Beans.DrinkResponseBean;
import Java.Data.Address;
import Java.Domain.Behaviors.OrderCondimentBehavior;
import Java.Domain.Behaviors.OrderDrinkBehavior;
import Java.GsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class CoffeeMachineController implements Observer{
    protected OrderCondimentBehavior ocb;
    protected OrderDrinkBehavior odb;
    protected int id;
    protected String type;
    protected int status;
    protected Address address;

    public CoffeeMachineController(int id, String type, int status, Address address, Subject subject) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.address = address;
        subject.registerObserver(this);
    }

    @Override
    public void update(String command, OrderManager orderManager) {
        String drResponse = processCommandStream(command);
        orderManager.setResponse(drResponse);
    }

    public String processCommandStream(String commandStreamJson) {
        try {
        	//receive command json obj
			CommandBean cb = GsonUtil.parseJsonWithGson(commandStreamJson, CommandBean.class);
			CommandStream cmd = cb.getCommand();

			String drJson = generateDRJson(cmd);
			return drJson;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return "";
    }

    private String generateDRJson(CommandStream cmd) {
        //create new drink response
        DrinkResponseBean db = new DrinkResponseBean();
        DrinkResponse dr = db.getDrinkResponse();
        dr.setOrderID(cmd.getOrderID());
        dr.setStatus(this.status);
        if (status == 0) {
            System.out.println("[CoffeeMachine] Coffee machine status = " + status);
            dr.setErrorcode(0);
            dr.setErrordesc("");
        } else {
            int errCode = this.getErrorType();
            System.out.println("[CoffeeMachine] Coffee machine status = " + status + ", Error = " + errCode);
            dr.setErrorcode(errCode);
            dr.setErrordesc(this.getErrorMsg(errCode));
        }
        db.setDrinkresponse(dr);
        String drJson = GsonUtil.serializeWithGson(db);
        System.out.println("[CoffeeMachine] Sending back drink-response JSON object: \n" + drJson + "\n");
        return drJson;
    }
//    public DrinkResponse generateDR(Order order) {
//        System.out.println("[CoffeeMachine] Order No." + order.getOrderId() + " received by CoffeeMachine No." + this.getId());
//        int status = this.getStatus();
//        this.addCondiments(order.getCondiments());
//        if (status == 0) {
//            System.out.println("[CoffeeMachine] Coffee machine status=" + status);
//            this.produceDrink(order.getDrink());
//            return new DrinkResponse(order.getOrderId(), status);
//        } else {
//            System.out.println("[System] Coffee machine status=" + status + ", Error="
//                    + this.getErrorType());
//            return new DrinkResponse(order.getOrderId(), status, this.getErrorType());
//        }
//    }

    ArrayList<String> condiments = new ArrayList<>();

    public void produceDrink(String drink) {
        if (this.status == 1) {
            int errCode = this.getErrorType();
            System.out.println("[CoffeeMachine] Error. Cannot produce drink. errorCode = " + errCode + " errorMessage = " + this.getErrorMsg(errCode));
            return;
        }
        this.odb.produceDrink(drink);
    };

    public void addCondiments(List<Condiment> condiments) {
        if (this.status == 1) {
//            int errCode = this.getErrorType();
//            System.out.println("[CoffeeMachine] Error. Cannot add condiments. errorCode = " + errCode + " errorMessage = " + this.getErrorMsg(errCode));
            return;
        }
        this.ocb.addCondiments(condiments);
    };

    @Override
    public String toString() {
        return "CoffeeMachineController{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", status=" + status +
                ", address=" + address +
                '}';
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

    public ArrayList<String> getCondiments() {
        return condiments;
    }

    public void setCondiments(ArrayList<String> condiments) {
        this.condiments = condiments;
    }

}
