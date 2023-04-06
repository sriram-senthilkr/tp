package seedu.penus.ui;

import static seedu.penus.common.Messages.MESSAGE_GOODBYE;
import static seedu.penus.common.Messages.MESSAGE_WELCOME;
import static seedu.penus.common.Messages.LOGO;
import static java.util.Objects.requireNonNull;

import seedu.penus.logic.commands.CommandResult;

import java.util.List;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.InputStream;

public class Ui {
    private static Thread loadingThread;
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

    public void printStorageError(String... messages) {
        out.println();
        out.println(DIVIDER);
        for (String m : messages) {
            out.println("\tStorage " + m);
        }
        out.println("\tPlease check ./data/penus.txt again");
        out.println(DIVIDER);
    }

    public void printResultString(CommandResult result) {
        requireNonNull(result);
        printMessage(result.feedbackToUser);
    }

    public void printResultArray(CommandResult result) {
        List<String> feedbackArray = result.feedbackArray;
        out.println();
        out.println(DIVIDER);
        for (String s : feedbackArray) {
            out.println("\t" + s);
        }
        out.println(DIVIDER);

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

    public static void printStatus(List<String> statusList){
        for (String s : statusList){
            System.out.println(s);
            System.out.println();
        }
    }

    public static void showLoadingAnimation() {
        char[] animationChars = {'|', '/', '-', '\\' };
        loadingThread = new Thread(() -> {
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\tLoading " + animationChars[i % 4] + "\r");
                i++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.print("\n");
        });
        loadingThread.start();
    }

    public static void stopLoadingAnimation() {
        if (loadingThread != null && loadingThread.isAlive()) {
            loadingThread.interrupt();
        }
    }
}
