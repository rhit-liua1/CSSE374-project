package Java.Beans;

import Java.Data.Responses.UserResponse;

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

}
