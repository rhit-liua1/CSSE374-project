package Java.Data;

public class Option {
    private String Name;
    private int qty;

    @Override
    public String toString() {
        return "Option{" +
                "Name='" + Name + '\'' +
                ", qty=" + qty +
                '}';
    }

    public Option(String name, int qty) {
        this.Name = name;
        this.qty = qty;
    }

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
}
