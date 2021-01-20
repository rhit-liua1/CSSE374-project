import java.util.Scanner;

public class Server {

    private boolean isServerOn;

    public Server() {
        isServerOn = true;

    }

    private void run() {
        System.out.println("[Server] Welcome to the Game Server!");
        Scanner commandScanner = new Scanner(System.in);
        int globalPlayerIdCounter = 0;
        while(isServerOn){
            System.out.println("[Server] command list: \n" +
                    "> shutdown -- will shut down the server;\n" +
                    "> newplayer -- will create a new player and game;\n" +
                    "> pushnews -- will ask for the news to broadcast and then push to all active games;\n" +
                    "> bonustries -- will ask for the number of bonus tries and then push to all active games.\n" +
                    "Now, please enter a server command: ");
            String command = commandScanner.nextLine();

            switch(command){  // pave future stories for command pattern
                case "shutdown":
                    System.out.println("[Server] Command received: shutdown");
                    System.out.println("[Server] Shutdown received...server shut down. Bye.");
                    isServerOn = false;

                    break;
                case "newplayer":
                    System.out.println("[Server] Command received: newplayer");
//                    Player player = new Player(globalPlayerIdCounter);
//                    GameClient gameClient = new GameClient(player);
//                    globalPlayerIdCounter += 1;
//                    Thread t = new Thread(gameClient);
//                    t.start();
                    break;
                case "pushnews":
                    System.out.println("[Server] Command received: pushnews");
                    // taking in news
                    System.out.println("[Server] Please enter the news to be broadcast.");
//                    this.state.setLastNewsBroadcast(commandScanner.nextLine()) ;
                    // notify, TODO in Part 2
                    break;
                case "bonustries":
                    System.out.println("[Server] Command received: bonustries");
                    // taking in the number of tries added to all
                    System.out.println("[Server] Please enter the number of bonus tries.");
//                    this.state.setBonusTries(commandScanner.nextInt()) ;
                    // notify, TODO in Part 2
                    break;
                default:
                    System.out.println("[Server] Command not recognized.");
                    break;
            }
        }
        commandScanner.close();
    }
}
