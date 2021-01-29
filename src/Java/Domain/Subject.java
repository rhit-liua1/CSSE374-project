package Java.Domain;

public interface Subject {

	void registerObserver(Observer observer);

	void removeObserver(Observer observer);

	void notifyObserver(String Command, int id);
}
