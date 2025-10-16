package seedu.sgsafe.utils.exceptions;

public class UnknownCommandException extends InvalidCommandException {
    private static final String STARTING_MESSAGE = "The command '";
    private static final String ERROR_MESSAGE = "' that you have entered is not recognised.";

    public UnknownCommandException(String commandWord) {
        super(STARTING_MESSAGE + commandWord + ERROR_MESSAGE);
    }
}
