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

    /**
     * Wraps text so that it starts on the next line after the label,
     * and each subsequent line begins with a tab for indentation.
     */
    public static String formatIndentedText(String label, String text, int width) {
        if (text == null || text.isEmpty()) {
            return label;
        }
        StringBuilder out = new StringBuilder(label + "\n");
        String indent = "\t\t\t";
        int max = width - indent.length();
        String[] words = text.split("\\s+");
        StringBuilder line = new StringBuilder(indent);

        for (String w : words) {
            if (line.length() + w.length() + 1 > max) {
                out.append(line).append("\n");
                line = new StringBuilder(indent).append(w);
            } else {
                if (line.length() > indent.length()) {
                    line.append(" ");
                }
                line.append(w);
            }
        }
        out.append(line);
        return out.toString();
    }
}
