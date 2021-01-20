import Model.Address;

public class Controller {
    enum ControllerType {
        SIMPLE,
        ADVANCED
    }

    private Address address;
    private int id;
    private ControllerType type;

    public Controller(Address address, int id, String type) {
        this.address = address;
        this.id = id;
        if (type.isEmpty()) {
            this.type = ControllerType.SIMPLE;
        } else {
            this.type = type.equals("simple") ? ControllerType.SIMPLE : ControllerType.ADVANCED;
        }
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type == ControllerType.SIMPLE ? "simple" : "advanced";
    }

    public void setType(String type) {
        this.type = type.equals("simple") ? ControllerType.SIMPLE : ControllerType.ADVANCED;
    }
}
