package seedu.sgsafe.utils.exceptions;

/**
 * Represents an exception that is thrown when an invalid command is encountered.
 * This abstract class serves as a base for more specific invalid command exceptions.
 */
public abstract class InvalidCommandException extends RuntimeException {

    private final String[] errorMessages ;

    /**
     * Constructs an InvalidCommandException with the specified error messages.
     *
     * @param errorMessages A variable-length array of error messages describing the reason for the exception.
     *                     Each message provides additional context about the invalid command.
     */

    protected InvalidCommandException(String... errorMessages) {
        this.errorMessages = errorMessages;
    }

    /**
     * Retrieves the error messages associated with this exception.
     *
     * @return An array of error messages.
     */
    public String[] getErrorMessage() {
        return errorMessages;
    }
}
