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
    @Override
    public CommandResult execute(ModelManager model) {
        return new CommandResult(
                (String.format("%-51s %s", "clear [FILTER]", "Clears modules in the specified Year or Semester.") +
                         "\n\t" + String.format("%-51s %s", "", "If [FILTER] is not specified, then all modules will cleared.") +
                        "\n\n\n\t" + String.format("%-51s %s", "exit", "Exits the program.") +
                        "\n\n\n\t" + String.format("%-51s %s", "list [FILTER]", "Displays a list of all modules taken or planned in the specified Year or Semester.") +
                        "\n\t" + String.format("%-51s %s", "", "If [FILTER] is not specified, then all modules will shown.") +
                        "\n\n\n\t" + String.format("%-51s %s", "mark [MODULE CODE] g/[GRADE]", "Marks the module that has been cleared, while updating its grades.") +
                        "\n\n\n\t" + String.format("%-51s %s", "plan [MODULE CODE] y/[YEAR] s/[SEMESTER]", "Adds a module to the planner as an untaken module.") +
                        "\n\n\n\t" + String.format("%-51s %s", "remove [MODULECODE]", "Removes a module from the planner.") +
                        "\n\n\n\t" + String.format("%-51s %s", "status", "Displays the status of Core Modules and MCs taken.") +
                        "\n\n\n\t" + String.format("%-51s %s", "taken [MODULE CODE] y/[YEAR] s/[SEMESTER] g/[GRADE]", "Adds a module to the planner as a module you have already taken.") +
                        "\n\n\n\t" + String.format("%-51s %s", "details [MODULE CODE]", "Displays the details of given module, including Title, Description, Prerequisites, Module Credits") +
                        "\n\t" + String.format("%-51s %s", "", "and if it can be SU-ed.") +
                        "\n\n\n\t" + String.format("%-51s %s", "init n/[NAME] c/[COURSE NUMBER]", "Exits the program.") +
                        "\n\t" + String.format("%-51s %s", "", "Initialize User.") +
                        "\n\t" + String.format("%-51s %s", "", "[COURSE NUMBER] -> [COURSE NAME]") +
                        "\n\t" + String.format("%-51s %s", "", "1 -> Biomedical Engineering") +
                        "\n\t" + String.format("%-51s %s", "", "2 -> Chemical Engineering") +
                        "\n\t" + String.format("%-51s %s", "", "3 -> Civil Engineering") +
                        "\n\t" + String.format("%-51s %s", "", "4 -> Computer Engineering") +
                        "\n\t" + String.format("%-51s %s", "", "5 -> Electrical Engineering") +
                        "\n\t" + String.format("%-51s %s", "", "6 -> Environmental Engineering") +
                        "\n\t" + String.format("%-51s %s", "", "7 -> Industrial and Systems Engineering") +
                        "\n\t" + String.format("%-51s %s", "", "8 -> Mechanical Engineering")
                        ), false);

    }
}
