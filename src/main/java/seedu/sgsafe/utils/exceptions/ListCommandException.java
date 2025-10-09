package seedu.sgsafe.utils.exceptions;

public class ListCommandException extends InvalidCommandException {
    private static final String ERROR_MESSAGE = "Your list command format is incorrect.";
    private static final String CORRECT_FORMAT_MESSAGE = "The correct format for a list command is";
    private static final String EXAMPLE_COMMAND = "list";

    public ListCommandException() {
        super(ERROR_MESSAGE,
                CORRECT_FORMAT_MESSAGE,
                EXAMPLE_COMMAND);
    }
}
