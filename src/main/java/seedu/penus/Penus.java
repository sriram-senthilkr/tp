package seedu.penus;

import seedu.penus.modules.ModuleList;
import seedu.penus.parser.CommandParser;
import seedu.penus.ui.Ui;

public class Penus {
    /**
     * Main entry-point for the java.penus.Penus application.
     */
    public static void main(String[] args) {

        ModuleList moduleList = new ModuleList();
        CommandParser parser = new CommandParser(moduleList);
        Ui.printWelcome();
        parser.getInput();
        Ui.printExit();
    }
}
