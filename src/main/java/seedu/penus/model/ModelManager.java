package seedu.penus.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

public class ModelManager {
    private final ModuleList moduleList;
    private final User user;

    public ModelManager(User user, List<Module> list) {
        this.user = user;
        this.moduleList = new ModuleList(list);
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
        moduleList.addModule(module);
    }

    public void removeModule(int index) {
        moduleList.removeModule(index);
    }

    public void markModule(int index, String grade) {
        Module module = moduleList.getModule(index);
        module.markTaken(grade);


    }


//=============================== User ==========================================
    public User getUser() {
        return this.user;
    }
}
