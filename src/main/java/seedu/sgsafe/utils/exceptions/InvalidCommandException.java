package seedu.sgsafe.utils.exceptions;

public abstract class InvalidCommandException extends RuntimeException {

    private final String[] errorMessage ;

    protected InvalidCommandException(String... errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String[] getErrorMessage() {
        return errorMessage;
    }
}
