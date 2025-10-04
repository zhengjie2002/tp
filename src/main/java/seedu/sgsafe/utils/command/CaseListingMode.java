package seedu.sgsafe.utils.command;

/**
 * Specifies the mode for listing case files in the SGSafe system.
 * Determines whether the command should list open cases, closed cases, or all cases.
 */
public enum CaseListingMode {
    /** List all cases regardless of status. */
    ALL,

    /** List only open cases. */
    OPEN_ONLY,

    /** List only closed cases. */
    CLOSED_ONLY
}
