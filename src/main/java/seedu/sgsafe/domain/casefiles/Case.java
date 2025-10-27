package seedu.sgsafe.domain.casefiles;

import seedu.sgsafe.domain.casefiles.type.CaseType;
import seedu.sgsafe.domain.casefiles.type.CaseCategory;
import seedu.sgsafe.utils.settings.Settings;
import seedu.sgsafe.utils.storage.Storage;
import seedu.sgsafe.utils.ui.DateFormatter;

import java.time.LocalDate;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * Represents a case file in the SGSafe system.
 * Each case contains metadata such as title, date, victim, officer, and status.
 */
public abstract class Case {

    /**
     * The maximum number of characters allowed in a single display line.
     * <p>
     * Used to constrain field values in summary and verbose outputs, ensuring consistent formatting
     * and preventing overflow in fixed-width terminal views. Also governs truncation and wraparound logic.
     */
    static final int MAX_DISPLAY_WIDTH_CHARACTERS = 100;

    /**
     * The fixed width allocated for field labels in verbose listings.
     * <p>
     * Labels are left-aligned and padded to this width, followed by a colon and space.
     * This ensures all wrapped field values align vertically for readability.
     */
    static final int MAX_LABEL_WIDTH = 10;

    /**
     * The maximum number of lines allowed per field in verbose display mode.
     * <p>
     * Used to prevent excessively long fields from flooding the output. If a field wraps
     * beyond this limit, the final visible line is suffixed with {@code "..."}.
     */
    static final int MAX_VERBOSE_LINES_PER_FIELD = 5;

    /** The type of case. */
    protected CaseType type;

    /** The category of the case. */
    protected CaseCategory category;

    /** The category name to be printed. */
    protected String categoryString;

    /** The title or summary of the case. */
    private final String id;

    /** The title or summary of the case. */
    private String title;

    /** The date the case was recorded or occurred. */
    private LocalDate date;

    /** Additional information or notes about the case. */
    private String info;

    /** The name of the victim involved in the case. */
    private String victim;

    /** The name of the officer assigned to the case. */
    private String officer;

    /** Indicates whether the case is currently open. */
    private boolean isOpen;

    /** Indicates whether a case has been deleted. */
    private boolean isDeleted;

    /** Metadata timestamp for auditing of when the case is created. */
    private LocalDateTime createdAt;

    /** Metadata timestamp for auditing of when the case is updated. */
    private LocalDateTime updatedAt;

    /**
     * Constructs a {@code Case} object with the specified details.
     * The case is initialized as closed by default.
     *
     * @param id      the case ID in hex form
     * @param title   the title or summary of the case
     * @param date    the date the case was recorded or occurred
     * @param info    additional information or notes about the case
     * @param victim  the name of the victim involved
     * @param officer the name of the officer assigned
     */
    public Case(String id, String title, LocalDate date, String info, String victim, String officer) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.info = info;
        this.victim = victim;
        this.officer = officer;
        this.isOpen = true;
        this.isDeleted = false;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Retrieves the title or summary of the case.
     *
     * @return the title of the case
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves the date the case was recorded or occurred.
     *
     * @return the date of the case
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Retrieves additional information about the case.
     *
     * @return the additional information about the case
     */
    public String getInfo() {
        return info;
    }

    /**
     * Retrieves the type of the case.
     *
     * @return the type of the case.
     */
    public CaseType getType() {
        return type;
    }

    /**
     * Retrieves the category of the case.
     *
     * @return the category of the case.
     */
    public CaseCategory getCategory() {
        return category;
    }

    /**
     * Retrieves the name of the victim involved in the case.
     *
     * @return the name of the victim, or null if not specified
     */
    public String getVictim() {
        return victim;
    }

    /**
     * Retrieves the name of the officer assigned to the case.
     *
     * @return the name of the officer, or null if not specified
     */
    public String getOfficer() {
        return officer;
    }

    /**
     * Retrieves the unique ID of the case.
     *
     * @return the case ID
     */
    public String getId() {
        return this.id;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Sets the createdAt timestamp.
     *
     * @param createdAt the {@link LocalDateTime} that createdAt should be set to.
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Sets the updatedAt timestamp.
     *
     * @param updatedAt the {@link LocalDateTime} that updatedAt should be set to.
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getAdditionalFields() {
        return new ArrayList<>();
    }

    //@@ author xelisce

    /**
     * Constructs a formatted summary line for this case, suitable for display in summary listings.
     * <p>
     * The output includes:
     * <ul>
     *   <li>Status indicator: {@code [Open]} or {@code [Closed]}</li>
     *   <li>Category of the case</li>
     *   <li>Case ID: a unique 6-character hexadecimal string</li>
     *   <li>Date of the case (formatted)</li>
     *   <li>Title of the case</li>
     * </ul>
     * <p>
     * Fields are padded for alignment using fixed-width formatting.
     * Optional victim and officer details are excluded in summary mode.
     * <p>
     * Example output:
     * <pre>
     * [Open]   Theft            0001a3 2025-10-14 Theft from DBS in broad daylight
     * [Closed] Scam             0001a4 2025-10-15 Fraud involving MFA
     * [Closed] Traffic accident 0001a4 2025-10-15 Accident at the Nicolson Highway
     * </pre>
     *
     * @return a display-friendly summary string representing this case
     */
    public String getDisplayLine() {
        String status = this.isOpen ? "[Open]" : "[Closed]";
        String dateString = (date == null ? "" : DateFormatter.formatDate(date, Settings.getOutputDateFormat()));
        String titleString = truncate(this.title);
        return String.format("%-8s %-16s %-6s %-10s %s", status, categoryString, this.id, dateString, titleString);
    }

    /**
     * Truncates the input string to a maximum of {@code MAX_DISPLAY_WIDTH_CHARACTERS}.
     * <p>
     * If the input is {@code null}, returns an empty string.
     * If the input exceeds the maximum display width, the result is truncated and suffixed with {@code "..."}.
     * This method is typically used to ensure summary lines remain within a readable width.
     *
     * @param input the string to truncate
     * @return the truncated string, or an empty string if input is {@code null}
     */
    protected String truncate(String input) {
        if (input == null) {
            return "";
        }
        return input.length() <=
                MAX_DISPLAY_WIDTH_CHARACTERS ? input : input.substring(0, MAX_DISPLAY_WIDTH_CHARACTERS) + "...";
    }

    /**
     * Constructs a detailed, multi-line string representation of this case for verbose display.
     * <p>
     * The output begins with a header line in the format {@code ==== CASE ID 000000 ====},
     * followed by wrapped key-value lines for each non-null field.
     * Long values are wrapped across multiple lines using {@link #addWrappedField(List, String, String)}.
     * Optional fields such as {@code victim} and {@code officer} are only included if present.
     *
     * @return an array of strings representing the verbose, multi-line display of the case
     */
    public String[] getMultiLineVerboseDisplay() {
        List<String> lines = new ArrayList<>();
        lines.add(formatCaseIDHeader());
        String dateString = (date == null ? "" : DateFormatter.formatDate(date, Settings.getOutputDateFormat()));

        addWrappedField(lines, "Status", getStatusString());
        addWrappedField(lines, "Category", categoryString);
        addWrappedField(lines, "Title", title);
        addWrappedField(lines, "Date", dateString);
        addWrappedField(lines, "Info", info);
        addWrappedField(lines, "Created at", createdAt.toString());
        addWrappedField(lines, "Updated at", updatedAt.toString());
        addWrappedField(lines, "Victim", victim);
        addWrappedField(lines, "Officer", officer);

        return lines.toArray(new String[0]);
    }

    /**
     * Constructs the header line for the verbose display.
     * Format: {@code "======== CASE ID 000000 ========"}
     *
     * @return the formatted header string
     */
    private String formatCaseIDHeader() {
        return "======== CASE ID " + this.id + " ========";
    }

    /**
     * Returns the status of the case as a plain string.
     * <p>
     * Possible values are {@code "[Open]"} or {@code "[Closed]"} depending on the case state.
     *
     * @return the status string
     */
    private String getStatusString() {
        return this.isOpen ? "Open" : "Closed";
    }
    
    //@@ author

    /**
     * Adds a wrapped field to the given list if the value is non-null and non-empty.
     * <p>
     * The field is formatted using {@link #wrapField(String, String, int)} to ensure
     * long values are wrapped across multiple lines with proper indentation.
     * This is typically used to conditionally include optional fields (e.g., victim or officer)
     * in verbose case displays.
     *
     * @param lines the list to which the formatted lines will be added
     * @param label the label to display (e.g., "Victim", "Officer")
     * @param value the value associated with the label; ignored if {@code null} or empty
     */
    private void addWrappedField(List<String> lines, String label, String value) {
        if (value != null && !value.isEmpty()) {
            lines.addAll(wrapField(label, value, MAX_DISPLAY_WIDTH_CHARACTERS));
        }
    }

    /**
     * Formats a label-value pair for display, wrapping the value if it exceeds the given width.
     * <p>
     * The label is left-aligned using {@link #formatPrefix(String)} and the value is wrapped using
     * {@link #wrapWords(String, String, int)} to fit within the available width.
     * If the wrapped output exceeds {@code MAX_VERBOSE_LINES_PER_FIELD}, it is truncated to that limit
     * and the final line is suffixed with {@code "..."} to indicate truncation.
     *
     * @param label the field label (e.g., "Title", "Info")
     * @param value the field value to display
     * @param width the maximum number of characters allowed per line
     * @return a list of formatted lines representing the label and wrapped value
     */
    public static List<String> wrapField(String label, String value, int width) {
        String prefix = formatPrefix(label);
        int available = width - prefix.length();

        List<String> wrapped = wrapWords(prefix, value, available);

        // Limit the number of lines per field for verbose printing
        if (wrapped.size() > MAX_VERBOSE_LINES_PER_FIELD) {
            List<String> limited = new ArrayList<>(wrapped.subList(0, MAX_VERBOSE_LINES_PER_FIELD));
            String last = limited.get(MAX_VERBOSE_LINES_PER_FIELD - 1);
            limited.set(MAX_VERBOSE_LINES_PER_FIELD - 1, last + "...");
            return limited;
        }

        return wrapped;
    }

    /**
     * Formats a label into a fixed-width prefix for alignment in verbose listings.
     * <p>
     * The label is left-aligned and padded to {@code MAX_LABEL_WIDTH}, followed by a colon and space.
     * Example: {@code "Title      : "}
     *
     * @param label the label to format
     * @return the formatted prefix string
     */
    protected static String formatPrefix(String label) {
        return String.format("%-" + MAX_LABEL_WIDTH + "s: ", label);
    }

    /**
     * Immutable record representing the current state of a line being wrapped.
     *
     * @param line          the current line buffer
     * @param currentLength the number of characters currently used in the line (excluding prefix padding)
     */
    protected record LineState(StringBuilder line, int currentLength) {
    }

    /**
     * Wraps a value into multiple lines if necessary, preserving indentation.
     * <p>
     * Splits the value into words and places them into lines of at most {@code available} characters.
     * Long unbreakable words are delegated to {@link #handleLongWord(String, int, String, List, StringBuilder)}.
     *
     * @param prefix    the formatted label prefix (e.g., "Title      : ")
     * @param value     the value to wrap
     * @param available the maximum number of characters available per line (excluding prefix)
     * @return a list of wrapped lines
     */
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

    /**
     * Handles words that exceed the available width by splitting them into chunks.
     * <p>
     * Each chunk is truncated to {@code available - 1} characters and suffixed with a dash ("-").
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

    public void setClosed() {
        this.isOpen = false;
        updatedAt = LocalDateTime.now();
    }

    public void setOpen() {
        this.isOpen = true;
        updatedAt = LocalDateTime.now();
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    /**
     * Returns the list of valid flags that can be used to edit this case type.
     * The default implementation returns common flags shared by all case types.
     * Subclasses with additional fields should override this method.
     *
     * @return list of valid flag names for editing
     */
    public List<String> getValidEditFlags() {
        // Default flags for all case types
        return List.of("title", "date", "info", "victim", "officer");
    }

    /**
     * Updates the editable fields of {@code Case} instance using the provided map of new values.
     * <p>
     * Each key in {@code newValues} corresponds to a valid editable field (e.g. {@code title}, {@code date},
     * {@code info}, {@code victim}, {@code officer}). Only fields present in the map are updated; all
     * others remain unchanged.
     *
     * @param newValues a map containing field names and their new values
     */
    public void update(Map<String, Object> newValues) {
        if (newValues.containsKey("title")) {
            this.title = (String) newValues.get("title");
        }
        if (newValues.containsKey("date")) {
            this.date = (LocalDate) newValues.get("date");
        }
        if (newValues.containsKey("info")) {
            this.info = (String) newValues.get("info");
        }
        if (newValues.containsKey("victim")) {
            this.victim = (String) newValues.get("victim");
        }
        if (newValues.containsKey("officer")) {
            this.officer = (String) newValues.get("officer");
        }
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Converts this object's data fields into a single comma-separated string suitable for saving.
     * <p>
     * If any string field (e.g. {@code title}, {@code date}, etc.) is {@code null}, it will be replaced
     * with an empty string in the output. The {@code isDeleted} field is represented as {@code "1"} if true
     * and {@code "0"} if false.
     * </p>
     *
     * @return a formatted string containing all of this object's field values
     */
    public String toSaveString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Storage.getSaveDateTimePattern());
        return "id:" + this.id
                + "|category:" + this.category.toString()
                + "|title:" + (this.title == null ? "" : this.title)
                + "|date:" + (this.date == null ? "" : this.date.format(dateFormatter))
                + "|info:" + (this.info == null ? "" : this.info)
                + "|victim:" + (this.victim == null ? "" : this.victim)
                + "|officer:" + (this.officer == null ? "" : this.officer)
                + "|is-deleted:" + (this.isDeleted ? "1" : "0")
                + "|is-open:" + (this.isOpen ? "1" : "0")
                + "|created-at:" + (this.createdAt == null ? "" : this.createdAt.format(dateTimeFormatter))
                + "|updated-at:" + (this.updatedAt == null ? "" : this.updatedAt.format(dateTimeFormatter));
    }
}
