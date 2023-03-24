package seedu.penus.common.exceptions;

public class PenusException extends Exception {
    PenusException(String message) {
        super("Error: " + message);
    }
}
