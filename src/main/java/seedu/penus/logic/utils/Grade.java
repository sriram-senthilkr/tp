package seedu.penus.logic.utils;

import java.util.List;
import java.util.Arrays;

import seedu.penus.common.exceptions.InvalidGradeException;
import seedu.penus.model.Module;

public class Grade {
    /**
     * Converts a {@code String} grade into its associated gradepoint
     *
     * @param grade string
     * @return {@code double} corresponding value of the grade
     * @throws InvalidGradeException invalid grade
     */
    public static double getGradePoint(String grade) throws InvalidGradeException {
        double gradePoint;
        switch (grade.toUpperCase()) {
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
     *
     * @param grade string
     * @return Boolean true if grade is valid
     */
    public static Boolean isValid(String grade) {
        List<String> validGrades = Arrays.asList(
                "A+", "A", "A-",
                "B+", "B", "B-",
                "C+", "C",
                "D+", "D",
                "F", "S", "U",
                "CS", "CU"
        );
        return validGrades.contains(grade.toUpperCase());
    }

    /**
     * For every module taken, calculate weighted score = number of MC * grade
     * Sum up weighted score for all mods and divide by total MCs taken thus far
     * S/U grades are not calculated for in Overall CAP
     *
     * @param moduleList the list containing all modules taken
     * @return total CAP for all mods taken thus far
     * @throws InvalidGradeException if there exists an unidentified Grade type
     */
    public static double calculateOverallCAP(List<Module> moduleList) throws InvalidGradeException {
        double cap;
        int numOfSUMods = 0;
        int numOfModsTaken = 0;
        int numOfModsPlanned = 0;
        double totalScore = 0.0;
        double totalMC = 0.0;
        String numberOfMCs;
        for (Module module : moduleList) {
            if (module.getStatus().equals("Taken")) {
                numOfModsTaken++;
                if (module.getGrade().matches("S|U|CS|CU")) {
                    numOfSUMods++;
                } else {
                    numberOfMCs = ModuleRetriever.getModuleCredit2223(module.getCode());
                    double weightedScore = Double.parseDouble(numberOfMCs) *
                            module.getGradePoint();
                    totalScore += weightedScore;
                    totalMC += Double.parseDouble(numberOfMCs);
                }
            } else {
                numOfModsPlanned++;
            }
        }
        if (numOfModsPlanned == moduleList.size()) {//all mods are planned
            cap = 0.0;
        } else if (numOfSUMods == numOfModsTaken) { //all mods are SU
            cap = 5.0;
        } else {
            cap = totalScore / totalMC;
        }
        return cap;
    }

    /**
     * For every module taken in a semester, calculate weighted score = number of MC
     * * grade
     * Sum up weighted score for all mods and divide by total MCs taken in the
     * semester
     * S/U grades are not calculated for in Semester CAP
     *
     * @param semArray list of String array containing moduleCode and moduleGrade
     * @return total CAP for a particular semester
     * @throws InvalidGradeException if there exists an unidentified Grade type
     */
    public static double calculateSemCAP(List<String[]> semArray) throws InvalidGradeException {
        double cap;
        int numOfSUMods = 0;
        double totalScore = 0.0;
        double totalMC = 0.0;
        String numberOfMCs;
        for (String[] module : semArray) {
            String moduleCode = module[0];
            String moduleGrade = module[1];
            if (moduleGrade.matches("S|U|CS|CU")) {
                numOfSUMods++;
            }
            if (!moduleGrade.matches("S|U|CS|CU") && !moduleGrade.equals("")) {
                numberOfMCs = ModuleRetriever.getModuleCredit2223(moduleCode);
                double weightedScore = Double.parseDouble(numberOfMCs) *
                        Grade.getGradePoint(moduleGrade);
                totalScore += weightedScore;
                totalMC += Double.parseDouble(numberOfMCs);
            }
        }
        if (numOfSUMods == semArray.size()) {//all mods in sem are SU-ed
            cap = 5.0;
        } else if ((totalScore == 0.0) || (totalMC == 0.0)) {//all mods are planned
            cap = 0.0;
        } else {
            cap = totalScore / totalMC;
        }
        return cap;
    }

    /**
     * Calls calculateOverallCAP and prints the overall CAP to 2 decimal places
     *
     * @param moduleList the list containing all modules taken
     * @return capMessage String
     * @throws InvalidGradeException if there exists an unidentified Grade
     */
    public static String getOverallCAP(List<Module> moduleList) throws InvalidGradeException {
        String capMessage;
        if (moduleList.isEmpty()) {
            capMessage = "Overall CAP : 0.00\n";
        } else {
            Double overallCAP = calculateOverallCAP(moduleList);
            capMessage = String.format("Overall CAP : %.2f\n", overallCAP);
        }
        return capMessage;
    }

    /**
     * Calls calculateSemCAP and prints the semester CAP to 2 decimal places
     *
     * @param semArray list of String array containing moduleCode and moduleGrade
     * @return semCapMessage String
     * @throws InvalidGradeException if there exists an unidentified Grade
     */
    public static String getSemCAP(List<String[]> semArray) throws InvalidGradeException {
        String semCapMessage;
        if (semArray.isEmpty()) {
            semCapMessage = "Semester CAP : 0.00\n";
        } else {
            Double semCAP = calculateSemCAP(semArray);
            semCapMessage = String.format("Semester CAP : %.2f\n", semCAP);
        }
        return semCapMessage;
    }
}
