package Domain;

public interface Subject {

    void registerObserver(Order o);
    void removeObserver(Order o);
    void notifyObserver();
}
