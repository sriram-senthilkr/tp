package seedu.penus;

import org.junit.jupiter.api.Test;
import seedu.penus.ui.Ui;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {
    public static final String WINDOWS_NEWLINE = "\r\n";
    public static final String IOS_NEWLINE = "\n";

    @Test
    void testWelcome() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui.printWelcome();
        String expectedOutput;
        String os = System.getProperty("os.name");

        if (os.contains("Windows")) {
            expectedOutput = 
                    "\t____________________________________" + WINDOWS_NEWLINE + WINDOWS_NEWLINE + 
                    "\t___   ____  _      _     __  " + WINDOWS_NEWLINE +
                    "\t| |_) | |_  | |\\ | | | | ( (`" + WINDOWS_NEWLINE +
                    "\t|_|   |_|__ |_| \\| \\_\\_/ _)_)" + WINDOWS_NEWLINE +
                    "\tWelcome to PENUS!" + WINDOWS_NEWLINE + 
                    "\tWhat can I do for you?" + WINDOWS_NEWLINE + 
                    "\t____________________________________" + WINDOWS_NEWLINE;
        } else {
            expectedOutput = 
                    "\t____________________________________" + IOS_NEWLINE + IOS_NEWLINE +
                    "\t___   ____  _      _     __  " + IOS_NEWLINE +
                    "\t| |_) | |_  | |\\ | | | | ( (`" + IOS_NEWLINE +
                    "\t|_|   |_|__ |_| \\| \\_\\_/ _)_)" + IOS_NEWLINE +
                    "\tWelcome to PENUS!" + IOS_NEWLINE + 
                    "\tWhat can I do for you?" + IOS_NEWLINE + 
                    "\t____________________________________" + IOS_NEWLINE;
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void testExit() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        Ui.printExit();
        String expectedOutput;
        String os = System.getProperty("os.name");

        if (os.contains("Windows")) {
            expectedOutput = 
                    "\t____________________________________" + WINDOWS_NEWLINE +
                    "\t\tBye see you again!" + WINDOWS_NEWLINE + 
                    "\t____________________________________" + WINDOWS_NEWLINE;
        } else {
            expectedOutput = 
                    "\t____________________________________" + IOS_NEWLINE +
                    "\t\tBye see you again!" + IOS_NEWLINE + 
                    "\t____________________________________" + IOS_NEWLINE;
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }


    @Test
    void testMessage() {
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));

        String expectedOutput;
        String[] messageInput = {"Hi", "Added correctly", "\tCS2113"};
        Ui.printMessage(messageInput);
        String os = System.getProperty("os.name");

        if (os.contains("Windows")) {
            expectedOutput = 
                    "\t____________________________________" + WINDOWS_NEWLINE +
                    "Hi" + WINDOWS_NEWLINE + 
                    "Added correctly" + WINDOWS_NEWLINE +
                    "\tCS2113" + WINDOWS_NEWLINE +
                    "\t____________________________________" + WINDOWS_NEWLINE;
        } else {
            expectedOutput = 
                    "\t____________________________________" + IOS_NEWLINE +
                    "Hi" + IOS_NEWLINE  + 
                    "Added correctly" + IOS_NEWLINE  +
                    "\tCS2113" + IOS_NEWLINE  +
                    "\t____________________________________" + IOS_NEWLINE;
        }
        assertEquals(expectedOutput, actualOutput.toString());
    }
}
