package seedu.penus.storage;

import org.junit.jupiter.api.Test;
import seedu.penus.model.Module;
import seedu.penus.model.ModuleList;
import seedu.penus.model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


import static org.junit.jupiter.api.Assertions.assertEquals;

class FileStorageTest {

    @Test
    public void testConstructor() {
        FileStorage fileStorage = new FileStorage();
                assertEquals("./data/penus.txt", fileStorage.filePath);
                assertEquals("./data/", fileStorage.dataDirectory);
                assertEquals(new File("./data/penus.txt"), fileStorage.file);
    }

    @Test
    void testSave_inputModuleListAndUser_writeSuccess() throws IOException {
        FileStorage fileStorage = new FileStorage();
        ModuleList moduleList = new ModuleList();
        moduleList.addModule(new Module("CS1010", 1, 1, "A+"));
        User user = new User("John", "Electrical Engineering");
        fileStorage.save(moduleList, user);
        Scanner scanner = new Scanner(fileStorage.file);
        assertEquals("User ### John ### Electrical Engineering", scanner.nextLine());
        assertEquals("Taken ### CS1010 ### 1 ### 1 ### A+", scanner.nextLine());
        FileWriter writer = new FileWriter(fileStorage.file);
        writer.write("");
        writer.close();
    }

    @Test
    void testRetrieveMods_returnsModuleListSuccess() throws IOException {
        FileStorage fileStorage = new FileStorage();
        FileWriter writer = new FileWriter("./data/penus.txt");
        writer.write("User ### John ### Electrical Engineering\n" +
                "Taken ### CS1010 ### 1 ### 1 ### A+\n" +
                "Plan ### CS2040C ### 2 ### 1\n");
        writer.close();
        List<Module> moduleList = fileStorage.retrieveMods();
        FileWriter toDelete = new FileWriter("./data/penus.txt");
        toDelete.write("");
        toDelete.close();
        assertEquals("CS1010", moduleList.get(0).getCode());
        assertEquals("CS2040C", moduleList.get(1).getCode());
        assertEquals(2, moduleList.size());
    }

    @Test
    void testRetrieveUser_returnsUserSuccess() throws IOException {
        FileStorage fileStorage = new FileStorage();
        FileWriter writer = new FileWriter("./data/penus.txt");
        writer.write("User ### John ### Electrical Engineering\n" +
                "Taken ### CS1010 ### 1 ### 1 ### A+\n" +
                "Plan ### CS2040C ### 2 ### 1\n");
        writer.close();
        User user = fileStorage.retrieveUser();
        FileWriter toDelete = new FileWriter("./data/penus.txt");
        toDelete.write("");
        toDelete.close();
        assertEquals("John", user.getName());
        assertEquals("Electrical Engineering", user.getCourse());
    }
}
