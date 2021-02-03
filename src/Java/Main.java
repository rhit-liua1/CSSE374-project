package Java;

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
    }
}
