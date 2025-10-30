package seedu.sgsafe.utils.exceptions;

public class CaseCannotBeEditedException extends InvalidCommandException {
    private static final String STARTING_MESSAGE = "The case with ID: ";
    private static final String ENDING_MESSAGE = " has been closed. ";
    private static final String TIP = "To edit the case, reopen the case by typing: open ";

    public CaseCannotBeEditedException(String caseId) {
        super(STARTING_MESSAGE + caseId + ENDING_MESSAGE + TIP + caseId);
    }
}