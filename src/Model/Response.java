package Model;

public abstract class Response {

    int orderId;
    int status;

    Response(int orderId, int status){
        this.orderId = orderId;
        this.status = status;
    }
}
