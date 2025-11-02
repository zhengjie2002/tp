package seedu.sgsafe.utils.exceptions;

public class InvalidInputException extends InvalidCommandException {

    private static final String ERROR_MESSAGE = "Sorry, only ASCII printable characters are allowed.";

    public InvalidInputException() {
        super(ERROR_MESSAGE);
    }
}
