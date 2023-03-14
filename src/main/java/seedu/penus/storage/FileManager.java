package seedu.penus.storage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class FileManager {
    private File coreModFile;
    private String coreModFilePath;
    private String dataDirectory;


    public FileManager() {
        this.dataDirectory = "./data/";
        this.coreModFilePath = this.dataDirectory + "CoreMods.txt";
        this.coreModFile = new File(this.coreModFilePath);
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
}


