package seedu.sgsafe.utils.exceptions;

public class InvalidByeCommandException extends InvalidCommandException {

    private static final String ERROR_MESSAGE = "The 'bye' command does not take any arguments.";
    private static final String USAGE = "Usage: bye";
    private static final String TIP = "Simply type 'bye' to exit the application.";

    public InvalidByeCommandException() {
        super(ERROR_MESSAGE, USAGE, TIP);
    }
}
