package Java.Beans;

import Java.Data.Responses.DrinkResponse;

public class DrinkResponseBean {

    DrinkResponse drinkresponse;

    public DrinkResponseBean() {
        this.drinkresponse = new DrinkResponse();
    }

    public DrinkResponse getDrinkResponse() {
        return drinkresponse;
    }

    public void setDrinkresponse(DrinkResponse dr) {
        this.drinkresponse = dr;
    }

    @Override
    public String toString() {
        return "DrinkResponseBean{" +
                "drinkresponse=" + drinkresponse +
                '}';
    }

}
