package seedu.sgsafe.utils.exceptions;

public class InvalidCaseIdException extends InvalidCommandException {
    private static final String ERROR_MESSAGE = "The case ID format is incorrect.";
    private static final String TIP = "Case ID should be exactly 6 characters of 0-9 or A-F.";

    public InvalidCaseIdException() {
        super(ERROR_MESSAGE,
                TIP);
    }
}
