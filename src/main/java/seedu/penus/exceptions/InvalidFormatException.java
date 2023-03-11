package seedu.penus.exceptions;

public class InvalidFormatException extends PenusException {
    public InvalidFormatException(String... missing) {
        super("Wrong Format! Please include " + String.join(" and ", missing));
    }
}
