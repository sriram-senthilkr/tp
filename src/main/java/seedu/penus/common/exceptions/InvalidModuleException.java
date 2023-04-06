package seedu.penus.common.exceptions;

public class InvalidModuleException extends PenusException {
    public InvalidModuleException(String module) {
        super("The module code must be given.");
    }

    public InvalidModuleException() {
        super("Invalid module. Please try again");
    }
}
