package Java.Domain;

public interface Observer {

    void update(String command, OrderManager orderManager, int id);
}
