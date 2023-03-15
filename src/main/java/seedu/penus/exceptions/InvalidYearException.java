package seedu.penus.exceptions;

public class InvalidYearException extends PenusException {
    private static final String message = "The year must be an integer. Please try again";
    InvalidYearException(String message) {
        super(message);
    }
}
