package seedu.penus.common.exceptions;

public class InvalidCommandException extends PenusException {
    private static final String MESSAGE = "This command is unsupported. Please try again";

    public InvalidCommandException(String message) {
        super(message);
    }

    public InvalidCommandException() {
        super(MESSAGE);
    }
}
