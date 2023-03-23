package seedu.penus;

import seedu.penus.modules.ModuleList;
import seedu.penus.parser.CommandParser;
import seedu.penus.storage.FileManager;
import seedu.penus.ui.Ui;

public class Penus {
    /**
     * Main entry-point for the java.penus.Penus application.
     */
    public static void main(String[] args) {

        FileManager fileManager = new FileManager();
        ModuleList moduleList = new ModuleList(fileManager.retrieveUser(),fileManager.retrieveMods());
        CommandParser parser = new CommandParser(moduleList);
        Ui.printWelcome();
        parser.getInput(fileManager);
        Ui.printExit();
    }
}
