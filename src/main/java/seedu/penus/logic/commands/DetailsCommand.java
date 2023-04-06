package seedu.penus.logic.commands;

import seedu.penus.model.ModelManager;
import seedu.penus.logic.utils.DetailsCompiler;

public class DetailsCommand extends Command {
    public static final String COMMAND_WORD = "details";
    public final String moduleCode;

    /**
     * Creates a DetailsCommand with the moduleCode for querying during execution
     * @param moduleCode string
     */
    public DetailsCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    public CommandResult execute(ModelManager model) {
        String result = DetailsCompiler.getDetails(this.moduleCode);

        return new CommandResult(moduleCode + " " + result, false);
    }
}
