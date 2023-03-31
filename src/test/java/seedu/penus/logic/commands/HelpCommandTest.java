package seedu.penus.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.penus.model.ModelManager;
import seedu.penus.model.User;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {

    @Test
    public void execute() {
        final ModelManager model = new ModelManager(new User(), new ArrayList<>(),
                                                         new ArrayList<>(), new HashMap<>());

        CommandResult expected = new CommandResult(
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
                + "\t\tAdds a module to the planner as a module you have already taken", 
            false
        );
        CommandResult actual = new HelpCommand().execute(model);
        assertEquals(expected.feedbackToUser, actual.feedbackToUser);
    }
}
