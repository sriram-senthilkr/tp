package seedu.penus.logic.commands;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import seedu.penus.common.exceptions.InvalidCommandException;
import seedu.penus.model.ModelManager;
import seedu.penus.model.Module;
import seedu.penus.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveCommandTest {
    private final ModelManager model = new ModelManager(new User(), new ArrayList<>(),
                                                         new ArrayList<>(), new HashMap<>());

    private RemoveCommand command;

    @Test
    public void testValidModuleSuccess() throws InvalidCommandException {
        Module module = new Module("CS2113", 2, 1);
        model.addModule(module);

        command = new RemoveCommand("CS2113");

        CommandResult result = command.execute(model);

        assertEquals(String.format(RemoveCommand.MESSAGE, module, model.getSize()), result.feedbackToUser);
        assertEquals(0, model.getSize());
    }

    //module does not exist
    @Test
    public void testInvalidModuleThrowsInvalidCommandException() {
        command = new RemoveCommand("CS2113");

        assertThrows(InvalidCommandException.class, () -> command.execute(model));
    }

    @Test
    public void testExecute_moduleNotFound_throwsException() {

        model.addModule(new Module("CS2113", 2, 2));
        RemoveCommand command = new RemoveCommand("CS2105");

        assertThrows(InvalidCommandException.class, () -> command.execute(model));
    }
}
