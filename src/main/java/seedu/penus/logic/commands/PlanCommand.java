package seedu.penus.logic.commands;

import seedu.penus.common.exceptions.DuplicateModuleException;
import seedu.penus.common.exceptions.InvalidModuleException;
import seedu.penus.common.exceptions.PenusException;
import seedu.penus.logic.utils.ModuleRetriever;
import seedu.penus.model.ModelManager;
import seedu.penus.model.Module;

/**
 * Adds a Plan module to the list
 */
public class PlanCommand extends Command {
    public static final String COMMAND_WORD = "plan";
    public static final String MESSAGE = 
            "Module has been added:\n" 
            + "\t  %s\n"
            + "\tYou have %s module(s) in your planner";

    public final Module plan;

    /**
     * Creates a PlanCommand to add the specified {@code Module} 
     * by constructing a new Module object with the given parameters
     * @param moduleCode string
     * @param semester int
     * @param year int
     */
    public PlanCommand(String moduleCode, int year, int semester) {
        this.plan = new Module(moduleCode, year, semester);
    }

    @Override
    public CommandResult execute(ModelManager model) throws PenusException {
        if (model.hasModule(plan)) {
            throw new DuplicateModuleException();
        }
        if (!ModuleRetriever.isValidMod(this.plan.getCode())) {
            throw new InvalidModuleException();
        }
        model.addModule(plan);

        return new CommandResult(String.format(MESSAGE, plan, model.getSize()), false);
    }
}

