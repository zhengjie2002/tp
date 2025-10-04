package seedu.sgsafe.utils.ui;

import seedu.sgsafe.utils.command.CaseListingMode;
import seedu.sgsafe.utils.command.Command;
import seedu.sgsafe.utils.command.InvalidCommand;
import seedu.sgsafe.utils.command.InvalidCommandType;
import seedu.sgsafe.utils.command.ListCommand;

/**
 * Responsible for interpreting raw user input and converting it into structured {@link Command} objects.
 * Acts as the first step in the command execution pipeline by identifying the command type and validating arguments.
 */
public class Parser {

    /**
     * Parses raw user input into a {@link Command} object.
     * <p>
     * This method trims the input, extracts the command keyword, and delegates to specialized parsers
     * based on the keyword. If the input is empty or unrecognized, an {@link InvalidCommand} is returned.
     *
     * @param userInput the full input string entered by the user
     * @return a {@link Command} representing the parsed action, or an {@link InvalidCommand} if parsing fails
     */
    public static Command parseInput(String userInput) {
        userInput = userInput.strip();
        if (userInput.isEmpty()) {
            return new InvalidCommand(InvalidCommandType.EMPTY_COMMAND);
        }

        int spaceIndex = userInput.indexOf(" ");
        String keyword = userInput;
        if (spaceIndex != -1) {
            keyword = userInput.substring(0, spaceIndex).trim();
        }

        String remainder = "";
        if (spaceIndex != -1 && userInput.length() > spaceIndex + 1) {
            remainder = userInput.substring(spaceIndex + 1).trim();
        }

        return switch (keyword) {
            case "list" -> parseListCommand(remainder);
            default -> new InvalidCommand(InvalidCommandType.UNKNOWN_COMMAND);
        };
    }

    /**
     * Parses the {@code list} command and validates its arguments.
     * <p>
     * If the remainder of the input is non-empty, the command is considered invalid.
     * Otherwise, a {@link ListCommand} is returned with {@link CaseListingMode#ALL}.
     *
     * @param remainder the portion of the input following the {@code list} keyword
     * @return a valid {@link ListCommand} or an {@link InvalidCommand} if arguments are invalid
     */
    private static Command parseListCommand(String remainder) {
        if (!remainder.isEmpty()) {
            return new InvalidCommand(InvalidCommandType.LIST_COMMAND_INVALID_ARGUMENTS);
        }
        return new ListCommand(CaseListingMode.ALL);
    }
}