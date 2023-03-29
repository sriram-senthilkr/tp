package seedu.penus.logic.utils;

import java.util.List;
import java.util.Arrays;
import seedu.penus.common.exceptions.InvalidGradeException;
import seedu.penus.model.Module;

public class Grade {
    /**
     * Converts a {@code String} grade into its associated gradepoint
     * @param grade string
     * @return {@code double} corresponding value of the grade
     * @throws InvalidGradeException invalid grade
     */
    public static double getGradePoint(String grade) throws InvalidGradeException {
        double gradePoint;
        switch(grade.toUpperCase()) {
        case "A+":
        case "A":
            gradePoint = 5.0;
            break;

        case "A-":
            gradePoint = 4.5;
            break;

        case "B+":
            gradePoint = 4.0;
            break;

        case "B":
            gradePoint = 3.5;
            break;

        case "B-":
            gradePoint = 3.0;
            break;

        case "C+":
            gradePoint = 2.5;
            break;

        case "C":
            gradePoint = 2.0;
            break;

        case "D+":
            gradePoint = 1.5;
            break;

        case "D":
            gradePoint = 1.0;
            break;

        case "F":
            gradePoint = 0.0;
            break;

        default:
            throw new InvalidGradeException();

        }
        return gradePoint;
    }

    /**
     * Checks if a grade {@code String} is valid and within expected inputs
     * @param grade string
     * @return Boolean true if grade is valid
     */
    public static Boolean isValid(String grade) {
        List<String> validGrades = Arrays.asList(
            "A+", "A", "A-", 
            "B+", "B", "B-", 
            "C+", "C", 
            "D+", "D", 
            "F", "S", "U"
        );

        return validGrades.contains(grade.toUpperCase());
    }

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
                ModuleRetriever.getData2223(module.getCode());
                numberOfMCs = (String) ModuleRetriever.moduleInfo2223.get("moduleCredit");
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
                numberOfMCs = ModuleRetriever.getModuleCredit(moduleCode);
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
     * Calls calculateOverallCAP and prints the overall CAP to 2 decimal places
     * @param moduleList the list containing all modules taken
     * @return capMessage String
     * @throws InvalidGradeException if there exists an unidentified Grade
     */
    public static String getOverallCAP(List<Module> moduleList) throws InvalidGradeException {
        String capMessage;
        if (moduleList.isEmpty()) {
            capMessage = "\nOverall CAP : 0.00\n";
        } else {
            Double overallCAP = calculateOverallCAP(moduleList);
            capMessage = String.format("\nOverall CAP : %.2f\n", overallCAP);
        }
        return capMessage;
    }

    /**
     * Calls calculateSemCAP and prints the semester CAP to 2 decimal places
     * @param semArray list of String array containing moduleCode and moduleGrade
     * @return semCapMessage String
     * @throws InvalidGradeException if there exists an unidentified Grade
     */
    public static String getSemCAP(List<String[]> semArray) throws InvalidGradeException {
        String semCapMessage;
        if (semArray.isEmpty()) {
            semCapMessage = "\nSemester CAP : 0.00\n";
        } else {
            Double semCAP = calculateSemCAP(semArray);
            semCapMessage = String.format("\nSemester CAP : %.2f\n", semCAP);       
        }
        return semCapMessage;
    }
}

