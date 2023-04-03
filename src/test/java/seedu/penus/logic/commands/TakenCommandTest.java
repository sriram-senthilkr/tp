package seedu.penus.logic.commands;

import seedu.penus.common.exceptions.DuplicateModuleException;
import seedu.penus.common.exceptions.PenusException;
import seedu.penus.model.ModelManager;
import seedu.penus.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TakenCommandTest {
    private final ModelManager model = new ModelManager(new User(), new ArrayList<>(),
                                                         new ArrayList<>(), new HashMap<>());

    private TakenCommand command;

    @Test
    public void execute_validModule_success() throws PenusException {
        command = new TakenCommand("CS2113", 2, 2, "A+");
        CommandResult result = command.execute(model);

        assertEquals(String.format(TakenCommand.MESSAGE, command.taken, model.getSize()), result.feedbackToUser);
        assertEquals(1, model.getSize());
    }

    @Test
    public void execute_duplicateModule_throwsDuplicateModuleException() throws PenusException {
        TakenCommand command1 = new TakenCommand("CS2113", 2, 2, "A+");
        TakenCommand command2 = new TakenCommand("CS2113", 2, 2, "B+");

        command1.execute(model);

        assertThrows(DuplicateModuleException.class, () -> command2.execute(model));
    }

    //test for invalid module
}
