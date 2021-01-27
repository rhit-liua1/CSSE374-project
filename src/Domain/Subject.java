package Domain;

public interface Subject {

	void registerObserver(CoffeeMachineController cm);

	void removeObserver(CoffeeMachineController cm);

	void notifyObserver();
}
