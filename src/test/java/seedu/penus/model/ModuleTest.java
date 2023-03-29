package seedu.penus.model;


import org.junit.jupiter.api.Test;
import seedu.penus.common.exceptions.InvalidGradeException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModuleTest {
    @Test
    public void testGetCode() {
        Module module = new Module("CS1010", 2022, 1);
        assertEquals("CS1010", module.getCode());
    }

    @Test
    public void testGetYear() {
        Module module = new Module("CS1010", 2022, 1);
        assertEquals(2022, module.getYear().intValue());
    }

    @Test
    public void testGetSem() {
        Module module = new Module("CS1010", 2022, 1);
        assertEquals(1, module.getSem().intValue());
    }

    @Test
    public void testMarkTaken() {
        Module module = new Module("CS1010", 2022, 1);
        module.markTaken("A");
        assertEquals("Taken", module.getStatus());
        assertEquals("A", module.getGrade());
    }

    @Test
    public void testMarkUntaken() {
        Module module = new Module("CS1010", 2022, 1, "A");
        module.markUntaken();
        assertEquals("Plan", module.getStatus());
        assertEquals("", module.getGrade());
    }

    @Test
    public void testGetStatus() {
        Module modulePlan = new Module("CS1010", 2022, 1);
        Module moduleTaken = new Module("CS1010", 2022, 1, "A");
        assertEquals("Plan", modulePlan.getStatus());
        assertEquals("Taken", moduleTaken.getStatus());
    }

    @Test
    public void testGetGrade() {
        Module modulePlan = new Module("CS1010", 2022, 1);
        Module moduleTaken = new Module("CS1010", 2022, 1, "A");
        assertEquals("", modulePlan.getGrade());
        assertEquals("A", moduleTaken.getGrade());
    }

    @Test
    public void testGetGradePoint() throws InvalidGradeException {
        Module module = new Module("CS1010", 2022, 1, "A");
        assertEquals(5.0, module.getGradePoint(), 0.01);
    }

    @Test
    public void testToString() {
        Module modulePlan = new Module("CS1010", 2022, 1);
        Module moduleTaken = new Module("CS1010", 2022, 1, "A");
        assertEquals("Plan CS1010 year 2022 semester 1 ", modulePlan.toString());
        assertEquals("Taken CS1010 year 2022 semester 1 A", moduleTaken.toString());
    }

    @Test
    public void testEncode() {
        Module modulePlan = new Module("CS1010", 2022, 1);
        Module moduleTaken = new Module("CS1010", 2022, 1, "A");
        assertEquals("Plan ### CS1010 ### 2022 ### 1", modulePlan.encode());
        assertEquals("Taken ### CS1010 ### 2022 ### 1 ### A", moduleTaken.encode());
    }

}
