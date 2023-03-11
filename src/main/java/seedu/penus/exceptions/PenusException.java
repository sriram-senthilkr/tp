package seedu.penus.exceptions;

public class PenusException extends Exception {
    PenusException(String message) {
        super("\tError: " + message);
    }
}
