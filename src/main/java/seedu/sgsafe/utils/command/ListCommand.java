package seedu.sgsafe.utils.command;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Represents a command to list case files in the SGSafe system.
 * This command can be configured to list open cases, closed cases, or all cases.
 */
public class ListCommand extends Command {

    private static final Logger logger = Logger.getLogger(ListCommand.class.getName());

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
        logger.log(Level.INFO, "ListCommand created");
    }

    public CaseListingMode getListingMode() {
        assert listingMode != null : "Listing mode must be set";
        return listingMode;
    }
}
