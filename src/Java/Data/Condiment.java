package Java.Data;

public class Condiment {

    private int condimentId;
    private String name;

    public Condiment(int condimentId, String name) {
        this.condimentId = condimentId;
        this.name = name;
    }

    public void setCondimentId(int condimentId) {
        this.condimentId = condimentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCondimentId() {
        return condimentId;
    }

    public String getName() {
        return name;
    }
}
