package seedu.sgsafe.utils.exceptions;

public class InvalidFormatStringException extends InvalidCommandException {
    private static final String ERROR_MESSAGE = "There are unknown or invalid characters in your format string.";
    private static final String TIP = "Please refer to the documentation for valid format patterns.";

    public InvalidFormatStringException() {
        super(ERROR_MESSAGE, TIP);
    }
}
