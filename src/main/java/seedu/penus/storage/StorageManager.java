package seedu.penus.storage;

import java.util.List;
import seedu.penus.model.Module;
import seedu.penus.model.User;
import seedu.penus.model.ModuleList;

public class StorageManager {
    FileStorage storage;
    ResourceStorage resource;

    public StorageManager() {
        this.storage = new FileStorage();
        this.resource = new ResourceStorage();
    }

    public List<Module> loadStorage() {
        return storage.retrieveMods();
    }

    public User loadUser() {
        return storage.retrieveUser();
    }

    public void saveStorage(ModuleList list, User user) {
        storage.save(list, user);
    }
}
