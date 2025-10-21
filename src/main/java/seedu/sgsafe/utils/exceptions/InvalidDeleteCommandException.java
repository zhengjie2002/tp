package seedu.sgsafe.utils.exceptions;

public class InvalidDeleteCommandException extends InvalidCommandException {
    private static final String ERROR_MESSAGE = "The case ID does not exist or your command format is wrong.";
    private static final String TIP = "Please format your message as \"delete <case ID>\".";
    private static final String EXAMPLE = "Example: \"delete 00af08\"";

    public InvalidDeleteCommandException() {
        super(ERROR_MESSAGE, TIP, EXAMPLE);
    }
}
