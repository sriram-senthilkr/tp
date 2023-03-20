package seedu.penus.modules;

import seedu.penus.api.ModuleRetriever;
import seedu.penus.exceptions.InvalidGradeException;

import java.util.List;

public class CAP {
    /**
     * For every module taken, calculate weighted score = number of MC * grade
     * Sum up weighted score for all mods and divide by total MCs taken thus far
     *
     * @param moduleList the list containing all modules taken
     * @return the total CAP for all mods taken thus far
     */
    public static double calculateCAP(ModuleList moduleList) throws InvalidGradeException {
        ModuleRetriever moduleRetriever = new ModuleRetriever();
        List<Module> modsTaken = moduleList.getModuleList();
        Double totalScore = 0.0;
        Double totalMC = 0.0;
        String numberOfMCs;
        for (Module module : modsTaken) {
            moduleRetriever.getData(module.moduleCode);
            numberOfMCs = (String) ModuleRetriever.moduleInfo.get("moduleCredit");
            double weightedScore = Double.parseDouble(numberOfMCs) * module.getGradePoint();
            totalScore += weightedScore;
            totalMC += Double.parseDouble(numberOfMCs);
        }
        return (totalScore/totalMC);
    }
}
