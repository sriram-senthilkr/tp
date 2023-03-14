package seedu.penus.modules;

import java.util.List;
import java.util.Arrays;

import seedu.penus.exceptions.InvalidGradeException;

public class Grade {
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
}

