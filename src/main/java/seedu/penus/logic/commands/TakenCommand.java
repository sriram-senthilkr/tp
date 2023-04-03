package seedu.penus.logic.commands;

import seedu.penus.common.exceptions.DuplicateModuleException;
import seedu.penus.common.exceptions.InvalidCommandException;
import seedu.penus.common.exceptions.InvalidModuleException;
import seedu.penus.common.exceptions.PenusException;
import seedu.penus.logic.utils.ModuleRetriever;
import seedu.penus.model.ModelManager;
import seedu.penus.model.Module;

/**
 * Adds a Taken module to the list
 */
public class TakenCommand extends Command {
    public static final String COMMAND_WORD = "taken";
    public static final String MESSAGE = 
            "Module has been added:\n" 
            + "\t  %s\n"
            + "\tYou have %s module(s) in your planner";
    public final Module taken;

    /**
     * Creates a TakenCommand to add the specified {@code Module} 
     * by constructing a new Module object with the given parameters
     * @param moduleCode string
     * @param year int
     * @param semester int
     * @param grade string
     */
    public TakenCommand(String moduleCode, int year, int semester, String grade) {
        this.taken = new Module(moduleCode, year, semester, grade);
    }

    @Override
    public CommandResult execute(ModelManager model) throws PenusException {
        if (!ModuleRetriever.isValidMod(this.taken.getCode())) {
            throw new InvalidModuleException();
        } 
        if (taken.getGrade().equals("U") || taken.getGrade().equals("S")) {
            if (!ModuleRetriever.getSUstatus(taken.getCode())){
                throw new InvalidCommandException("The module cannot be SU-ed");
            }
        }
        if (model.hasModule(taken)) {
            throw new DuplicateModuleException();
        }
        
        model.addModule(taken);

        return new CommandResult(String.format(MESSAGE, taken, model.getSize()), false);
    }
}
