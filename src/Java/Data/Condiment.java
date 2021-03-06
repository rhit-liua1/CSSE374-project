package Java.Data;

public class Condiment {
    private String Name;
    private int qty;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Condiment(String name, int qty) {
        Name = name;
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Condiment{" +
                "Name='" + Name + '\'' +
                ", qty=" + qty +
                '}';
    }
}
