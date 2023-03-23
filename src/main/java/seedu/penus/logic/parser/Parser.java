package seedu.penus.logic.parser;

import static seedu.penus.logic.parser.CliSyntax.LIST;
import static seedu.penus.logic.parser.CliSyntax.STATUS;
import static seedu.penus.logic.parser.CliSyntax.EXIT;
import static seedu.penus.logic.parser.CliSyntax.MARK;
import static seedu.penus.logic.parser.CliSyntax.REMOVE;
import static seedu.penus.logic.parser.CliSyntax.PLAN;
import static seedu.penus.logic.parser.CliSyntax.TAKEN;
import seedu.penus.common.exceptions.DuplicateModuleException;
import seedu.penus.common.exceptions.InvalidCommandException;
import seedu.penus.common.exceptions.InvalidFormatException;
import seedu.penus.common.exceptions.InvalidGradeException;
import seedu.penus.common.exceptions.InvalidIndexException;
import seedu.penus.common.exceptions.InvalidModuleException;
import seedu.penus.common.exceptions.InvalidSemesterException;
import seedu.penus.common.exceptions.InvalidYearException;
import static seedu.penus.logic.parser.CliSyntax.DETAILS;
import static seedu.penus.logic.parser.CliSyntax.HELP;
import static seedu.penus.logic.parser.CliSyntax.INIT;
import seedu.penus.logic.utils.Grade;
import seedu.penus.logic.commands.Command;
import seedu.penus.logic.commands.PlanCommand;
import seedu.penus.logic.commands.TakenCommand;
import seedu.penus.logic.commands.RemoveCommand;
import seedu.penus.logic.commands.DetailsCommand;
import seedu.penus.logic.commands.ExitCommand;
import seedu.penus.logic.commands.InitCommand;
import seedu.penus.logic.commands.ListCommand;
import seedu.penus.logic.commands.MarkCommand;
import seedu.penus.logic.commands.StatusCommand;
import seedu.penus.logic.commands.HelpCommand;



public class Parser {
    public Command parseCommand(String userInput)
            throws InvalidCommandException, InvalidModuleException, InvalidFormatException,
            InvalidGradeException, DuplicateModuleException,
            InvalidSemesterException, InvalidIndexException, InvalidYearException {

        String[] inputArray = userInput.split(" ", 2);
        String command = inputArray[0];
        String arguments = inputArray[1];

        switch (command) {
        case INIT:
            return new InitCommand();

        case HELP:
            return new HelpCommand();

        case PLAN:
            PlanParser(arguments);

        case TAKEN:
            TakenParser(arguments);

        case MARK:
            MarkParser(arguments);

        case LIST:
            ListParser(arguments);

        case STATUS:
            return new StatusCommand();

        case REMOVE:
            RemoveParser(arguments);

        case DETAILS:
            DetailsParser(arguments);

        case EXIT:
            return new ExitCommand();

        default: 
            throw new InvalidCommandException();
        }
    }

    public Command PlanParser(String args) {
        if (args.contains("g/")) {
            throw new InvalidFormatException("Grade should not be included!");
        }
        String[] planDetails = args.split(" y/| s/", 3);
        if (planDetails.length != 3 || planDetails[1].length() == 0 || planDetails[2].length() == 0) {
            throw new InvalidFormatException("Try again in the format: PLAN CODE y/YEAR s/SEM");
        }

        String moduleCode = planDetails[0].toUpperCase();
        Integer year = Integer.parseInt(planDetails[1]);

        if (year != 1 && year != 2 && year != 3 && year != 4) {
            throw new InvalidYearException("Year must be 1 to 4. Please try again.");
        }

        int semester = Integer.parseInt(planDetails[2]);
        if (semester != 1 && semester != 2) {
            throw new InvalidSemesterException("Semester must be 1 or 2!");
        }

        return new PlanCommand(moduleCode, year, semester);
    }

    public Command TakenParser(String args) {
        String[] takenDetails = args.split(" y/| s/| g/", 4);
        if (takenDetails.length != 4) {
            throw new InvalidFormatException("Try again in the format: PLAN CODE y/YEAR s/SEM g/GRADE");
        }
        if (takenDetails[1].length() == 0 || takenDetails[2].length() == 0 || takenDetails[3].length() == 0) {
            throw new InvalidFormatException("Try again, y/ s/ g/ cannot be empty");
        }
        String moduleCode = takenDetails[0].toUpperCase();
        int year = Integer.parseInt(takenDetails[1]);
        int semester = Integer.parseInt(takenDetails[2]);
        String grade = takenDetails[3];
        if (!Grade.isValid(grade)) {
            throw new InvalidGradeException();
        }
        if (semester != 1 && semester != 2) {
            throw new InvalidFormatException("Semester must be 1 or 2!");
        }

        return new TakenCommand(moduleCode, year, semester, grade);
    }

    public Command MarkParser(String args) {
        if (!args.contains("g/")) {
            throw new InvalidFormatException("g/");
        }
        String[] details = args.split(" g/");
        if (!Grade.isValid(details[1])) {
            throw new InvalidGradeException();
        }
        String moduleCode = details[0];
        String grade = details[1].toUpperCase();

        return new MarkCommand(moduleCode, grade);
    }

    public Command ListParser(String args) {
        return new ListCommand();
    }   

    public Command RemoveParser(String args) {
        String[] details = args.split(" ");
        if (args.equals("") || details.length >= 2) {
            throw new InvalidModuleException(args);
        }

        String moduleCode = args;

        return new RemoveCommand(moduleCode);
    }

    public Command DetailsParser(String args) {
        String[] details = args.split(" ");
        if (args.equals("") || details.length >= 2) {
            throw new InvalidModuleException(args);
        }
        String moduleCode = args;

        return new DetailsCommand(moduleCode);
    }


    
}
