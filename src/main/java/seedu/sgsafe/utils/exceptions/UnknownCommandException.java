package seedu.sgsafe.utils.exceptions;

public class UnknownCommandException extends InvalidCommandException {
    private static final String STARTING_MESSAGE = "The command '";
    private static final String ERROR_MESSAGE = "' that you have entered is not recognised.";
    private static final String COMMAND_ENTERED = "You have entered: ";

    public UnknownCommandException(String commandEntered, String commandWord) {
        super(COMMAND_ENTERED +
                commandEntered, STARTING_MESSAGE +
                commandWord +
                ERROR_MESSAGE);
    }
}
