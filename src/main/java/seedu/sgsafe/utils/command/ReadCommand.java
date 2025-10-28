package seedu.sgsafe.utils.command;

import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.CaseManager;
import seedu.sgsafe.utils.ui.Display;
import seedu.sgsafe.utils.exceptions.InvalidCaseIdException;

public class ReadCommand extends Command {
    private final String caseId;

    public ReadCommand(String caseId) {
        this.commandType = CommandType.READ;
        this.caseId = caseId;
    }

    @Override
    public void execute() {
        Case targetCase = CaseManager.getCaseById(caseId);
        if (targetCase == null) {
            throw new InvalidCaseIdException();
        }
        Display.printMessage(targetCase.getReadCaseDisplay());
    }
}
