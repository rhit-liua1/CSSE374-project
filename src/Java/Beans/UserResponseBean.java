package Java.Beans;

public class UserResponseBean {

    UserResponse user_response;

    public UserResponseBean() {
        this.user_response = new UserResponse();
    }

    public UserResponse getUser_response() {
        return user_response;
    }

    public void setUser_response(UserResponse user_response) {
        this.user_response = user_response;
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

        public UserResponse() {};

        public UserResponse(int orderID, int coffee_machine_id, int status, String status_message, String error_message) {
            this.orderID = orderID;
            this.coffee_machine_id = coffee_machine_id;
            this.status = status;
            this.status_message = status_message;
            this.error_message = error_message;
        }

        public int getOrderID() {
            return orderID;
        }

        public void setOrderID(int orderID) {
            this.orderID = orderID;
        }

        public int getCoffee_machine_id() {
            return coffee_machine_id;
        }

        public void setCoffee_machine_id(int coffee_machine_id) {
            this.coffee_machine_id = coffee_machine_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatus_message() {
            return status_message;
        }

        public void setStatus_message(String status_message) {
            this.status_message = status_message;
        }

        public String getError_message() {
            return error_message;
        }

        public void setError_message(String error_message) {
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
