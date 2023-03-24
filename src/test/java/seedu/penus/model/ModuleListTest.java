package seedu.penus.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModuleListTest {
    private ModuleList list;

    @BeforeEach
    public void setUp() {
        list = new ModuleList();
    }

    @Test
    void addModuleTest() {
        Module testModule = new Module("CS2113", 2, 2, "A+");
        list.addModule(testModule);

        assertEquals(1, list.size());
    }

    @Test
    void removeModuleTest() {
        Module unmarkedModule = new Module("CS2113", 2, 2);
        list.addModule(unmarkedModule);

        list.removeModule(0);
        assertEquals(0, list.size());
    }
}
