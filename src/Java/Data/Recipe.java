package Java.Data;

public class Recipe {

    private String commandstep;
    private String object;

    public Recipe(String commandstep, String object) {
        this.commandstep = commandstep;
        this.object = object;
    }

    public String getCommandstep() {
        return commandstep;
    }

    public void setCommandstep(String commandstep) {
        this.commandstep = commandstep;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
