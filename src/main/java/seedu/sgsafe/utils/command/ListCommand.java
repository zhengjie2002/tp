package seedu.sgsafe.utils.command;

/**
 * Represents a command to list case files in the SGSafe system.
 * This command can be configured to list open cases, closed cases, or all cases.
 */
public class ListCommand extends Command {

    /**
     * Specifies the listing mode for this command:
     * {@code OPEN_ONLY}, {@code CLOSED_ONLY}, or {@code ALL}.
     */
    private final CaseListingMode listingMode;

    /**
     * Constructs a {@code ListCommand} with the specified listing mode.
     *
     * @param listingMode the mode that determines which cases to list
     */
    public ListCommand(CaseListingMode listingMode) {
        this.commandType = CommandType.LIST;
        this.listingMode = listingMode;
    }

    public CaseListingMode getListingMode() {
        return listingMode;
    }
}
