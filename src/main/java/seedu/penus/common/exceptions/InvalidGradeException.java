package seedu.penus.common.exceptions;

public class InvalidGradeException extends PenusException {
    public InvalidGradeException() {
        super("Grade is not valid");
    }
}
