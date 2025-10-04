package seedu.sgsafe.utils.command;

import java.util.Map;

/**
 * Provides descriptive error messages for each {@link InvalidCommandType}.
 */
public class InvalidCommandErrorMessages {

    private static final Map<InvalidCommandType, String[]> errorMessages = Map.of(
        InvalidCommandType.EMPTY_COMMAND,
        new String[] { "The command you have entered is empty." },

        InvalidCommandType.UNKNOWN_COMMAND,
        new String[] {
            "The command you have entered is not recognized."
        },

        InvalidCommandType.LIST_COMMAND_INVALID_ARGUMENTS,
        new String[] {
            "You have entered too many arguments.",
            "The correct format for list is:",
            "list"
        }
    );

    /**
     * Returns the error message corresponding to the given error type.
     *
     * @param errorType the type of error
     * @return a descriptive error message
     */
    public static String[] getMessage(InvalidCommandType errorType) {
        return errorMessages.getOrDefault(errorType, new String[] {"An unknown error occurred."});
    }
}
