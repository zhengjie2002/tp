package seedu.sgsafe.utils.exceptions;

import seedu.sgsafe.utils.settings.Settings;

public class InvalidDateInputException extends InvalidCommandException {
    private static final String ERROR_MESSAGE = "The date input format is invalid.";
    private static final String INSTRUCTION =
            "Please enter the date in the format: " + Settings.getInputDateFormat() + ".";
    private static final String TIP2 = "You may change this in the settings.";

    public InvalidDateInputException() {
        super(ERROR_MESSAGE, INSTRUCTION, TIP2);
    }
}
