package seedu.penus.exceptions;

public class InvalidIndexException extends PenusException {
    private static final String MESSAGE = "This command is unsupported. Please try again";

    public InvalidIndexException(String message) {
        super(message);
    }

    public InvalidIndexException() {
        super(MESSAGE);
    }
}
