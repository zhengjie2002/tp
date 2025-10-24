package seedu.sgsafe.utils.exceptions;

public class InvalidCategoryException extends InvalidCommandException {
    private static final String ERROR_MESSAGE = "The category does not exist.";
    private static final String TIP = "For categories not included in the given list, enter 'others' as the category.";

    public InvalidCategoryException() {
        super(ERROR_MESSAGE, TIP);
    }
}
