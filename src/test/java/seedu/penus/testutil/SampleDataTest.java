package seedu.penus.testutil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.penus.model.ModelManager;
import seedu.penus.testutils.SampleData;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleDataTest {
    private ModelManager model;

    @BeforeEach
    public void setUp() {
        model = SampleData.getSampleModel();
    }

    @Test
    public void testSampleUserSuccess() {
        assertEquals("bentohset", model.getUserName());
        assertEquals("Computer Engineering", model.getUserCourse());
    }

    @Test
    public void testSampleCoreModsListSuccess() {
        assertTrue(model.getCoreModList().containsKey("Computer Engineering"));
        assertEquals("CS2040C", model.getCoreModList().get("Computer Engineering").get(0));
    }

    @Test
    public void testSampleModulesSuccess() {
        assertEquals("CS2040C", model.getModule(0).getCode());
        assertEquals("CS1231", model.getModule(1).getCode());
        assertEquals("GESS1004", model.getModule(2).getCode());
        assertEquals("Taken", model.getModule(2).getStatus());
        assertEquals("Plan", model.getModule(3).getStatus());
        assertEquals("CS2040C", model.getModuleList().getModule(0).getCode());
    }

    @Test
    public void testGetCoreModsListFromUserCourseSuccess() {
        assertEquals("CS2040C", model.getCoreModList().get(model.getUserCourse()).get(0));
    }
}


