package Java.Data;

public class Address {
    private String street;
    private int ZIP;

    public Address(String street, int ZIP) {
        this.street = street;
        this.ZIP = ZIP;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZIP() {
        return ZIP;
    }

    public void setZIP(int ZIP) {
        this.ZIP = ZIP;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", ZIP='" + ZIP + '\'' +
                '}';
    }
}
