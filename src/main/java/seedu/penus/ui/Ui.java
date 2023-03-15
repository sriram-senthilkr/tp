package seedu.penus.ui;

public class Ui {
    public static final String LOGO = "\n"
    + "\t ___  _____        ______    ___  ___     ___   _________\n"
    + "\t/   \\/      \\     /      \\  /   \\/   \\   /   \\ /         \\ \n"
    + "\t|      /\\    \\    |       \\/    ||   |  |     ||    _     |\n"
    + "\t|     |  |    |   |        |    ||   |  |     ||     \\    |\n"
    + "\t\\      \\/    /    |    |   |    ||   |  |     |\\      \\_ /\n"
    + "\t/        ___/___  |    |   |    ||   |  |     | \\_      \\\n"
    + "\t|       | /     \\ |    |   |    ||   |  |     |/  \\      \\\n"
    + "\t|_______|/   <> _\\|    |   |    ||   \\__/     ||   \\_     |\n"
    + "\t\\       /\\   \\____|    /\\       |\\            /|          |\n"
    + "\t \\__|__/  \\______/\\___/  \\_____/  \\__________/  \\_________/\n";


    public static void printDivider() {
        System.out.println("\t____________________________________");
    }

    public static void printWelcome() {
        printDivider();
        System.out.println(LOGO);
        System.out.println("\tWelcome to PENUS!\n"
                + "\tWhat can I do for you?");
        printDivider();
    }

    public static void printExit() {
        printDivider();
        System.out.println("\tBye see you again!");
        printDivider();
    }

    /**
     * Prints the message passed as an array onto the terminal line by line
     * {@code @input} messages string array
     */
    public static void printMessage(String[] messages) {
        printDivider();
        for (String message : messages) {
            System.out.println(message);
        }
        printDivider();
    }
}
