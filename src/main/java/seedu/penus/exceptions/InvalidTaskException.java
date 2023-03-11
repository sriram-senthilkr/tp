package seedu.penus.exceptions;

public class InvalidTaskException extends PenusException {
    public InvalidTaskException(String module) {
        super("The module code of " + module + " must be given.");
    }
}
