package seedu.penus.logic.commands;

import seedu.penus.common.exceptions.PenusException;
import seedu.penus.model.ModelManager;

public abstract class Command {

    /**
     * Executes the command and returns the result message.
     * 
     * @param model {@code ModelManager} which the command operates on
     * @return CommandResult with the feedback message for display
     * @throws PenusException if an error occurs during the execution
     */
    public abstract CommandResult execute(ModelManager model) throws PenusException;
}
