package seedu.sgsafe.utils.command;

import seedu.sgsafe.domain.casefiles.CaseManager;
import seedu.sgsafe.utils.exceptions.CaseNotFoundException;
import seedu.sgsafe.utils.ui.Display;

public class OpenCommand extends Command {
    private final String caseId;

    public OpenCommand(String caseId) {
        this.commandType = CommandType.OPEN;
        this.caseId = caseId;
    }

    @Override
    public void execute() {
        try {
            String displayLine = CaseManager.openCase(caseId);
            Display.printMessage("Case reopened:", displayLine);
        } catch (CaseNotFoundException e) {
            Display.printMessage(e.getMessage());
        }
    }
}
