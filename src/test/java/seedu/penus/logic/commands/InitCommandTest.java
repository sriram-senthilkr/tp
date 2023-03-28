package seedu.penus.logic.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import seedu.penus.common.exceptions.InvalidCommandException;
import seedu.penus.common.exceptions.PenusException;
import seedu.penus.model.ModelManager;
import seedu.penus.model.User;
import java.util.ArrayList;
import java.util.HashMap;


public class InitCommandTest {
    private final ModelManager modelManager = new ModelManager(new User(), new ArrayList<>(),
                                                         new ArrayList<>(), new HashMap<>());
    private InitCommand initCommand;

    @Test
    public void testInitCommandSuccess() throws Exception {
        initCommand = new InitCommand("John Doe", 1);
        CommandResult commandResult = initCommand.execute(modelManager);
        assertEquals("John Doe", modelManager.getUserName());
        assertEquals("Biomedical Engineering", modelManager.getUserCourse());
    }

    @Test
    public void execute_invalidCourseCode_throwsInvalidCommandException() {
        initCommand = new InitCommand("John Doe", 10);
        PenusException invalidCommandException = assertThrows(InvalidCommandException.class, () -> initCommand.execute(modelManager));
        assertEquals("Error: Enter within the index. Please initialize again", invalidCommandException.getMessage());
    }
}