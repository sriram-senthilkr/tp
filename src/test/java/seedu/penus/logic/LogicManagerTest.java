package seedu.penus.logic;

import seedu.penus.common.exceptions.PenusException;
import seedu.penus.model.ModelManager;
import seedu.penus.model.User;
import seedu.penus.storage.StorageManager;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LogicManagerTest {
    private LogicManager logicManager;
    private final ModelManager model = new ModelManager(new User(), new ArrayList<>(),
                                                         new ArrayList<>(), new HashMap<>());
    private final StorageManager storage = new StorageManager();

    @BeforeEach
    public void setUp() {
        logicManager = new LogicManager(model, storage);
    }                                                     

    @Test
    public void getCommand_invalidCommand_exceptionThrown() {
        assertThrows(PenusException.class, () -> logicManager.getCommand("invalid command"));
    }
}
