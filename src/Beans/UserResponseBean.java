package Beans;

public class UserResponseBean {

    UserResponse user_response;

    public UserResponseBean() {
        this.user_response = new UserResponse(1,1,0,"Your coffee has been prepared with your desired options.","");
    }

    @Override
    public String toString() {
        return "UserResponseBean{" +
                "user_response=" + user_response +
                '}';
    }

    public static class UserResponse{
        int orderID;
        int coffee_machine_id;
        int status;
        String status_message;
        String error_message;

        public UserResponse(int orderID, int coffee_machine_id, int status, String status_message, String error_message) {
            this.orderID = orderID;
            this.coffee_machine_id = coffee_machine_id;
            this.status = status;
            this.status_message = status_message;
            this.error_message = error_message;
        }

        @Override
        public String toString() {
            return "UserResponse{" +
                    "orderID=" + orderID +
                    ", coffee_machine_id=" + coffee_machine_id +
                    ", status=" + status +
                    ", status_message='" + status_message + '\'' +
                    ", error_message='" + error_message + '\'' +
                    '}';
        }
    }
}
