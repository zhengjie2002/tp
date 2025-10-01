package seedu.sgsafe.utils.ui;

/**
 * Handles all user-facing output for the Espresso application.
 * Provides formatted messages and feedback for command execution.
 */
public class Display {

    private static final String DIVIDER = "\t____________________________________________________________";

    public static void printWelcomeMessage() {
        printMessage("Welcome to SGSafe");
    }

    public static void printGoodbyeMessage() {
        printMessage("Thanks for using SGSafe :)",
                "We hope to see you again!");
    }

    /**
     * Displays one or more lines of text wrapped in a visual divider.
     *
     * @param message variable number of message lines to display
     */
    public static void printMessage(String... message) {
        System.out.println(DIVIDER);
        for (String s : message) {
            System.out.println("\t" + s);
        }
        System.out.println(DIVIDER);
    }
}
