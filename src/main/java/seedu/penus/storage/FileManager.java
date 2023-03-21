package seedu.penus.storage;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import seedu.penus.modules.Module;
import seedu.penus.modules.ModuleList;
import seedu.penus.user.User;

public class FileManager {
    public File file;
    public String dataDirectory;
    public String filePath;

    /**
     * Constructor for the File Manager object.
     * <p>
     * Creates a File object according to the relative path /data/duke.txt to store the data
     * <p>
     * Initializes a /data/ folder and duke.txt if it does not exist
     */
    public FileManager() {
        this.dataDirectory = "./data/";
        this.filePath = this.dataDirectory + "penus.txt";
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
     * Saves the current moduleList accumulated over the program's life and stores it in /data/duke.txt
     *
     * @param moduleList the moduleList containing all the user's modules
     */
    public void save(ModuleList moduleList) {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            User user = moduleList.getUser();
            if (!user.getName().equals("") && !user.getCourse().equals("")) {
                writer.write(user.encode() + "\n");
            }
            for (Module module : moduleList.getModuleList()) {
                writer.write(module.encode() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Retrieves any modules saved in /data/duke.txt if the directory exists.
     * <p>
     * Decodes the contents of duke.txt into a moduleList object. 
     * Called upon initialisation of Duke.java
     *
     * @return moduleList the moduleList containing all the user's modules saved in storage
     */
    public List<Module> retrieveMods() {
        Scanner scanner = null;
        List<Module> moduleList = new ArrayList<>();
        try {
            scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String encoded = scanner.nextLine();
                if (encoded.contains("User")) {
                    continue;
                }
                moduleList.add(this.decodemodule(encoded));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        
        return moduleList;
    }

    public User retrieveUser() {
        Scanner scanner = null;
        User user = new User();
        try {
            scanner = new Scanner(this.file);
            if (scanner.hasNextLine()) {
                String userLine = scanner.nextLine();
                if (userLine.contains("User")) {
                    String[] components = userLine.split(" ### ");
                    user.setName(components[1]);
                    user.setCourse(components[2]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return user;
    }

    /**
     * Decoder method to read a line of module-details.txt storage and splits the string
     * into a string array
     * Format: moduleCode ### moduleName ### numberOfMcs ### preRequisites ### coRequisites ###
     * preclusions ### semOfferedIn ### canSU
     * @param module the string corresponding to the lines of module-details.txt
     * @return decoded String array
     */
    private Module decodemodule(String module) {
        String[] components = module.split(" ### ");
        String status = components[0];
        String moduleCode = components[1];
        int year = Integer.parseInt(components[2]);
        int semester = Integer.parseInt(components[3]);

        Module decoded = null;

        switch (status) {
        case "Taken":
            String grade = components[4];
            decoded = new Module(moduleCode, year, semester, grade);
            System.out.println(moduleCode + year + semester + grade);
            break;

        case "Plan":
            decoded = new Module(moduleCode, year, semester);
            System.out.println(moduleCode + year + semester);
            break;

        default:
            break;
        }

        return decoded;
    }

}


