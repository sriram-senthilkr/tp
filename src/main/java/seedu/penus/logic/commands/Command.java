package seedu.penus.logic.commands;

import seedu.penus.common.exceptions.DuplicateModuleException;
import seedu.penus.common.exceptions.InvalidCommandException;
import seedu.penus.model.ModelManager;

public abstract class Command {

    /**
     * Executes the command and returns the result.
     * @throws DuplicateModuleException
     */
    public abstract CommandResult execute(ModelManager model) 
        throws DuplicateModuleException, InvalidCommandException;

}
