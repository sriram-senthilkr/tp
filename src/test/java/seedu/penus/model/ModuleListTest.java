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

    @Test
    void getGECTest() {
        Module gecModule = new Module("GEC1015", 2, 2, "A+");
        list.addModule(gecModule);
        assertEquals("GEC1015", list.getGEC());
        list.removeModule(0);
        assertEquals("", list.getGEC());
    }

    @Test
    void getGENTest() {
        Module genModule = new Module("GEN2000", 2, 2, "A+");
        list.addModule(genModule);
        assertEquals("GEN2000", list.getGEN());
        list.removeModule(0);
        assertEquals("", list.getGEN());
    }

    @Test
    void getGESSTest() {
        Module gessModule = new Module("GESS1004", 2, 2, "A+");
        list.addModule(gessModule);
        assertEquals("GESS1004", list.getGESS());
        list.removeModule(0);
        assertEquals("", list.getGESS());
    }
}
