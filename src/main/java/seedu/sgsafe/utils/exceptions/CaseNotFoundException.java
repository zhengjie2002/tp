package seedu.sgsafe.utils.exceptions;

public class CaseNotFoundException extends Exception{
    public CaseNotFoundException(String caseId) {
        super("No case found with ID: "+ caseId);
    }
}
