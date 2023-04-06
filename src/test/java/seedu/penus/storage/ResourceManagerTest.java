package seedu.penus.storage;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ResourceManagerTest {
    ResourceStorage resource;

    @BeforeEach
    public void setUp() {
        resource = new ResourceStorage();
    }

    //test that file exists and path is correct
    @Test
    public void testConstructor() {
        assertNotNull(resource.coreModFile);
        assertNotNull(resource.modDetailsFile);
        assertEquals("core-modules.txt", resource.coreModFile);
        assert resource.coreModFile == "core-modules.txt" : "core modules file name error";
        assertEquals("core-module-details.txt", resource.modDetailsFile);
        assert resource.modDetailsFile == "core-module-details.txt" : "core module details file name error";
    }

    //test if getAllModuleDetails successfully converts module-details.txt into List<String[]> form
    @Test
    public void testGetAllModuleDetails() {
        List<String[]> moduleDetailsList = resource.getAllModuleDetails();
        assertEquals(28, moduleDetailsList.size());
    }
}
