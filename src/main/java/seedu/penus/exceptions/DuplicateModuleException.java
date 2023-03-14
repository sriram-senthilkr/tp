package seedu.penus.exceptions;

public class DuplicateModuleException extends PenusException {
    private static final String message = "The module you entered already exists in the list. Please try again";

    public DuplicateModuleException(String message) {
        super(message);
    }
}
