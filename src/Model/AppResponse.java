package Model;

public class AppResponse extends Response{

    private int machineId;
    private String statusMsg;
    private String errMsg;
    private final static String CANCELLED = "Your coffee order has been cancelled";
    private final static String PREPARED = "Your coffee has been prepared with your desired options";

    public AppResponse(int orderId, int status, int machineId, String errMsg) {
        super(orderId, status);
        this.machineId = machineId;
        if (status == 0){
            this.statusMsg = PREPARED;
            this.errMsg = "";
        } else {
            this.statusMsg = CANCELLED;
            this.errMsg = errMsg;
        }
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public static String getCANCELLED() {
        return CANCELLED;
    }

    public static String getPREPARED() {
        return PREPARED;
    }
}
