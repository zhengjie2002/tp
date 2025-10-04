package seedu.sgsafe.utils.command;

public abstract class Command {
    public CommandType getType() {
        return CommandType.UNKNOWN;
    }

    public String[] getErrorMessage() {
        return null;
    }
}
