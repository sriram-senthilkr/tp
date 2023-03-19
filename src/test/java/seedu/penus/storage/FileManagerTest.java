package seedu.penus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileManagerTest {
    FileManager fileManager;

    @BeforeEach
    public void setUp() {
        fileManager = new FileManager();
    }
    //test that file exists and path is correct
    @Test
    public void testConstructor() {
        assertNotNull(fileManager.filePath);
        assertEquals("./data/penus.txt", fileManager.filePath);
        assert fileManager.filePath.equals("./data/penus.txt") : "saved file path error";
    }
}

