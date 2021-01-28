package Java;

import Java.Beans.CommandBean;
import Java.Beans.DrinkResponseBean;
import Java.Beans.OrderBean;
import Java.Beans.UserResponseBean;
import Java.Server.Server;

//import static sun.net.www.protocol.http.AuthCacheValue.Type.Server;

public class Main {
    public static void main(String[] args) {
        System.out.println("");
        System.out.println("");
        System.out.println("[Main] Coffee Presentation.Server program starts.");
        Server server = new Server();
        server.run();
        System.out.println("[Main] Exiting server....");

//
//
//        String json2 = "{\"command\":{\"controller_id\":2,\"coffee_machine_id\":1,\"orderID\":1,\"DrinkName\":\"Americano\",\"Requesttype\":\"Automated\",\"Options\":[{\"Name\":\"Cream\",\"qty\":2}]}}";
//        String json3 = "{\"command\":{\"controller_id\":2,\"coffee_machine_id\":1,\"orderID\":1,\"DrinkName\":\"Americano\",\"Requesttype\":\"Automated\"}}";

//        System.out.println(json1);
//        System.out.println(json2);
//        System.out.println(json3);
//
//        CommandBean cb = GsonUtil.parseJsonWithGson(json2, CommandBean.class);
//        System.out.println(cb.toString());
//        CommandBean cb1 = GsonUtil.parseJsonWithGson(json3, CommandBean.class);
//        System.out.println(cb1.toString());
//
//        System.out.println(GsonUtil.deserializeWithGson(cb));
//



//        String j1 = "{\"user_response\":{\"orderID\":1,\"coffee_machine_id\":1,\"status\":0,\"status_message\":\"Your coffee has been prepared with your desired options.\"}}";
//        System.out.println(j1);
//
//        String j2 = GsonUtil.deserializeWithGson(new UserResponseBean());
//        System.out.println(j2);
//
//        UserResponseBean ur = GsonUtil.parseJsonWithGson(j1,UserResponseBean.class);
//        System.out.println(ur.toString());



//        String j1 = GsonUtil.deserializeWithGson(new DrinkResponseBean());
//        System.out.println(j1);
//
//        DrinkResponseBean db = GsonUtil.parseJsonWithGson(j1, DrinkResponseBean.class);
//        System.out.println(db.toString());


        String j1 = GsonUtil.deserializeWithGson(new OrderBean());
        System.out.println(j1);

        OrderBean ob = GsonUtil.parseJsonWithGson(j1, OrderBean.class);
        System.out.println(ob.toString());
    }
}
