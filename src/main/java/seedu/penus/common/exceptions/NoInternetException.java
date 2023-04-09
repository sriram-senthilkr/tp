package seedu.penus.common.exceptions;

public class NoInternetException extends PenusException{
    private static final String message = "You are not connected to the internet! Connect now to prevent any errors.";
    public NoInternetException() {
        super(message);
    }
}
