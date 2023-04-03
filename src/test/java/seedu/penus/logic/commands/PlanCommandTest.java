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

public class PlanCommandTest {
    private final ModelManager model = new ModelManager(new User(), new ArrayList<>(),
                                                         new ArrayList<>(), new HashMap<>());

    private PlanCommand command;

    @Test
    public void execute_validModule_success() throws PenusException {
        command = new PlanCommand("CS2113", 2, 2);
        CommandResult result = command.execute(model);

        assertEquals(String.format(PlanCommand.MESSAGE, command.plan, model.getSize()), result.feedbackToUser);
        assertEquals(1, model.getSize());
    }

    @Test
    public void execute_duplicateModule_throwsDuplicateModuleException() throws PenusException {
        PlanCommand command1 = new PlanCommand("CS2113", 2, 2);
        PlanCommand command2 = new PlanCommand("CS2113", 2, 2);

        command1.execute(model);

        assertThrows(DuplicateModuleException.class, () -> command2.execute(model));
    }

    //test for invalid module
}
