package seedu.sgsafe.utils.ui;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.regex.Pattern;

/**
 * The `Validator` class provides utility methods for validating flags and inputs.
 * It includes methods to check if flags are valid, if required flags are present,
 * and if a given input is empty.
 */
public class Validator {

    // Logger for logging validation events
    private static final Logger logger = Logger.getLogger(Validator.class.getName());
    // Constant for case ID validation pattern
    private static final String CASE_ID_REGEX = "^[0-9A-Fa-f]{6}$";

    // Pattern to validate date time format strings
    private static final Pattern VALID_FORMAT_PATTERN =
            Pattern.compile("^[yMdHmsESaGwWDFkKhzZXx'\\-/.,: ]+$");

    // Regex pattern for ASCII printable characters (English characters)
    private static final String ASCII_PRINTABLE_REGEX = "[ -~\\s]*";

    //@@author shennontay

    /**
     * Checks if all the flags in the provided map are within the list of valid flags.
     *
     * @param flagValues A map containing flag names as keys and their values as values.
     * @param validFlags A list of valid flag names.
     * @return {@code true} if all flags are valid, {@code false} otherwise.
     */
    public Boolean haveValidFlags(Map<String, String> flagValues, List<String> validFlags) {
        for (String flag : flagValues.keySet()) {
            if (!validFlags.contains(flag)) {
                logger.log(Level.WARNING, "Flags provided are invalid for the command.");
                return false;
            }
        }
        return true;
    }

    //@@author zhengjie2002

    /**
     * Checks if all the required flags are present in the provided map.
     *
     * @param flagValues    A map containing flag names as keys and their values as values.
     * @param requiredFlags A list of required flag names.
     * @return {@code true} if all required flags are present, {@code false} otherwise.
     */
    public Boolean haveAllRequiredFlags(Map<String, String> flagValues, List<String> requiredFlags) {
        for (String requiredFlag : requiredFlags) {
            if (!flagValues.containsKey(requiredFlag)) {
                logger.log(Level.WARNING, "Missing required flags for the command.");
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given input string is empty.
     *
     * @param input The input string to check.
     * @return {@code true} if the input is empty, {@code false} otherwise.
     */
    public Boolean inputIsEmpty(String input) {
        if (input.isEmpty()) {
            logger.log(Level.WARNING, "Command input is empty");
            return true;
        }
        return false;
    }

    //@@author shennontay
    /**
     * Checks whether the provided case ID is a valid 6-character hexadecimal string.
     * <p>
     * A valid case ID must be exactly six hexadecimal characters (0–9, A–F, a–f).
     *
     * @param caseId the case ID to validate
     * @return {@code true} if the case ID is valid; {@code false} otherwise
     */
    public boolean isValidCaseId(String caseId) {
        if (caseId == null) {
            return false;
        }
        return caseId.matches(CASE_ID_REGEX);
    }


    //@@author zhengjie2002
    /**
     * Validates whether the provided date-time format string contains only valid characters.
     * <p>
     * Valid characters include those used in `DateTimeFormatter` patterns, such as:
     * y, M, d, H, m, s, S, E, a, G, w, W, D, F, k, K, h, z, Z, X, x, single quotes for literals,
     * and separators like -, /, ., :, space, and comma.
     *
     * @param format The date-time format string to validate.
     * @return {@code true} if the format string is valid; {@code false} otherwise.
     */
    public Boolean isValidDateTimeString(String format) {
        return (VALID_FORMAT_PATTERN.matcher(format).matches());
    }

    /**
     * Checks if the input string contains only English characters (ASCII printable characters).
     *
     * @param input The input string to check.
     * @return {@code true} if the input contains only English characters, {@code false} otherwise.
     */
    public boolean containsOnlyEnglishCharacters(String input) {
        return input.matches(ASCII_PRINTABLE_REGEX);
    }

    //@@author
}
