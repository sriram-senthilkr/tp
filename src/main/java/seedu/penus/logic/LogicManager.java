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

    public CommandResult execute(Command command) throws PenusException {
        CommandResult commandResult = command.execute(model);
        storage.saveStorage(model.getModuleList(), model.getUser());

        return commandResult;
    }

    public Command getCommand(String commandText) throws PenusException {
        Command command = parser.parseCommand(commandText);

        return command;
    }
}
