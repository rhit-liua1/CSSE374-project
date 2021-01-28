package Java.Beans;

import Java.Data.CommandStream;

public class CommandBean{

    private CommandStream commandStream;

    public CommandBean() {
        this.commandStream = new CommandStream();
    }

    @Override
    public String toString() {
        return "CommandBean{" +
                "command=" + commandStream +
                '}';
    }

    public CommandStream getCommand() {
        return commandStream;
    }

    public void setCommand(CommandStream cs) {
        this.commandStream = cs;
    }

}
