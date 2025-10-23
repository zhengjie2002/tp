package seedu.sgsafe.utils.exceptions;

public class CaseAlreadyClosedException extends InvalidCommandException {
    private static final String STARTING_MESSAGE = "The case with ID: ";
    private static final String ENDING_MESSAGE = " has already been closed.";

    public CaseAlreadyClosedException(String caseId) {
        super(STARTING_MESSAGE + caseId + ENDING_MESSAGE);
    }
}
