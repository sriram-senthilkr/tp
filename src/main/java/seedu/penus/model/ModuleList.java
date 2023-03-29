package seedu.penus.model;

import java.util.ArrayList;
import java.util.List;

public class ModuleList {
    public final List<Module> modules;
    //private final ResourceStorage resource;
    //private final List<String[]> moduleDetails;


    //private User user = new User();

    /**
     * Overloaded constructor for the creation of a ModuleList object.
     */
    public ModuleList() {
        this.modules = new ArrayList<>();
        //this.resource = new ResourceStorage();
        //this.moduleDetails = resource.getAllModuleDetails();
        //this.user = new User();
    }

    /**
     * Constructor for the creation of a ModuleList object.
     *
     * @param mods List of mods to be included in ModuleLIst after creation.
     */
    public ModuleList(List<Module> mods) {
        this.modules = mods;
        //this.resource = new ResourceStorage();
        //this.moduleDetails = resource.getAllModuleDetails();
        //this.user = user;
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

    public boolean hasModule(Module module) {
        for (Module m : modules) {
            if (m.getCode().equals(module.getCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a given module to the ModuleList object.
     *
     * @param module The module to be added.
     */
    public void addModule(Module module) {
        this.modules.add(module);
    }

    /**
     * Deletes the module identified by its code from the ModuleList
     *
     * @param index The index corresponding to the module to be deleted.
     */
    public void removeModule(int index) {
        this.modules.remove(index);
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
     * Returns the module code of GESS module if exist and taken, "" if does not exist
     *
     * @return the module code of GESS module if exist and taken, "" if does not exist
     */
    public String getGESS() {
        for (Module module : modules) {
            if (module.moduleCode.substring(0, 4).equals("GESS") && module.isTaken) {
                return module.moduleCode;
            }
        }
        return "";
    }

    /**
     * Returns the module code of GEC module if exist and taken, "" if does not exist
     *
     * @return the module code of GEC module if exist and taken, "" if does not exist
     */
    public String getGEC() {
        for (Module module : modules) {
            if (module.moduleCode.substring(0, 3).equals("GEC") && module.isTaken) {
                return module.moduleCode;
            }
        }
        return "";
    }

    /**
     * Returns the module code of GEN module if exist and taken, "" if does not exist
     *
     * @return the module code of GEN module if exist and taken, "" if does not exist
     */
    public String getGEN() {
        for (Module module : modules) {
            if (module.moduleCode.substring(0, 3).equals("GEN") && module.isTaken) {
                return module.moduleCode;
            }
        }
        return "";
    }

    // /**
    //  * Lists out the modules in the user specified range.
    //  * 
    //  * @param userSemester
    //  * @param userYear
    //  * @throws InvalidGradeException
    //  */
    // public void printModules(int userYear, int userSemester) throws InvalidGradeException {
    //     Map<Integer, Map<Integer, List<String[]>>> modulesByYearAndSemester = new HashMap<>();
    //     for (Module m : modules) {

    //         int year = m.getYear();
    //         int sem = m.getSem();
    //         String[] moduleArray = new String[] { m.getCode(), m.getGrade() };

    //             modulesByYearAndSemester.computeIfAbsent(year, k -> new HashMap<>())
    //                     .computeIfAbsent(sem, k -> new ArrayList<>())
    //                     .add(moduleArray);

    //     }

    //     Ui.printDivider();
    //     if (userYear == -1 && userSemester == -1) { // List all modules
    //         for (int year = 1; year < 5; year++) {
    //             for (int semester = 1; semester <= 2; semester++) {
    //                 System.out.println("- Year " + year + " Semester " + semester + " -");

    //                 List<String[]> modules = modulesByYearAndSemester.getOrDefault(year, new HashMap<>())
    //                         .getOrDefault(semester, new ArrayList<>());

    //                 if (modules.isEmpty()) {
    //                     System.out.println("\tNo modules taken/added.");
    //                 } else {
    //                     for (String[] s : modules) {
    //                         System.out.println(s[0] + " " + s[1]);
    //                     }
    //                 }
    //             }
    //         }
    //     } else if (userYear != -1 && userSemester == -1) { // Year specified but not semester
    //         for (int semester = 1; semester <= 2; semester++) {
    //             System.out.println("- Year " + userYear + " Semester " + semester + " -");
           
    //             List<String[]> modules = modulesByYearAndSemester.getOrDefault(userYear, new HashMap<>())
    //                 .getOrDefault(semester, new ArrayList<>());

    //             if (modules.isEmpty()) {
    //                 System.out.println("\tNo modules taken/added.");
    //             } else {
    //                 for (String[] s : modules) {
    //                     System.out.println(s[0] + " " + s[1]);
    //                 }
    //             }
    //             CAP.printSemCAP(modules);
    //         }
    //     } else if (userYear != -1 && userSemester != -1) { // both Sem and Year specified
    //         System.out.println("- Year " + userYear + " Semester " + userSemester + " -");

    //         List<String[]> modules = modulesByYearAndSemester.getOrDefault(userYear, new HashMap<>())
    //                 .getOrDefault(userSemester, new ArrayList<>());

    //         if (modules.isEmpty()) {
    //             System.out.println("\tNo modules taken/added.");
    //         } else {
    //             for (String[] s : modules) {
    //                 System.out.println(s[0] + " " + s[1]);
    //             }
    //         }
    //     }
    //     CAP.printOverallCAP(modules);
    // }
    

}
