package seedu.sgsafe.domain.casefiles;

import java.util.ArrayList;
import java.util.List;

public class CaseFormatter {

    /** Max characters per display line (used for truncation/wrapping). Counted from after the tab in display */
    static final int MAX_DISPLAY_WIDTH_CHARACTERS = 100;

    //@@author shennontay
    /** Fixed width for field labels in read command. */
    static final String MAX_LABEL_STRING_WIDTH_FOR_READ_COMMAND = "%-17s"; // longest label is "Created by"
    static final String PREFIX_FORMAT_STRING_FOR_READ_COMMAND = MAX_LABEL_STRING_WIDTH_FOR_READ_COMMAND + " : ";
    //@@author

    /** Fixed width for field labels in verbose command. */
    static final String MAX_LABEL_STRING_WIDTH_FOR_VERBOSE = "%-10s"; // longest label is "Created by"
    static final String PREFIX_FORMAT_STRING_FOR_VERBOSE = MAX_LABEL_STRING_WIDTH_FOR_VERBOSE + " : ";

    /** Max lines per field in verbose mode before truncating. */
    static final int MAX_VERBOSE_LINES_PER_FIELD = 5;

    /** Format specifiers for summary fields. */
    static final String MAX_STATUS_STRING_WIDTH   = "%-8s";   // [Closed]
    static final String MAX_CATEGORY_STRING_WIDTH = "%-16s";  // Traffic accident
    static final String MAX_ID_STRING_WIDTH       = "%-6s";   // 6-char hex ID
    static final String MAX_DATE_STRING_WIDTH     = "%-10s";  // dd/MM/yyyy
    static final String MAX_TITLE_STRING_WIDTH    = "%s";
    static final int MAX_TITLE_WIDTH              = 10;       // title is truncated separately

    /** Composite format string for summary lines. */
    static final String STATUS_FORMAT_STRING =
            MAX_STATUS_STRING_WIDTH + " "
                    + MAX_CATEGORY_STRING_WIDTH + " "
                    + MAX_ID_STRING_WIDTH + " "
                    + MAX_DATE_STRING_WIDTH + " "
                    + MAX_TITLE_STRING_WIDTH;

    public static String convertStatusToString(boolean isOpen) {
        return isOpen ? "Open" : "Closed";
    }

    public static String formatCaseSummaryLine(
            boolean status,
            String category,
            String id,
            String date,
            String title) {
        String statusString = convertStatusToString(status);
        String titleString = truncateWithEllipses(title, MAX_TITLE_WIDTH);
        return String.format(STATUS_FORMAT_STRING,
                statusString, category, id, date, titleString);
    }

    /**
     * Constructs the header line for the verbose display.
     * Format: {@code "======== CASE ID 000000 ========"}
     *
     * @return the formatted header string
     */
    public static String formatCaseIDHeader(String id) {
        return "======== CASE ID " + id + " ========";
    }

    public static String truncateWithEllipses(String input, int maximumLength) {
        if (input == null) {
            return "";
        }
        return input.length() <=
                maximumLength ? input : input.substring(0, maximumLength) + "...";
    }

    //@@author shennontay
    public static void addWrappedFieldForRead(List<String> lines, String label, String value) {
        if (value != null && !value.isEmpty()) {
            List<String> fieldLines = wrapField(false, label, value, MAX_DISPLAY_WIDTH_CHARACTERS);
            lines.addAll(fieldLines);
        }
    }
    //@@author

    public static void addWrappedFieldForVerbose(List<String> lines, String label, String value) {
        if (value != null && !value.isEmpty()) {
            List<String> fieldLines = wrapField(true, label, value, MAX_DISPLAY_WIDTH_CHARACTERS);
            lines.addAll(fieldLines);
        }
    }

    //@@author shennontay
    public static List<String> wrapField(boolean verbose, String label, String value, int width) {
        String prefix = formatPrefix(label, verbose);
        int available = width - prefix.length();

        List<String> wrapped = wrapWords(prefix, value, available);

        //@@author xelisce
        // Limit the number of lines per field for verbose printing
        if (verbose && wrapped.size() > MAX_VERBOSE_LINES_PER_FIELD) {
            List<String> limited = new ArrayList<>(wrapped.subList(0, MAX_VERBOSE_LINES_PER_FIELD));
            String last = limited.get(MAX_VERBOSE_LINES_PER_FIELD - 1);
            limited.set(MAX_VERBOSE_LINES_PER_FIELD - 1, last + "...");
            return limited;
        }

        return wrapped;
    }

    //@@author shennontay
    protected static String formatPrefix(String label, boolean verbose) {
        if (verbose) {
            return String.format(PREFIX_FORMAT_STRING_FOR_VERBOSE, label);
        } else {
            return String.format(PREFIX_FORMAT_STRING_FOR_READ_COMMAND, label);
        }
    }
    //@@author

    /**
     * Immutable record representing the current state of a line being wrapped.
     *
     * @param line          the current line buffer
     * @param currentLength the number of characters currently used in the line (excluding prefix padding)
     */
    protected record LineState(StringBuilder line, int currentLength) {
    }

    private static List<String> wrapWords(String prefix, String value, int available) {
        List<String> lines = new ArrayList<>();
        String[] words = value.split(" ");
        StringBuilder line = new StringBuilder(prefix);
        int currentLength = 0;

        for (String word : words) {
            word = handleLongWord(word, available, prefix, lines, line);
            LineState state = tryAddWord(word, line, currentLength, available, prefix, lines);
            line = state.line;
            currentLength = state.currentLength;
        }

        lines.add(line.toString());
        return lines;
    }

    //@@author xelisce
    /**
     * Handles words that exceed the available width by splitting them into chunks.
     * <p>
     * Each chunk is truncateWithEllipsesd to {@code available - 1} characters and suffixed with a dash ("-").
     * Remaining characters are placed on subsequent lines with proper indentation.
     *
     * @param word   the word to process
     * @param available the maximum number of characters available per line
     * @param prefix the formatted label prefix (used for indentation)
     * @param lines  the list of lines to append to
     * @param line   the current line buffer
     * @return the remaining portion of the word that fits within the available width
     */
    protected static String handleLongWord(String word, int available, String prefix,
                                           List<String> lines, StringBuilder line) {
        while (word.length() > available) {
            int chunkLength = available - 1; // leave one character's space for dash
            String chunk = word.substring(0, chunkLength) + "-";
            lines.add(line.toString() + chunk);
            word = word.substring(chunkLength);
            line.setLength(0);
            line.append(" ".repeat(prefix.length())); // indent
        }
        return word;
    }
    //@@author

    //@@author shennontay
    /**
     * Attempts to add a word to the current line, wrapping to a new line if necessary.
     * <p>
     * If the word does not fit within the available width, the current line is flushed
     * to {@code lines}, and a new indented line is started. Spaces are inserted between
     * words as needed.
     *
     * @param word          the word to add
     * @param line          the current line buffer
     * @param currentLength the number of characters currently used in the line
     * @param available     the maximum number of characters available per line
     * @param prefix        the formatted label prefix (used for indentation)
     * @param lines         the list of lines to append to
     * @return a {@link LineState} containing the updated line buffer and character count
     */
    protected static LineState tryAddWord(String word, StringBuilder line, int currentLength,
                                          int available, String prefix, List<String> lines) {
        // If word doesn't fit, flush current line
        if (currentLength + word.length() > available) {
            lines.add(line.toString());
            line = new StringBuilder(" ".repeat(prefix.length()));
            currentLength = 0;
        }

        // Add space if not first word
        if (currentLength > 0) {
            line.append(" ");
            currentLength++;
        }

        line.append(word);
        currentLength += word.length();

        return new LineState(line, currentLength);
    }
    //@@author shennontay

}
