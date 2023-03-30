package seedu.penus.logic.commands;

import seedu.penus.model.ModelManager;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    @Override
    public CommandResult execute(ModelManager model) {
        return new CommandResult(
            "exit"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\tExits the program\n" +
            "\tlist [FILTER]" 
                + "\t\t\t\t\t\t\t\t\t\t\tDisplays a list of all modules taken or planned in the specified " +
                    "Year or Semester\n"
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tIf [FILTER] is not specified, then all modules will shown.\n" +
            "\tmark [MODULE CODE] g/[GRADE]" 
                + "\t\t\t\t\t\t\tMarks the module that has been cleared, while updating its grades\n" +
            "\tplan [MODULE CODE] y/[YEAR] s/[SEMESTER]" 
                + "\t\t\t\tAdds a module to the planner as an untaken module\n" +
            "\tremove [MODULECODE]" 
                + "\t\t\t\t\t\t\t\t\t\tRemoves a module from the planner\n" +
            "\tstatus" 
                + "\t\t\t\t\t\t\t\t\t\t\t\t\tDisplays the status of Core Modules and MCs taken\n" +
            "\tdetails [MODULECODE]"
                + "\t\t\t\t\t\t\t\t\tDisplays the details of given module, including Title, Description, " +
                    "Prerequisites, Module Credits \n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tand if it can be SU-ed.\n" +
            "\ttaken [MODULE CODE] y/[YEAR] s/[SEMESTER] g/[GRADE]"
                + "\t\tAdds a module to the planner as a module you have already taken",
            false
        );
    }
}
