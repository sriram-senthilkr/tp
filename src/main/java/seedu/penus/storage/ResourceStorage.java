package seedu.penus.storage;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.HashMap;

import java.util.Objects;


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
 * then in VSCode Explorer Tab -> Java Projects -> ... -> Clean Workspace
 * 
 * returns "jar:file:/C:/path/to/project/build/resources/main/.txt"
 * returns "file:/C:/path/to/project/src/main/resources/.txt"
 */

public class ResourceStorage {
    public String coreModFile;
    public String modDetailsFile;

    public ResourceStorage() {
        this.coreModFile = "core-modules.txt";
        this.modDetailsFile = "core-module-details.txt";
    }

    /**
     * Retrieves all module details in /resource/core-modules.txt
     * 
     * Parses the content of core-modules.txt into a Hashmap
     * Key: courseName String
     * Value: coreModulesList Array
     * 
     * @return HashMap
     */
    public HashMap<String, List<String>> getCoreMods() {
        HashMap <String, List<String>> coreModHashMap = new HashMap<>();
        String courseName = "";
        BufferedReader reader;
        List<String> coreModulesList = new ArrayList<>();
        try {
            InputStreamReader stream = new InputStreamReader(
                 getClass().getClassLoader().getResourceAsStream(coreModFile)
            );
            reader = new BufferedReader(stream);
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("##")) {
                    courseName = line.substring(2);
                } else if (line.equals("END")){
                    List<String> coreModulesListCopy = new ArrayList<>(coreModulesList);
                    coreModHashMap.put(courseName, coreModulesListCopy);
                    coreModulesList.clear();
                } else {
                    coreModulesList.add(line);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return coreModHashMap;
    }

    /**
     * Retrieves all module details in /resource/module-details.txt
     * 
     * Parses the content of module-details.txt into a List of decoded modules.
     * @return the List containing all the decoded modules.
     */
    public List<String[]> getAllModuleDetails() {
        BufferedReader reader;
        List<String[]> moduleDetailsList = new ArrayList<>();
        try {
            InputStreamReader stream = new InputStreamReader(
                    Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(modDetailsFile))
            );
            reader = new BufferedReader(stream);
            String line;
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
     * 
     * Format: 
     * moduleCode ### moduleName ### numberOfMcs ### preRequisites ### coRequisites ###
     * preclusions ### semOfferedIn ### canSU
     * @param module the string corresponding to the lines of module-details.txt
     * @return decoded String array
     */
    public String[] decodeModule(String module) {
        return module.split(" ### ");
    }
}
