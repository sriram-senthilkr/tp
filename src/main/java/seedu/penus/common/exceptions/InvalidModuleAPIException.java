package seedu.penus.common.exceptions;

public class InvalidModuleAPIException extends PenusException {
    private static final String message = "The module's details were not able to be retrieved," +
                                          " as it is not available in the API.";
    public InvalidModuleAPIException() {
        super(message);
    }

}
