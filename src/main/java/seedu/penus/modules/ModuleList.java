package seedu.penus.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.naming.PartialResultException;

import seedu.penus.exceptions.DuplicateModuleException;
import seedu.penus.exceptions.InvalidCommandException;

import seedu.penus.storage.FileManager;
//import seedu.penus.exceptions.InvalidGradeException;
import seedu.penus.ui.Ui;

public class ModuleList {
    private final List<Module> modules;

    private FileManager fileManager = new FileManager();
    List<String[]> moduleDetails = fileManager.getAllModuleDetails();

    /**
     * Overloaded constructor for the creation of a ModuleList object.
     */
    public ModuleList() {
        this.modules = new ArrayList<>();
        this.fileManager = new FileManager();
    }

    /**
     * Constructor for the creation of a ModuleList object.
     * 
     * @param mods List of mods to be included in ModuleLIst after creation.
     */
    public ModuleList(List<Module> mods) {
        this.modules = mods;
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

    public void printModules() {
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
            }
        }
    }

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

    public List<String> retrieveTakenCoreModsList() {
        List<String> coreMods = fileManager.retrieveCoreMods();
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

    public List<String> retrieveUntakenCoreModsList() {
        List<String> coreMods = fileManager.retrieveCoreMods();
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

    public void statusPrintFunction(List<String> moduleList) {
        List<String[]> moduleDetails = fileManager.getAllModuleDetails();
        for (String s : moduleList) {
            System.out.print(s);
            for (String[] moduleDetail : moduleDetails) {
                if (moduleDetail[0].equals(s)) {
                    System.out.print(" " + moduleDetail[1] + " " + "MCs:" + moduleDetail[2]);
                }
            }
            System.out.println();
        }
    }

    public void printStatus() {
        List<String> takenCoreModsList = retrieveTakenCoreModsList();
        List<String> untakenCoreModsList = retrieveUntakenCoreModsList();
        System.out.println("-Taken-");
        statusPrintFunction(takenCoreModsList);
        System.out.println("-Not Taken-");
        statusPrintFunction(untakenCoreModsList);
        System.out.println(numberOfMcsTaken(takenCoreModsList) + "/160");
    }

    public static void printHelp() {
        Ui.printDivider();
        System.out.println("\texit" + "\t\t\t\t\t\t\t\tExits the program");
        System.out.println("\tlist mods [FILTER]" + "\t\t\t\t\t\tDisplays a list of all modules taken or planned."
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tIf [FILTER] is not specified, then all modules will shown.");
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
