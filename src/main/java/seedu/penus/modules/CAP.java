package seedu.penus.modules;

import seedu.penus.api.ModuleRetriever;
import seedu.penus.exceptions.InvalidGradeException;

import java.util.List;

public class CAP {
    /**
     * For every module taken, calculate weighted score = number of MC * grade
     * Sum up weighted score for all mods and divide by total MCs taken thus far
     * S/U grades are not calculated for in Overall CAP
     * @param moduleList the list containing all modules taken
     * @return total CAP for all mods taken thus far
     * @throws InvalidGradeException if there exists an unidentified Grade type
     */
    public static double calculateOverallCAP(List<Module> moduleList) throws InvalidGradeException {
        double totalScore = 0.0;
        double totalMC = 0.0;
        String numberOfMCs;
        for (Module module : moduleList) {
            if (!module.getGrade().matches("[SU]") && module.getStatus().equals("Taken")) {
                ModuleRetriever.getData(module.moduleCode);
                numberOfMCs = (String) ModuleRetriever.moduleInfo.get("moduleCredit");
                double weightedScore = Double.parseDouble(numberOfMCs) * module.getGradePoint();
                totalScore += weightedScore;
                totalMC += Double.parseDouble(numberOfMCs);
            }
        }
        if ((totalScore == 0.0) || (totalMC == 0.0)) {
            return 0.0;
        } else {
            return (totalScore / totalMC);
        }
    }

    /**
     * For every module taken in a semester, calculate weighted score = number of MC * grade
     * Sum up weighted score for all mods and divide by total MCs taken in the semester
     * S/U grades are not calculated for in Semester CAP
     * @param semArray list of String array containing moduleCode and moduleGrade
     * @return total CAP for a particular semester
     * @throws InvalidGradeException if there exists an unidentified Grade type
     */
    public static double calculateSemCAP(List<String[]> semArray) throws InvalidGradeException {
        double totalScore = 0.0;
        double totalMC = 0.0;
        String numberOfMCs;
        for (String[] module : semArray) {
            String moduleCode = module[0];
            String moduleGrade = module[1];
            if (!moduleGrade.matches("[SU]") && !moduleGrade.equals("")) {
                ModuleRetriever.getData(moduleCode);
                numberOfMCs = (String) ModuleRetriever.moduleInfo.get("moduleCredit");
                double weightedScore = Double.parseDouble(numberOfMCs) * Grade.getGradePoint(moduleGrade);
                totalScore += weightedScore;
                totalMC += Double.parseDouble(numberOfMCs);
            }
        }
        if ((totalScore == 0.0) || (totalMC == 0.0)) {
            return 0.0;
        } else {
            return (totalScore / totalMC);
        }
    }

    /**
     * Calls CAP.calculateOverallCAP and prints the overall CAP to 2 decimal places
     * @param moduleList the list containing all modules taken
     * @throws InvalidGradeException if there exists an unidentified Grade
     */
    public static void printOverallCAP(List<Module> moduleList) throws InvalidGradeException {
        if (moduleList.isEmpty()) {
            System.out.println("\nOverall CAP : 0.00\n");
        } else {
            Double overallCAP = CAP.calculateOverallCAP(moduleList);
            System.out.println("\nOverall CAP : " +
                    String.format("%.2f", overallCAP) + '\n');
        }
    }

    /**
     * Calls CAP.calculateSemCAP and prints the semester CAP to 2 decimal places
     * @param semArray list of String array containing moduleCode and moduleGrade
     * @throws InvalidGradeException if there exists an unidentified Grade
     */
    public static void printSemCAP(List<String[]> semArray) throws InvalidGradeException {
        if (semArray.isEmpty()) {
            System.out.println("\nSemester CAP : 0.00\n");
        } else {
            Double semCAP = CAP.calculateSemCAP(semArray);
            System.out.println("\nSemester CAP : " +
                    String.format("%.2f", semCAP) + '\n');
        }
    }
}
