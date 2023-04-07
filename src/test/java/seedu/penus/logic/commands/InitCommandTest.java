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
    public void testInitChemCommandSuccess() throws Exception {
        initCommand = new InitCommand("John Doe", 2);
        CommandResult commandResult = initCommand.execute(modelManager);
        assertEquals("John Doe", modelManager.getUserName());
        assertEquals("Chemical Engineering", modelManager.getUserCourse());
    }
    @Test
    public void testInitCivilCommandSuccess() throws Exception {
        initCommand = new InitCommand("John Doe", 3);
        CommandResult commandResult = initCommand.execute(modelManager);
        assertEquals("John Doe", modelManager.getUserName());
        assertEquals("Civil Engineering", modelManager.getUserCourse());
    }
    @Test
    public void testInitComputerCommandSuccess() throws Exception {
        initCommand = new InitCommand("John Doe", 4);
        CommandResult commandResult = initCommand.execute(modelManager);
        assertEquals("John Doe", modelManager.getUserName());
        assertEquals("Computer Engineering", modelManager.getUserCourse());
    }
    @Test
    public void testInitElectricalCommandSuccess() throws Exception {
        initCommand = new InitCommand("John Doe", 5);
        CommandResult commandResult = initCommand.execute(modelManager);
        assertEquals("John Doe", modelManager.getUserName());
        assertEquals("Electrical Engineering", modelManager.getUserCourse());
    }
    @Test
    public void testInitEnvCommandSuccess() throws Exception {
        initCommand = new InitCommand("John Doe", 6);
        CommandResult commandResult = initCommand.execute(modelManager);
        assertEquals("John Doe", modelManager.getUserName());
        assertEquals("Environmental Engineering", modelManager.getUserCourse());
    }
    @Test
    public void testInitISECommandSuccess() throws Exception {
        initCommand = new InitCommand("John Doe", 7);
        CommandResult commandResult = initCommand.execute(modelManager);
        assertEquals("John Doe", modelManager.getUserName());
        assertEquals("Industrial and Systems Engineering", modelManager.getUserCourse());
    }
    @Test
    public void testInitMechCommandSuccess() throws Exception {
        initCommand = new InitCommand("John Doe", 8);
        CommandResult commandResult = initCommand.execute(modelManager);
        assertEquals("John Doe", modelManager.getUserName());
        assertEquals("Mechanical Engineering", modelManager.getUserCourse());
    }

    @Test
    public void testExecuteInvalidCourseCodeThrowsInvalidCommandException() {
        initCommand = new InitCommand("John Doe", 10);
        PenusException invalidCommandException = assertThrows(InvalidCommandException.class,
                                                              () -> initCommand.execute(modelManager));
        assertEquals("Error: Enter within the index. Please initialize again", invalidCommandException.getMessage());
    }
}
