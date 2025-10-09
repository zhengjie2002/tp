package seedu.sgsafe.utils.exceptions;

public class DuplicateFlagException extends InvalidCommandException {
    private static final String ERROR_MESSAGE = "Your command format is incorrect.";
    private static final String TIP = "There is a duplicate flag.";

    public DuplicateFlagException() {
        super(ERROR_MESSAGE,
                TIP);
    }
}
