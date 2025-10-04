package seedu.sgsafe.utils.ui;
import seedu.sgsafe.utils.command.CaseListingMode;
import seedu.sgsafe.utils.command.Command;
import seedu.sgsafe.utils.command.InvalidCommand;
import seedu.sgsafe.utils.command.InvalidCommandType;
import seedu.sgsafe.utils.command.ListCommand;

/**
 * Parses user input strings into structured {@code Command} objects.
 */
public class Parser {

    /**
     * Parses raw user input into a {@code Command} object.
     * Determines the command type based on the first keyword and delegates to specialized parsers.
     *
     * @param userInput the full input string entered by the user
     * @return a {@code Command} object representing the parsed action
     */
    public static Command parseInput(String userInput) {
        // Handles empty command case
        userInput = userInput.strip();
        if (userInput.isEmpty()) {
            return new InvalidCommand(InvalidCommandType.EMPTY_COMMAND);
        }

        // Split user input into keyword & remainder
        int spaceIndex = userInput.indexOf(" ");
        String keyword = userInput;
        if (spaceIndex != -1) {
            keyword = userInput.substring(0, spaceIndex).trim();
        }
        String remainder = "";
        if (spaceIndex != -1 && userInput.length() > spaceIndex + 1) {
            remainder = userInput.substring(spaceIndex + 1).trim();
        }

        // Parses and returns the appropriate command
        return switch (keyword) {
        case "list" -> parseListCommand(remainder);
        default -> new InvalidCommand(InvalidCommandType.UNKNOWN_COMMAND);
        };
    }

    private static Command parseListCommand(String remainder) {
        if (!remainder.isEmpty()) {
            return new InvalidCommand(InvalidCommandType.LIST_COMMAND_INVALID_ARGUMENTS);
        }
        return new ListCommand(CaseListingMode.ALL);
    }
}
