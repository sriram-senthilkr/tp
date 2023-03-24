// package seedu.penus.logic.parser;

// import java.util.Scanner;
// import seedu.penus.common.exceptions.DuplicateModuleException;
// import seedu.penus.common.exceptions.InvalidCommandException;
// import seedu.penus.common.exceptions.InvalidFormatException;
// import seedu.penus.common.exceptions.InvalidGradeException;
// import seedu.penus.common.exceptions.InvalidIndexException;
// import seedu.penus.common.exceptions.InvalidModuleException;
// import seedu.penus.common.exceptions.InvalidSemesterException;
// import seedu.penus.common.exceptions.InvalidYearException;
// import seedu.penus.logic.utils.Grade;
// import seedu.penus.logic.utils.ModuleRetriever;
// import seedu.penus.model.Module;
// import seedu.penus.model.ModuleList;
// import seedu.penus.storage.FileStorage;
// import seedu.penus.ui.Ui;

// public class CommandParser {
//     private static final String LIST = "list";
//     private static final String STATUS = "status";
//     private static final String EXIT = "exit";
//     private static final String MARK = "mark";
//     private static final String REMOVE = "remove";
//     private static final String PLAN = "plan";
//     private static final String TAKEN = "taken";
//     private static final String PREREQUISITE = "prerequisite";
//     private static final String DESCRIPTION = "description";
//     private static final String TITLE = "title";
//     private static final String MODULECREDIT = "modulecredit";
//     private static final String DETAILS = "details";
//     private static final String HELP = "help";
//     private static final String INITIALIZATION = "init";

//     private final ModuleList moduleList;

//     public CommandParser(ModuleList moduleList) {
//         this.moduleList = moduleList;
//     }

//     public void parseCommand(String[] inputArray)
//             throws InvalidCommandException, InvalidModuleException, InvalidFormatException,
//             InvalidGradeException, DuplicateModuleException,
//             InvalidSemesterException, InvalidIndexException, InvalidYearException {

//         String command = inputArray[0];
//         String moduleCode;

//         switch (command) {
//         case INITIALIZATION:
//             moduleList.initialize();
//             break;

//         case PLAN:
//         case TAKEN:
//             if (inputArray.length == 1) {
//                 throw new InvalidModuleException(command);
//             }
//             Module moduleToAdd = ModuleParser.getModuleFromCommand(inputArray);
//             moduleList.addModule(moduleToAdd);
//             break;

//         case MARK:
//             if (!inputArray[1].contains("g/")) {
//                 throw new InvalidFormatException("g/");
//             }
//             String[] markDetails = inputArray[1].split(" g/");
//             if (!Grade.isValid(markDetails[1])) {
//                 throw new InvalidGradeException();
//             }
//             moduleList.markModule(markDetails[0], markDetails[1]);
//             break;

//         case LIST:
//             if (inputArray.length == 1) {
//                 moduleList.printModules(-1, -1); // Print all modules
//             } else {
//                 String[] rangeToPrint = inputArray[1].split("y/| s/", 3);

//                 if (rangeToPrint.length > 3) {
//                     throw new InvalidFormatException("\tTry again in the format: list y/YEAR s/SEM\n"
//                             + "\tTo show all modules, do not enter year and semester");
//                 }

//                 for (String s : inputArray) {
//                     if (s.contains("s/") && !s.contains("y/")) { // Semester specified but year not specified
//                         throw new InvalidFormatException(
//                                 "\tTry again, y/ must not be empty if s/ is not empty. " +
//                                         "To show modules for that semester, please specify the year of study.");
//                     }
//                 }
//                 // Default values
//                 int yearSpecified = 0;
//                 int semesterSpecified = 0;
//                 try {
//                     yearSpecified = Integer.parseInt(rangeToPrint[1]);
//                 } catch (NumberFormatException e) {
//                     throw new InvalidFormatException("\tYear must be specified as an integer!");
//                 }
                
//                 if (yearSpecified < 1 || yearSpecified > 4) {
//                     throw new InvalidYearException("\tYear must be within 1 to 4!");
//                 }

//                 if (rangeToPrint.length == 2) { // rangeToPrint = ["","1"]
//                     moduleList.printModules(yearSpecified, -1); // Print all modules for the year
//                 } else if (rangeToPrint.length == 3) {

//                     if (inputArray[1].contains("s/")) {
//                         try {
//                             semesterSpecified = Integer.parseInt(rangeToPrint[2]);
//                         } catch (NumberFormatException e) {
//                             throw new InvalidFormatException("\tSemester must be specified as an integer!");
//                         }
//                         if (semesterSpecified != 1 && semesterSpecified != 2) {
//                             throw new InvalidFormatException("\tSemester must be 1 or 2!");
//                         }
//                         moduleList.printModules(yearSpecified, semesterSpecified);
//                     }
//                 } else {
//                     throw new InvalidFormatException("Format is wrong.Type \"help\"for a list of accepted commands");
//                 }
//             }
//             break;

//         case STATUS:
//             moduleList.printStatus();
//             break;

//         case REMOVE:
//             String removeCode = inputArray[1];
//             moduleList.deleteModule(removeCode);
//             break;

//         case PREREQUISITE:
//             if (inputArray.length == 1 || inputArray.length > 2) {
//                 throw new InvalidModuleException(command);
//             }
//             moduleCode = inputArray[1];
//             ModuleRetriever.printPrerequisite(moduleCode);
//             break;

//         case DESCRIPTION:
//             if (inputArray.length == 1 || inputArray.length > 2) {
//                 throw new InvalidModuleException(command);
//             }
//             moduleCode = inputArray[1];
//             ModuleRetriever.printDescription(moduleCode);
//             break;

//         case TITLE:
//             if (inputArray.length == 1 || inputArray.length > 2) {
//                 throw new InvalidModuleException(command);
//             }
//             moduleCode = inputArray[1];
//             ModuleRetriever.printTitle(moduleCode);
//             break;

//         case MODULECREDIT:
//             if (inputArray.length == 1 || inputArray.length > 2) {
//                 throw new InvalidModuleException(command);
//             }
//             moduleCode = inputArray[1];
//             ModuleRetriever.printModuleCredit(moduleCode);
//             break;

//         case DETAILS:
//             if (inputArray.length == 1 || inputArray.length > 2) {
//                 throw new InvalidModuleException(command);
//             }
//             moduleCode = inputArray[1];
//             ModuleRetriever.printDetails(moduleCode);
//             break;

//         case HELP:
//             Ui.printHelp();
//             break;

//         default:
//             throw new InvalidCommandException();
//         }
//     }

//     public void getInput(FileStorage fileManager) {
//         Scanner input = new Scanner(System.in);
//         boolean isRunning = true;

//         do {
//             String inputString = input.nextLine();
//             String[] inputArray = inputString.split(" ", 2);
//             if (inputArray[0].equals(EXIT)) {
//                 isRunning = false;
//             } else {
//                 try {
//                     parseCommand(inputArray);
//                 } catch (InvalidModuleException | InvalidCommandException | InvalidGradeException |
//                          InvalidFormatException | DuplicateModuleException | InvalidIndexException |
//                          InvalidSemesterException | InvalidYearException e) {
//                     System.out.println(e.getMessage());
//                 }
//             }
//             fileManager.save(moduleList);
//         } while (isRunning);

//         input.close();
//     }
// }
