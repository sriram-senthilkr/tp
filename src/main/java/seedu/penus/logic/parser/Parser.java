package seedu.penus.logic.parser;

import static seedu.penus.logic.parser.CliSyntax.LIST;
import static seedu.penus.logic.parser.CliSyntax.STATUS;
import static seedu.penus.logic.parser.CliSyntax.EXIT;
import static seedu.penus.logic.parser.CliSyntax.MARK;
import static seedu.penus.logic.parser.CliSyntax.REMOVE;
import static seedu.penus.logic.parser.CliSyntax.PLAN;
import static seedu.penus.logic.parser.CliSyntax.TAKEN;
import static seedu.penus.logic.parser.CliSyntax.DETAILS;
import static seedu.penus.logic.parser.CliSyntax.HELP;
import static seedu.penus.logic.parser.CliSyntax.INIT;

import seedu.penus.common.exceptions.InvalidCommandException;
import seedu.penus.common.exceptions.InvalidFormatException;
import seedu.penus.common.exceptions.InvalidGradeException;
import seedu.penus.common.exceptions.InvalidModuleException;
import seedu.penus.common.exceptions.InvalidSemesterException;
import seedu.penus.common.exceptions.InvalidYearException;
import seedu.penus.common.exceptions.PenusException;

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
    public Command parseCommand(String userInput) throws PenusException {
        String[] inputArray = userInput.split(" ", 2);
        String command = inputArray[0];
        String arguments = "";
        if (inputArray.length > 1) {
            arguments = inputArray[1];
        }
        switch (command) {
        case INIT:
            return initParser(arguments);

        case HELP:
            return new HelpCommand();

        case PLAN:
            return planParser(arguments);

        case TAKEN:
            return takenParser(arguments);

        case MARK:
            return markParser(arguments);

        case LIST:
            return listParser(arguments);

        case STATUS:
            return new StatusCommand();

        case REMOVE:
            return removeParser(arguments);

        case DETAILS:
            return detailsParser(arguments);

        case EXIT:
            return new ExitCommand();

        default: 
            throw new InvalidCommandException();
        }
    }

    public Command planParser(String args) throws PenusException {
        if (args.contains("g/")) {
            throw new InvalidFormatException("Grade should not be included!");
        }
        String[] planDetails = args.split(" y/| s/", 3);
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
        return new PlanCommand(moduleCode, year, semester);
    }

    public Command takenParser(String args) throws PenusException {
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
        String grade = takenDetails[3].toUpperCase();
        if (!Grade.isValid(grade)) {
            throw new InvalidGradeException();
        }
        if (semester != 1 && semester != 2) {
            throw new InvalidFormatException("Semester must be 1 or 2!");
        }

        return new TakenCommand(moduleCode, year, semester, grade);
    }

    public Command markParser(String args) throws PenusException {
        if (!args.contains("g/")) {
            throw new InvalidFormatException("g/");
        }
        String[] details = args.split(" g/");
        if (!Grade.isValid(details[1])) {
            throw new InvalidGradeException();
        }
        String moduleCode = details[0].toUpperCase();
        String grade = details[1].toUpperCase();

        return new MarkCommand(moduleCode, grade);
    }

    public Command listParser(String args) {
        return new ListCommand();
    }   

    public Command removeParser(String args) throws PenusException {
        String[] details = args.split(" ");
        if (args.equals("") || details.length >= 2) {
            throw new InvalidModuleException(args);
        }
        if (args.equals(" ")) {
            throw new InvalidModuleException("Please specify a module");
        }

        String moduleCode = args.toUpperCase();

        return new RemoveCommand(moduleCode);
    }

    public Command detailsParser(String args) throws PenusException {
        String[] details = args.split(" ");
        if (args.equals("") || details.length >= 2) {
            throw new InvalidModuleException(args);
        }
        String moduleCode = args.toUpperCase();

        return new DetailsCommand(moduleCode);
    }

    public Command initParser (String args) throws PenusException {
        int courseCode;
        String [] initDetails = args.split ("n/| c/");
        if (initDetails.length != 3) {
            throw new InvalidFormatException("Try again in the format: init n/NAME c/COURSE CODE");
        }
        if (initDetails[1].length() == 0 || initDetails[2].length() == 0) {
            throw new InvalidFormatException("Try again, n/ c/ cannot be empty");
        }
        String name = initDetails[1];
        try {
            courseCode = Integer.parseInt(initDetails[2]);
        } catch (NumberFormatException e){
            throw new InvalidFormatException("c/ must be an integer");
        }
        return new InitCommand(name, courseCode);

    }


    
}
