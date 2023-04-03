package seedu.penus.storage;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.penus.common.exceptions.PenusException;
import seedu.penus.model.ModuleList;
import seedu.penus.model.User;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class StorageManagerTest {
    StorageManager storageManager;
    @BeforeEach
    public void setUp() {
        storageManager = new StorageManager();
    }


    @Test
    public void testConstructor() {
        assertNotNull(storageManager);
        assertNotNull(storageManager.storage);
        assertNotNull(storageManager.resource);
    }

    @Test
    public void testLoadStorage() throws PenusException {
        storageManager.storage = new FileStorage();
        assertNotNull(storageManager.loadStorage());
    }
    @Test
    public void testLoadUser() throws PenusException {
        storageManager.storage = new FileStorage();
        assertNotNull(storageManager.loadUser());
    }
    @Test
    public void testSaveStorage() {
        storageManager.storage = new FileStorage();
        storageManager.saveStorage(new ModuleList(), new User());
    }
    @Test
    public void testLoadCoreDetails() {
        storageManager.resource = new ResourceStorage();
        assertNotNull(storageManager.loadCoreDetails());
    }

    @Test
    public void testLoadCoreModList() {
        storageManager.resource = new ResourceStorage();
        assertNotNull(storageManager.loadCoreModList());
    }
}
