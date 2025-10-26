package seedu.sgsafe.utils.settings;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The `Settings` class provides utility methods to manage input and output date formats.
 * It allows setting and retrieving date formats while ensuring the formats are valid.
 */
public class Settings {

    // Default date format used for input and output
    private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
    private static String inputDateFormat = DEFAULT_DATE_FORMAT;
    private static String outputDateFormat = DEFAULT_DATE_FORMAT;

    public static String getInputDateFormat() {
        return inputDateFormat;
    }

    public static String getOutputDateFormat() {
        return outputDateFormat;
    }

    /**
     * Sets the output date format.
     * Validates the provided format by attempting to create a `DateTimeFormatter`.
     *
     * @param format the new output date format as a `String`.
     */
    public static void setOutputDateFormat(String format) {
        // Validate format by attempting to create a DateTimeFormatter and parse. If it fails it will throw an exception
        if (format == null || format.isEmpty()) {
            throw new IllegalArgumentException();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate testDate = LocalDate.of(2024, 1, 15);
        String formattedDate = testDate.format(formatter);
        LocalDate parsedDate = LocalDate.parse(formattedDate, formatter);
        outputDateFormat = format;
    }

    /**
     * Sets the input date format.
     * Validates the provided format by attempting to create a `DateTimeFormatter`.
     *
     * @param format the new input date format as a `String`.
     */
    public static void setInputDateFormat(String format) {
        // Validate format by attempting to create a DateTimeFormatter and parse. If it fails it will throw an exception
        if (format == null || format.isEmpty()) {
            throw new IllegalArgumentException();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate testDate = LocalDate.of(2024, 1, 15);
        String formattedDate = testDate.format(formatter);
        LocalDate parsedDate = LocalDate.parse(formattedDate, formatter);
        inputDateFormat = format;
    }
}
