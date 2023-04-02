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
        String expectedString =
                "-------------------------- User --------------------------\n" +
                "\tUser: bentohset\n" +
                "\tCourse: Computer Engineering\n" +
                "\t------------------- Core Modules Taken --------------------\n" +
                "\tGESS1004 Singapore and India: Emerging Relations MCs: 4\n" +
                "\tCS2040C Data Structures and Algorithms MCs: 4\n" +
                "\tCS1231 Discrete Structures MCs: 4\n" +
                "\t----------------- Core Modules Not Taken ------------------\n" +
                "\tGECXXXX\n" +
                "\tGENXXXX\n" +
                "\tEE2211 Introduction to Machine Learning MCs: 4\n" +
                "\tEG1311 Design and Make MCs: 4\n" +
                "\t------------------------ MCs Status -----------------------\n" +
                "\tCore Modules MCs Taken: 12\n" +
                "\tElective MCs Taken: 8\n" +
                "\tTotal MCs Taken: 20/160\n";
        assertEquals(expectedString, result.feedbackToUser);
    }
}
