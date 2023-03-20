package seedu.penus.exceptions;

public class WrongSemesterException extends PenusException {
    private static final String message = "The module is not available in the semester you have entered. Please try again";

    WrongSemesterException(String message) {
        super(message);
    }
}
