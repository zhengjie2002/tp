package seedu.sgsafe.domain.casefiles;

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
        this.isOpen = false;
    }

    /**
     * Returns a formatted summary line for display purposes.
     * Includes the case status indicator, date, and title.
     *
     * @return a display-friendly string representing the case
     */
    public String getDisplayLine() {
        String open = this.isOpen ? "[C]" : "[O]";
        return open + " " + this.date + " " + this.title;
    }
}