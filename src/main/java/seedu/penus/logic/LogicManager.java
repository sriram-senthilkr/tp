package seedu.penus.logic;

import seedu.penus.model.ModelManager;
import seedu.penus.storage.StorageManager;
import seedu.penus.logic.parser.Parser;
import seedu.penus.logic.commands.CommandResult;
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

    public CommandResult execute(Command command) {

        CommandResult commandResult = command.execute(model);

        try {
            storage.saveStorage(model.getModuleList(), model.getUser());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    public Command getCommand(String commandText) {
        Command command = parser.parseCommand(commandText);

        return command;
    }
}
