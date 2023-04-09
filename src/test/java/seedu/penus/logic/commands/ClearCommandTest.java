package seedu.penus.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.penus.common.exceptions.PenusException;
import seedu.penus.model.ModelManager;
import seedu.penus.testutils.SampleData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ClearCommandTest {
    private ModelManager model;
    private ClearCommand clearCommand;

    @BeforeEach
    public void setUp() {
        model = SampleData.getSampleModel();
    }

    @Test
    public void testClearYearAndSemModsSuccess() throws PenusException {
        clearCommand = new ClearCommand(1, 1);
        clearCommand.execute(model);
        assertFalse(model.hasModuleCode("CS2040C"));
        assertFalse(model.hasModuleCode("CS1231"));
        assertTrue(model.hasModuleCode("GESS1004"));
        assertTrue(model.hasModuleCode("GEC1015"));
        assertTrue(model.hasModuleCode("BN1111"));
        assertTrue(model.hasModuleCode("BN2111"));
        assertEquals(4, model.getSize());
    }

    @Test
    public void testClearYearModsSuccess() throws PenusException {
        clearCommand = new ClearCommand(1, 0);
        clearCommand.execute(model);
        assertFalse(model.hasModuleCode("CS2040C"));
        assertFalse(model.hasModuleCode("CS1231"));
        assertFalse(model.hasModuleCode("GESS1004"));
        assertFalse(model.hasModuleCode("GEC1015"));
        assertTrue(model.hasModuleCode("BN1111"));
        assertTrue(model.hasModuleCode("BN2111"));
        assertEquals(2, model.getSize());
    }

    @Test
    public void testClearAllModsSuccess() throws PenusException {
        clearCommand = new ClearCommand(0, 0);
        clearCommand.execute(model);
        assertEquals(0, model.getSize());
    }
}
