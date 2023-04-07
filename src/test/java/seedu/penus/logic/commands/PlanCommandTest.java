package seedu.penus.logic.commands;

import seedu.penus.common.exceptions.DuplicateModuleException;
import seedu.penus.common.exceptions.InvalidModuleException;
import seedu.penus.common.exceptions.PenusException;
import seedu.penus.model.ModelManager;
import seedu.penus.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlanCommandTest {
    private ModelManager model;
    private PlanCommand command;

    @BeforeEach
    public void setup() {
        model = new ModelManager(new User(), new ArrayList<>(), new ArrayList<>(), new HashMap<>());
    }
    @Test
    public void testExecuteValidModuleSuccess() throws PenusException {
        command = new PlanCommand("CS2113", 2, 2);
        CommandResult result = command.execute(model);

        assertEquals(String.format(PlanCommand.MESSAGE, command.plan, model.getSize()), result.feedbackToUser);
        assertEquals(1, model.getSize());
    }

    @Test
    public void testExecuteDuplicateModuleThrowsDuplicateModuleException() throws PenusException {
        PlanCommand command1 = new PlanCommand("CS2113", 2, 2);
        PlanCommand command2 = new PlanCommand("CS2113", 2, 2);

        command1.execute(model);

        assertThrows(DuplicateModuleException.class, () -> command2.execute(model));
    }

    @Test
    public void testExecuteInvalidModuleThrowsInvalidModuleException() throws PenusException {
        PlanCommand command = new PlanCommand("CS211300", 2, 2);
        
        assertThrows(InvalidModuleException.class, () -> command.execute(model));
    }
}
