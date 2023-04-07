package seedu.penus.model;

import static java.util.Objects.requireNonNull;
import java.util.HashMap;
import java.util.List;

public class ModelManager {
    private ModuleList moduleList;
    private final User user;
    private final List<String[]> coreDetails;
    private final HashMap<String, List<String>> coreModList;


    public ModelManager(User user,
                        List<Module> list,
                        List<String[]> coreDetails,
                        HashMap<String, List<String>> coreModList) {
        this.user = user;
        this.moduleList = new ModuleList(list);
        this.coreDetails = coreDetails;
        this.coreModList = coreModList;
    }

    //=============================== Module List ===================================
    public ModuleList getModuleList() {
        return this.moduleList;
    }

    public List<Module> getModuleListObj() {
        return moduleList.getModuleList();
    }

    /**
     * Checks if the moduleList contains the specified Module
     * @param module module
     * @return boolean true if module exists
     */
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return moduleList.hasModule(module);
    }

    public boolean hasModuleCode(String moduleCode) {
        requireNonNull(moduleCode);
        return moduleList.hasModuleCode(moduleCode);
    }

    /**
     * Adds the specificed Module to the moduleList
     * @param module Module
     */
    public void addModule(Module module) {
        requireNonNull(module);
        moduleList.addModule(module);
    }

    /**
     * removes the specified module by its index from the moduleList
     * @param index int
     */
    public void removeModule(int index) {
        moduleList.removeModule(index);
    }

    /**
     * Marks the specified module by getting the Module from the moduleList by its index
     * @param index int
     * @param grade string
     */
    public void markModule(int index, String grade) {
        Module module = moduleList.getModule(index);
        module.markTaken(grade);
    }

    public int getSize() {
        return moduleList.size();
    }

    public Module getModule(int index) {
        return moduleList.getModule(index);
    }

    public Module getModuleByCode(String moduleCode) {
        return moduleList.getModuleByCode(moduleCode);
    }

    public String getGESS() {
        return moduleList.getGESS();
    }

    public String getGEC() {
        return moduleList.getGEC();
    }

    public String getGEN() {
        return moduleList.getGEN();
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = new ModuleList(moduleList);
    }

    //=============================== User ==========================================
    public User getUser() {
        return user;
    }

    public String getUserName() {
        return user.getName();
    }

    public String getUserCourse() {
        return user.getCourse();
    }

    public void setUserName(String name) {
        user.setName(name);
    }

    public void setUserCourse(String course) {
        user.setCourse(course);
    }

    //==============================Core mods =======================================
    public List<String[]> getCoreDetails() {
        return coreDetails;
    }

    public HashMap<String, List<String>> getCoreModList() {
        return coreModList;
    }
}
