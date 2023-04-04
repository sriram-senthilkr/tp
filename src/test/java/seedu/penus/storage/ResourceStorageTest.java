package seedu.penus.storage;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class ResourceStorageTest {
    static final int NUMBER_OF_CORE_MODS_FOR_MECH_ENG = 27;
    static final int NUMBER_OF_CORE_MODS_FOR_ENV_ENG = 25;

    public ResourceStorage storage;
    @BeforeEach
    public void setUp() {
        storage = new ResourceStorage();
    }

    @Test
    public void testGetCoreModsSuccess() {
        HashMap<String, List<String>> coreMods = storage.getCoreMods();
        assertNotNull(coreMods);
        assertTrue(coreMods.containsKey("Electrical Engineering"));
        assertEquals(NUMBER_OF_CORE_MODS_FOR_MECH_ENG, coreMods.get("Mechanical Engineering").size());
        assertTrue(coreMods.containsKey("Environmental Engineering"));
        assertEquals(NUMBER_OF_CORE_MODS_FOR_ENV_ENG, coreMods.get("Environmental Engineering").size());
        assertEquals("EG2501", coreMods.get("Civil Engineering").get(7));
    }

    @Test
    public void testConstructor() {
        assertEquals("core-modules.txt", storage.coreModFile);
        assertEquals("core-module-details.txt", storage.modDetailsFile);
    }

    @Test
    public void testGetCoreMods() {
        HashMap<String, List<String>> coreHashMap = storage.getCoreMods();
        List<String> courseNameList = new ArrayList<>(Arrays.asList("Computer Engineering",
                "Electrical Engineering", "Biomedical Engineering", "Chemical Engineering",
                "Civil Engineering", "Environmental Engineering",
                "Industrial and Systems Engineering", "Mechanical Engineering"));
        for (String courseName : coreHashMap.keySet()) {
            assertTrue(courseNameList.contains(courseName));
        }
        List<String> compareList = new ArrayList<>(Arrays.asList("ES2631", "CS1010", "GEA1000",
                "DTK1234", "EG1311", "IE2141", "EE2211", "EG2501", "CDE2000", "PF1101",
                "CG4002", "MA1511", "MA1512", "MA1508E", "EG2401A", "EG3611A",
                "CG1111A", "CG2111A", "CS1231", "CG2023", "CG2027", "CG2028",
                "CG2271", "CS2040C", "CS2113", "EE2026", "EE4204"));
        List<String> modCodeList = coreHashMap.get("Computer Engineering");
        for (String modCode : modCodeList) {
            assertTrue(compareList.contains(modCode));
        }
    }

    @Test
    public void testGetAllModuleDetails() {
        List<String[]> moduleDetailsList = storage.getAllModuleDetails();
        String[] moduleDetails = moduleDetailsList.get(0);
        List<String> compareList = new ArrayList<>(Arrays.asList("CG1111A",
                "Engineering Principles and Practice I", "4", "0", "0",
                "BN2111, EE1111A, EE1111B, EE2111A", "Sem 1", "1"));
        for (int i = 0; i < 8; ++i) {
            assert (moduleDetails[i].equals(compareList.get(i)));
        }
    }




}
