package seedu.penus.exceptions;

public class InvalidYearException extends PenusException {
    
    private static final String message = "The year of study can only be an integer. Please try again.";

    public InvalidYearException(String message) {
        super(message);
    }
    
}
