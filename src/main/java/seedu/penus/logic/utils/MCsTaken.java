package seedu.penus.logic.utils;

import java.util.List;
import seedu.penus.model.Module;

public class MCsTaken {
    public static int numberOfMcsTaken(List<Module> moduleList) {
        int numberOfMcs = 0;
        for (Module module : moduleList) {
            if (module.getStatus().equals("Taken")) {
                numberOfMcs = numberOfMcs + Integer.parseInt(ModuleRetriever.getModuleCredit(module.getCode()));
            }
        }
        return numberOfMcs;
    }
}
