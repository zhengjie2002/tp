package seedu.sgsafe.utils.command;

import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.CaseManager;
import seedu.sgsafe.utils.ui.Display;

public class ReadCommand extends Command {
    private final String caseId;

    public ReadCommand(String caseId) {
        this.commandType = CommandType.READ;
        this.caseId = caseId;
    }

    @Override
    public void execute() {
        try {
            Case targetCase = CaseManager.getCaseById(caseId);
            if (targetCase == null) {
                Display.printMessage("No case found with ID: " + caseId);
                return;
            }

            Display.printMessage(targetCase.getReadCaseDisplay());
        } catch (Exception e) {
            Display.printMessage("An error occurred while reading the case.");
        }
    }
}
