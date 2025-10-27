package seedu.sgsafe.utils.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Utility class for parsing and formatting dates.
 * Provides methods to convert between LocalDate objects and their string representations
 * based on a specified date format.
 */
public class DateFormatter {

    /**
     * Parses a date string into a LocalDate object using the specified date format.
     *
     * @param dateString The date string to be parsed.
     * @param dateFormat The format of the date string, following the DateTimeFormatter pattern.
     * @return A LocalDate object representing the parsed date.
     * @throws DateTimeParseException if the date string cannot be parsed with the given format.
     */
    public static LocalDate parseDate(String dateString, String dateFormat) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        return LocalDate.parse(dateString, formatter);
    }

    /**
     * Formats a LocalDate object into a string representation using the specified date format.
     *
     * @param date       The LocalDate object to be formatted.
     * @param dateFormat The format to use for the date string, following the DateTimeFormatter pattern.
     * @return A string representation of the formatted date.
     */
    public static String formatDate(LocalDate date, String dateFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        return date.format(formatter);
    }
}
