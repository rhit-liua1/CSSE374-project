public class Main {
    public static void main(String[] args) {
        System.out.println("[Main] Coffee Server program starts.");
        Server server = new Server();
        server.run();
        System.out.println("[Main] Exiting server....");
    }
}
