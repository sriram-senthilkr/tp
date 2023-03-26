package seedu.penus.logic.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import seedu.penus.model.ModelManager;
import seedu.penus.model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class InitCommandTest {
    private User user = new User();


    private ModelManager model = new ModelManager(user, new ArrayList<>(), new ArrayList<>(), new HashMap<>());

    @Test
    public void executeValidInitCommand(){
        new InitCommand("Bob", 4);
        assertEquals("Bob", model.getUserName());
        assertEquals("Computer Engineering", model.getUserCourse());
    }

}
