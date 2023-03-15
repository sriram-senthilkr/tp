package seedu.penus.parser;

import java.util.Scanner;

import seedu.penus.exceptions.*;
import seedu.penus.modules.Module;
import seedu.penus.modules.ModuleList;
import seedu.penus.modules.Grade;

public class CommandParser {
    private static final String LIST = "list";
    private static final String STATUS = "status";
    private static final String EXIT = "exit";
    private static final String MARK = "mark";
    private static final String REMOVE = "remove";
    private static final String PLAN = "plan";
    private static final String TAKEN = "taken";
    
    private final ModuleList moduleList;

    public CommandParser(ModuleList moduleList) {
        this.moduleList = moduleList;
    }

    public void parseCommand(String[] inputArray)
            throws InvalidCommandException, InvalidModuleException, InvalidFormatException, InvalidGradeException, DuplicateModuleException {
        String command = inputArray[0];

        switch(command) {
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

        default:
            throw new InvalidCommandException();
        }
    }

    public void getInput() {
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

                } catch (InvalidModuleException | InvalidCommandException |
                         InvalidGradeException | InvalidFormatException | DuplicateModuleException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while(isRunning);

        input.close();
    }
}
