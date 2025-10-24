package seedu.sgsafe.domain.casefiles;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a case file in the SGSafe system.
 * Each case contains metadata such as title, date, victim, officer, and status.
 */
public class Case {

    /** The title or summary of the case. */
    private final String id;

    /** The title or summary of the case. */
    private String title;

    /** The date the case was recorded or occurred. */
    private String date;

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
    public Case(String id, String title, String date, String info, String victim, String officer) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.info = info;
        this.victim = victim;
        this.officer = officer;
        this.isOpen = true;
        this.isDeleted = false;
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
    public String getDate() {
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

    public void setDeleted() {
        this.isDeleted = true;
    }

    /**
     * Returns a formatted summary line representing this case for display purposes.
     * <p>
     * The output includes:
     * <ul>
     *   <li>Status indicator: {@code [O]} for open, {@code [C]} for closed</li>
     *   <li>Case ID: a unique 6-character hexadecimal string</li>
     *   <li>Date and title of the case</li>
     *   <li>Optional victim and officer details, if present</li>
     * </ul>
     * <p>
     * Example output:
     * <pre>
     * [O] #0001a3 2025-10-14 Robbery | Victim: Alice | Officer: Officer Tan
     * [C] #0001a4 2025-10-15 Fraud
     * </pre>
     *
     * @return a display-friendly string summarizing the case
     */
    public String getDisplayLine() {
        String status = this.isOpen ? "[Open]" : "[Closed]";
        return String.format("%-8s %-6s %-10s %s", status, this.id, this.date, this.title);
    }

    /**
     * Returns a verbose, multi-line representation of this case for detailed display.
     * <p>
     * The header line uses the format {@code ==== CASE ID 000000 ====}.
     * All fields are truncated to 100 characters and suffixed with {@code "..."} if longer.
     * Optional fields like {@code victim} and {@code officer} are omitted if not present.
     *
     * @return an array of strings representing the verbose display of the case
     */
    public String[] getMultiLineVerboseDisplay() {
        List<String> lines = new ArrayList<>();
        lines.add(formatCaseIDHeader());
        lines.add(formatStatus());
        lines.add(formatLine("Title", title));
        lines.add(formatLine("Date", date));
        lines.add(formatLine("Info", info));

        if (victim != null) {
            lines.add(formatLine("Victim", victim));
        }
        if (officer != null) {
            lines.add(formatLine("Officer", officer));
        }

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
     * Constructs the status line indicating whether the case is open or closed.
     * Format: {@code "Status  : Open"} or {@code "Status  : Closed"}
     *
     * @return the formatted status string
     */
    private String formatStatus() {
        return "Status  : " + (this.isOpen ? "Open" : "Closed");
    }

    /**
     * Formats a labeled line with truncated content.
     * If the value is {@code null}, an empty string is used.
     * Format: {@code "Label   : value"}
     *
     * @param label the label to display (e.g., "Title", "Date")
     * @param value the value to display, which will be truncated
     * @return the formatted line
     */
    private String formatLine(String label, String value) {
        return label + "  : " + truncate(value);
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
        if (input == null) return "";
        return input.length() <= 100 ? input : input.substring(0, 100) + "...";
    }

    public void setClosed() {
        this.isOpen = false;
    }

    public void setOpen() {
        this.isOpen = true;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    /**
     * Updates the fields of this Case object using the values provided in the map.
     * Only the fields that appear in newValues will be changed.
     *
     * @param newValues a map containing the fields to update and their new values
     */
    public void update(Map<String, String> newValues) {
        if (newValues.containsKey("title")) {
            this.title = newValues.get("title");
        }
        if (newValues.containsKey("date")) {
            this.date = newValues.get("date");
        }
        if (newValues.containsKey("info")) {
            this.info = newValues.get("info");
        }
        if (newValues.containsKey("victim")) {
            this.victim = newValues.get("victim");
        }
        if (newValues.containsKey("officer")) {
            this.officer = newValues.get("officer");
        }
    }
}
