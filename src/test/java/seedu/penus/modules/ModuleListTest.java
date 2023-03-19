package seedu.penus.modules;

import java.util.List;

import seedu.penus.exceptions.DuplicateModuleException;
import seedu.penus.exceptions.InvalidCommandException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;

class ModuleListTest {
    private ModuleList list;

    @BeforeEach
    public void setUp() {
        list = new ModuleList();
    }

    @Test
    void addModuleTest() throws DuplicateModuleException {
        Module testModule = new Module("CS2113", 2, 2, "A+");
        list.addModule(testModule);

        assertEquals(1, list.size());
    }

    @Test
    void markModuleTest() throws InvalidCommandException, DuplicateModuleException {
        Module unmarkedModule = new Module("CS2113", 2, 2);
        list.addModule(unmarkedModule);

        assertEquals("Plan", list.getModule(0).getStatus());

        list.markModule("cs2113", "A+");

        assertEquals("Taken", list.getModule(0).getStatus());
    }

    @Test
    void markModule_noMod_expectException() {
        assertThrows(InvalidCommandException.class, 
            () -> list.markModule("cs2113", "A+")
        );
    }

    @Test
    void deleteModuleTest() throws InvalidCommandException, DuplicateModuleException {
        Module unmarkedModule = new Module("CS2113", 2, 2);
        list.addModule(unmarkedModule);

        list.deleteModule("cs2113");
        assertEquals(0, list.size());
    }

    @Test
    void deleteModule_noMod_expectException() {
        assertThrows(InvalidCommandException.class, 
            () -> list.deleteModule("CS2113")
        );
    }

    @Test
    void retrieveUntakenCoreModsTest() throws DuplicateModuleException {
        Module coreModuleUntaken = new Module("CS2113", 2, 2);
        Module coreModuleTaken = new Module("CS2040C", 2, 2, "A+");
        Module notCoreModule = new Module("CS2103", 2, 2);
        list.addModule(coreModuleUntaken);
        list.addModule(coreModuleTaken);
        list.addModule(notCoreModule);

        List<String> untakenList = list.getUntakenCoreModsList();
        assertEquals(30, untakenList.size());
    }

    @Test 
    void retrieveTakenCoreModsTest() throws DuplicateModuleException {
        Module coreModuleUntaken = new Module("CS2113", 2, 2);
        Module coreModuleTaken = new Module("CS2040C", 2, 2, "A+");
        Module notCoreModule = new Module("CS2103", 2, 2);
        list.addModule(coreModuleUntaken);
        list.addModule(coreModuleTaken);
        list.addModule(notCoreModule);

        List<String> takenList = list.getTakenCoreModsList();
        assertEquals(1, takenList.size());
    }
}
