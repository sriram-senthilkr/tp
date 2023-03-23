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
        this.model = new ModelManager(storage.loadUser(),storage.loadStorage());
        this.logic = new LogicManager(model, storage);
        ui.printWelcome();
    }

    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = logic.getCommand(userCommandText);
            CommandResult result = executeCommand(command);
            ui.printResult(result);
        } while (!ExitCommand.isExit(command));
    }

    private CommandResult executeCommand(Command command) {
        try {
            CommandResult result = logic.execute(command);
            return result;
        } catch (Exception e) {
            ui.printMessage(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void exit() {
        ui.printExit();
        System.exit(0);
    }
}
