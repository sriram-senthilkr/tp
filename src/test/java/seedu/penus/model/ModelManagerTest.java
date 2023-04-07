package seedu.penus.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModelManagerTest {
    private ModelManager modelManager;
    private User user;
    private List<Module> moduleList;
    private List<String[]> coreDetails;
    private HashMap<String, List<String>> coreModList;

    @BeforeEach
    public void setUp() {
        user = new User("John Doe", "Computer Engineering");
        moduleList = new ArrayList<>();
        moduleList.add(new Module("CS1010", 1, 1));
        moduleList.add(new Module("CS1231", 2, 1));
        coreDetails = new ArrayList<>();
        coreDetails.add(new String[]{"DTK1234", "Design Thinking", "4", "0"});
        coreModList = new HashMap<>();
        coreModList.put("Computer Engineering", new ArrayList<>());
        coreModList.get("Computer Engineering").add("CS1010");
        modelManager = new ModelManager(user, moduleList, coreDetails, coreModList);
    }

    @Test
    public void hasModule_moduleInList_returnsTrue() {
        assertTrue(modelManager.hasModule(new Module("CS1010", 1, 1)));
    }

    @Test
    public void hasModule_moduleNotInList_returnsFalse() {
        assertFalse(modelManager.hasModule(new Module("MA1508E", 1, 2)));
    }

    @Test
    public void hasModuleCode_success() {
        assertFalse(modelManager.hasModuleCode("CS2040C"));
        assertTrue(modelManager.hasModuleCode("CS1231"));
    }

    @Test
    public void addModule_validModule_success() {
        modelManager.addModule(new Module("MA1508E", 1, 2));
        assertEquals(3, modelManager.getSize());
    }

    @Test
    public void removeModule_validIndex_success() {
        modelManager.removeModule(1);
        assertEquals(1, modelManager.getSize());
    }

    @Test
    public void markModule_validIndexAndGrade_success() {
        modelManager.markModule(0, "A+");
        assertEquals("A+", modelManager.getModule(0).getGrade());
    }

    @Test
    public void getUserName_returnsName() {
        assertEquals("John Doe", modelManager.getUserName());
    }

    @Test
    public void getUserCourse_returnsCourse() {
        assertEquals("Computer Engineering", modelManager.getUserCourse());
    }

}
