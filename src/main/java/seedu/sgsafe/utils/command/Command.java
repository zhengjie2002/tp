package seedu.sgsafe.utils.command;

public abstract class Command {
    protected CommandType commandType;

    public CommandType getCommandType() {
        return commandType;
    }

    public String[] getErrorMessage() {
        return null;
    }

    public void execute(){
        // to be overridden by subclasses
    };
}
