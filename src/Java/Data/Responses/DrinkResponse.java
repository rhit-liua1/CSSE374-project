package Java.Data.Responses;

public class DrinkResponse {
    int orderID;
    int status;
    String errordesc;
    int errorcode;

    public DrinkResponse() {
    }

    public DrinkResponse(int orderID, int status, String errordesc, int errorcode) {
        this.orderID = orderID;
        this.status = status;
        this.errordesc = errordesc;
        this.errorcode = errorcode;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setErrordesc(String errordesc) {
        this.errordesc = errordesc;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getStatus() {
        return status;
    }

    public String getErrordesc() {
        return errordesc;
    }

    public int getErrorcode() {
        return errorcode;
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
