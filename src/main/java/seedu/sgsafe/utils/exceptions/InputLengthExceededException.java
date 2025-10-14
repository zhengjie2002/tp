package seedu.sgsafe.utils.exceptions;

public class InputLengthExceededException extends InvalidCommandException {
    private static final String ERROR_MESSAGE = "You have exceeded the input length.";
    private static final String USAGE = "Only 5000 characters are allowed for each field.";

    public InputLengthExceededException() {
        super(ERROR_MESSAGE, USAGE);
    }
}
