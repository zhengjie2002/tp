package seedu.sgsafe.utils.exceptions;

public class CaseAlreadyOpenException extends InvalidCommandException {
    private static final String STARTING_MESSAGE = "The case with ID: ";
    private static final String ENDING_MESSAGE = " is already open.";

    public CaseAlreadyOpenException(String caseId) {
        super(STARTING_MESSAGE + caseId + ENDING_MESSAGE);
    }
}
