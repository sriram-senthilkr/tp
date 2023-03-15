package seedu.penus;

import java.util.List;
import seedu.penus.storage.FileManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class FileManagerTest {
    FileManager fileManager = new FileManager();

    //test that file exists and path is correct
    @Test
    public void testConstructor() {
        assertNotNull(fileManager.coreModFile);
        assertNotNull(fileManager.modDetailsFile);
        assertEquals("./data/CoreMods.txt", fileManager.coreModFilePath);
        assertEquals("./data/module-details.txt", fileManager.modDetailsFilePath);
    }

    //test if retrieveCoreMods() successfully converts CoreMods.txt into List<String> form
    @Test
    public void testRetrieveCoreMods(){
        List <String> coreModules = fileManager.retrieveCoreMods();
        assertEquals(coreModules.get(0),"GESS");
        assertEquals(coreModules.size(),31);
    }

    //test if getAllModuleDetails successfully converts module-details.txt into List<String[]> form
    @Test
    public void testGetAllModuleDetails() {
        List<String[]> moduleDetailsList = fileManager.getAllModuleDetails();
        assertEquals(28, moduleDetailsList.size());
    }
}

