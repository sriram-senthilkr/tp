package seedu.penus.logic.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.HashMap;
import seedu.penus.common.exceptions.InvalidCommandException;
import seedu.penus.model.ModelManager;
import seedu.penus.model.User;

public class DetailsCommandTest {
    private final ModelManager model = new ModelManager(new User(), new ArrayList<>(),
            new ArrayList<>(), new HashMap<>());
    private DetailsCommand command;

    @Test
    public void testValidModuleCodeSuccess() throws InvalidCommandException {
        command = new DetailsCommand("CS2113");
        CommandResult result = command.execute(model);
        
        assertTrue(!result.feedbackToUser.contains("This information is not available"));
    }
}
