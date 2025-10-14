package seedu.sgsafe.utils.command;

/**
 * Represents a command to close an existing case in the SGSafe system.
 * This command stores the case number to identify which case to update
 */
public class CloseCommand extends Command {
    private final int caseNumber;

    public CloseCommand(int caseNumber) {
        this.commandType = CommandType.CLOSE;
        this.caseNumber = caseNumber;
    }

    public int getCaseNumber() {
        return caseNumber;
    }
}
