package seedu.penus.logic.commands;

import seedu.penus.common.exceptions.DuplicateModuleException;
import seedu.penus.common.exceptions.InvalidCommandException;
import seedu.penus.common.exceptions.InvalidModuleException;
import seedu.penus.common.exceptions.PenusException;
import seedu.penus.model.ModelManager;
import seedu.penus.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TakenCommandTest {
    private final ModelManager model = new ModelManager(new User(), new ArrayList<>(),
                                                         new ArrayList<>(), new HashMap<>());

    private TakenCommand command;

    @Test
    public void testValidModuleSuccess() throws PenusException {
        command = new TakenCommand("CS2113", 2, 2, "A+");
        CommandResult result = command.execute(model);

        assertEquals(String.format(TakenCommand.MESSAGE, command.taken, model.getSize()), result.feedbackToUser);
        assertEquals(1, model.getSize());
    }

    @Test
    public void testDuplicateModuleThrowsDuplicateModuleException() throws PenusException {
        TakenCommand command1 = new TakenCommand("CS2113", 2, 2, "A+");
        TakenCommand command2 = new TakenCommand("CS2113", 2, 2, "B+");

        command1.execute(model);

        assertThrows(DuplicateModuleException.class, () -> command2.execute(model));
    }

    @Test
    public void testInvalidModuleThrowsInvalidModuleException() throws PenusException {
        TakenCommand command = new TakenCommand("CS211300 ", 2, 2, "A+");
        
        assertThrows(InvalidModuleException.class, () -> command.execute(model));
    }

    //su
    @Test
    public void testInvalidSThrowsInvalidCommandException() throws PenusException {
        TakenCommand command = new TakenCommand("CS2113", 2, 2, "S");
        
        assertThrows(InvalidCommandException.class, () -> command.execute(model));
    }

    @Test
    public void testInvalidUThrowsInvalidCommandException() throws PenusException {
        TakenCommand command = new TakenCommand("CS2113", 2, 2, "U");
        
        assertThrows(InvalidCommandException.class, () -> command.execute(model));
    }

    @Test
    public void testValidUSuccess() throws PenusException {
        TakenCommand command = new TakenCommand("CS1231", 2, 2, "S");
        CommandResult result = command.execute(model);

        assertEquals(String.format(TakenCommand.MESSAGE, command.taken, model.getSize()), result.feedbackToUser);
        assertEquals(1, model.getSize());
    }

    @Test
    public void testValidSSuccess() throws PenusException {
        TakenCommand command = new TakenCommand("CS1231", 2, 2, "U");
        CommandResult result = command.execute(model);

        assertEquals(String.format(TakenCommand.MESSAGE, command.taken, model.getSize()), result.feedbackToUser);
        assertEquals(1, model.getSize());
    }
}
