package seedu.penus.ui;

import static seedu.penus.common.Messages.MESSAGE_GOODBYE;
import static seedu.penus.common.Messages.MESSAGE_WELCOME;
import static seedu.penus.common.Messages.LOGO;

import seedu.penus.logic.commands.CommandResult;

import java.util.Scanner;
import java.io.PrintStream;
import java.io.InputStream;

public class Ui {
    private static final String DIVIDER = "\t____________________________________";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserCommand() {
        out.print("Enter command: ");
        String fullInputLine = in.nextLine();

        return fullInputLine;
    }

    public void printMessage(String... messages) {
        out.println(DIVIDER);
        for (String m : messages) {
            out.println("\t" + m);
        }
        out.println(DIVIDER);
    }

    public void printResult(CommandResult result) {
        printMessage(result.feedbackToUser);
    }

    //TODO: String parsing for complicated results

    public void printWelcome() {
        printMessage(
            LOGO,
            MESSAGE_WELCOME
        );
    }

    public void printExit() {
        printMessage(
            MESSAGE_GOODBYE
        );
    }
}
