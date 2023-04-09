package seedu.penus;

import seedu.penus.common.exceptions.NoInternetException;
import seedu.penus.logic.utils.ModuleRetriever;
import seedu.penus.ui.Ui;
import seedu.penus.logic.LogicManager;
import seedu.penus.model.ModelManager;
import seedu.penus.storage.StorageManager;
import seedu.penus.logic.commands.Command;
import seedu.penus.logic.commands.CommandResult;
import seedu.penus.logic.commands.ExitCommand;

public class Penus {

    private Ui ui;
    private StorageManager storage;
    private ModelManager model;
    private LogicManager logic;

    public static void main(String[] args) {
        new Penus().run();
    }

    /** Run program till termination */
    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up required objects, loads data from file and prints welcome message
     */
    private void start() {
        this.ui = new Ui();
        this.storage = new StorageManager();
        try {
            ModuleRetriever.connectionChecker();
        } catch (NoInternetException e) {
            ui.printMessage(e.getMessage());
        }
        try {
            this.model = new ModelManager(
            storage.loadUser(),
            storage.loadStorage(),
            storage.loadCoreDetails(),
            storage.loadCoreModList()
            );
        } catch (Exception e) {
            ui.printStorageError(e.getMessage());
            //unchecked exception to exit application upon initialising with error
            throw new RuntimeException(e);
        }
        
        this.logic = new LogicManager(model, storage);
        ui.printWelcome();
    }

    /**
     * Reads the user command and executes it until user issues exit command
     */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            CommandResult result = null;
            String userCommandText = ui.getUserCommand();
            command = getCommand(userCommandText);
            if (command != null) {
                result = executeCommand(command);
            }
            if (result != null && result.isArray) {
                ui.printResultArray(result);
            }
            if (result != null && !result.isArray) {
                ui.printResultString(result);
            }
            
        } while (!ExitCommand.isExit(command));
    }

    /**
     * Executes the command and returns the result
     * @param command user command
     * @return CommandResult
     */
    private CommandResult executeCommand(Command command) {
        CommandResult result = null;
        try {
            result = logic.execute(command);
            
        } catch (Exception e) {
            ui.printMessage(e.getMessage());
        }
        return result;
    }

    /**
     * Directs the command input to the LogicManager to retrieve the Command object
     * @param commandText
     * @return Command object relating to the commandText
     */
    private Command getCommand(String commandText) {
        Command command = null;
        try {
            command = logic.getCommand(commandText);
        } catch (Exception e) {
            ui.printMessage(e.getMessage());
        }
        return command;
    }

    /** Exits */
    private void exit() {
        System.exit(0);
    }
}
