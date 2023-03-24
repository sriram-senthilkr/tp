package seedu.penus.logic.commands;

import seedu.penus.common.exceptions.PenusException;
import seedu.penus.model.ModelManager;

public abstract class Command {

    /**
     * Executes the command and returns the result.
     */
    public abstract CommandResult execute(ModelManager model) throws PenusException;
}
