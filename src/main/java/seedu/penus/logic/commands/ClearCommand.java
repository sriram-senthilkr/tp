package seedu.penus.logic.commands;

import java.util.ArrayList;
import java.util.List;
import seedu.penus.common.exceptions.PenusException;
import seedu.penus.model.ModelManager;
import seedu.penus.model.Module;

public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE = "Cleared!";

    public final int year;
    public final int semester;

    public ClearCommand() {
        this.year = 0;
        this.semester = 0;
    }

    //Overloaded constructor for filter
    public ClearCommand(int year, int semester) {
        this.year = year;
        this.semester = semester;
    }

    @Override
    public CommandResult execute(ModelManager model) throws PenusException {
        List<String> messageArray = new ArrayList<>();
        List<Module> moduleList = model.getModuleListObj();

        //clear all modules
        if (this.year == 0 && this.semester == 0) {
            List<String> clearAllModules = clearAllMods(moduleList);
            messageArray.addAll(clearAllModules);
        }

        //clear specific year only
        if (this.year != 0 && this.semester == 0) {
            List<String> clearYearModules = clearYearMods(model);
            messageArray.addAll(clearYearModules);
        }

        //list year and sem specific
        if (this.year != 0 && this.semester != 0) {
            List<String> clearYearAndSemModules = clearYearAndSemMods(model);
            messageArray.addAll(clearYearAndSemModules);
        }

        return new CommandResult(messageArray, true);
    }

    //clear all modules method
    private List<String> clearAllMods(List<Module> moduleList) {
        List<String> messageList = new ArrayList<>();
        if (moduleList.isEmpty()) {
            messageList.add("No modules to clear.");

        } else {
            moduleList.clear();
            messageList.add(MESSAGE);
        }
        return messageList;
    }

    //clear specific year method
    private List<String> clearYearMods(ModelManager model) {
        List<String> messageList = new ArrayList<>();
        messageList.add("Clearing modules for Year " + this.year);

        int listSize = model.getModuleList().size();

        if (listSize == 0) {

            messageList.add("No modules to clear.");
        } else {

            for (int index = listSize - 1; index >= 0; index -= 1) {
                if (model.getModuleList().getModule(index).getYear().equals(this.year)) {
                    model.removeModule(index);
                }
            }
            messageList.add(MESSAGE);
        }
        return messageList;
    }

    //list year and sem specific method
    private List<String> clearYearAndSemMods(ModelManager model) {
        List<String> messageList = new ArrayList<>();
        messageList.add("Clearing modules for Year " + this.year + " and Semester " + this.semester);

        int listSize = model.getModuleList().size();

        if (listSize == 0) {

            messageList.add("No modules to clear.");
        } else {

            for (int index = listSize - 1; index >= 0; index -= 1) {
                if ((model.getModuleList().getModule(index).getYear() == this.year) &&
                        (model.getModuleList().getModule(index).getSem() == this.semester)) {
                    model.removeModule(index);
                }
            }
            messageList.add(MESSAGE);
        }
        return messageList;
    }
    
}
