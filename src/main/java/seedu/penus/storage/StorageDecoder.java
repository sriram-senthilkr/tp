package seedu.penus.storage;

import java.util.Arrays;
import java.util.List;
import seedu.penus.common.exceptions.InvalidFormatException;
import seedu.penus.common.exceptions.InvalidGradeException;
import seedu.penus.common.exceptions.InvalidModuleException;
import seedu.penus.common.exceptions.InvalidSemesterException;
import seedu.penus.common.exceptions.InvalidYearException;
import seedu.penus.common.exceptions.PenusException;
import seedu.penus.logic.utils.Grade;
import seedu.penus.logic.utils.ModuleRetriever;
import seedu.penus.model.User;
import seedu.penus.model.Module;

public class StorageDecoder {
    public static User decodeUser(String userLine) throws PenusException {
        User user = new User();
        String[] components = userLine.split(" ### ");
        if (components[1].length() == 0 || components[2].length() == 0) {
            throw new InvalidFormatException("Try again, name and course cannot be empty");
        }
        String name = components[1].trim();
        if (!name.matches("[a-zA-Z ]+")){
            throw new InvalidFormatException("Name must only include letters and spaces.");
        }
        
        String course = components[2].trim();
        List<String> validCourse = Arrays.asList(
            "Biomedical Engineering", "Chemical Engineering", "Civil Engineering", 
            "Computer Engineering", "Electrical Engineering", "Environmental Engineering", 
            "Industrial and Systems Engineering", "Mechanical Engineering"
        );
        if (!validCourse.contains(course)) {
            throw new InvalidFormatException("Course is not valid!");
        }
        user.setName(name);
        user.setCourse(course);


        return user;
    }

    /**
     * Decoder method to read a lines in storage and splits the string
     * into a string array
     * Format: Taken/Plan ### moduleCode ### year ### semester (### grade for taken)
     * @param module String
     * @return decoded Module object
     */
    public static Module decodemodule(String module) throws PenusException {
        String[] components = module.split(" ### ");
        String status = components[0].trim();
        String moduleCode = components[1].trim().toUpperCase();
        if (!ModuleRetriever.isValidMod(moduleCode)) {
            throw new InvalidModuleException();
        }
        int year;
        int semester;
        try {
            year = Integer.parseInt(components[2].trim());
            semester = Integer.parseInt(components[3].trim());
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Year and semester must be integers.");
        }
        if (year < 1 || year > 4) {
            throw new InvalidYearException("Year must be 1 to 4. Please try again.");
        }
        if (semester != 1 && semester != 2) {
            throw new InvalidSemesterException("Semester must be 1 or 2!");
        }
        Module decoded = null;

        switch (status) {
        case "Taken":
            if (components.length <= 4) {
                throw new InvalidFormatException("Grade must be included for taken command");
            }
            String grade = components[4].trim().toUpperCase();
            if (!Grade.isValid(grade)) {
                throw new InvalidGradeException();
            }
            decoded = new Module(moduleCode, year, semester, grade);
            break;

        case "Plan":
            decoded = new Module(moduleCode, year, semester);
            break;

        default:
            break;
        }
        return decoded;
    }

    
}
