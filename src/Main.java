import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("[Main] Coffee Server program starts.");

        System.out.println(getErrorType());
        //Server server = new Server();
        //server.run();
        System.out.println("[Main] Exiting server....");
    }
    public static int getErrorType() {
        Random r = new Random();
        return r.nextInt(26-2) > 12 ? 26 : 2;
    }
}
