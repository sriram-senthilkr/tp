package seedu.penus.modules;

import seedu.penus.api.ModuleRetriever;
import seedu.penus.exceptions.InvalidGradeException;

import java.text.DecimalFormat;
import java.util.List;

public class CAP {
    /**
     * For every module taken, calculate weighted score = number of MC * grade
     * Sum up weighted score for all mods and divide by total MCs taken thus far
     *
     * @param moduleList the list containing all modules taken
     * @return the total CAP for all mods taken thus far
     */
    public static double calculateOverallCAP(List<Module> moduleList) throws InvalidGradeException {
        ModuleRetriever moduleRetriever = new ModuleRetriever();
        Double totalScore = 0.0;
        Double totalMC = 0.0;
        String numberOfMCs;
        for (Module module : moduleList) {
            moduleRetriever.getData(module.moduleCode);
            numberOfMCs = (String) ModuleRetriever.moduleInfo.get("moduleCredit");
            double weightedScore = Double.parseDouble(numberOfMCs) * module.getGradePoint();
            totalScore += weightedScore;
            totalMC += Double.parseDouble(numberOfMCs);
        }
        return (totalScore/totalMC);
    }
    public static double calculateSemCAP(List<String[]> semArray) throws InvalidGradeException {
        ModuleRetriever moduleRetriever = new ModuleRetriever();
        Double totalScore = 0.0;
        Double totalMC = 0.0;
        String numberOfMCs;
        for (String[] module : semArray) {
            String moduleCode = module[0];
            String moduleGrade = module[1];
            moduleRetriever.getData(moduleCode);
            numberOfMCs = (String) ModuleRetriever.moduleInfo.get("moduleCredit");
            double weightedScore = Double.parseDouble(numberOfMCs) * Grade.getGradePoint(moduleGrade);
            totalScore += weightedScore;
            totalMC += Double.parseDouble(numberOfMCs);
        }
        return (totalScore/totalMC);
    }
    public static void printOverallCAP(List<Module> moduleList) throws InvalidGradeException {
        if (moduleList.isEmpty()) {
            System.out.println("\nOverall CAP : 0.00\n");
        } else {
            System.out.println("\nOverall CAP : " +
                    new DecimalFormat("#.##").format(CAP.calculateOverallCAP(moduleList)) + '\n');
        }
    }

    public static void printSemCAP(List<String[]> semArray) throws InvalidGradeException {
        if (semArray.isEmpty()) {
            System.out.println("\nSemester CAP : 0.00\n");
        } else {
            System.out.println("\nSemester CAP : " +
                    new DecimalFormat("#.##").format(CAP.calculateSemCAP(semArray)) + '\n');
        }
    }
}
