package seedu.penus.common.exceptions;

public class DuplicateModuleException extends PenusException {
    private static final String message = "This module has already been added to the list. Please try again.";
    public DuplicateModuleException() {
        super(message);
    }
}
