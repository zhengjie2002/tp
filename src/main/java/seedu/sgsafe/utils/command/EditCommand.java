package seedu.sgsafe.utils.command;

import java.util.Map;

import seedu.sgsafe.domain.casefiles.CaseManager;

/**
 * Represents a command to edit an existing case in the SGSafe system.
 * This command stores the case number to identify which case to update
 * and a set of new field values to replace the old ones.
 */
public class EditCommand extends Command {
    // The case number of the case to edit
    private final int caseId;

    // Map of new field values, where the key is the flag (e.g. "title", "date")
    private final Map<String, String> newFlagValues;

    // Constructor that sets the case number and new field values
    public EditCommand(int caseNumber, Map<String, String> newFlagValues) {
        this.commandType = CommandType.EDIT;
        this.caseId = caseNumber;
        this.newFlagValues = newFlagValues;
    }

    // Returns the map of new field values
    public Map<String, String> getNewFlagValues() {
        return newFlagValues;
    }

    @Override
    public void execute() {
        CaseManager.editCase(caseId, newFlagValues);
    }
}

