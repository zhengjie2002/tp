package seedu.sgsafe;
import java.util.Scanner;

import seedu.sgsafe.utils.casefiles.CaseManager;
import seedu.sgsafe.utils.command.Command;
import seedu.sgsafe.utils.ui.Display;
import seedu.sgsafe.utils.ui.Parser;

/**
 * Entry point for the SGSafe application.
 */
public class SGSafe {

    /**
     * Main method that starts the SGSafe application.
     * Invokes initialization, enters the command loop, and prints exit message.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Display.printWelcomeMessage();
        mainLoop();
        Display.printGoodbyeMessage();
    }

    /**
     * Starts the main input loop for the application.
     * Continuously reads user input from the console until the user types "bye".
     * Each input is parsed and executed as a command.
     */
    public static void mainLoop() {
        Scanner in = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                return;
            }
            doAction(userInput);
        }
    }

    /**
     * Parses and executes a user command.
     *
     * @param userInput the raw input string entered by the user
     */
    public static void doAction(String userInput) {
        Command command = Parser.parseInput(userInput);

        switch (command.getType()) {
        case LIST:
            CaseManager.listCases();
            break;
        case INVALID:
        case UNKNOWN:
            Display.printMessage(command.getErrorMessage());
            break;
        }
    }
}
