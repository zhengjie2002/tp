package seedu.sgsafe.utils.exceptions;

public class IndexOutOfBoundsException extends InvalidCommandException {

    public IndexOutOfBoundsException() {
        super("Case number is out of range.");
    }
}
