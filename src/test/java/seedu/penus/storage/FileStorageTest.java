package seedu.penus.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.penus.common.exceptions.DuplicateModuleException;
import seedu.penus.common.exceptions.InvalidFormatException;
import seedu.penus.common.exceptions.InvalidGradeException;
import seedu.penus.common.exceptions.InvalidModuleException;
import seedu.penus.common.exceptions.InvalidSemesterException;
import seedu.penus.common.exceptions.InvalidYearException;
import seedu.penus.common.exceptions.PenusException;
import seedu.penus.model.Module;
import seedu.penus.model.ModuleList;
import seedu.penus.model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileStorageTest {
    static final int NUMBER_OF_MODULES = 2;
    static final String DATA_DIRECTORY = "./data/";
    static final String FILE_PATH = "./data/penus.txt";

    FileStorage fileStorage;

    @BeforeEach
    public void setUp() {
        fileStorage = new FileStorage();
    }


    @Test
    public void testConstructor() {
        assertEquals(FILE_PATH, fileStorage.filePath);
        assertEquals(DATA_DIRECTORY, fileStorage.dataDirectory);
        assertEquals(new File("./data/penus.txt"), fileStorage.file);
    }

    @Test
    void testSave_inputModuleListAndUser_writeSuccess() throws IOException {
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
    void testRetrieveMods_returnsModuleListSuccess() throws IOException, PenusException {
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
        assertEquals(NUMBER_OF_MODULES, moduleList.size());
    }

    @Test
    void testRetrieveUser_returnsUserSuccess() throws IOException, PenusException {
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

    @Test
    void testRetrieveUser_returnsUserSuccessEmptyLine() throws IOException, PenusException {
        FileWriter writer = new FileWriter("./data/penus.txt");
        writer.write("User ### John ### Electrical Engineering\n" +
                "Taken ### CS1010 ### 1 ### 1 ### A+\n" +
                " \n" +
                "Plan ### CS2040C ### 2 ### 1\n");
        writer.close();
        User user = fileStorage.retrieveUser();
        FileWriter toDelete = new FileWriter("./data/penus.txt");
        toDelete.write("");
        toDelete.close();
        assertEquals("John", user.getName());
        assertEquals("Electrical Engineering", user.getCourse());
    }

    @Test
    void testInvalidUser_throwsException() throws PenusException, IOException {
        FileWriter writer = new FileWriter("./data/penus.txt");
        writer.write("User ### John1 ### Electrical Engineering\n");
        writer.close();
        assertThrows(InvalidFormatException.class, () -> fileStorage.retrieveUser());
    }

    @Test
    void testInvalidCourse_throwsException() throws PenusException, IOException {
        FileWriter writer = new FileWriter("./data/penus.txt");
        writer.write("User ### John ### Electrical Engineering sadasd\n");
        writer.close();
        assertThrows(InvalidFormatException.class, () -> fileStorage.retrieveUser());
    }

    @Test
    void testInvalidModule_throwsException() throws PenusException, IOException {
        FileWriter writer = new FileWriter("./data/penus.txt");
        writer.write("Taken ### CS10101 ### 1 ### 1 ### A+\n");
        writer.close();
        assertThrows(InvalidModuleException.class, () -> fileStorage.retrieveMods());
    }

    @Test
    void testInvalidGrade_throwsException() throws PenusException, IOException {
        FileWriter writer = new FileWriter("./data/penus.txt");
        writer.write("Taken ### CS1010 ### 1 ### 1 ### 12\n");
        writer.close();
        assertThrows(InvalidGradeException.class, () -> fileStorage.retrieveMods());
    }

    @Test
    void testInvalidYear_throwsException() throws PenusException, IOException {
        FileWriter writer = new FileWriter("./data/penus.txt");
        writer.write("Taken ### CS1010 ### 9999 ### 1 ### A+\n");
        writer.close();
        assertThrows(InvalidYearException.class, () -> fileStorage.retrieveMods());
    }

    @Test
    void testInvalidSemester_throwsException() throws PenusException, IOException {
        FileWriter writer = new FileWriter("./data/penus.txt");
        writer.write("Taken ### CS1010 ### 1 ### 1090 ### A+\n");
        writer.close();
        assertThrows(InvalidSemesterException.class, () -> fileStorage.retrieveMods());
    }

    @Test
    void testInvalidInteger_throwsException() throws PenusException, IOException {
        FileWriter writer = new FileWriter("./data/penus.txt");
        writer.write("Taken ### CS1010 ### 1 ### asdf ### A+\n");
        writer.close();
        assertThrows(InvalidFormatException.class, () -> fileStorage.retrieveMods());
    }

    @Test
    void testDuplicateMod_throwsException() throws PenusException, IOException {
        FileWriter writer = new FileWriter("./data/penus.txt");
        writer.write("Taken ### CS1010 ### 1 ### 1 ### A+\n" +
                "Plan ### CS1010 ### 2 ### 1\n");
        writer.close();
        assertThrows(DuplicateModuleException.class, () -> fileStorage.retrieveMods());
    }
}
