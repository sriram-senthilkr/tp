package seedu.penus.logic.commands;

import seedu.penus.model.ModelManager;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    @Override
    public CommandResult execute(ModelManager model) {
        return new CommandResult(
            "\texit" 
                + "\t\t\t\t\t\t\t\tExits the program" +
            "\tlist [FILTER]" 
                + "\t\t\t\t\t\t\tDisplays a list of all modules taken or planned in the specified Year or Semester\n"
                + "\t\t\t\t\t\t\t\t\tIf [FILTER] is not specified, then all modules will shown." +
            "\tmark [MODULE CODE] g/[GRADE]" 
                + "\t\t\t\t\tMarks the module that has been cleared, while updating its grades" +
            "\tplan [MODULE CODE] y/[YEAR] s/[SEMESTER]" 
                + "\t\t\tAdds a module to the planner as an untaken module" +
            "\tremove [MODULECODE]" 
                + "\t\t\t\t\t\tRemoves a module from the planner" + 
            "\tstatus" 
                + "\t\t\t\t\t\t\t\tDisplays the status of Core Modules and MCs taken" +
            "\ttaken [MODULE CODE] y/[YEAR] s/[SEMESTER] g/[GRADE]"
                + "\t\tAdds a module to the planner as a module you have already taken"
        );
    }
}
