package seedu.penus.logic.commands;

import seedu.penus.model.ModelManager;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE = "List of";

    @Override
    public CommandResult execute(ModelManager model) {
        /*
          TODO: implement list
          HOW:
          use or add methods to modelManager to get list of mods

          NOTE:
          ONLY ALGORITHM/ DATA MANIPULATION IMPLEMENTED HERE + relevant Exceptions
          parsing + formatting exceptions: add in Parser.java, under listParser(args)
          modelManager should only have simple commands to manipulate the moduleList

          additional helper functions: create in utils

          printing stuff: edit the MESSAGE, (refer to other commands for example)
          if need more, add a new command to Ui.java

          TO RETURN:
          one string of module list in year and sem with CAP
          lines separated by /n
          useful to use String.format with "%s"
        */
        return new CommandResult(MESSAGE);
    }
}
