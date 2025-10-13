package seedu.sgsafe;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.sgsafe.domain.casefiles.CaseManager;
import seedu.sgsafe.utils.command.AddCommand;
import seedu.sgsafe.utils.command.CloseCommand;
import seedu.sgsafe.utils.command.Command;
import seedu.sgsafe.utils.command.EditCommand;
import seedu.sgsafe.utils.command.DeleteCommand;
import seedu.sgsafe.utils.exceptions.EmptyCommandException;
import seedu.sgsafe.utils.exceptions.InvalidCommandException;
import seedu.sgsafe.utils.ui.Display;
import seedu.sgsafe.utils.ui.Parser;

/**
 * Entry point for the SGSafe application.
 */
public class SGSafe {

    // Logger for logging application events
    private static final Logger logger = Logger.getLogger(SGSafe.class.getName());

    /**
     * Main method that starts the SGSafe application.
     * Invokes initialization, enters the command loop, and prints exit message.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        logger.log(Level.INFO, "SGSafe application started.");
        Display.printWelcomeMessage();
        mainLoop();
        Display.printGoodbyeMessage();
    }

    /**
     * Starts the main input loop for the application.
     * Continuously reads user input from the console until the user types "bye".
     * Each input is parsed and executed as a command.
     */
    private static void mainLoop() {
        Scanner in = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                return;
            }
            handleUserCommand(userInput);
        }
    }

    /**
     * Parses and executes a user command.
     *
     * @param userInput the raw input string entered by the user
     */
    private static void handleUserCommand(String userInput) {
        try {
            Command command = Parser.parseInput(userInput);

            switch (command.getCommandType()) {
            case LIST:
                CaseManager.listCases();
                break;
            case ADD:
                if (command instanceof AddCommand) {
                    CaseManager.addCase((AddCommand) command);
                } else {
                    Display.printMessage("Error: Invalid ADD command");
                }
                break;
            case CLOSE:
                if (command instanceof CloseCommand) {
                    CaseManager.closeCase((CloseCommand) command);
                } else {
                    Display.printMessage("Error: Invalid CLOSE command");
                }
                break;
            case EDIT:
                CaseManager.editCase((EditCommand) command);
                break;
            case DELETE:
                if(command instanceof DeleteCommand) {
                    CaseManager.deleteCase((DeleteCommand) command);
                }else {
                    Display.printMessage("Error: Invalid CLOSE command");
                }
                break;
            default:
                throw new EmptyCommandException();
            }
        } catch (InvalidCommandException e) {
            Display.printMessage(e.getErrorMessage());
        }
    }
}
