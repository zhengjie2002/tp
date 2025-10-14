package seedu.sgsafe.utils.exceptions;

public class InvalidAddCommandException extends InvalidCommandException {
    private static final String ERROR_MESSAGE = "Your add command format is incorrect.";
    private static final String CORRECT_FORMAT_MESSAGE = "The correct format for a add command is";
    private static final String EXAMPLE_COMMAND =
            "add --title TITLE --info INFO --date DATE [--victim VICTIM] [--officer OFFICER]";
    private static final String TIP = "The items in [ ] are optional.";

    public InvalidAddCommandException() {
        super(ERROR_MESSAGE, CORRECT_FORMAT_MESSAGE, EXAMPLE_COMMAND, TIP);
    }
}
