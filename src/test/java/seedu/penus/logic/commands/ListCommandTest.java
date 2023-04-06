package seedu.penus.logic.commands;

import org.junit.Test;
import seedu.penus.common.exceptions.PenusException;
import seedu.penus.model.ModelManager;
import seedu.penus.model.Module;
import seedu.penus.model.User;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    private final ModelManager model = new ModelManager(new User(), new ArrayList<>(),
                                                         new ArrayList<>(), new HashMap<>());
    private ListCommand command;

    //add plan module, cap should be 0
    @Test
    public void test_successList_noFilter() throws PenusException {
        Module module = new Module("CS2113", 2, 1);
        model.addModule(module);
        command = new ListCommand();

        CommandResult result = command.execute(model);

        assertEquals(result.feedbackArray.get(result.feedbackArray.size() - 1), "Overall CAP : 0.00\n");
    }

    //add taken module cap should be correct, filter list
    @Test
    public void test_successList_filterYearSem() throws PenusException {
        Module module = new Module("CS2113", 2, 1, "B+");
        model.addModule(module);
        command = new ListCommand(2, 1);

        CommandResult result = command.execute(model);

        assertEquals(result.feedbackArray.get(result.feedbackArray.size() - 1), "Overall CAP : 4.00\n");
    }
}
