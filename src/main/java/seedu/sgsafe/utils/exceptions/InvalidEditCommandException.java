package seedu.sgsafe.utils.exceptions;

public class InvalidEditCommandException extends InvalidCommandException {
    private static final String ERROR_MESSAGE = "The 'edit' command requires a case ID followed" +
            " by at least one flag and its value.";

    public InvalidEditCommandException() {
        super(ERROR_MESSAGE);
    }
}
