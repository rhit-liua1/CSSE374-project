package Java.Domain;

import Java.Data.Address;

public class ProgrammableCoffeeMachineController extends CoffeeMachineController{
    public ProgrammableCoffeeMachineController(int id, String type, int status, Address address, Subject subject) {
        super(id, type, status, address, subject);
    }

    public ProgrammableCoffeeMachineController(){}


}
