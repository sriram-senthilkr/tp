package seedu.penus.modules;

import java.util.ArrayList;
import java.util.List;
import seedu.penus.exceptions.InvalidCommandException;

import seedu.penus.storage.FileManager;
import seedu.penus.exceptions.InvalidGradeException;
import seedu.penus.ui.Ui;


public class ModuleList {
    private final List<Module> modules;

    FileManager fileManager = new FileManager();
    List<String> coreMods = fileManager.retrieveCoreMods();
    List<String[]> moduleDetails = fileManager.getAllModuleDetails();


    /**
     * Overloaded constructor for the creation of a ModuleList object.
     */
    public ModuleList() {
        this.modules = new ArrayList<>();
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
    public void addModule(Module module) {
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

    public int numberOfMcsTaken(){
        int numberOfMcs = 0;
        for (int i = 0; i < modules.size(); i++){
            for (int j = 0; j < moduleDetails.size(); j++){
                if(modules.get(i).moduleCode.equals(moduleDetails.get(j)[0]) && modules.get(i).isTaken){
                    numberOfMcs = numberOfMcs + Integer.parseInt(moduleDetails.get(j)[2]);
                }
            }
        }
        return numberOfMcs;
    }

    public void printStatus() {
        List<String> coreMods = fileManager.retrieveCoreMods();
        List<String[]> moduleDetails = fileManager.getAllModuleDetails();
        List<String> takenCoreMods = new ArrayList<>();
        List<String> untakenCoreMods = new ArrayList<>();
        for (int i = 0; i < coreMods.size(); i++) {
            boolean isCoreModTaken = false;
            for (int j = 0; j < modules.size(); j++) {
                if (coreMods.get(i).equals(modules.get(j).moduleCode) && modules.get(j).isTaken) {
                    takenCoreMods.add(coreMods.get(i));
                    isCoreModTaken = true;
                    break;
                }
            }
            if (!isCoreModTaken) {
                untakenCoreMods.add(coreMods.get(i));
            }
        }
        System.out.println("-Taken-");
        for (int k = 0; k < takenCoreMods.size(); k++) {
            System.out.print(takenCoreMods.get(k));
            for (int j = 0; j < moduleDetails.size(); j++){
                if (moduleDetails.get(j)[0].equals(takenCoreMods.get(k))){
                    System.out.print(" " + moduleDetails.get(j)[1] + " " + "MCs:" + moduleDetails.get(j)[2]);
                }
            }
            System.out.println();
        }
        System.out.println("-Not Taken-");
        for (int k = 0; k < untakenCoreMods.size(); k++) {
            System.out.print(untakenCoreMods.get(k));
            for (int j = 0; j < moduleDetails.size(); j++){
                if (moduleDetails.get(j)[0].equals(untakenCoreMods.get(k))){
                    System.out.print(" " + moduleDetails.get(j)[1] + " " + "MCs:" + moduleDetails.get(j)[2]);
                }
            }
            System.out.println();
        }
        System.out.println(numberOfMcsTaken() + "/160");
    }

    /*public void printStatus() throws InvalidGradeException {
        String[] messagePacket = new String[this.modules.size() + 1];
        messagePacket[0] = "\tListing all modules:";
        int messageCount = 1;
        for (int i = 0; i < this.modules.size(); i++) {
            String line = "\t" + (i + 1) + ". " + this.modules.get(i).getGradePoint();
            messagePacket[messageCount++] = line;
        }
        Ui.printMessage(messagePacket);

    } */
}
