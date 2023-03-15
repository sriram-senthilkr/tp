package seedu.penus.storage;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class FileManager {
    public File coreModFile;
    public File modDetailsFile;
    public String coreModFilePath;
    public String modDetailsFilePath;
    public String dataDirectory;


    public FileManager() {
        this.dataDirectory = "./data/";
        this.coreModFilePath = this.dataDirectory + "CoreMods.txt";
        this.coreModFile = new File(this.coreModFilePath);
        this.modDetailsFilePath = this.dataDirectory + "module-details.txt";
        this.modDetailsFile = new File(this.modDetailsFilePath);
        File directory = new File(this.dataDirectory);
        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!this.modDetailsFile.exists()) {
                this.modDetailsFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public List<String> retrieveCoreMods() {
        Scanner scanner = null;
        List<String> coreModules = new ArrayList<>();
        try {
            scanner = new Scanner(this.coreModFile);
            while (scanner.hasNextLine()) {
                String coreModuleCode = scanner.nextLine();
                coreModules.add(coreModuleCode);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Core Mod File not found");
        } finally {
            scanner.close();
        }
        return coreModules;
    }

    /**
     * Retrieves all module details in /data/module-details.txt
     * <p>
     * Parses the content of module-details.txt into a List of decoded modules.
     * @return the List containing all the decoded modules.
     */
    public List<String[]> getAllModuleDetails() {
        Scanner scanner = null;
        List<String[]> moduleDetailsList = new ArrayList<>();
        try {
            scanner = new Scanner(this.modDetailsFile);
            while (scanner.hasNextLine()) {
                String encoded = scanner.nextLine();
                String[] decodedModule = decodeModule(encoded);
                moduleDetailsList.add(decodedModule);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } finally {
            scanner.close();
        }
        return moduleDetailsList;
    }

    /**
     * Decoder method to read a line of module-details.txt storage and splits the string
     * into a string array
     * Format: moduleCode ### moduleName ### numberOfMcs ### preRequisites ### coRequisites ###
     * preclusions ### semOfferedIn ### canSU
     * @param module the string corresponding to the lines of module-details.txt
     * @return decoded String array
     */
    public String[] decodeModule(String module) {
        String[] components = module.split(" ### ");
        return components;
    }

}


