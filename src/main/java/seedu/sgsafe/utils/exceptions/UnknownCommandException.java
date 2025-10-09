package seedu.sgsafe.utils.exceptions;

public class UnknownCommandException extends InvalidCommandException {
    public UnknownCommandException() {
        super("The command you have entered is not recognized.");
    }
}
