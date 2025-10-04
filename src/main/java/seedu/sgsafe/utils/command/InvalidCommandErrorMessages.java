package seedu.sgsafe.utils.command;

import java.util.Map;

/**
 * Enumerates reusable error message strings for invalid commands in the SGSafe system.
 * Each constant represents a specific error scenario and holds a descriptive message.
 */
enum ErrorText {

    /** Message shown when the command input is empty. */
    EMPTY("The command you have entered is empty."),

    /** Message shown when the command is not recognized. */
    UNKNOWN("The command you have entered is not recognized."),

    /** Message shown when a command has invalid arguments. */
    COMMAND_INVALID_ARGS("You have entered invalid arguments."),

    /** Hint message for correct usage of the {@code list} command. */
    LIST_HINT("The correct format for list is:"),

    /** Example usage of the {@code list} command. */
    LIST_EXAMPLE("list"),

    /** Fallback message for unknown or unhandled error types. */
    DEFAULT("An unknown error occurred.");

    /** The descriptive message associated with the error. */
    final String text;

    /**
     * Constructs an {@code ErrorText} constant with the given message.
     *
     * @param text the descriptive error message
     */
    ErrorText(String text) {
        this.text = text;
    }
}

/**
 * Provides descriptive error messages for each {@link InvalidCommandType}.
 * Maps command error types to one or more human-readable messages using {@link ErrorText}.
 */
public class InvalidCommandErrorMessages {

    /**
     * Maps each {@link InvalidCommandType} to its corresponding error message(s).
     */
    private static final Map<InvalidCommandType, String[]> errorMessages = Map.of(
        InvalidCommandType.EMPTY_COMMAND,
        new String[] { ErrorText.EMPTY.text },

        InvalidCommandType.UNKNOWN_COMMAND,
        new String[] { ErrorText.UNKNOWN.text },

        InvalidCommandType.LIST_COMMAND_INVALID_ARGUMENTS,
        new String[] {
            ErrorText.COMMAND_INVALID_ARGS.text,
            ErrorText.LIST_HINT.text,
            ErrorText.LIST_EXAMPLE.text
        }
    );

    /**
     * Returns the error message(s) associated with the given {@link InvalidCommandType}.
     * If the type is not explicitly mapped, a default fallback message is returned.
     *
     * @param errorType the type of invalid command
     * @return an array of descriptive error messages
     */
    public static String[] getMessage(InvalidCommandType errorType) {
        return errorMessages.getOrDefault(errorType, new String[] { ErrorText.DEFAULT.text });
    }
}
