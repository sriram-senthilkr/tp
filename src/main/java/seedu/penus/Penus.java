package seedu.penus;

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
    /**
     * Main entry-point for the java.penus.Penus application.
     */
    public static void main(String[] args) {
        new Penus().run();
    }

    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    private void start() {
        this.ui = new Ui();
        this.storage = new StorageManager();
        this.model = new ModelManager(
            storage.loadUser(),
            storage.loadStorage(),
            storage.loadCoreDetails(),
            storage.loadCoreModList()
        );
        this.logic = new LogicManager(model, storage);
        ui.printWelcome();
    }

    private void runCommandLoopUntilExitCommand() {
        Command command;
        CommandResult result = null;
        do {
            String userCommandText = ui.getUserCommand();
            command = getCommand(userCommandText);
            if (command != null) {
                result = executeCommand(command);
            }
            if (result != null) {
                ui.printResult(result);
            }
            
        } while (!ExitCommand.isExit(command));
    }

    private CommandResult executeCommand(Command command) {
        CommandResult result = null;
        try {
            result = logic.execute(command);
            
        } catch (Exception e) {
            ui.printMessage(e.getMessage());
        }
        return result;
    }

    private Command getCommand(String commandText) {
        Command command = null;
        try {
            command = logic.getCommand(commandText);
        } catch (Exception e) {
            ui.printMessage(e.getMessage());
        }
        return command;
    }

    private void exit() {
        System.exit(0);
    }
}
