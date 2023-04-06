package seedu.penus.logic.commands;

import seedu.penus.common.exceptions.InvalidCommandException;
import seedu.penus.model.ModelManager;
import seedu.penus.model.ModuleList;
import seedu.penus.model.Module;

public class RemoveCommand extends Command {
    public static final String COMMAND_WORD = "remove";
    public static final String MESSAGE = 
            "Module has been removed:\n" 
            + "\t  %s\n"
            + "\tYou have %s module(s) in your planner";

    public final String moduleCode;

    /**
     * Creates a PlanCommand to with the moduleCode attribute
     * @param moduleCode string
     */
    public RemoveCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Searches for the index of the moduleCode in the list and passes it to ModelManager to remove the module by index
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
        Module removedModule = model.getModule(index);
        model.removeModule(index);

        return new CommandResult(String.format(MESSAGE, removedModule, model.getSize()), false);
    }
}
