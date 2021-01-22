package Data.Responses;

public class ControllerResponse extends Response{

    private String errDesc;
    private int errCode;

    public ControllerResponse(int orderId, int status, int errCode) {
        super(orderId, status);
        this.errCode = errCode;
        switch (errCode) {
            case 2:
                this.errDesc = "Out of milk, drink canceled";
                break;
            case 26:
                this.errDesc = "Machine jammed";
                break;
            default:
                break;
        }
    }

    public ControllerResponse(int orderId, int status) {
        super(orderId, status);
        this.errCode = -1;
    }

    public String getErrDesc() {
        return errDesc;
    }

    public void setErrDesc(String errDesc) {
        this.errDesc = errDesc;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }
}
