package seedu.penus.storage;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import seedu.penus.common.exceptions.DuplicateModuleException;
import seedu.penus.common.exceptions.PenusException;
import seedu.penus.model.Module;
import seedu.penus.model.ModuleList;
import seedu.penus.model.User;

public class FileStorage {
    public File file;
    public String dataDirectory;
    public String filePath;

    /**
     * Constructor for the File Manager object.
     * Creates a File object according to the relative path /data/penus.txt to store the data
     * 
     * Initializes a /data/ folder and penus.txt if it does not exist
     */
    public FileStorage() {
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
     * Saves the current moduleList accumulated over the program's life and stores it in /data/penus.txt
     *
     * @param moduleList the moduleList containing all the user's modules
     * @param user the user containing the user preferences
     */
    public void save(ModuleList moduleList, User user) {
        try {
            FileWriter writer = new FileWriter(this.filePath);
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
     * Retrieves any modules saved in /data/penus.txt if the directory exists.
     * Decodes the contents of penus.txt into a moduleList object. 
     *
     * @return moduleList the moduleList containing all the user's modules saved in storage
     */
    public List<Module> retrieveMods() throws PenusException {
        Scanner scanner = null;
        List<Module> moduleList = new ArrayList<>();
        try {
            scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String encoded = scanner.nextLine();
                if (encoded.length() == 0) {
                    continue;
                }
                if (encoded.contains("User")) {
                    continue;
                }
                Module decodedModule = StorageDecoder.decodemodule(encoded);
                if (hasModule(decodedModule, moduleList)) {
                    throw new DuplicateModuleException();
                }
                moduleList.add(decodedModule);
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

    /**
     * Retrieves any User saved in /data/penus.txt if the directory exists.
     * Decodes the contents of penus.txt into a User object. 
     *
     * @return user the User containing the user details saved in storage
     */
    public User retrieveUser() throws PenusException {
        Scanner scanner = null;
        User user = new User();
        try {
            scanner = new Scanner(this.file);
            if (scanner.hasNextLine()) {
                String userLine = scanner.nextLine();
                if (userLine.contains("User")) {
                    user = StorageDecoder.decodeUser(userLine);
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

    private boolean hasModule(Module module, List<Module> moduleList) {
        for (Module m : moduleList) {
            if (m.getCode().equals(module.getCode())) {
                return true;
            }
        }
        return false;
    }
}


