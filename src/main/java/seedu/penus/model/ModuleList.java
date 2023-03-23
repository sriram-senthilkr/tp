package seedu.penus.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import seedu.penus.common.exceptions.DuplicateModuleException;
import seedu.penus.common.exceptions.InvalidCommandException;
import seedu.penus.common.exceptions.InvalidGradeException;
import seedu.penus.common.exceptions.InvalidIndexException;
import seedu.penus.logic.utils.CAP;
import seedu.penus.logic.utils.ModuleRetriever;
import seedu.penus.storage.ResourceStorage;
import seedu.penus.ui.Ui;

public class ModuleList {
    private final List<Module> modules;
    private final ResourceStorage resource;
    private final List<String[]> moduleDetails;


    private User user = new User();

    /**
     * Overloaded constructor for the creation of a ModuleList object.
     */
    public ModuleList() {
        this.modules = new ArrayList<>();
        this.resource = new ResourceStorage();
        this.moduleDetails = resource.getAllModuleDetails();
        //this.user = new User();
    }

    /**
     * Constructor for the creation of a ModuleList object.
     *
     * @param mods List of mods to be included in ModuleLIst after creation.
     */
    public ModuleList(List<Module> mods) {
        this.modules = mods;
        this.resource = new ResourceStorage();
        this.moduleDetails = resource.getAllModuleDetails();
        this.user = user;
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

    public User getUser() {
        return this.user;
    }

    public boolean hasModule(Module module) {
        return modules.contains(module);
    }

    /**
     * Adds a given module to the ModuleList object.
     *
     * @param module The module to be added.
     */
    public void addModule(Module module) {
        this.modules.add(module);
    }

    /**
     * Deletes the module identified by its code from the ModuleList
     *
     * @param deleteCode The module code corresponding to the module to be deleted.
     */
    public void removeModule(int index) {
        this.modules.remove(index);
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
     * Gets the number of modules in the list as a message.
     *
     * @return sizeMessage The message indicating how many modules are in the list.
     */
    public String getSizeMessage() {

        if (this.modules.size() == 1) {
            return "\tYou have " + this.modules.size() + " module in your planner.";
        } else {
            return "\tYou have " + this.modules.size() + " modules in your planner.";
        }
    }

    /**
     * Lists out the modules in the user specified range.
     * 
     * @param userSemester
     * @param userYear
     * @throws InvalidGradeException
     */
    public void printModules(int userYear, int userSemester) throws InvalidGradeException {
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
                CAP.printSemCAP(modules);
            }
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
        }
        CAP.printOverallCAP(modules);
    }

    /**
     * Calculates the total number of MCs that the user has completed
     *
     * @return Returns the number of MCs that the user has taken
     */
    public int numberOfMcsTaken() {
        int numberOfMcs = 0;
        for (Module currentUserModule : modules) {
            if (currentUserModule.isTaken) {
                numberOfMcs = numberOfMcs +
                Integer.parseInt(ModuleRetriever.retrieveModuleCredit(currentUserModule.moduleCode));
            }
        }
        return numberOfMcs;
    }
    
    public String getGESS() {
        for (Module currentUserModule : modules)  {
            if (currentUserModule.moduleCode.substring(0,4).equals("GESS")) {
                return "\t" + currentUserModule.moduleCode +
                        " " + ModuleRetriever.retrieveTitle(currentUserModule.moduleCode)
                        + " " + "MCs:" + ModuleRetriever.retrieveModuleCredit(currentUserModule.moduleCode);
            }
        }
        return "";
    }

    public String getGEC() {
        for (Module currentUserModule : modules) {
            if (currentUserModule.moduleCode.substring(0, 3).equals("GEC")) {
                return "\t" + currentUserModule.moduleCode +
                        " " + ModuleRetriever.retrieveTitle(currentUserModule.moduleCode)
                        + " " + "MCs:" + ModuleRetriever.retrieveModuleCredit(currentUserModule.moduleCode);
            }
        }
        return "";
    }

    public String getGEN() {
        for (Module currentUserModule : modules)  {
            if (currentUserModule.moduleCode.substring(0,3).equals("GEN")) {
                return "\t" + currentUserModule.moduleCode +
                        " " + ModuleRetriever.retrieveTitle(currentUserModule.moduleCode) +
                        " " + "MCs:" + ModuleRetriever.retrieveModuleCredit(currentUserModule.moduleCode);
            }
        }
        return "";
    }

    public List<String> getTakenCoreModsList() {
        List<String> coreMods = resource.getCoreMods().get(user.course);
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
     * @return
     */
    public List<String> getUntakenCoreModsList() {
        List<String> coreMods = resource.getCoreMods().get(user.course);
        List<String> untakenCoreMods = new ArrayList<>();
        for (String coreMod : coreMods) {
            boolean isCoreModTaken = false;
            for (Module module : modules) {
                String currentUserModuleCode = module.moduleCode;
                boolean isCurrentUserModuleTaken = module.isTaken;
                if (coreMod.equals(currentUserModuleCode) && isCurrentUserModuleTaken) {
                    isCoreModTaken = true;
                    break;
                }
            }
            if (!isCoreModTaken) {
                untakenCoreMods.add(coreMod);
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
        for (String s : moduleList) {
            System.out.print("\t" + s);
            System.out.print(" " + ModuleRetriever.retrieveTitle(s) + " " + "MCs:"
                    + ModuleRetriever.retrieveModuleCredit(s));
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
        if (!getGESS().equals("")) {
            System.out.println(getGESS());
        }
        if (!getGEC().equals("")) {
            System.out.println(getGEC());
        }
        if (!getGEN().equals("")) {
            System.out.println(getGEN());
        }
        statusPrintFunction(takenCoreModsList);
        System.out.println("\n\t--------- Not Taken ---------");
        if (getGESS().equals("")) {
            System.out.println("\tGESSXXXX");
        }
        if (getGEC().equals("")) {
            System.out.println("\tGECXXXX");
        }
        if (getGEN().equals("")) {
            System.out.println("\tGENXXXX");
        }
        statusPrintFunction(untakenCoreModsList);
        System.out.println("\n\tTotal MCs taken: " + numberOfMcsTaken() + "/160");
        Ui.printDivider();
    }

    /**
     * Initialises the user's profile in the program. Allows the user to choose
     * their course, so as to get their core modules.
     * 
     * @throws InvalidIndexException
     */
    public void initialize() throws InvalidIndexException {
        Scanner input = new Scanner(System.in);
        String inputCourse;
        int inputCourseIndex;
        Ui.printDivider();
        System.out.println("\tWhat is your name?");
        Ui.printDivider();
        String inputName = input.nextLine();
        if (inputName.equals("")) {
            throw new InvalidIndexException("Name cannot be empty. Please init again");
        }
        user.setName(inputName);
        Ui.printDivider();
        System.out.println("\t Name confirmed: " + user.name + "\n");
        System.out.println("\t Now, please enter the index of your corresponding course");
        System.out.println("\t 1. Biomedical Engineering \n" +
                           "\t 2. Chemical Engineering \n" +
                           "\t 3. Civil Engineering \n" +
                           "\t 4. Computer Engineering \n" +
                           "\t 5. Electrical Engineering \n" +
                           "\t 6. Environmental Engineering \n" +
                           "\t 7. Industrial and Systems Engineering \n" +
                           "\t 8. Mechanical Engineering");
        Ui.printDivider();
        try {
            inputCourseIndex = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            throw new InvalidIndexException("The index must be an integer. Please initialize again.");
        }
        switch(inputCourseIndex) {
        case 1: inputCourse = "Biomedical Engineering";
                break;
        case 2: inputCourse = "Chemical Engineering";
                break;
        case 3: inputCourse = "Civil Engineering";
                break;
        case 4: inputCourse = "Computer Engineering";
                break;
        case 5: inputCourse = "Electrical Engineering";
                break;
        case 6: inputCourse = "Environmental Engineering";
                break;
        case 7: inputCourse = "Industrial and Systems Engineering";
                break;
        case 8: inputCourse = "Mechanical Engineering";
                break;
        default: throw new InvalidIndexException("Enter within the index. Please initialize again");
        }
        user.setCourse(inputCourse);
        Ui.printDivider();
        System.out.println("\t Course Confirmed: " + user.course);
        System.out.println("\t Initialization Completed. Please type help for list of commands");
    }

    

}
