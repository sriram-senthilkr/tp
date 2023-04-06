package seedu.penus.logic.commands;

import java.util.ArrayList;
import java.util.HashMap;
import seedu.penus.model.ModelManager;
import seedu.penus.model.Module;
import seedu.penus.model.User;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import seedu.penus.common.exceptions.InvalidCommandException;

public class MarkCommandTest {
    private final ModelManager model = new ModelManager(new User(), new ArrayList<>(),
            new ArrayList<>(), new HashMap<>());

    private MarkCommand command;

    @Test
    public void test_validModuleCode_success() throws InvalidCommandException {
        Module module = new Module("CS2113", 2, 1);
        model.addModule(module);

        command = new MarkCommand("CS2113", "A");
        CommandResult result = command.execute(model);
        Module markedModule = model.getModuleByCode("CS2113");
        

        assertEquals(String.format(MarkCommand.MESSAGE, module), result.feedbackToUser);
        assertEquals(markedModule.getStatus(), "Taken");
    }

    // create and execute a MarkCommand on a non-existent module
    @Test
    public void execute_invalidModuleCode_throwsInvalidCommandException() {
        MarkCommand markCommand = new MarkCommand("CS2113T", "A");
        assertThrows(InvalidCommandException.class, () -> markCommand.execute(model));
    }

    // create and execute a MarkCommand with grade "S" on the module, cannot be SU-ed
    @Test
    public void execute_suUnsuccessful_throwsInvalidCommandException() {
        Module module = new Module("CS2040C", 2, 1);
        model.addModule(module);

        MarkCommand markCommand = new MarkCommand("CS2040C", "S");
        assertThrows(InvalidCommandException.class, () -> markCommand.execute(model));
    }
}
