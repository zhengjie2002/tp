package seedu.sgsafe.utils.command;

/**
 * Enumerates the types of errors that can occur when parsing or executing a command.
 */
public enum InvalidCommandType {
    EMPTY_COMMAND,
    UNKNOWN_COMMAND,

    // List Command Errors
    LIST_COMMAND_INVALID_ARGUMENTS,

    // Add Command Errors
    ADD_COMMAND_NO_ARGUMENTS,
    ADD_COMMAND_INVALID_ARGUMENTS,

}
