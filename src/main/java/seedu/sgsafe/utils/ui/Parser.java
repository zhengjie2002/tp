package seedu.sgsafe.utils.ui;

import seedu.sgsafe.utils.command.AddCommand;
import seedu.sgsafe.utils.command.CaseListingMode;
import seedu.sgsafe.utils.command.CloseCommand;
import seedu.sgsafe.utils.command.Command;
import seedu.sgsafe.utils.command.ListCommand;
import seedu.sgsafe.utils.command.EditCommand;
import seedu.sgsafe.utils.command.DeleteCommand;

import seedu.sgsafe.utils.exceptions.DuplicateFlagException;
import seedu.sgsafe.utils.exceptions.EmptyCommandException;
import seedu.sgsafe.utils.exceptions.IncorrectFlagException;
import seedu.sgsafe.utils.exceptions.InputLengthExceededException;
import seedu.sgsafe.utils.exceptions.InvalidCloseCommandException;
import seedu.sgsafe.utils.exceptions.InvalidEditCommandException;
import seedu.sgsafe.utils.exceptions.ListCommandException;
import seedu.sgsafe.utils.exceptions.InvalidAddCommandException;
import seedu.sgsafe.utils.exceptions.UnknownCommandException;

import seedu.sgsafe.utils.exceptions.InvalidDeleteIndexException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.regex.Pattern;

/**
 * Responsible for interpreting raw user input and converting it into structured {@link Command} objects.
 * Acts as the first step in the command execution pipeline by identifying the command type and validating arguments.
 */
public class Parser {

    // Logger for logging parsing activities and errors
    private static final Logger logger = Logger.getLogger(Parser.class.getName());

    // Regular expression used to split input into flags and their values. It retains the delimiter.
    private static final String FLAG_SEPARATOR_REGEX = "\\s+(?=--)";

    // Prefix used to identify flags in the input
    private static final String FLAG_PREFIX = "--";

    // List of valid flags to be taken as input from the user
    private static final List<String> VALID_FLAGS = List.of("title", "date", "info", "victim", "officer");

    // Validator instance for input validation
    private static final Validator validator = new Validator();

    // Maximum allowed length for any input value
    private static final int MAX_INPUT_LENGTH = 5000;

    /**
     * Parses raw user input into a {@link Command} object.
     * <p>
     * This method trims the input, extracts the command keyword, and delegates to specialized parsers
     * based on the keyword. If the input is empty or unrecognized, an appropriate exception is thrown.
     *
     * @param userInput the full input string entered by the user
     * @return a {@link Command} representing the parsed action
     * @throws EmptyCommandException   if the input is empty or contains only whitespace
     * @throws UnknownCommandException if the command keyword is not recognized
     * @throws ListCommandException    if the {@code list} command contains unexpected arguments
     */
    public static Command parseInput(String userInput) {
        userInput = cleanUserInput(userInput);
        String keyword = getKeywordFromUserInput(userInput);
        String remainder = getRemainderFromUserInput(userInput);

        return switch (keyword) {
        case "list" -> parseListCommand(remainder);
        case "add" -> parseAddCommand(remainder);
        case "edit" -> parseEditCommand(remainder);
        case "close" -> parseCloseCommand(remainder);
        case "delete" -> parseDeleteCommand(remainder);
        default -> throw new UnknownCommandException();
        };
    }

    /**
     * Cleans the raw user input by trimming whitespace and validating non-emptiness.
     *
     * @param userInput the raw input string from the user
     * @return a trimmed, non-empty input string
     * @throws EmptyCommandException if the input is empty after trimming
     */
    private static String cleanUserInput(String userInput) {
        userInput = userInput.strip();
        if (userInput.isEmpty()) {
            throw new EmptyCommandException();
        }
        return userInput;
    }

    /**
     * Extracts the command keyword from the user input.
     * <p>
     * The keyword is assumed to be the first word before any space.
     *
     * @param userInput the full input string
     * @return the command keyword (e.g., "add", "list", "edit")
     */
    private static String getKeywordFromUserInput(String userInput) {
        int spaceIndex = userInput.indexOf(" ");
        if (spaceIndex != -1) {
            return userInput.substring(0, spaceIndex).trim();
        } else {
            return userInput;
        }
    }

    /**
     * Extracts the remainder of the input after the command keyword.
     * <p>
     * Used to pass arguments to command-specific parsers.
     *
     * @param userInput the full input string
     * @return the remainder of the input after the keyword, or an empty string if none
     */
    private static String getRemainderFromUserInput(String userInput) {
        int spaceIndex = userInput.indexOf(" ");
        if (spaceIndex != -1 && userInput.length() > spaceIndex + 1) {
            return userInput.substring(spaceIndex + 1).trim();
        } else {
            return "";
        }
    }

    /**
     * Parses the {@code list} command and validates its arguments.
     * <p>
     * If the remainder of the input is non-empty, the command is considered invalid.
     *
     * @param remainder the portion of the input following the {@code list} keyword
     * @return a valid {@link ListCommand} if no arguments are present
     * @throws ListCommandException if unexpected arguments are provided
     */
    private static Command parseListCommand(String remainder) {
        if (!remainder.isEmpty()) {
            throw new ListCommandException();
        }
        return new ListCommand(CaseListingMode.DEFAULT);
    }

    /**
     * Parses the {@code add} command and validates its arguments.
     * <p>
     * This method extracts flags and their values from the input, ensuring that required fields
     * (title, date, and info) are present.
     *
     * @param remainder the portion of the input following the {@code add} keyword
     * @return a valid {@link AddCommand} if arguments are invalid
     */
    private static Command parseAddCommand(String remainder) {
        List<String> requiredFlags = List.of("title", "date", "info");

        if (validator.inputIsEmpty(remainder)) {
            throw new InvalidAddCommandException();
        }

        Map<String, String> flagValues = extractFlagValues(remainder);

        if (!validator.haveAllRequiredFlags(flagValues, requiredFlags) ||
                !validator.haveValidFlags(flagValues, VALID_FLAGS)) {
            throw new InvalidAddCommandException();
        }

        return new AddCommand(flagValues.get("title"), flagValues.get("date"), flagValues.get("info"),
                flagValues.get("victim"), flagValues.get("officer"));
    }

    private static Command parseCloseCommand(String remainder) {
        if (validator.inputIsEmpty(remainder)) {
            throw new InvalidCloseCommandException();
        }
        try {
            int caseNumber = Integer.parseInt(remainder);
            return new CloseCommand(caseNumber);
        } catch (NumberFormatException e) {
            throw new InvalidCloseCommandException();
        }
    }

    /**
     * Extracts flags and their corresponding values from the input string.
     * <p>
     * The input is split based on the defined flag separator regex, and each part is processed
     * to isolate the flag name and its value. The results are stored in a map.
     *
     * @param input the portion of the input containing flags and their values
     * @return a map of flag names with their corresponding values
     * @throws DuplicateFlagException if a flag appears more than once in the input
     * @throws IncorrectFlagException if a flag is malformed or missing its value
     */
    private static Map<String, String> extractFlagValues(String input) {

        String[] parts = input.split(FLAG_SEPARATOR_REGEX);
        Map<String, String> flagValues = new HashMap<>();

        for (String part : parts) {

            // First, the prefix -- is removed.
            String trimmedPart = part.replaceFirst(FLAG_PREFIX, "").trim();
            if (trimmedPart.isEmpty()) {
                logger.log(Level.WARNING, "Incorrect flag usage detected");
                throw new IncorrectFlagException();
            }

            int spaceIndex = trimmedPart.indexOf(" ");
            if (spaceIndex == -1) {
                logger.log(Level.WARNING, "Incorrect flag usage detected");
                throw new IncorrectFlagException();
            }

            // Then we separate the flag from its value.
            String flag = trimmedPart.substring(0, spaceIndex).trim();
            String value = trimmedPart.substring(spaceIndex + 1).trim();

            if(value.length() > MAX_INPUT_LENGTH){
                logger.log(Level.WARNING, "Input exceeds character limit");
                throw new InputLengthExceededException();
            }

            if (flagValues.containsKey(flag)) {
                logger.log(Level.WARNING, "Duplicated flags detected");
                throw new DuplicateFlagException();
            }

            // Finally, we store the flag and its value in the map.
            flagValues.put(flag, value);
        }
        return flagValues;
    }

    /**
     * Parses the 'edit' command input, validates its format, and constructs an EditCommand object.
     * Throws an InvalidEditCommandException if the input is missing, incorrectly formatted, or contains invalid flags.
     */
    private static Command parseEditCommand(String remainder) {
        if (remainder.isEmpty() || !isValidEditCommandInput(remainder)) {
            throw new InvalidEditCommandException("The 'edit' command requires a case number, " +
                    "followed by at least one flag and its value.");
        }

        int firstSpaceIndex = remainder.indexOf(" ");
        if (firstSpaceIndex == -1) {
            throw new InvalidEditCommandException("Missing case number or flags in 'edit' command.");
        }

        String caseNumberString = remainder.substring(0, firstSpaceIndex);
        int caseNumberInteger = Integer.parseInt(caseNumberString);
        String replacements = remainder.substring(firstSpaceIndex + 1).trim();

        Map<String, String> flagValues = extractFlagValues(replacements);

        if (flagValues == null) {
            throw new InvalidEditCommandException("The 'edit' command requires at least one flag " +
                    "and every flag's corresponding value.");
        }

        for (String flag : flagValues.keySet()) {
            if (!VALID_FLAGS.contains(flag)) {
                throw new InvalidEditCommandException("The flag '" + flag + "' is not recognized.");
            }
        }

        return new EditCommand(caseNumberInteger, flagValues);
    }

    /**
     * Checks whether the provided input string matches the valid format for an 'edit' command.
     * The valid format must begin with a case number followed by one or more flags and their values.
     *
     * @param input the user input string to validate
     * @return true if the input matches the required format, false otherwise
     */
    public static boolean isValidEditCommandInput(String input) {
        final String inputPattern = "^\\d+\\s+(--\\s*\\w+(?:\\s+\\S+)+)(?:\\s+--\\s*\\w+(?:\\s+\\S+)+)*$";
        return Pattern.matches(inputPattern, input.strip());
    }

    /**
     * Parses the 'edit' command input, validates its format, and constructs an EditCommand object.
     * Throws an InvalidDeleteIndexException if the input is missing or incorrectly formatted.
     */
    private static Command parseDeleteCommand(String remainder) {
        if (remainder.isEmpty() || !validator.isNumeric(remainder)) {
            throw new InvalidDeleteIndexException();
        }
        return new DeleteCommand(Integer.parseInt(remainder));
    }
}
