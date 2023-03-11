package seedu.penus.parser;

import java.util.Scanner;
import seedu.penus.exceptions.InvalidCommandException;
import seedu.penus.exceptions.InvalidFormatException;
import seedu.penus.exceptions.InvalidTaskException;
import seedu.penus.modules.Module;
import seedu.penus.modules.ModuleList;

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

    public void parseCommand(String[] inputArray) throws InvalidCommandException, InvalidTaskException, InvalidFormatException {
        String command = inputArray[0];
        switch(command) {
        case PLAN:
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
            Module plan = new Module(moduleCode, year, semester);
            moduleList.addModule(plan);
            break;

        case TAKEN:
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
            Module taken = new Module(takenCode, takenYear, takenSemester, takenGrade);
            moduleList.addModule(taken);
            break;

        case MARK:
            if (!inputArray[1].contains("g/")) {
                throw new InvalidFormatException("g/");
            }
            String[] markDetails = inputArray[1].split(" g/");
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

                } catch (InvalidTaskException | InvalidCommandException | InvalidFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while(isRunning);

        input.close();
    }
}
