package seedu.sgsafe.utils.exceptions;

public class InvalidCaseIdException extends InvalidCommandException {
    private static final String ERROR_MESSAGE = "The case ID does not exist.";
    private static final String TIP = "Please enter a valid case ID.";

    public InvalidCaseIdException() {
        super(ERROR_MESSAGE,
                TIP);
    }
}
