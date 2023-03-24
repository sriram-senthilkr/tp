package seedu.penus.logic.commands;

import seedu.penus.model.ModelManager;

public class StatusCommand extends Command {
    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE = "Status of";

    @Override
    public CommandResult execute(ModelManager model) {
        /*
          TODO: implement status
          HOW:
          use model.getUser to retrieve user data
          use model.getXXX (scroll to bottom) to retrieve core mod stuff

          should resource be under models?

          NOTE:
          modelManager should only have simple commands to edit the moduleList

          additional helper functions: create in utils

          printing stuff: edit the MESSAGE, (refer to other commands for example)
          if need more, add a new command to Ui.java

          TO RETURN:
          one string of status in with taken and untaken core mods
          lines separated by /n
          useful to use String.format with "%s"
        */
        return new CommandResult(MESSAGE);
    }
}
