package seedu.sgsafe.utils.command;

import seedu.sgsafe.domain.casefiles.CaseManager;

/**
 * Represents a command to edit an existing case in the SGSafe system.
 * This command stores the case number to identify which case to update
 */
public class DeleteCommand extends Command {
    // The case number of the case to delete
    private final int caseNumber;

    // Constructor that sets the case number
    public DeleteCommand(int caseNumber) {
        this.commandType = CommandType.DELETE;
        this.caseNumber = caseNumber;
    }

    @Override
    public void execute() {
        CaseManager.deleteCase(caseNumber);
    }
}
