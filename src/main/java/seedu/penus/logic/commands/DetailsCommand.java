package seedu.penus.logic.commands;

import seedu.penus.logic.utils.ModuleRetriever;
import seedu.penus.model.ModelManager;

public class DetailsCommand extends Command {
    public static final String COMMAND_WORD = "details";
    private final String moduleCode;

    public DetailsCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    @Override
    public CommandResult execute(ModelManager model) {
        String result = ModuleRetriever.getDetails(this.moduleCode);

        return new CommandResult(moduleCode + " " + result);
    }
}
