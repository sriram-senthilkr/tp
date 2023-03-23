package seedu.penus.logic.commands;

import seedu.penus.common.exceptions.DuplicateModuleException;
import seedu.penus.model.ModelManager;
import seedu.penus.model.Module;

public class PlanCommand extends Command {
    public static final String COMMAND_WORD = "plan";
    public static final String MESSAGE = 
            "\tModule has been added:\n" 
            + "\t  %s";

    private final Module plan;

    public PlanCommand(String moduleCode, int year, int semester) {
        this.plan = new Module(moduleCode, year, semester);
    }

    @Override
    public CommandResult execute(ModelManager model) throws DuplicateModuleException {
        if (model.hasModule(plan)) {
            throw new DuplicateModuleException();
        }

        model.addModule(plan);

        return new CommandResult(String.format(MESSAGE, plan));
    }
}

