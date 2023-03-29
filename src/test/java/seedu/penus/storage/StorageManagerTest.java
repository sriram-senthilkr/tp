package seedu.penus.storage;


import org.junit.jupiter.api.Test;
import seedu.penus.model.ModuleList;
import seedu.penus.model.User;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class StorageManagerTest {


    @Test
    public void testConstructor() {
        StorageManager storageManager = new StorageManager();
        assertNotNull(storageManager);
        assertNotNull(storageManager.storage);
        assertNotNull(storageManager.resource);
    }

    @Test
    public void testLoadStorage() {
        StorageManager storageManager = new StorageManager();
        storageManager.storage = new FileStorage();
        assertNotNull(storageManager.loadStorage());
    }
    @Test
    public void testLoadUser() {
        StorageManager storageManager = new StorageManager();
        storageManager.storage = new FileStorage();
        assertNotNull(storageManager.loadUser());
    }
    @Test
    public void testSaveStorage() {
        StorageManager storageManager = new StorageManager();
        storageManager.storage = new FileStorage();
        storageManager.saveStorage(new ModuleList(), new User());
    }
    @Test
    public void testLoadCoreDetails() {
        StorageManager storageManager = new StorageManager();
        storageManager.resource = new ResourceStorage();
        assertNotNull(storageManager.loadCoreDetails());
    }

    @Test
    public void testLoadCoreModList() {
        StorageManager storageManager = new StorageManager();
        storageManager.resource = new ResourceStorage();
        assertNotNull(storageManager.loadCoreModList());
    }
}
