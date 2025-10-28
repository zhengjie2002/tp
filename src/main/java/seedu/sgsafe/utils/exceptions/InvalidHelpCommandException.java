package seedu.sgsafe.utils.exceptions;

public class InvalidHelpCommandException extends InvalidCommandException {

    private static final String ERROR_MESSAGE = "The 'help' command does not take any arguments.";
    private static final String USAGE = "Usage: help";
    private static final String TIP = "Simply type 'help' to display the help manual.";

    public InvalidHelpCommandException() {
        super(ERROR_MESSAGE, USAGE, TIP);
    }
}
