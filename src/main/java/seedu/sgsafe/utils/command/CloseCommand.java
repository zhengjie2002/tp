package seedu.sgsafe.utils.command;

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
