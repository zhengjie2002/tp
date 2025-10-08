package seedu.sgsafe.utils.exceptions;

public class EmptyCommandException extends InvalidCommandException {
    public EmptyCommandException() {
        super("The command you have entered is empty.");
    }
}
