package seedu.penus.parser;

import java.util.Scanner;

import seedu.penus.api.ModuleRetriever;
import seedu.penus.exceptions.DuplicateModuleException;
import seedu.penus.exceptions.InvalidCommandException;
import seedu.penus.exceptions.InvalidFormatException;
import seedu.penus.exceptions.InvalidGradeException;
import seedu.penus.exceptions.InvalidModuleException;
import seedu.penus.exceptions.InvalidSemesterException;
import seedu.penus.exceptions.InvalidIndexException;

import seedu.penus.modules.Module;
import seedu.penus.modules.ModuleList;
import seedu.penus.storage.FileManager;
import seedu.penus.ui.Ui;
import seedu.penus.modules.Grade;

public class CommandParser {
    private static final String LIST = "list";
    private static final String STATUS = "status";
    private static final String EXIT = "exit";
    private static final String MARK = "mark";
    private static final String REMOVE = "remove";
    private static final String PLAN = "plan";
    private static final String TAKEN = "taken";
    private static final String PREREQUISITE = "prerequisite";
    private static final String PRECLUSION = "preclusion";
    private static final String DESCRIPTION = "description";
    private static final String TITLE = "title";
    private static final String MODULECREDIT = "modulecredit";
    private static final String HELP = "help";

    private static final String INITIALIZATION = "init";

    private final ModuleList moduleList;

    public CommandParser(ModuleList moduleList) {
        this.moduleList = moduleList;
    }

    public void parseCommand(String[] inputArray)
            throws InvalidCommandException, InvalidModuleException, InvalidFormatException,
            InvalidGradeException, DuplicateModuleException,
            InvalidSemesterException, InvalidIndexException {
        String command = inputArray[0];
        String moduleCode;

        switch(command) {
        case INITIALIZATION:
            moduleList.initialize();
            break;
            
        case PLAN:
        case TAKEN:
            if (inputArray.length == 1) {
                throw new InvalidModuleException(command);
            }
            Module moduleToAdd = ModuleParser.getModuleFromCommand(inputArray);
            moduleList.addModule(moduleToAdd);
            break;

        case MARK:
            if (!inputArray[1].contains("g/")) {
                throw new InvalidFormatException("g/");
            }
            String[] markDetails = inputArray[1].split(" g/");
            if (!Grade.isValid(markDetails[1])) {
                throw new InvalidGradeException();
            }
            moduleList.markModule(markDetails[0], markDetails[1]);
            break;

        case LIST:
            moduleList.printModules();
            break;

        case STATUS:
            moduleList.printStatus();
            break;

        case REMOVE:
            String removeCode = inputArray[1];
            moduleList.deleteModule(removeCode);
            break;

        case PREREQUISITE:
            if (inputArray.length == 1 || inputArray.length > 2) {
                throw new InvalidModuleException(command);
            }
            moduleCode = inputArray[1];
            ModuleRetriever.getData(moduleCode);
            ModuleRetriever.printPrerequisite();
            break;

        case PRECLUSION:
            if (inputArray.length == 1 || inputArray.length > 2) {
                throw new InvalidModuleException(command);
            }
            moduleCode = inputArray[1];
            ModuleRetriever.getData(moduleCode);
            ModuleRetriever.printPreclusion();
            break;

        case DESCRIPTION:
            if (inputArray.length == 1 || inputArray.length > 2) {
                throw new InvalidModuleException(command);
            }
            moduleCode = inputArray[1];
            ModuleRetriever.getData(moduleCode);
            ModuleRetriever.printDescription();
            break;

        case TITLE:
            if (inputArray.length == 1 || inputArray.length > 2) {
                throw new InvalidModuleException(command);
            }
            moduleCode = inputArray[1];
            ModuleRetriever.getData(moduleCode);
            ModuleRetriever.printTitle();
            break;

        case MODULECREDIT:
            if (inputArray.length == 1 || inputArray.length > 2) {
                throw new InvalidModuleException(command);
            }
            moduleCode = inputArray[1];
            ModuleRetriever.getData(moduleCode);
            ModuleRetriever.printModuleCredit();
            break;

        case HELP:
            Ui.printHelp();
            break;

        default:
            throw new InvalidCommandException();
        }
    }

    public void getInput(FileManager fileManager) {
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;

        do {
            String inputString = input.nextLine();
            String[] inputArray = inputString.split(" ", 2);
            if (inputArray[0].equals(EXIT)) {
                isRunning = false;
            } else {
                try {
                    parseCommand(inputArray);

                } catch (InvalidModuleException | InvalidCommandException | InvalidGradeException |
                         InvalidFormatException | DuplicateModuleException | InvalidIndexException |
                         InvalidSemesterException e) {
                    System.out.println(e.getMessage());
                }
            }
            fileManager.save(moduleList);
        } while(isRunning);

        input.close();
    }
}
