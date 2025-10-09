package seedu.sgsafe.utils.exceptions;

public class ListCommandException extends InvalidCommandException {
    static private final String ERROR_MESSAGE = "Your list command format is incorrect.";
    static private final String CORRECT_FORMAT_MESSAGE = "The correct format for a list command is";
    static private final String EXAMPLE_COMMAND = "list";

    public ListCommandException() {
        super(ERROR_MESSAGE,
                CORRECT_FORMAT_MESSAGE,
                EXAMPLE_COMMAND);
    }
}
