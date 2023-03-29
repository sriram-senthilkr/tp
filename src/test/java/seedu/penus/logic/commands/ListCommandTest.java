package seedu.penus.logic.commands;

// import org.junit.jupiter.api.Test;
// import seedu.penus.common.exceptions.PenusException;
// import seedu.penus.logic.utils.Grade;
// import seedu.penus.model.ModelManager;
// import seedu.penus.model.Module;
// import seedu.penus.model.User;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
//    private final ModelManager model = new ModelManager(new User(), new ArrayList<>(),
//                                                         new ArrayList<>(), new HashMap<>());
//    private ListCommand command;
//
//    @Test
//    public void execute_listAllModules_listNotEmpty() throws PenusException {
//        command = new ListCommand();
//
//        CommandResult result = command.execute(model);
//        List<String> messageArray = result.feedbackArray;
//
//        assertEquals(false, messageArray.isEmpty());
//    }
//
//    @Test
//    public void execute_listYearModules_listNotEmpty() throws PenusException {
//        command = new ListCommand(1, 1);
//
//        List<Module> moduleList = new ArrayList<>();
//        moduleList.add(new Module("CS1010", 4, 1, "A"));
//        model.setModuleList(moduleList);
//
//        CommandResult result = command.execute(model);
//        List<String> messageArray = result.feedbackArray;
//
//        assertEquals(false, messageArray.isEmpty());
//    }
//
//    @Test
//    public void execute_listYearSemesterModules_listNotEmpty() throws PenusException {
//        ListCommand command = new ListCommand(1, 1);
//
//        List<Module> moduleList = new ArrayList<>();
//        moduleList.add(new Module("CS1010", 4, 1, "A"));
//        model.setModuleList(moduleList);
//
//        CommandResult result = command.execute(model);
//        List<String> messageArray = result.feedbackArray;
//
//        assertEquals(false, messageArray.isEmpty());
//    }
//
//    @Test
//    public void execute_listYearSemesterModules_invalidYearSemester() throws PenusException {
//        ListCommand command = new ListCommand(10, 3);
//
//        List<Module> moduleList = new ArrayList<>();
//        moduleList.add(new Module("CS1010", 4, 1, "A"));
//        model.setModuleList(moduleList);
//
//        CommandResult result = command.execute(model);
//        List<String> messageArray = result.feedbackArray;
//
//        assertEquals("No modules taken/added", messageArray.get(1));
//    }
//
//    @Test
//    public void execute_listAllModules_correctOverallCAP() throws PenusException {
//        ListCommand command = new ListCommand();
//
//        List<Module> moduleList = new ArrayList<>();
//        moduleList.add(new Module("CS1010", 4, 1, "A"));
//        moduleList.add(new Module("CS1231", 4, 1, "B+"));
//        moduleList.add(new Module("MA1511", 4, 1, "A-"));
//        moduleList.add(new Module("GEA1000", 4, 2, "S"));
//        moduleList.add(new Module("CS2103", 4, 2, "A+"));
//        model.setModuleList(moduleList);
//
//        CommandResult result = command.execute(model);
//        List<String> messageArray = result.feedbackArray;
//        String overallCAPString = messageArray.get(messageArray.size() - 2);
//        String overallCAP = Grade.getOverallCAP(moduleList);
//
//        assertEquals("Overall CAP: " + overallCAP, overallCAPString);
//    }
}
