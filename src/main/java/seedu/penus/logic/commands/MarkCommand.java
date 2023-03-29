package seedu.penus.logic.commands;

import seedu.penus.common.exceptions.InvalidCommandException;
import seedu.penus.model.ModelManager;
import seedu.penus.model.Module;
import seedu.penus.model.ModuleList;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE = 
            "Module has been taken:\n" 
            + "\t  %s";

    private final String moduleCode;
    private final String grade;

    /**
     * Creates a PlanCommand with the moduleCode and grade
     */
    public MarkCommand(String moduleCode, String grade) {
        this.moduleCode = moduleCode;
        this.grade = grade;
    }

    /*
     * Searches for the index of the moduleCode in the list and passes it to ModelManager to mark the module by index
     */
    @Override
    public CommandResult execute(ModelManager model) throws InvalidCommandException {
        int index = -1;
        ModuleList list = model.getModuleList();
        for (int i = 0; i < list.size(); i++) {
            if (list.getModule(i).getCode().equals(moduleCode)) {
                index = i;
            }
        }
        if (index == -1) {
            throw new InvalidCommandException("No such module exists!");
        }
  
        model.markModule(index, this.grade);
        Module markedModule = model.getModule(index);

        return new CommandResult(String.format(MESSAGE, markedModule));
    }
}
