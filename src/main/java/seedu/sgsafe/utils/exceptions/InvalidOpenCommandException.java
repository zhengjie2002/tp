package seedu.sgsafe.utils.exceptions;

public class InvalidOpenCommandException extends InvalidCommandException {
    private static final String ERROR_MESSAGE = "Your open command format is incorrect.";
    private static final String CORRECT_FORMAT_MESSAGE = "The correct format for an open command is";
    private static final String EXAMPLE_COMMAND = "open <case ID>";

    public InvalidOpenCommandException() {
        super(ERROR_MESSAGE,
                CORRECT_FORMAT_MESSAGE,
                EXAMPLE_COMMAND);
    }
}
