package seedu.penus.parser;

import seedu.penus.exceptions.InvalidCommandException;
import seedu.penus.exceptions.InvalidFormatException;
import seedu.penus.exceptions.InvalidTaskException;
import seedu.penus.modules.Module;

public class ModuleParser {
    public static Module getModuleFromCommand(String[] inputArray)
            throws InvalidFormatException, InvalidCommandException, InvalidTaskException {
        Module module;
        String command = inputArray[0];

        switch (command) {
        case "plan":
            if (inputArray.length == 1) {
                throw new InvalidTaskException(command);
            }
            if (!inputArray[1].contains("y/") && !inputArray[1].contains("s/")) {
                throw new InvalidFormatException("y/", "s/");
            }
            String[] planDetails = inputArray[1].split(" y/| s/", 3);
            String moduleCode = planDetails[0];
            Integer year = Integer.parseInt(planDetails[1]);
            Integer semester = Integer.parseInt(planDetails[2]);
            module = new Module(moduleCode, year, semester);
            break;

        case "taken":
            if (inputArray.length == 1) {
                throw new InvalidTaskException(command);
            }
            if (!inputArray[1].contains("y/") && !inputArray[1].contains("s/") && !inputArray[1].contains("g/")) {
                throw new InvalidFormatException("y/", "s/", "g/");
            }
            String[] takenDetails = inputArray[1].split(" y/| s/| g/", 4);
            String takenCode = takenDetails[0];
            Integer takenYear = Integer.parseInt(takenDetails[1]);
            Integer takenSemester = Integer.parseInt(takenDetails[2]);
            String takenGrade = takenDetails[3];
            module = new Module(takenCode, takenYear, takenSemester, takenGrade);
            break;

        default:
            throw new InvalidCommandException();

        }
        return module;
    }
}
