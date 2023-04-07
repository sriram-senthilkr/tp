package seedu.penus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;



public class ExitCommandTest {
    @Test
    public void isExit_returnsTrue() {
        assertTrue(ExitCommand.isExit(new ExitCommand()));
    }

    @Test
    public void testExecuteReturnsCorrectResult() {
        CommandResult expectedResult = new CommandResult(ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT, false);
        CommandResult actualResult = new ExitCommand().execute(null);
        assertEquals(expectedResult.feedbackToUser, actualResult.feedbackToUser);
    }
}
