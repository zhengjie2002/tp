package seedu.sgsafe.utils.exceptions;

public class InvalidReadCommandException extends InvalidCommandException {
    private static final String ERROR_MESSAGE = "Your read command format is incorrect.";
    private static final String CORRECT_FORMAT_MESSAGE = "The correct format for a close command is";
    private static final String EXAMPLE_COMMAND = "read <case ID>";

    public InvalidReadCommandException() {
        super(ERROR_MESSAGE,
                CORRECT_FORMAT_MESSAGE,
                EXAMPLE_COMMAND);
    }
}
