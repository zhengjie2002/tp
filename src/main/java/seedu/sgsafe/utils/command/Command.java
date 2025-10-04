package seedu.sgsafe.utils.command;

public abstract class Command {
    protected CommandType commandType = CommandType.UNKNOWN;

    public CommandType getCommandType() {
        return commandType;
    }

    public String[] getErrorMessage() {
        return null;
    }
}
