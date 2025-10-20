package seedu.sgsafe.utils.exceptions;

public class InvalidDeleteIndexException extends InvalidCommandException {
    private static final String ERROR_MESSAGE = "The case number has to be a positive integer.";
    private static final String TIP = "Please format your message as \"delete <case number>\".";
    private static final String EXTRA = "The number you entered could also be greater than the integer maximum.";

    public InvalidDeleteIndexException() {
        super(ERROR_MESSAGE, TIP, EXTRA);
    }
}
