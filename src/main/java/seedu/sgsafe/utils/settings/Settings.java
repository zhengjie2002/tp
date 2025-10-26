package seedu.sgsafe.utils.settings;

import java.time.format.DateTimeFormatter;

public class Settings {
    private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
    private static String inputDateFormat = DEFAULT_DATE_FORMAT;
    private static String outputDateFormat = DEFAULT_DATE_FORMAT;

    public static String getInputDateFormat() {
        return inputDateFormat;
    }

    public static String getOutputDateFormat() {
        return outputDateFormat;
    }

    public static void setOutputDateFormat(String format) throws IllegalArgumentException {
        // Validate format by attempting to create a DateTimeFormatter
        DateTimeFormatter.ofPattern(format);
        outputDateFormat = format;
    }

    public static void setInputDateFormat(String format) throws IllegalArgumentException {
        // Validate format by attempting to create a DateTimeFormatter
        DateTimeFormatter.ofPattern(format);
        inputDateFormat = format;
    }
}