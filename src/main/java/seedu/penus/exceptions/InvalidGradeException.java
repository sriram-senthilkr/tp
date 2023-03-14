package seedu.penus.exceptions;

public class InvalidGradeException extends PenusException {
    public InvalidGradeException() {
        super("Grade is not valid");
    }
}
