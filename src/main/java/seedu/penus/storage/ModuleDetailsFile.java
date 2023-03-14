package seedu.penus.storage;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class ModuleDetailsFile {
    private File file;
    private String filePath;
    private String dataDirectory;

    /**
     * Constructor for the File Manager object.
     * <p>
     * Creates a File object according to the relative path /data/module-details.txt to store the data
     * <p>
     * Initializes a /data/ folder and module-details.txt if it does not exist
     */
    public ModuleDetailsFile() {

        this.dataDirectory = "./data/";
        this.filePath = this.dataDirectory + "module-details.txt";
        this.file = new File(this.filePath);
        File directory = new File(this.dataDirectory);
        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
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
            scanner = new Scanner(this.file);
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
     * Format: moduleCode ### numberOfMcs ### preRequisites ### coRequisites ### preclusions ###
     * semOfferedIn ### canSU
     * @param module the string corresponding to the lines of module-details.txt
     * @return decoded String array
     */
    private String[] decodeModule(String module) {
        String[] components = module.split(" ### ");
        return components;
    }
}

