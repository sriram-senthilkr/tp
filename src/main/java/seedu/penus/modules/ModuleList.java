package seedu.penus.modules;

import java.util.ArrayList;
import java.util.List;

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
            if (modules.get(i).getCode().equals(deleteCode)) {
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

        return "\tYou have " + this.modules.size() + " modules in your planner.";
    }

    /**
     * Marks the module as taken with grade
     * 
     * @param modCode The module code to be marked taken
     * @param grade The grade received upon taking the module
     */
    public void markModule(String modCode, String grade) throws InvalidCommandException {
        int index = -1;
        for (int i = 0; i < this.modules.size(); i++) {
            if (modules.get(i).getCode().equals(modCode)) {
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

        String unmarkMessage =
                "\tModule has been untaken:\n" + "\t  " + currentMod;

        String[] messagePacket = {unmarkMessage};
        Ui.printMessage(messagePacket);
    }

    //TODO: change list structure
    public void printModules() {
        String[] messagePacket = new String[this.modules.size() + 1];
        messagePacket[0] = "\tListing all modules:";
        int messageCount = 1;

        for (int i = 0; i < this.modules.size(); i++) {
            String line = "\t" + (i + 1) + ". " + this.modules.get(i);
            messagePacket[messageCount++] = line;
        }
        Ui.printMessage(messagePacket);
    }

    /*
     public void printModules() {
         for (int year  )
     }
    */


    public int numberOfMcsTaken(List<String> takenCoreModulesList){
        int numberOfMcs = 0;
        for (int i = 0; i < takenCoreModulesList.size(); i++){
            String currentUserModuleCode = takenCoreModulesList.get(i);
            for (int j = 0; j < moduleDetails.size(); j++){
                String moduleDetailCode = moduleDetails.get(j)[0];
                int moduleMcs = Integer.parseInt(moduleDetails.get(j)[2]);
                if(currentUserModuleCode.equals(moduleDetailCode)){
                    numberOfMcs = numberOfMcs + moduleMcs;
                }
            }
        }
        return numberOfMcs;
    }


    public List<String> retrieveTakenCoreModsList() {
        List<String> coreMods = fileManager.retrieveCoreMods();
        List<String> takenCoreMods = new ArrayList<>();
        for (int i = 0; i < coreMods.size(); i++) {
            String currentCoreModCode = coreMods.get(i);
            for (int j = 0; j < modules.size(); j++) {
                String currentUserModuleCode = modules.get(j).moduleCode;
                boolean isCurrentUserModuleTaken = modules.get(j).isTaken;
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
        for (int i = 0; i < coreMods.size(); i++) {
            boolean isCoreModTaken = false;
            String currentCoreModCode = coreMods.get(i);
            for (int j = 0; j < modules.size(); j++) {
                String currentUserModuleCode = modules.get(j).moduleCode;
                boolean isCurrentUserModuleTaken = modules.get(j).isTaken;
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
        for (int k = 0; k < moduleList.size(); k++) {
            System.out.print(moduleList.get(k));
            for (int j = 0; j < moduleDetails.size(); j++){
                if (moduleDetails.get(j)[0].equals(moduleList.get(k))){
                    System.out.print(" " + moduleDetails.get(j)[1] + " " + "MCs:" + moduleDetails.get(j)[2]);
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
}
