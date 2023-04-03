package seedu.penus.storage;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import seedu.penus.common.exceptions.DuplicateModuleException;
import seedu.penus.common.exceptions.InvalidFormatException;
import seedu.penus.common.exceptions.InvalidGradeException;
import seedu.penus.common.exceptions.InvalidModuleException;
import seedu.penus.common.exceptions.InvalidSemesterException;
import seedu.penus.common.exceptions.InvalidYearException;
import seedu.penus.common.exceptions.PenusException;
import seedu.penus.logic.utils.Grade;
import seedu.penus.logic.utils.ModuleRetriever;
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
                if (encoded.length() == 0) continue;
                if (encoded.contains("User")) {
                    continue;
                }
                Module decodedModule = this.decodemodule(encoded);
                if (hasModule(decodedModule, moduleList)) {
                    throw new DuplicateModuleException();
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
                    user = decodeUser(userLine);
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
    private User decodeUser(String userLine) throws PenusException {
        User user = new User();
        String[] components = userLine.split(" ### ");
        if (components[1].length() == 0 || components[2].length() == 0) {
            throw new InvalidFormatException("Try again, name and course cannot be empty");
        }
        String name = components[1].trim();
        if (!name.matches("[a-zA-Z ]+")){
            throw new InvalidFormatException("Name must only include letters and spaces.");
        }
        
        String course = components[2].trim();
        List<String> validCourse = Arrays.asList(
            "Biomedical Engineering", "Chemical Engineering", "Civil Engineering", 
            "Computer Engineering", "Electrical Engineering", "Environmental Engineering", 
            "Industrial and Systems Engineering", "Mechanical Engineering"
        );
        if (!validCourse.contains(course)) {
            throw new InvalidFormatException("Course is not valid!");
        }
        user.setName(name);
        user.setCourse(course);


        return user;
    }

    /**
     * Decoder method to read a lines in storage and splits the string
     * into a string array
     * Format: Taken/Plan ### moduleCode ### year ### semester (### grade for taken)
     * @param module String
     * @return decoded Module object
     */
    private Module decodemodule(String module) throws PenusException {
        String[] components = module.split(" ### ");
        String status = components[0].trim();
        String moduleCode = components[1].trim();
        if (!ModuleRetriever.isValidMod(moduleCode)) {
            throw new InvalidModuleException();
        }
        int year;
        int semester;
        try {
            year = Integer.parseInt(components[2].trim());
            semester = Integer.parseInt(components[3].trim());
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Year and semester must be integers.");
        }
        if (year < 1 || year > 4) {
            throw new InvalidYearException("Year must be 1 to 4. Please try again.");
        }
        if (semester != 1 && semester != 2) {
            throw new InvalidSemesterException("Semester must be 1 or 2!");
        }
        Module decoded = null;

        switch (status) {
        case "Taken":
            String grade = components[4].trim().toUpperCase();
            if (!Grade.isValid(grade)) {
                throw new InvalidGradeException();
            }
            decoded = new Module(moduleCode, year, semester, grade);
            break;

        case "Plan":
            decoded = new Module(moduleCode, year, semester);
            break;

        default:
            break;
        }
        return decoded;
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


