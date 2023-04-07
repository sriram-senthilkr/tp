package seedu.penus.logic.commands;

import org.junit.jupiter.api.Test;
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
    public void testSuccessListNoFilter() throws PenusException {
        Module module = new Module("CS2113", 2, 1);
        model.addModule(module);
        command = new ListCommand();

        CommandResult result = command.execute(model);

        assertEquals(result.feedbackArray.get(result.feedbackArray.size() - 1), "Overall CAP : 0.00\n");
    }

    //add taken module cap should be correct, filter list
    @Test
    public void testSuccessListFilterYearSem() throws PenusException {
        Module module = new Module("CS2113", 2, 1, "B+");
        model.addModule(module);
        command = new ListCommand(2, 1);

        CommandResult result = command.execute(model);

        assertEquals(result.feedbackArray.get(result.feedbackArray.size() - 1), "Overall CAP : 4.00\n");
    }

    @Test
    public void testSuccessListFilterYear() throws PenusException {
        Module module = new Module("CS2113", 2, 1, "B+");
        model.addModule(module);
        command = new ListCommand(2,0);

        CommandResult result = command.execute(model);

        assertEquals(result.feedbackArray.get(result.feedbackArray.size() - 1), "Overall CAP : 4.00\n");
    }

    @Test
    public void testSuccessEmptyListYearSem() throws PenusException {
        command = new ListCommand(1,2);

        CommandResult result = command.execute(model);

        assertEquals(result.feedbackArray.get(result.feedbackArray.size() - 1), "Overall CAP : 0.00\n");
    }

    @Test
    public void testSuccessEmptyListNoFilter() throws PenusException {
        command = new ListCommand();

        CommandResult result = command.execute(model);

        assertEquals(result.feedbackArray.get(result.feedbackArray.size() - 1), "Overall CAP : 0.00\n");
    }
}
