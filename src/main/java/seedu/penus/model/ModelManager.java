package seedu.penus.model;

import static java.util.Objects.requireNonNull;
import java.util.HashMap;
import java.util.List;

public class ModelManager {
    private final ModuleList moduleList;
    private final User user;
    private final List<String[]> coreDetails;
    private final HashMap<String, List<String>> coreModList;


    public ModelManager(User user, List<Module> list, List<String[]> coreDetails, HashMap<String, List<String>> coreModList) {
        this.user = user;
        this.moduleList = new ModuleList(list);
        this.coreDetails = coreDetails;
        this.coreModList = coreModList;
    }

//=============================== Module List ===================================
    public ModuleList getModuleList() {
        return this.moduleList;
    }

    public boolean hasModule(Module module) {
        requireNonNull(module);
        return moduleList.hasModule(module);
    }

    public void addModule(Module module) {
        requireNonNull(module);
        moduleList.addModule(module);
    }

    public void removeModule(int index) {
        moduleList.removeModule(index);
    }

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
