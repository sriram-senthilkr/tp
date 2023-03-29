package seedu.penus.logic;

import seedu.penus.model.ModelManager;
import seedu.penus.storage.StorageManager;
import seedu.penus.logic.parser.Parser;
import seedu.penus.logic.commands.CommandResult;
import seedu.penus.common.exceptions.PenusException;
import seedu.penus.logic.commands.Command;

public class LogicManager {
    private final ModelManager model;
    private final StorageManager storage;
    private final Parser parser;

    public LogicManager(ModelManager model, StorageManager storage) {
        this.model = model;
        this.storage = storage;
        this.parser = new Parser();
    }

    /**
     * Executes the command of a Command object and saves the moduleList and user to the storage file
     * @param command command
     * @return CommandResult from the execution of the command
     * @throws PenusException exception
     */
    public CommandResult execute(Command command) throws PenusException {
        CommandResult commandResult = command.execute(model);
        storage.saveStorage(model.getModuleList(), model.getUser());

        return commandResult;
    }
    
    /**
     * Directs the commandText string to the Parser object for parsing commands
     * @param commandText string
     * @return Command object
     * @throws PenusException exception
     */
    public Command getCommand(String commandText) throws PenusException {
        return parser.parseCommand(commandText);
    }
}
