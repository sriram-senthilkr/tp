package seedu.penus.testutils;

import seedu.penus.model.ModelManager;
import seedu.penus.model.Module;
import seedu.penus.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SampleData {

    public static Module sampleModule1 = new Module("CS2040", 1, 1, "A");

    public static Module sampleModule2 = new Module("CS1231", 1, 1, "A");

    public static Module sampleModule3 = new Module("GESS1004", 1, 1, "A");

    public static Module sampleModule4 = new Module("GEC1015", 1, 1);

    public static User user = new User("bentohset", "Computer Engineering");

    public static HashMap<String, List<String>> sampleCoreModList = new HashMap<String, List<String>>();
    public static ModelManager getSampleModel() {
        List<String > dummyCoreMods = new ArrayList<>();
        dummyCoreMods.add("CS2040");
        dummyCoreMods.add("CS1231");
        sampleCoreModList.put("Computer Engineering", dummyCoreMods);
        ModelManager model = new ModelManager(user, new ArrayList<>(), new ArrayList<>(), sampleCoreModList);
        model.addModule(sampleModule1);
        model.addModule(sampleModule2);
        model.addModule(sampleModule3);
        model.addModule(sampleModule4);
        return model;
    }
}
