package seedu.penus.logic.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.penus.common.exceptions.InvalidFormatException;
import seedu.penus.common.exceptions.InvalidGradeException;
import seedu.penus.common.exceptions.InvalidModuleException;
import seedu.penus.common.exceptions.InvalidSemesterException;
import seedu.penus.common.exceptions.InvalidYearException;
import seedu.penus.common.exceptions.PenusException;
import seedu.penus.logic.commands.ClearCommand;
import seedu.penus.logic.commands.Command;
import seedu.penus.logic.commands.DetailsCommand;
import seedu.penus.logic.commands.ExitCommand;
import seedu.penus.logic.commands.HelpCommand;
import seedu.penus.logic.commands.InitCommand;
import seedu.penus.logic.commands.ListCommand;
import seedu.penus.logic.commands.MarkCommand;
import seedu.penus.logic.commands.PlanCommand;
import seedu.penus.logic.commands.RemoveCommand;
import seedu.penus.logic.commands.StatusCommand;
import seedu.penus.logic.commands.TakenCommand;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        PenusException invalidFormatException = assertThrows(InvalidFormatException.class,
                ()->parser.parseCommand(args));
        assertEquals("Error: Try again in the format: init n/NAME c/COURSE CODE",
                invalidFormatException.getMessage());
    }

    @Test
    public void testInitParserEmptyParameters() throws PenusException {
        String args = "init n/ c/1";
        PenusException invalidFormatException = assertThrows(InvalidFormatException.class,
                ()->parser.parseCommand(args));
        assertEquals("Error: Try again, n/ c/ cannot be empty", invalidFormatException.getMessage());
    }

    @Test
    public void testInitParserCourseCodeNotInteger() throws PenusException {
        String args = "init n/John Doe c/abc";
        Exception invalidFormatException = assertThrows(InvalidFormatException.class, ()->parser.parseCommand(args));
        assertEquals("Error: c/ must be an integer", invalidFormatException.getMessage());
    }

    @Test
    public void testInitParserNameInteger() {
        String args = "init n/John Doe2 c/3";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(args));
    }

    @Test
    public void testInitParserEmptyField() {
        String args = "init n/ c/";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(args));
    }


    @Test
    public void testPlanParser_validInput() throws PenusException {
        String input = "plan CS2113 y/1 s/1";

        Command result = parser.parseCommand(input);
        assertTrue(result instanceof PlanCommand);
        PlanCommand command = (PlanCommand) result;
        assertEquals(command.plan.getCode(), "CS2113");
        assertEquals(command.plan.getYear(), 1);
        assertEquals(command.plan.getSem(), 1);
    }

    //plan contains g/
    @Test
    public void testPlanParser_containsGrade() {
        String input = "plan CS2113 y/1 s/1 g/A";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }

    //plan empty fields
    @Test
    public void testPlanParser_emptyField() {
        String input = "plan CS2113 y/ s/1";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }

    //plan not valid integer
    @Test
    public void testPlanParser_invalidInteger() {
        String input = "plan CS2113 y/g s/1";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }

    //plan year invalid
    @Test
    public void testPlanParser_invalidYear() {
        String input = "plan CS2113 y/5 s/1";
        assertThrows(InvalidYearException.class, () -> parser.parseCommand(input));
    }

    //plan sem invalid
    @Test
    public void testPlanParser_invalidSem() {
        String input = "plan CS2113 y/2 s/0";
        assertThrows(InvalidSemesterException.class, () -> parser.parseCommand(input));
    }


    @Test
    public void testTakenParser_validInput() throws PenusException {
        String input = "taken CS2113 y/1 s/1 g/A";

        Command result = parser.parseCommand(input);
        assertTrue(result instanceof TakenCommand);
        TakenCommand command = (TakenCommand) result;
        assertEquals(command.taken.getCode(), "CS2113");
        assertEquals(command.taken.getYear(), 1);
        assertEquals(command.taken.getSem(), 1);
        assertEquals(command.taken.getGrade(), "A");
    }

    //taken dont contain g/\@Test
    @Test
    public void testTakenParser_missingGrade() {
        String input = "taken CS2113 y/2 s/1";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }

    //taken empty fields
    @Test
    public void testTakenParser_emptyField() {
        String input = "taken CS2113 y/ s/1 g/A";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }


    //taken invalid integer
    @Test
    public void testTakenParser_invalidInteger() {
        String input = "taken CS2113 y/g s/1 g/A";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }

    //taken invalid grade
    @Test
    public void testTakenParser_invalidGrade() {
        String input = "taken CS2113 y/1 s/1 g/G";
        assertThrows(InvalidGradeException.class, () -> parser.parseCommand(input));
    }

    //taken year invalid
    @Test
    public void testTakenParser_invalidYear() {
        String input = "taken CS2113 y/999 s/1 g/A";
        assertThrows(InvalidYearException.class, () -> parser.parseCommand(input));
    }

    //taken invalid sem
    @Test
    public void testTakenParser_invalidSem() {
        String input = "taken CS2113 y/1 s/0 g/A";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }


    @Test
    public void testMarkParser_validInput() throws PenusException {
        String input = "mark CS2113 g/A";

        Command result = parser.parseCommand(input);
        assertTrue(result instanceof MarkCommand);
        MarkCommand command = (MarkCommand) result;
        assertEquals(command.moduleCode, "CS2113");
        assertEquals(command.grade, "A");
    }

    //mark missing g/
    @Test
    public void testMarkParser_missingGrade() {
        String input = "mark CS2113";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }

    //mark invalid grade
    @Test
    public void testMarkParser_invalidGrade() {
        String input = "mark CS2113 g/N";
        assertThrows(InvalidGradeException.class, () -> parser.parseCommand(input));
    }


    @Test
    public void testListParser_validInputNoFilter() throws PenusException {
        String input = "list";

        Command result = parser.parseCommand(input);
        assertTrue(result instanceof ListCommand);
        ListCommand command = (ListCommand) result;
        assertEquals(command.year, 0);
        assertEquals(command.semester, 0);
    }

    @Test
    public void testListParser_validInput() throws PenusException {
        String input = "list y/1";

        Command result = parser.parseCommand(input);
        assertTrue(result instanceof ListCommand);
        ListCommand command = (ListCommand) result;
        assertEquals(command.year, 1);
        assertEquals(command.semester, 0);
    }

    @Test
    public void testListParser_validInputYearSem() throws PenusException {
        String input = "list y/1 s/1";

        Command result = parser.parseCommand(input);
        assertTrue(result instanceof ListCommand);
        ListCommand command = (ListCommand) result;
        assertEquals(command.year, 1);
        assertEquals(command.semester, 1);
    }

    //list sem but not year specified
    @Test
    public void testListParser_yearUnspecified() {
        String input = "list s/2";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }

    //list invalid integer
    @Test
    public void testListParser_invalidInteger() {
        String input = "list y/g s/2";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }

    @Test
    public void testListParser_invalidIntegerSem() {
        String input = "list y/1 s/5,5";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }


    //list year invalid
    @Test
    public void testListParser_invalidYear() {
        String input = "list y/6 s/2";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }

    //list sem invalid
    @Test
    public void testListParser_invalidSemester() {
        String input = "list y/1 s/0";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }
    
    //list empty year
    @Test
    public void testListParser_emptyYear() {
        String input = "list y/";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }

    //list empty semester
    @Test
    public void testListParser_emptySem() {
        String input = "list y/1 s/";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }

    //list empty year and semester
    @Test
    public void testListParser_emptyYearAndSem() {
        String input = "list y/ s/";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }

    @Test
    public void testRemoveParser_validInput() throws PenusException {
        String input = "remove CS2113";

        Command result = parser.parseCommand(input);
        assertTrue(result instanceof RemoveCommand);
        RemoveCommand command = (RemoveCommand) result;
        assertEquals(command.moduleCode, "CS2113");
    }

    //remove without code
    @Test
    public void testRemoveParser_noCode() {
        String input = "remove ";
        assertThrows(InvalidModuleException.class, () -> parser.parseCommand(input));
    }

    @Test
    public void testDetailsParser_validInput() throws PenusException {
        String input = "details CS2113";

        Command result = parser.parseCommand(input);
        assertTrue(result instanceof DetailsCommand);
        DetailsCommand command = (DetailsCommand) result;
        assertEquals(command.moduleCode, "CS2113");
    }

    //detials no module code
    @Test
    public void testDetailsParser_noCode() {
        String input = "details ";
        assertThrows(InvalidModuleException.class, () -> parser.parseCommand(input));
    }

    @Test
    public void testDetailsParser_noCode2() {
        String input = "details  ";
        assertThrows(InvalidModuleException.class, () -> parser.parseCommand(input));
    }

    //clear
    @Test
    public void testClearParser_validInputNoFilter() throws PenusException {
        String input = "clear";

        Command result = parser.parseCommand(input);
        assertTrue(result instanceof ClearCommand);
        ClearCommand command = (ClearCommand) result;
        assertEquals(command.year, 0);
        assertEquals(command.semester, 0);
    }

    @Test
    public void testClearParser_validInput() throws PenusException {
        String input = "clear y/1";

        Command result = parser.parseCommand(input);
        assertTrue(result instanceof ClearCommand);
        ClearCommand command = (ClearCommand) result;
        assertEquals(command.year, 1);
        assertEquals(command.semester, 0);
    }

    @Test
    public void testClearParser_validInputYearSem() throws PenusException {
        String input = "clear y/1 s/2";

        Command result = parser.parseCommand(input);
        assertTrue(result instanceof ClearCommand);
        ClearCommand command = (ClearCommand) result;
        assertEquals(command.year, 1);
        assertEquals(command.semester, 2);
    }

    //clear year not specified but sem is
    @Test
    public void testClearParser_noYearButSem() {
        String input = "clear s/2";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }

    //clear invalid integer
    @Test
    public void testClearParser_invalidInteger() {
        String input = "clear y/2,2";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }

    //clear invalid year (not integer)
    @Test
    public void testClearParser_invalidYearInteger() {
        String input = "clear y/g";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }

    //clear invalid year
    @Test
    public void testClearParser_invalidYear() {
        String input = "clear y/1000";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }

    //clear invalid sem
    @Test
    public void testClearParser_invalidSem() {
        String input = "clear y/1 s/0";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }

    @Test
    public void testClearParser_invalidSemInteger() {
        String input = "clear y/1 s/g";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }

    //clear empty fields
    @Test
    public void testClearParser_emptyField() {
        String input = "clear y/ s/ ";
        assertThrows(InvalidFormatException.class, () -> parser.parseCommand(input));
    }

    @Test
    public void testHelpParser_validInput() throws PenusException {
        String input = "help";

        Command result = parser.parseCommand(input);
        assertTrue(result instanceof HelpCommand);
    }

    @Test
    public void testExitParser_validInput() throws PenusException {
        String input = "exit";

        Command result = parser.parseCommand(input);
        assertTrue(result instanceof ExitCommand);
    }

    @Test
    public void testStatusParser_validInput() throws PenusException {
        String input = "status";

        Command result = parser.parseCommand(input);
        assertTrue(result instanceof StatusCommand);
    }
}

