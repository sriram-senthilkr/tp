package seedu.penus.logic.commands;

import seedu.penus.model.ModelManager;

public class InitCommand extends Command {
    public static final String COMMAND_WORD = "init";

    public static final String MESSAGE = "Name and course?";

    @Override
    public CommandResult execute(ModelManager model) {
        /** 
         * TODO: implement init
         * HOW: 
         * use model.setUserName() and setUserCourse() to set user prefs
         * printing stuff edit the MESSAGE, 
         * 
         * if need more, add a new command to Ui.java
         * Alternative: change command to init [NAME] [COURSE]
        */
        return new CommandResult(MESSAGE);
    }
}
