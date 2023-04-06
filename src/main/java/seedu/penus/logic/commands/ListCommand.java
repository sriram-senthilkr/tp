package seedu.penus.logic.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import seedu.penus.common.exceptions.PenusException;
import seedu.penus.logic.utils.Grade;
import seedu.penus.model.ModelManager;
import seedu.penus.model.Module;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE = "List of";

    public final int year;
    public final int semester;

    public ListCommand() {
        this.year = 0;
        this.semester = 0;
    }

    //Overloaded constructor for filter
    public ListCommand(int year, int semester) {
        this.year = year;
        this.semester = semester;
    }
    
    @Override
    public CommandResult execute(ModelManager model) throws PenusException {
        List<String> messageArray = new ArrayList<>();
        Map<Integer, Map<Integer, List<String[]>>> modules = new HashMap<>();
        List<Module> moduleList = model.getModuleListObj();
        for (Module currMod : moduleList) {
            int currYear = currMod.getYear();
            int currSem = currMod.getSem();
            String[] arr = new String[] { currMod.getCode(), currMod.getGrade() };
            modules.computeIfAbsent(currYear, k -> new HashMap<>())
                    .computeIfAbsent(currSem,k -> new ArrayList<>())
                    .add(arr);
        }

        //list all modules
        if (this.year == 0 && this.semester == 0) {
            List<String> allModules = getAllMods(modules);
            messageArray.addAll(allModules);
        }

        //list for year only
        if (this.year != 0 && this.semester == 0) {
            List<String> yearModules = getYearMods(modules);
            messageArray.addAll(yearModules);
        }

        //list year and sem specific
        if (this.year != 0 && this.semester != 0) {
            List<String> semModules = getSemMods(modules);
            messageArray.addAll(semModules);
        }

        messageArray.add("");
        messageArray.add(Grade.getOverallCAP(moduleList));

        return new CommandResult(messageArray, true);
    }

    private List<String> getAllMods(Map<Integer, Map<Integer, List<String[]>>> modules) {
        List<String> messageList = new ArrayList<>();
        for (int y = 1; y < 5; y++) {
            for (int s = 1; s <= 2; s++) {
                messageList.add("- Year " + y + " Semester " + s + " -");

                List<String[]> modulesInYearSem = modules.getOrDefault(y, new HashMap<>())
                        .getOrDefault(s, new ArrayList<>());
                
                if (modulesInYearSem.isEmpty()) {
                    messageList.add("No modules taken/added.");
                } else {
                    for (String[] string : modulesInYearSem) {
                        messageList.add(string[0] + " " + string[1]);
                    }
                }
                messageList.add("");
            }
        }
        return messageList;
    }

    private List<String> getYearMods(Map<Integer, Map<Integer, List<String[]>>> modules) throws PenusException {
        List<String> messageList = new ArrayList<>();
        for (int s = 1; s <= 2; s++) {
            messageList.add("- Year " + this.year + " Semester " + s + " -");

            List<String[]> modulesInYear = modules.getOrDefault(this.year, new HashMap<>())
                    .getOrDefault(s, new ArrayList<>());

            if (modulesInYear.isEmpty()) {
                messageList.add("No modules taken/added");
            } else {
                for (String[] string : modulesInYear) {
                    messageList.add(string[0] + " " + string[1]);
                }
            }
            messageList.add(Grade.getSemCAP(modulesInYear));
            messageList.add("");
        }

        return messageList;
    }

    private List<String> getSemMods(Map<Integer, Map<Integer, List<String[]>>> modules) throws PenusException {
        List<String> messageList = new ArrayList<>();
        messageList.add("- Year " + this.year + " Semester " + this.semester + " -");

        List<String[]> modulesInYearAndSem = modules.getOrDefault(this.year, new HashMap<>())
                .getOrDefault(this.semester, new ArrayList<>());

        if (modulesInYearAndSem.isEmpty()) {
            messageList.add("No modules taken/added");
        } else {
            for (String[] string : modulesInYearAndSem)  {
                messageList.add(string[0] + " " + string[1]);
            }
        }
        messageList.add(Grade.getSemCAP(modulesInYearAndSem));

        return messageList;
    }
}
