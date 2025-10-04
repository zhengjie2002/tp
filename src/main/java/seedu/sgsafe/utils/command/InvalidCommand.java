package seedu.sgsafe.utils.command;

/**
 * Represents a command that failed to parse or execute due to an error.
 */
public class InvalidCommand extends Command {

    /** A human-readable description of the error. */
    private String[] errorMessage;

    /**
     * Constructs an {@code InvalidCommand} with the specified error type.
     *
     * @param errorType the type of error that caused the command to be invalid
     */
    public InvalidCommand(InvalidCommandType errorType) {
        this.errorMessage = InvalidCommandErrorMessages.getMessage(errorType);
    }

    @Override
    public CommandType getType() {
        return CommandType.INVALID;
    }

    public String[] getErrorMessage() {
        return errorMessage;
    }
}