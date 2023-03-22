package seedu.penus.modules;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import seedu.penus.api.ModuleRetriever;

import seedu.penus.exceptions.DuplicateModuleException;
import seedu.penus.exceptions.InvalidCommandException;
import seedu.penus.exceptions.InvalidGradeException;
import seedu.penus.exceptions.InvalidIndexException;
import seedu.penus.storage.ResourceManager;
import seedu.penus.ui.Ui;
import seedu.penus.user.User;

public class ModuleList {
    private final List<Module> modules;
    private final ResourceManager resource;
    private final List<String[]> moduleDetails;


    private User user = new User();

    /**
     * Overloaded constructor for the creation of a ModuleList object.
     */
    public ModuleList() {
        this.modules = new ArrayList<>();
        this.resource = new ResourceManager();
        this.moduleDetails = resource.getAllModuleDetails();
        //this.user = new User();
    }

    /**
     * Constructor for the creation of a ModuleList object.
     *
     * @param mods List of mods to be included in ModuleLIst after creation.
     */
    public ModuleList(User user, List<Module> mods) {
        this.modules = mods;
        this.resource = new ResourceManager();
        this.moduleDetails = resource.getAllModuleDetails();
        this.user = user;
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

    public User getUser() {
        return this.user;
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

        String[] messagePacket = {addedMessage, sizeMessage};
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

        String[] messagePacket = {removeMessage, sizeMessage};
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

        String[] messagePacket = {markMessage};
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

        String[] messagePacket = {unmarkMessage};
        Ui.printMessage(messagePacket);
    }


    public void printModules() throws InvalidGradeException {
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
                CAP.printSemCAP(modules);
            }
        }
        CAP.printOverallCAP(modules);
    }

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

    public void statusPrintFunction(List<String> moduleList) {
        for (String s : moduleList) {
            System.out.print("\t" + s);
            System.out.print(" " + ModuleRetriever.retrieveTitle(s) + " " + "MCs:"
                    + ModuleRetriever.retrieveModuleCredit(s));
            System.out.println();
        }
    }

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
        Ui.printDivider();
    }

}
