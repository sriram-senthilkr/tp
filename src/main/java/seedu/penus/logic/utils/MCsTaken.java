package seedu.penus.logic.utils;

import java.util.List;
import seedu.penus.model.Module;

public class MCsTaken {
    /**
     * Iterates throught he moduleList and adds the number of MCs of each taken module to the sum
     * The MCs are retreieved from the ModuleRetriever API from NUSMods.
     * @param moduleList list of modules
     * @return int total number of MCs of the taken modules in the list
     */
    public static int numberOfMcsTaken(List<Module> moduleList) {
        int numberOfMcs = 0;
        for (Module module : moduleList) {
            if (module.getStatus().equals("Taken")) {
                numberOfMcs = numberOfMcs + Integer.parseInt(ModuleRetriever.getModuleCredit2223(module.getCode()));
            }
        }
        return numberOfMcs;
    }
}
