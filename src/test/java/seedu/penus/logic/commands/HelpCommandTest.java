package seedu.penus.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.penus.model.ModelManager;
import seedu.penus.model.User;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {

    @Test
    public void testExecute() {
        final ModelManager model = new ModelManager(new User(), new ArrayList<>(),
                new ArrayList<>(), new HashMap<>());

        CommandResult expected = new CommandResult(
                ("\n\t" + String.format("%-52s %s", "Command", "Description") +
                        "\n\t" + String.format("%-52s %s", "-------", "-----------") +
                        "\n\t" + String.format("%-52s %s",
                        "clear [FILTER]", "Clears modules in the specified Year or Semester.") +
                        "\n\t" + String.format("%-52s %s", "",
                        "If [FILTER] is not specified, then all modules will cleared.") +
                        "\n\n\t" + String.format("%-52s %s", "exit", "Exits the program.") +
                        "\n\n\t" + String.format("%-52s %s", "list [FILTER]",
                        "Displays a list of all modules taken or planned") +
                        "\n\t" + String.format("%-52s %s", "", "in the specified Year or Semester.") +
                        "\n\t" + String.format("%-52s %s", "",
                        "If [FILTER] is not specified, then all modules will shown.") +
                        "\n\n\t" + String.format("%-52s %s", "mark [MODULE CODE] g/[GRADE]",
                        "Marks the module that has been cleared, while updating its grades.") +
                        String.format("%n %n \t") +
                        String.format("%-52s %s", "plan [MODULE CODE] y/[YEAR] s/[SEMESTER]",
                                "Adds a module to the planner as an untaken module.") +
                        "\n\n\t" + String.format("%-52s %s",
                        "remove [MODULECODE]", "Removes a module from the planner.") +
                        "\n\n\t" + String.format("%-52s %s",
                        "status", "Displays the status of Core Modules and MCs taken.") +
                        "\n\n\t" + String.format("%-52s %s",
                        "taken [MODULE CODE] y/[YEAR] s/[SEMESTER] g/[GRADE]",
                        "Adds a module to the planner as a module you have already taken.") +
                        "\n\n\t" + String.format("%-52s %s", "details [MODULE CODE]",
                        "Displays the details of given module, including") +
                        "\n\t" + String.format("%-52s %s", "",
                        "Title, Description, Prerequisites, Module Credits") +
                        "\n\t" + String.format("%-52s %s", "", "and if it can be SU-ed.") +
                        "\n\n\t" + String.format("%-52s %s",
                        "init n/[NAME] c/[COURSE NUMBER]", "Initialize User.") +
                        "\n\n\t" + String.format("%-52s %s", "", "[COURSE NUMBER] -> [COURSE NAME]") +
                        "\n\t" + String.format("%-52s %s", "", "1 -> Biomedical Engineering") +
                        "\n\t" + String.format("%-52s %s", "", "2 -> Chemical Engineering") +
                        "\n\t" + String.format("%-52s %s", "", "3 -> Civil Engineering") +
                        "\n\t" + String.format("%-52s %s", "", "4 -> Computer Engineering") +
                        "\n\t" + String.format("%-52s %s", "", "5 -> Electrical Engineering") +
                        "\n\t" + String.format("%-52s %s", "", "6 -> Environmental Engineering") +
                        "\n\t" + String.format("%-52s %s", "", "7 -> Industrial and Systems Engineering") +
                        "\n\t" + String.format("%-52s %s", "", "8 -> Mechanical Engineering") +
                        "\n\t"
                ), false);
        CommandResult actual = new HelpCommand().execute(model);
        assertEquals(expected.feedbackToUser, actual.feedbackToUser);
    }
}
