package seedu.sgsafe.utils.command;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Represents a command to add a new case.
 * This command contains details about the case, including its title, date, information,
 * and optionally the victim and officer involved.
 */
public class AddCommand extends Command {

    private static final Logger logger = Logger.getLogger(AddCommand.class.getName());

    // Title of the case
    private final String caseTitle;

    // Date of the case
    private final String caseDate;

    // Additional information about the case
    private final String caseInfo;

    // Name of the victim involved in the case (optional)
    private final String caseVictim;

    // Name of the officer handling the case (optional)
    private final String caseOfficer;

    /**
     * Constructs an AddCommand with the specified case details.
     *
     * @param caseTitle   The title of the case. Cannot be null.
     * @param caseDate    The date of the case. Cannot be null.
     * @param caseInfo    Additional information about the case. Cannot be null.
     * @param caseVictim  The name of the victim involved in the case. Can be null.
     * @param caseOfficer The name of the officer handling the case. Can be null.
     */
    public AddCommand(String caseTitle, String caseDate, String caseInfo, String caseVictim, String caseOfficer) {
        this.commandType = CommandType.ADD;
        this.caseTitle = caseTitle;
        this.caseDate = caseDate;
        this.caseInfo = caseInfo;
        this.caseVictim = caseVictim;
        this.caseOfficer = caseOfficer;
        logger.log(Level.INFO, "AddCommand created");
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public String getCaseDate() {
        return caseDate;
    }

    public String getCaseInfo() {
        return caseInfo;
    }

    public String getCaseVictim() {
        return caseVictim;
    }

    public String getCaseOfficer() {
        return caseOfficer;
    }
}
