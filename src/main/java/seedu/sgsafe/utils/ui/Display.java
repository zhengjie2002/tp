package seedu.sgsafe.utils.ui;

/**
 * Handles all user-facing output for the SGSafe application.
 * Provides formatted messages and feedback for command execution.
 */
public class Display {

    /** Visual divider used to wrap printed messages for clarity. */
    private static final String DIVIDER = "\t____________________________________________________________";

    /** Welcome message shown when the application starts. */
    private static final String WELCOME_MESSAGE = "Welcome to SGSafe";

    /** Goodbye message shown when the application exits. */
    private static final String GOODBYE_MESSAGE_LINE_1 = "Thanks for using SGSafe :)";
    private static final String GOODBYE_MESSAGE_LINE_2 = "We hope to see you again!";

    /**
     * Prints the welcome message to the console.
     * This is typically shown when the application starts.
     */
    public static void printWelcomeMessage() {
        printMessage(WELCOME_MESSAGE);
    }

    /**
     * Prints the goodbye message to the console.
     * This is typically shown when the application exits.
     */
    public static void printGoodbyeMessage() {
        printMessage(GOODBYE_MESSAGE_LINE_1, GOODBYE_MESSAGE_LINE_2);
    }

    /**
     * Displays one or more lines of text wrapped in a visual divider.
     * Each line is indented for readability.
     *
     * @param message variable number of message lines to display
     */
    public static void printMessage(String... message) {
        System.out.println(DIVIDER);
        for (String line : message) {
            System.out.println("\t" + line);
        }
        System.out.println(DIVIDER);
    }
}