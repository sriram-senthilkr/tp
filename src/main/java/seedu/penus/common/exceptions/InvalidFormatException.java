package seedu.penus.common.exceptions;

public class InvalidFormatException extends PenusException {
    public InvalidFormatException(String... missing) {
        super("Wrong Format! Please include " + String.join(" and ", missing));
    }

    public InvalidFormatException(String message) {
        super(message);
    }
}
