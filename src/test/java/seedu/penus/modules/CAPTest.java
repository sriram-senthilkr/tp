package seedu.penus.modules;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import seedu.penus.exceptions.DuplicateModuleException;
import seedu.penus.exceptions.InvalidGradeException;

import static org.junit.jupiter.api.Assertions.*;


class CAPTest {
    private ModuleList list;

    @BeforeEach
    public void setUp() {
        list = new ModuleList();
    }

    //public Module(String moduleCode, Integer year, Integer semester)
    @Test
    void CalculateCAP_moduleList_success() throws DuplicateModuleException, InvalidGradeException {
        list.addModule(new Module("CS1010", 1, 1, "B+"));
        list.addModule(new Module("CS2113", 2, 2, "A+"));
        double CAP = seedu.penus.modules.CAP.calculateCAP(this.list);
        assertEquals(CAP, 4.5);
    }
}
