package seedu.penus.logic.commands;

import seedu.penus.model.ModelManager;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    @Override
    public CommandResult execute(ModelManager model) {
        return new CommandResult(
                ("\n\t" + String.format("%-52s %-97s", "Command", "Description") +
                        "\n\t" + String.format("%-52s %-97s", "-------", "-----------") +
                        "\n\t" + String.format("%-52s %-97s",
                        "clear [FILTER]", "Clears modules in the specified Year or Semester.") +
                        "\n\t" + String.format("%-52s %-97s", "",
                        "If [FILTER] is not specified, then all modules will cleared.") +
                        "\n\n\n\t" + String.format("%-52s %-97s", "exit", "Exits the program.") +
                        "\n\n\n\t" + String.format("%-52s %-97s", "list [FILTER]",
                        "Displays a list of all modules taken or planned ") +
                        "\n\t" + String.format("%-52s %-97s", "", "in the specified Year or Semester.") +
                        "\n\t" + String.format("%-52s %-97s", "",
                        "If [FILTER] is not specified, then all modules will shown.") +
                        "\n\n\n\t" + String.format("%-52s %-97s", "mark [MODULE CODE] g/[GRADE]",
                        "Marks the module that has been cleared, while updating its grades.") +
                        "\n\n\n\t" + String.format("%-52s %-97s", "plan [MODULE CODE] y/[YEAR] s/[SEMESTER]",
                        "Adds a module to the planner as an untaken module.") +
                        "\n\n\n\t" + String.format("%-52s %-97s",
                        "remove [MODULECODE]", "Removes a module from the planner.") +
                        "\n\n\n\t" + String.format("%-52s %-97s",
                        "status", "Displays the status of Core Modules and MCs taken.") +
                        "\n\n\n\t" + String.format("%-52s %-97s",
                        "taken [MODULE CODE] y/[YEAR] s/[SEMESTER] g/[GRADE]",
                        "Adds a module to the planner as a module you have already taken.") +
                        "\n\n\n\t" + String.format("%-52s %-97s", "details [MODULE CODE]",
                        "Displays the details of given module, including") +
                        "\n\t" + String.format("%-52s %-97s", "",
                        "Title, Description, Prerequisites, Module Credits") +
                        "\n\t" + String.format("%-52s %-97s", "", "and if it can be SU-ed.") +
                        "\n\n\n\t" + String.format("%-52s %-97s",
                        "init n/[NAME] c/[COURSE NUMBER]", "Initialize User.") +
                        "\n" +
                        "\n\t" + String.format("%-52s %-97s", "", "[COURSE NUMBER] -> [COURSE NAME]") +
                        "\n\t" + String.format("%-52s %-97s", "", "1 -> Biomedical Engineering") +
                        "\n\t" + String.format("%-52s %-97s", "", "2 -> Chemical Engineering") +
                        "\n\t" + String.format("%-52s %-97s", "", "3 -> Civil Engineering") +
                        "\n\t" + String.format("%-52s %-97s", "", "4 -> Computer Engineering") +
                        "\n\t" + String.format("%-52s %-97s", "", "5 -> Electrical Engineering") +
                        "\n\t" + String.format("%-52s %-97s", "", "6 -> Environmental Engineering") +
                        "\n\t" + String.format("%-52s %-97s", "", "7 -> Industrial and Systems Engineering") +
                        "\n\t" + String.format("%-52s %-97s", "", "8 -> Mechanical Engineering") +
                        "\n\t"
                ), false);

    }
}
