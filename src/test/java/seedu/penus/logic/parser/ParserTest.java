package seedu.penus.logic.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.penus.common.exceptions.InvalidFormatException;
import seedu.penus.common.exceptions.PenusException;
import seedu.penus.logic.commands.Command;
import seedu.penus.logic.commands.InitCommand;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void testInitParserValidInput() throws PenusException, PenusException {
        String args = "init n/John Doe c/1";
        Command result = parser.initParser(args);
        assertTrue(result instanceof InitCommand);
        InitCommand initCommand = (InitCommand) result;
        assertEquals(initCommand.name, "John Doe");
        assertEquals(initCommand.courseCode, 1);
    }
    @Test
    public void testInitParserInvalidFormat() throws PenusException {
        String args = "init n/John Doe";
        PenusException invalidFormatException = assertThrows(InvalidFormatException.class, ()->parser.initParser(args));
        assertEquals("Error: Try again in the format: init n/NAME c/COURSE CODE", invalidFormatException.getMessage());
    }

    @Test
    public void testInitParserEmptyParameters() throws PenusException {
        String args = "init n/ c/1";
        PenusException invalidFormatException = assertThrows(InvalidFormatException.class, ()->parser.initParser(args));
        assertEquals("Error: Try again, n/ c/ cannot be empty", invalidFormatException.getMessage());
    }

    @Test
    public void testInitParserCourseCodeNotInteger() throws PenusException {
        String args = "init n/John Doe c/abc";
        Exception invalidFormatException = assertThrows(InvalidFormatException.class, ()->parser.initParser(args));
        assertEquals("Error: c/ must be an integer", invalidFormatException.getMessage());
    }
}

