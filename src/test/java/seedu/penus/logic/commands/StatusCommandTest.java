package seedu.penus.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.penus.common.exceptions.PenusException;
import seedu.penus.model.ModelManager;
import seedu.penus.testutils.SampleData;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StatusCommandTest {
    private ModelManager model;
    private StatusCommand statusCommand;

    @BeforeEach
    public void setUp() {
        model = SampleData.getSampleModel();
        statusCommand = new StatusCommand();
    }
    @Test
    public void testGetTakenCoreModsListSuccess() {
        assertEquals("GESS1004", statusCommand.getTakenCoreModsList(model).get(0));
        assertEquals("CS2040C", statusCommand.getTakenCoreModsList(model).get(1));
        assertEquals(3 , statusCommand.getTakenCoreModsList(model).size());
    }

    @Test
    public void testGetUntakenCoreModsListSuccess() {
        assertEquals("EE2211", statusCommand.getUntakenCoreModsList(model).get(0));
        assertEquals(2 , statusCommand.getUntakenCoreModsList(model).size());
    }

    @Test
    public void testModuleCodeToStringSuccess() {
        assertEquals("DTK1234 Design Thinking MCs: 4", statusCommand.moduleCodeToString("DTK1234"));
        assertEquals("EE2211 Introduction to Machine Learning MCs: 4", statusCommand.moduleCodeToString("EE2211"));
    }

    @Test
    public void testStatusCommandSuccess() throws PenusException {
        CommandResult result = statusCommand.execute(model);
        String expectedString = "--------- Taken ---------\n" +
                "GESS1004 Singapore and India: Emerging Relations MCs: 4\n" +
                "CS2040C Data Structures and Algorithms MCs: 4\n" +
                "CS1231 Discrete Structures MCs: 4\n" +
                "--------- Not Taken ---------\n" +
                "GECXXXX\n" +
                "GENXXXX\n" +
                "EE2211 Introduction to Machine Learning MCs: 4\n" +
                "EG1311 Design and Make MCs: 4\n" +
                "MCs Taken: 20/160\n";
        assertEquals(expectedString, result.feedbackToUser);
    }
}
