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
    /**
     * Parses user input into a Command object for execution.
     * @param userInput input string from user
     * @return Command object if no arguments needed, xYZParser(arguments) if arguments needed
     * @throws PenusException invalid command
     */
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

    /**
     * Parses the given {@code String} of arguments in the context of the PlanCommand
     * and returns an PlanCommand object for execution.
     * @throws PenusException if the user input does not conform the expected format
     */
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

    /**
     * Parses the given {@code String} of arguments in the context of the TakenCommand
     * and returns an TakenCommand object for execution.
     * @throws PenusException if the user input does not conform the expected format
     */
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

    /**
     * Parses the given {@code String} of arguments in the context of the MarkCommand
     * and returns an MarkCommand object for execution.
     * @throws PenusException if the user input does not conform the expected format
     */
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

    //can specify year or year+semester
    //but not semester only
    //handle empty
    public Command listParser(String args) throws PenusException {
        if (args.equals("") || args.equals(" ")) {
            //list command with all modules
            return new ListCommand();
        }

        //year must be specified
        // if (!args.contains("y/")) {
        //     throw new InvalidFormatException("Try again in the format: list y/YEAR s/SEM or list y/YEAR or list");
        // }
        if (args.contains("s/") && !args.contains("y/")) { // Semester specified but year not specified
            throw new InvalidFormatException(
                    "\tTry again, y/ must not be empty if s/ is not empty. " +
                            "To show modules for that semester, please specify the year of study.");
        }

        String[] details = args.split("y/| s/",3);
        int year = 0;
        int semester = 0;
        try {
            year = Integer.parseInt(details[1]);
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Must be specified as an integer!");
        }
        if (year < 1 || year > 4) {
            throw new InvalidFormatException("Year must be within 1 to 4");
        }

        if (details.length == 2) {
            return new ListCommand(year,0);
        } else if (details.length == 3) {
            if (args.contains("s/")) {
                try {
                semester = Integer.parseInt(details[2]);
                } catch (NumberFormatException e) {
                    throw new InvalidFormatException("Semester must be specified as an integer!");
                }
            }
            if (semester != 1 && semester != 2) {
                throw new InvalidFormatException("Semester must be 1 or 2!");
            }
            return new ListCommand(year, semester);
        } else {
            throw new InvalidFormatException("Try again in the format: list y/YEAR s/SEM or list y/YEAR or list");
        }
    }   

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveCommand
     * and returns an RemoveCommand object for execution.
     * @throws PenusException if the user input does not conform the expected format
     */
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

    /**
     * Parses the given {@code String} of arguments in the context of the DetailsCommand
     * and returns an DetailsCommand object for execution.
     * @throws PenusException if the user input does not conform the expected format
     */
    public Command detailsParser(String args) throws PenusException {
        String[] details = args.split(" ");
        if (args.equals("") || details.length >= 2) {
            throw new InvalidModuleException(args);
        }
        String moduleCode = args.toUpperCase();

        return new DetailsCommand(moduleCode);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the InitCommand
     * and returns an InitCommand object for execution.
     * @throws PenusException if the user input does not conform the expected format
     */
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
