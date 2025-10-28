package seedu.sgsafe.domain.casefiles;

import seedu.sgsafe.domain.casefiles.type.CaseType;
import seedu.sgsafe.domain.casefiles.type.CaseCategory;
import seedu.sgsafe.utils.ui.Display;
import seedu.sgsafe.utils.settings.Settings;
import seedu.sgsafe.utils.storage.Storage;
import seedu.sgsafe.utils.ui.DateFormatter;

import java.time.LocalDate;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * Represents a case file in the SGSafe system.
 * Each case contains metadata such as title, date, victim, officer, and status.
 */
public abstract class Case {
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
        return String.format("%-8s %-16s %-6s %-10s %s", status, categoryString, this.id, dateString, this.title);
    }

    /**
     * Constructs a detailed, multi-line string representation of this case for display purposes.
     * <p>
     * The output begins with a header line in the format {@code ==== CASE ID 000000 ====}, followed by
     * key-value lines for each non-null field. Each value is truncated to 100 characters and suffixed
     * with {@code "..."} if it exceeds that length. Optional fields such as {@code victim} and {@code officer}
     * are only included if they are non-null.
     * <p>
     * This method avoids stacking function calls and delegates conditional formatting and addition
     * to a helper method for clarity and maintainability.
     *
     * @return an array of strings representing the verbose, multi-line display of the case
     */
    public String[] getMultiLineVerboseDisplay() {
        List<String> lines = new ArrayList<>();
        lines.add(formatCaseIDHeader());
        String dateString = (date == null ? "" : DateFormatter.formatDate(date, Settings.getOutputDateFormat()));

        addFormattedLine(lines, "Status", getStatusString());
        addFormattedLine(lines, "Category", categoryString);
        addFormattedLine(lines, "Title", title);
        addFormattedLine(lines, "Date", dateString);
        addFormattedLine(lines, "Info", info);
        addFormattedLine(lines, "Created at", createdAt.toString());
        addFormattedLine(lines, "Updated at", updatedAt.toString());
        addFormattedLine(lines, "Victim", victim);
        addFormattedLine(lines, "Officer", officer);

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

    /**
     * Formats a labeled line with truncated content.
     * If the value is {@code null}, an empty string is used.
     * Format: {@code "Label      : value"} — with the label padded to 10 characters.
     *
     * @param label the label to display (e.g., "Title", "Date")
     * @param value the value to display, which will be truncated
     * @return the formatted line with aligned colon
     */
    private String formatLine(String label, String value) {
        if (value == null) {
            return "";
        }
        String paddedLabel = String.format("%-10s", label); // pad to 10 characters
        return paddedLabel + " : " + truncate(value);
    }

    /**
     * Appends a formatted line to the given list if the value is not {@code null}.
     * <p>
     * This method formats the provided label and value using {@link #formatLine(String, String)},
     * then adds the result to the specified list. If the value is {@code null}, the method does nothing.
     * <p>
     * This is typically used to conditionally include optional fields (e.g., victim or officer)
     * in verbose case displays.
     *
     * @param lines the list to which the formatted line will be added
     * @param label the label to display (e.g., "Victim", "Officer")
     * @param value the value associated with the label; ignored if {@code null}
     */
    private void addFormattedLine(List<String> lines, String label, String value) {
        if (value != null) {
            String formatted = formatLine(label, value);
            lines.add(formatted);
        }
    }

    /**
     * Truncates the input string to a maximum of 100 characters.
     * If the input is {@code null}, returns an empty string.
     * If the input exceeds 100 characters, appends {@code "..."}.
     *
     * @param input the string to truncate
     * @return the truncated string
     */
    private String truncate(String input) {
        return input.length() <= 100 ? input : input.substring(0, 100) + "...";
    }

    //@@ author
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
     * Formats a labeled line without truncating the value.
     * Behaves like {@link #formatLine(String, String)} but prints the full content.
     *
     * @param label the label to display
     * @param value the value to display
     * @return a formatted line with label and full value
     */
    public static String formatLineNoTruncate(String label, Object value) {
        if (value == null) {
            value = "";
        } else {
            value = value.toString();
        }
        String paddedLabel = String.format("%-17s", label); // same padding as the original
        return "\t" + paddedLabel + " : " + value;
    }

    /**
     * Returns the base display lines common to all case types,
     * excluding the Info field (so subclasses can safely add their own fields before Info).
     */
    protected List<String> getBaseDisplayLines() {
        return new ArrayList<>(Arrays.asList(
                formatLineNoTruncate("Title", title),
                formatLineNoTruncate("Case ID", id),
                formatLineNoTruncate("Status", isOpen ? "Open" : "Closed"),
                formatLineNoTruncate("Category",getCategory()),
                formatLineNoTruncate("Date", getDate()),
                formatLineNoTruncate("Victim", getVictim()),
                formatLineNoTruncate("Officer", getOfficer()),
                formatLineNoTruncate("Created at", createdAt),
                formatLineNoTruncate("Updated at", updatedAt)
        ));
    }


    /**
     * Default implementation of getReadCaseDisplay for case categories without unique fields.
     * Subclasses should override this method to add more details before the Info line.
     */
    public String[] getReadCaseDisplay() {
        List<String> displayList = getBaseDisplayLines();
        displayList.add(Display.formatIndentedText("Info :", getInfo()));


        return displayList.toArray(new String[0]);
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
