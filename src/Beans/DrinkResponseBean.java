package Beans;

public class DrinkResponseBean {

    DrinkResponse drinkresponse;

    public DrinkResponseBean() {
        this.drinkresponse = new DrinkResponse(1,0,"out of milk",2);
    }

    @Override
    public String toString() {
        return "DrinkResponseBean{" +
                "drinkresponse=" + drinkresponse +
                '}';
    }

    public static class DrinkResponse{
        int orderID;
        int status;
        String errordesc;
        int errorcode;

        public DrinkResponse(int orderID, int status, String errordesc, int errorcode) {
            this.orderID = orderID;
            this.status = status;
            this.errordesc = errordesc;
            this.errorcode = errorcode;
        }

        @Override
        public String toString() {
            return "DrinkResponse{" +
                    "orderID=" + orderID +
                    ", status=" + status +
                    ", errordesc='" + errordesc + '\'' +
                    ", errorcode=" + errorcode +
                    '}';
        }
    }
}
