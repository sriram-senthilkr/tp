package seedu.penus.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
class ResourceStorageTest {

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
        assertEquals(27, coreMods.get("Mechanical Engineering").size());
        assertTrue(coreMods.containsKey("Environmental Engineering"));
        assertEquals(25, coreMods.get("Environmental Engineering").size());
        assertEquals("EG2501", coreMods.get("Civil Engineering").get(7));
    }
}
