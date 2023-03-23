package seedu.penus.modules;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.naming.PartialResultException;
import java.util.Scanner;

import seedu.penus.api.ModuleRetriever;
import seedu.penus.exceptions.CourseIndexOutOfBoundsException;
import seedu.penus.exceptions.DuplicateModuleException;
import seedu.penus.exceptions.InvalidCommandException;
import seedu.penus.exceptions.InvalidCourseIndexException;
import seedu.penus.storage.ResourceManager;
import seedu.penus.ui.Ui;
import seedu.penus.user.User;

public class ModuleList {
    private final List<Module> modules;
    private User user;
    private ResourceManager resource;
    private List<String[]> moduleDetails;

    /**
     * Overloaded constructor for the creation of a ModuleList object.
     */
    public ModuleList() {
        this.modules = new ArrayList<>();
        this.resource = new ResourceManager();
        this.moduleDetails = resource.getAllModuleDetails();
        this.user = new User();
    }

    /**
     * Constructor for the creation of a ModuleList object.
     * 
     * @param mods List of mods to be included in ModuleLIst after creation.
     */
    public ModuleList(List<Module> mods) {
        this.modules = mods;
        this.resource = new ResourceManager();
        this.moduleDetails = resource.getAllModuleDetails();
    }

    /**
     * Returns the number of modules in the module list
     * 
     * @return the number of modules in the module list
     */
    public int size() {
        return modules.size();
    }

    /**
     * Gets the list of modules.
     * 
     * @return List of modules inline separated by commas
     */
    public List<Module> getModuleList() {
        return this.modules;
    }

    /**
     * Gets a module from the module list by index
     *
     * @param index the index of the module
     * @return the module corresponding to the given index
     */
    public Module getModule(int index) {
        return modules.get(index);
    }

    /**
     * Adds a given module to the ModuleList object.
     * 
     * @param module The module to be added.
     */
    public void addModule(Module module) throws DuplicateModuleException {
        for (Module m : modules) {
            if (m.getCode().equals(module.getCode())) {
                throw new DuplicateModuleException("This module has already been added to the list. Please try again");
            }
        }

        this.modules.add(module);

        String addedMessage = "\tModule has been added:\n" + "\t  " + module;
        String sizeMessage = printSize();

        String[] messagePacket = { addedMessage, sizeMessage };
        Ui.printMessage(messagePacket);
    }

    /**
     * Deletes the module identified by its code from the ModuleList
     * 
     * @param deleteCode The module code corresponding to the module to be deleted.
     */
    public void deleteModule(String deleteCode) throws InvalidCommandException {
        int index = -1;
        for (int i = 0; i < this.modules.size(); i++) {
            if (modules.get(i).getCode().equals(deleteCode.toUpperCase())) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new InvalidCommandException("No such module exists!");
        }
        Module modToDelete = this.modules.get(index);
        this.modules.remove(modToDelete);

        String removeMessage = "\tModule has been removed:\n" + "\t  " + modToDelete;
        String sizeMessage = printSize();

        String[] messagePacket = { removeMessage, sizeMessage };
        Ui.printMessage(messagePacket);
    }

    /**
     * Gets the number of modules in the list as a message.
     * 
     * @return sizeMessage The message indicating how many modules are in the list.
     */
    public String printSize() {

        if (this.modules.size() == 1) {
            return "\tYou have " + this.modules.size() + " module in your planner.";
        } else {
            return "\tYou have " + this.modules.size() + " modules in your planner.";
        }
    }

    /**
     * Marks the module as taken with grade
     * 
     * @param modCode The module code to be marked taken
     * @param grade   The grade received upon taking the module
     */
    public void markModule(String modCode, String grade) throws InvalidCommandException {
        int index = -1;
        for (int i = 0; i < this.modules.size(); i++) {
            if (modules.get(i).getCode().equals(modCode.toUpperCase())) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new InvalidCommandException("No such module exists!");
        }
        Module currentMod = this.modules.get(index);
        currentMod.markTaken(grade);

        String markMessage = "\tModule has been taken:\n" + "\t  " + currentMod;

        String[] messagePacket = { markMessage };
        Ui.printMessage(messagePacket);
    }

    /**
     * Unmarks the module, identified by its order, as incomplete.
     * 
     * @param modNum The number corresponding to the module to be unmarked.
     */
    public void unmarkMod(int modNum) {
        Module currentMod = this.modules.get(modNum - 1);
        currentMod.markUntaken();

        String unmarkMessage = "\tModule has been untaken:\n" + "\t  " + currentMod;

        String[] messagePacket = { unmarkMessage };
        Ui.printMessage(messagePacket);
    }

    /**
     * Lists out the modules in the user specified range.
     * 
     * @param userSemester
     * @param userYear
     */
    public void printModules(int userYear, int userSemester) {
        Map<Integer, Map<Integer, List<String[]>>> modulesByYearAndSemester = new HashMap<>();

        for (Module m : modules) {
            int year = m.getYear();
            int sem = m.getSem();
            String[] moduleArray = new String[] { m.getCode(), m.getGrade() };

            modulesByYearAndSemester.computeIfAbsent(year, k -> new HashMap<>())
                    .computeIfAbsent(sem, k -> new ArrayList<>())
                    .add(moduleArray);
        }

        Ui.printDivider();
        if (userYear == -1 && userSemester == -1) { // List all modules
            for (int year = 1; year < 5; year++) {
                for (int semester = 1; semester <= 2; semester++) {
                    System.out.println("- Year " + year + " Semester " + semester + " -");

                    List<String[]> modules = modulesByYearAndSemester.getOrDefault(year, new HashMap<>())
                            .getOrDefault(semester, new ArrayList<>());

                    if (modules.isEmpty()) {
                        System.out.println("\tNo modules taken/added.");
                    } else {
                        for (String[] s : modules) {
                            System.out.println(s[0] + " " + s[1]);
                        }
                    }
                }
            }
            Ui.printDivider();
        } else if (userYear != -1 && userSemester == -1) { // Year specified but not semester
            for (int semester = 1; semester <= 2; semester++) {
                System.out.println("- Year " + userYear + " Semester " + semester + " -");

                List<String[]> modules = modulesByYearAndSemester.getOrDefault(userYear, new HashMap<>())
                        .getOrDefault(semester, new ArrayList<>());

                if (modules.isEmpty()) {
                    System.out.println("\tNo modules taken/added.");
                } else {
                    for (String[] s : modules) {
                        System.out.println(s[0] + " " + s[1]);
                    }
                }
            }
            Ui.printDivider();
        } else if (userYear != -1 && userSemester != -1) { // both Sem and Year specified
            System.out.println("- Year " + userYear + " Semester " + userSemester + " -");

            List<String[]> modules = modulesByYearAndSemester.getOrDefault(userYear, new HashMap<>())
                    .getOrDefault(userSemester, new ArrayList<>());

            if (modules.isEmpty()) {
                System.out.println("\tNo modules taken/added.");
            } else {
                for (String[] s : modules) {
                    System.out.println(s[0] + " " + s[1]);
                }
            }
            Ui.printDivider();
        }
    }

    /**
     * Calculates the total number of MCs that the user has completed
     * 
     * @param takenCoreModulesList the list of modules that have been taken
     * @return Returns the number of MCs that the user has taken
     */
    public int numberOfMcsTaken(List<String> takenCoreModulesList) {
        int numberOfMcs = 0;
        for (String currentUserModuleCode : takenCoreModulesList) {
            for (String[] moduleDetail : moduleDetails) {
                String moduleDetailCode = moduleDetail[0];
                int moduleMcs = Integer.parseInt(moduleDetail[2]);
                if (currentUserModuleCode.equals(moduleDetailCode)) {
                    numberOfMcs = numberOfMcs + moduleMcs;
                }
            }
        }
        return numberOfMcs;
    }

    /**
     * 
     * @return
     */
    public List<String> getTakenCoreModsList() {
        List<String> coreMods = resource.getCoreMods();
        List<String> takenCoreMods = new ArrayList<>();
        for (String currentCoreModCode : coreMods) {
            for (Module module : modules) {
                String currentUserModuleCode = module.moduleCode;
                boolean isCurrentUserModuleTaken = module.isTaken;
                if (currentCoreModCode.equals(currentUserModuleCode) && isCurrentUserModuleTaken) {
                    takenCoreMods.add(currentCoreModCode);
                    break;
                }
            }
        }
        return takenCoreMods;
    }

    /**
     * 
     * @return
     */
    public List<String> getUntakenCoreModsList() {
        List<String> coreMods = resource.getCoreMods();
        List<String> untakenCoreMods = new ArrayList<>();
        for (String coreMod : coreMods) {
            boolean isCoreModTaken = false;
            String currentCoreModCode = coreMod;
            for (Module module : modules) {
                String currentUserModuleCode = module.moduleCode;
                boolean isCurrentUserModuleTaken = module.isTaken;
                if (currentCoreModCode.equals(currentUserModuleCode) && isCurrentUserModuleTaken) {
                    isCoreModTaken = true;
                    break;
                }
            }
            if (!isCoreModTaken) {
                untakenCoreMods.add(currentCoreModCode);
            }
        }
        return untakenCoreMods;
    }

    /**
     * Prints the status of the user's overall schooling progress.
     * Shows taken and untaken modules on separate lines
     * 
     * @param moduleList the list of all modules taken
     */
    public void statusPrintFunction(List<String> moduleList) {
        List<String[]> moduleDetails = resource.getAllModuleDetails();
        for (String s : moduleList) {
            System.out.print("\t" + s);
            for (String[] moduleDetail : moduleDetails) {
                if (moduleDetail[0].equals(s)) {
                    System.out.print(" " + moduleDetail[1] + " " + "MCs:" + moduleDetail[2]);
                }
            }
            System.out.println();
        }
    }

    /**
     * Prints the status of the user's overall schooling progress.
     */
    public void printStatus() {
        List<String> takenCoreModsList = getTakenCoreModsList();
        List<String> untakenCoreModsList = getUntakenCoreModsList();
        Ui.printDivider();
        System.out.println("\t--------- Taken ---------");
        statusPrintFunction(takenCoreModsList);
        System.out.println("\n\t--------- Not Taken ---------");
        statusPrintFunction(untakenCoreModsList);
        System.out.println("\n\tTotal MCs taken: " + numberOfMcsTaken(takenCoreModsList) + "/160");
        Ui.printDivider();
    }

    /**
     * Initialises the user's profile in the program. Allows the user to choose
     * their course, so as to get their core modules.
     * 
     * @throws InvalidCourseIndexException
     * @throws CourseIndexOutOfBoundsException
     */
    public void initialize() throws InvalidCourseIndexException, CourseIndexOutOfBoundsException {
        Scanner input = new Scanner(System.in);
        String inputCourse = "";
        int inputCourseIndex;
        System.out.println("\t What is your name?");
        String inputName = input.nextLine();
        user.setName(inputName);
        System.out.println("\t Name confirmed: " + user.name);
        System.out.println("\t Now, please enter the index of your corresponding course");
        System.out.println("\t 1. Biomedical Engineering \n" +
                "\t 2. Chemical Engineering \n" +
                "\t 3. Civil Engineering \n" +
                "\t 4. Computer Engineering \n" +
                "\t 5. Electrical Engineering \n" +
                "\t 6. Environmental Engineering \n" +
                "\t 7. Industrial and Systems Engineering \n" +
                "\t 8. Mechanical Engineering \n ");
        try {
            inputCourseIndex = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            throw new InvalidCourseIndexException("The index must be an integer. Please initialize again.");
        }

        switch (inputCourseIndex) {
            case 1:
                inputCourse = "Biomedical Engineering";
                break;
            case 2:
                inputCourse = "Chemical Engineering";
                break;
            case 3:
                inputCourse = "Civil Engineering";
                break;
            case 4:
                inputCourse = "Computer Engineering";
                break;
            case 5:
                inputCourse = "Electrical Engineering";
                break;
            case 6:
                inputCourse = "Environmental Engineering";
                break;
            case 7:
                inputCourse = "Industrial and Systems Engineering";
                break;
            case 8:
                inputCourse = "Mechanical Engineering";
                break;
            default:
                throw new CourseIndexOutOfBoundsException("Enter within the index. Please initialize again");
        }
        user.setCourse(inputCourse);
        System.out.println("\t Course Confirmed: " + user.course);
        System.out.println("\t Initialization Completed. Please type help for list of commands");
    }

    /**
     * Prints a user guide on accepted commands.
     */
    public static void printHelp() {
        Ui.printDivider();
        System.out.println("\texit" + "\t\t\t\t\t\t\t\tExits the program");
        System.out.println("\tlist [FILTER]"
                + "\t\t\t\t\t\t\tDisplays a list of all modules taken or planned in the specified Year or Semester\n"
                + "\t\t\t\t\t\t\t\t\tIf [FILTER] is not specified, then all modules will shown.");
        System.out.println("\tmark [MODULE CODE] g/[GRADE]"
                + "\t\t\t\t\tMarks the module that has been cleared, while updating its grades");
        System.out.println(
                "\tplan [MODULE CODE] y/[YEAR] s/[SEMESTER]"
                        + "\t\t\tAdds a module to the planner as an untaken module");
        System.out.println("\tremove [MODULECODE]" + "\t\t\t\t\t\tRemoves a module from the planner");
        System.out.println("\tstatus" + "\t\t\t\t\t\t\t\tDisplays the status of Core Modules and MCs taken");
        System.out.println("\ttaken [MODULE CODE] y/[YEAR] s/[SEMESTER] g/[GRADE]"
                + "\t\tAdds a module to the planner as a module you have already taken");
        Ui.printDivider();
    }
}
