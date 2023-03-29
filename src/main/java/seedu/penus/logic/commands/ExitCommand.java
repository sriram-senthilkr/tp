package seedu.penus.logic.commands;

import seedu.penus.model.ModelManager;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting PENUS ...\n\tBye see you again!";

    /**
     * Static method which signals the end of the program, called in main running loop
     * @param command Command object
     * @return boolean true if the given Command is an instance of ExitCommand
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }

    @Override
    public CommandResult execute(ModelManager model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false);
    }
}
