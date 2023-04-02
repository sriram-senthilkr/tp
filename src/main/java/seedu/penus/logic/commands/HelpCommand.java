package seedu.penus.logic.commands;

import seedu.penus.model.ModelManager;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    @Override
    public CommandResult execute(ModelManager model) {
        return new CommandResult(
            "clear [FILTER]" + "\t\t\t\t\t\t\tClears modules in the specified Year or Semester.\n"
                + "\t\t\t\t\t\t\t\t\tIf [FILTER] is not specified, then all modules will cleared.\n" +
            "\texit" 
                + "\t\t\t\t\t\t\t\tExits the program\n" +
            "\tlist [FILTER]" 
                + "\t\t\t\t\t\t\tDisplays a list of all modules taken or planned in the specified Year or Semester\n"
                + "\t\t\t\t\t\t\t\t\tIf [FILTER] is not specified, then all modules will shown.\n" +
            "\tmark [MODULE CODE] g/[GRADE]" 
                + "\t\t\t\t\tMarks the module that has been cleared, while updating its grades\n" +
            "\tplan [MODULE CODE] y/[YEAR] s/[SEMESTER]" 
                + "\t\t\tAdds a module to the planner as an untaken module\n" +
            "\tremove [MODULECODE]" 
                + "\t\t\t\t\t\tRemoves a module from the planner\n" + 
            "\tstatus" 
                + "\t\t\t\t\t\t\t\tDisplays the status of Core Modules and MCs taken\n" +
            "\ttaken [MODULE CODE] y/[YEAR] s/[SEMESTER] g/[GRADE]"
                + "\t\tAdds a module to the planner as a module you have already taken\n" +
            "\tdetails [MODULE CODE]" 
                + "\t\t\t\t\t\tDisplays the details of given module, including Title, Description, " 
                + "Prerequisites, Module Credits \n"
                +  "\t\t\t\t\t\t\t\t\tand if it can be SU-ed.\n" +
            "\tinit n/[NAME] c/[COURSE NUMBER]"
                + "\t\t\t\t\tInitialize User.\n" +
                    "\t\t\t\t\t\t\t\t\t [COURSE NUMBER] -> [COURSE NAME]\n"+
                    "\t\t\t\t\t\t\t\t\t 1 -> Biomedical Engineering\n" +
                    "\t\t\t\t\t\t\t\t\t 2 -> Chemical Engineering\n" +
                    "\t\t\t\t\t\t\t\t\t 3 -> Civil Engineering\n" +
                    "\t\t\t\t\t\t\t\t\t 4 -> Computer Engineering\n" +
                    "\t\t\t\t\t\t\t\t\t 5 -> Electrical Engineering\n" +
                    "\t\t\t\t\t\t\t\t\t 6 -> Environmental Engineering\n" +
                    "\t\t\t\t\t\t\t\t\t 7 -> Industrial and Systems Engineering\n" +
                    "\t\t\t\t\t\t\t\t\t 8 -> Mechanical Engineering\n"
            , 
            false
        );
    }
}
