package seedu.sgsafe.domain.casefiles;

import java.util.Map;

/**
 * Represents a case file in the SGSafe system.
 * Each case contains metadata such as title, date, victim, officer, and status.
 */
public class Case {

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

    /**
     * Constructs a {@code Case} object with the specified details.
     * The case is initialized as closed by default.
     *
     * @param title   the title or summary of the case
     * @param date    the date the case was recorded or occurred
     * @param info    additional information or notes about the case
     * @param victim  the name of the victim involved
     * @param officer the name of the officer assigned
     */
    Case(String title, String date, String info, String victim, String officer) {
        this.title = title;
        this.date = date;
        this.info = info;
        this.victim = victim;
        this.officer = officer;
        this.isOpen = true;
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
     * Returns a formatted summary line for display purposes.
     * Includes the case status indicator, date, and title.
     *
     * @return a display-friendly string representing the case
     */
    public String getDisplayLine() {
        String open = this.isOpen ? "[O]" : "[C]";
        return open + " " + this.date + " " + this.title;
    }

    public void setClosed() {
        this.isOpen = false;
    }

    public void setOpen() {
        this.isOpen = true;
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
