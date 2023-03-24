package seedu.penus.logic.parser;

import seedu.penus.common.exceptions.InvalidCommandException;
import seedu.penus.common.exceptions.InvalidFormatException;
import seedu.penus.common.exceptions.InvalidGradeException;
import seedu.penus.common.exceptions.InvalidSemesterException;
import seedu.penus.common.exceptions.InvalidYearException;
import seedu.penus.logic.utils.Grade;
import seedu.penus.model.Module;

public class ModuleParser {
    public static Module getModuleFromCommand(String[] inputArray)
            throws InvalidFormatException, InvalidCommandException,
            InvalidGradeException, InvalidSemesterException, InvalidYearException {
        Module module;
        String command = inputArray[0];

        switch (command) {
        case "plan":
            if (inputArray[1].contains("g/")) {
                throw new InvalidFormatException("Grade should not be included!");
            }
            String[] planDetails = inputArray[1].split(" y/| s/", 3);
            if (planDetails.length != 3 || planDetails[1].length() == 0 || planDetails[2].length() == 0) {
                throw new InvalidFormatException("Try again in the format: PLAN CODE y/YEAR s/SEM");
            }

            String moduleCode = planDetails[0].toUpperCase();
            int year = Integer.parseInt(planDetails[1]);
            if (year != 1 && year != 2 && year != 3 && year != 4) {
                throw new InvalidYearException("Year must be 1 to 4. Please try again.");
            }
            int semester = Integer.parseInt(planDetails[2]);
            if (semester != 1 && semester != 2) {
                throw new InvalidSemesterException("Semester must be 1 or 2!");
            }
            module = new Module(moduleCode, year, semester);
            break;

        case "taken":
            String[] takenDetails = inputArray[1].split(" y/| s/| g/", 4);
            if (takenDetails.length != 4) {
                throw new InvalidFormatException("Try again in the format: PLAN CODE y/YEAR s/SEM g/GRADE");
            }
            if (takenDetails[1].length() == 0 || takenDetails[2].length() == 0 || takenDetails[3].length() == 0) {
                throw new InvalidFormatException("Try again, y/ s/ g/ cannot be empty");
            }
            String takenCode = takenDetails[0].toUpperCase();
            int takenYear = Integer.parseInt(takenDetails[1]);
            int takenSemester = Integer.parseInt(takenDetails[2]);
            String takenGrade = takenDetails[3];
            if (!Grade.isValid(takenGrade)) {
                throw new InvalidGradeException();
            }

            if (takenSemester != 1 && takenSemester != 2) {
                throw new InvalidFormatException("Semester must be 1 or 2!");
            }
            module = new Module(takenCode, takenYear, takenSemester, takenGrade);
            break;

        default:
            throw new InvalidCommandException();

        }
        return module;
    }
}
