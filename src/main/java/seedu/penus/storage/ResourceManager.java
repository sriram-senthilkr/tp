package seedu.penus.storage;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * IMPORTANT NOTE: 
 * in IntelliJ: right click /resources folder -> Mark Directory as -> Resources root
 * in VSCode: check /.vscode/settings.json -> should include:
 *  "java.project.referencedLibraries": [
        {
            "scope": "java",
            "path": "src/main/resources"
        }
    ]
 * then in VScode Explorer Tab -> Java Projects -> ... -> Clean Workspace
 * 
 * returns "jar:file:/C:/path/to/project/build/resources/main/.txt"
 * returns "file:/C:/path/to/project/src/main/resources/.txt"
 */

public class ResourceManager {
    public String coreModFile;
    public String modDetailsFile;

    public ResourceManager() {
        this.coreModFile = "core-modules.txt";
        this.modDetailsFile = "core-module-details.txt";
    }

    public List<String> getCoreMods() {
        BufferedReader reader = null;
        List<String> coreModules = new ArrayList<>();
        try {
            InputStreamReader stream = new InputStreamReader(
                    getClass().getClassLoader().getResourceAsStream(coreModFile)
            );
            reader = new BufferedReader(stream);
            String line = null;
            while ((line = reader.readLine()) != null) {
                coreModules.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e);
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
        BufferedReader reader = null;
        List<String[]> moduleDetailsList = new ArrayList<>();
        try {
            InputStreamReader stream = new InputStreamReader(
                    getClass().getClassLoader().getResourceAsStream(modDetailsFile)
            );
            reader = new BufferedReader(stream);
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] decodedModule = decodeModule(line);
                moduleDetailsList.add(decodedModule);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        return moduleDetailsList;
    }

    /**
     * Decoder method to read a line of module-details.txt storage and splits the string
     * into a string array
     * <p>
     * Format: 
     * <p>
     * moduleCode ### moduleName ### numberOfMcs ### preRequisites ### coRequisites ###
     * preclusions ### semOfferedIn ### canSU
     * @param module the string corresponding to the lines of module-details.txt
     * @return decoded String array
     */
    public String[] decodeModule(String module) {
        String[] components = module.split(" ### ");
        return components;
    }
}
