package seedu.penus.ui;

import static seedu.penus.common.Messages.MESSAGE_GOODBYE;
import static seedu.penus.common.Messages.MESSAGE_WELCOME;
import static seedu.penus.common.Messages.LOGO;
import static java.util.Objects.requireNonNull;

import seedu.penus.logic.commands.CommandResult;

import java.util.Scanner;
import java.io.PrintStream;
import java.io.InputStream;

public class Ui {
    private static final String DIVIDER = "\t___________________________________________________________";

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

        return in.nextLine();
    }

    public void printMessage(String... messages) {
        out.println();
        out.println(DIVIDER);
        for (String m : messages) {
            out.println("\t" + m);
        }
        out.println(DIVIDER);
    }

    public void printResult(CommandResult result) {
        requireNonNull(result);
        printMessage(result.feedbackToUser);
    }

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
