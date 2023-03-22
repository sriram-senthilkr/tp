package seedu.penus;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import seedu.penus.ui.Ui;

public class PenusTest {

    public static final String LOGO = "\t___   ____  _      _     __  \n"
            + "\t| |_) | |_  | |\\ | | | | ( (`\n"
            + "\t|_|   |_|__ |_| \\| \\_\\_/ _)_)\n";

    public static final String Divider = "\t____________________________________\n";
    public static final String Welcome = "\tWelcome to PENUS!\n" + "\tWhat can I do for you?\n";
    public static final String Bye = "\tBye see you again!\n";

    @Test
    public void testTitleCommand() {
        // Arrange
        String input = "add CS1231\nexit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        Penus.main(new String[0]);

        // Assert
        String expectedOutput = Divider
                + LOGO
                + Welcome
                + Divider
                + "\tDiscrete Structures\n"
                + Divider
                + Bye
                + Divider;

        assertEquals(expectedOutput, outContent.toString());
    }

//    @Test
//    public void testDeleteCommand() {
//        // Arrange
//        String input = "delete CS1231\nexit\n";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//
//        // Act
//        Penus.main(new String[0]);
//
//        // Assert
//        // Add your assertions here to verify the output
//    }

}


