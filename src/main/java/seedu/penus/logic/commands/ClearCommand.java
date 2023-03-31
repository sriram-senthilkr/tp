package seedu.penus.logic.commands;

import java.util.ArrayList;
import java.util.List;
import seedu.penus.common.exceptions.PenusException;
import seedu.penus.model.ModelManager;
import seedu.penus.model.Module;

public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE = "Cleared!";

    private final int year;
    private final int semester;

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
            if (moduleList.isEmpty()) {
                messageArray.add("No modules to clear.");

            } else {
                moduleList.clear();
                messageArray.add(MESSAGE);
            }
        }

        //clear specific year only
        if (this.year != 0 && this.semester == 0) {
            messageArray.add("Clearing modules for Year " + this.year);
            
            int listSize = model.getModuleList().size();

            if (listSize == 0) {

                messageArray.add("No modules to clear.");
            } else {
                
                for (int index = listSize - 1; index >= 0; index -= 1) {
                    if (model.getModuleList().getModule(index).getYear().equals(this.year)) {
                        model.removeModule(index);
                    }
                }
                messageArray.add(MESSAGE);
            }
        }

        //list year and sem specific
        if (this.year != 0 && this.semester != 0) {
            messageArray.add("Clearing modules for Year " + this.year + " and Semester " + this.semester);

            int listSize = model.getModuleList().size();

            if (listSize == 0) {

                messageArray.add("No modules to clear.");
            } else {
            
                for (int index = listSize - 1; index >= 0; index -= 1) {
                    if ((model.getModuleList().getModule(index).getYear() == this.year) && 
                        (model.getModuleList().getModule(index).getSem() == this.semester)) {
                        model.removeModule(index);
                    }
                }
                messageArray.add(MESSAGE);
            }
        }

        return new CommandResult(messageArray, true);
    }
}
