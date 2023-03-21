package seedu.penus.ui;

public class Ui {
    public static final String LOGO = "\n"
            + "\t ___  _____        ______    ___  ___     ___   _________\n"
            + "\t/   \\/      \\     /      \\  /   \\/   \\   /   \\ /         \\ \n"
            + "\t|      __    \\    |       \\/    ||   |  |     ||    _     |\n"
            + "\t|     |__|    |   |        |    ||   |  |     ||     \\    |\n"
            + "\t\\            /    |    |   |    ||   |  |     |\\      \\_ /\n"
            + "\t/        ___/___  |    |   |    ||   |  |     | \\_      \\\n"
            + "\t|       | /     \\ |    |   |    ||   |  |     |/  \\      \\\n"
            + "\t|_______|/   <> _\\|    |   |    ||   \\__/     ||   \\_     |\n"
            + "\t\\       /|   \\____|    / \\      |\\            /|          |\n"
            + "\t \\__|__/  \\______/\\___/  \\_____/  \\__________/  \\_________/\n";

    // public static final String LOGO = "\n"
    // + "\t___ ____ _ _ __ \n"
    // + "\t| |_) | |_ | |\\ | | | | ( (`\n"
    // + "\t|_| |_|__ |_| \\| \\_\\_/ _)_)\n";

    public static void printDivider() {
        System.out.println("\t____________________________________");
    }

    public static void printWelcome() {
        printDivider();
        System.out.println(LOGO);
        System.out.println("\tWelcome to PENUS!\n"
                + "\tEnter help for a list of commands or init to start");
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

    public static void printHelp() {
        Ui.printDivider();
        System.out.println("\texit" + "\t\t\t\t\t\t\t\tExits the program");
        System.out.println("\tlist mods [FILTER]" + "\t\t\t\t\t\tDisplays a list of all modules taken or planned."
                + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tIf [FILTER] is not specified, then all modules will shown.");
        System.out.println("\tmark [MODULE CODE] g/[GRADE]"
                + "\t\t\t\t\tMarks the module that has been cleared, while updating its grades");
        System.out.println(
                "\tplan [MODULE CODE] y/[YEAR] s/[SEMESTER]"
                        + "\t\t\tAdds a module to the planner as an untaken module");
        System.out.println("\tremove [MODULECODE]" + "\t\t\t\t\t\tRemoves a module from the planner");
        System.out.println("\tstatus" + "\t\t\t\t\t\t\t\tDisplays the status of Core Modules and MCs taken");
        System.out.println("\ttaken [MODULE CODE] y/[YEAR] s/[SEMESTER] g/[GRADE]"
                + "\t\tAdds a module to the planner as a module you have already taken");
        Ui.printDivider();
    }
}
