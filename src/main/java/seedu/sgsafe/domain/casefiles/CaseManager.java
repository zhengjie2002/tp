package seedu.sgsafe.domain.casefiles;

import java.lang.reflect.Array;
import java.util.ArrayList;

import seedu.sgsafe.utils.command.AddCommand;
import seedu.sgsafe.utils.command.EditCommand;
import seedu.sgsafe.utils.ui.Display;

/**
 * Manages the collection of {@link Case} objects in the SGSafe system.
 * Provides functionality to store, retrieve, and display case records.
 */
public class CaseManager {

    /**
     * The central list of case records maintained by the application.
     * Each {@link Case} represents a single incident or report.
     */
    private static ArrayList<Case> caseList = new ArrayList<>();

    /**
     * Displays the current list of cases to the user.
     * <p>
     * This method delegates to {@link #getCaseDescriptions()} for formatting,
     * and passes the result to the {@code Display} class for output.
     *
     * @return the array of case descriptions that were printed
     */
    public static String[] listCases() {
        String[] caseDescriptions = getCaseDescriptions();
        Display.printMessage(caseDescriptions);
        return caseDescriptions;
    }

    /**
     * Builds and returns a formatted list of case descriptions.
     * <p>
     * The first line indicates the total number of cases, followed by each case's display line.
     * This method does not perform any printing or UI logic.
     *
     * @return an array of formatted case description strings
     */
    private static String[] getCaseDescriptions() {
        int caseListSize = caseList.size();
        String[] descriptions = new String[caseListSize + 1];
        descriptions[0] = generateCaseHeaderMessage(caseListSize);

        for (int i = 0; i < caseListSize; i++) {
            Case currentCase = caseList.get(i);
            descriptions[i+1] = formatCaseLine(i, currentCase);
        }

        return descriptions;
    }

    /**
     * Generates a header message based on the number of cases.
     * <p>
     * The output varies depending on the case count:
     * <ul>
     *   <li>For 0 cases: {@code "You currently have no cases. Add some now!"}</li>
     *   <li>For 1 case: {@code "You currently have 1 case"}</li>
     *   <li>For n > 1: {@code "You currently have n cases"}</li>
     * </ul>
     *
     * @param caseCount the number of cases in the system
     * @return a formatted header message string
     */
    private static String generateCaseHeaderMessage(int caseCount) {
        if (caseCount == 0) {
            return "You currently have no cases. Add some now!";
        } else if (caseCount == 1) {
            return "You currently have 1 case";
        } else {
            return "You currently have " + caseCount + " cases";
        }
    }

    /**
     * Formats a single case line for display.
     * <p>
     * The format includes the case index and its display line.
     *
     * @param index       the index of the case in the list (1-based)
     * @param currentCase the {@link Case} object to format
     * @return a formatted string representing the case
     */
    private static String formatCaseLine(int index, Case currentCase) {
        return (index + 1) + ". " + currentCase.getDisplayLine();
    }

    /**
     * Adds a new case to the {@link #caseList} based on the details provided in the {@link AddCommand}.
     * After adding the case, a confirmation message is displayed with the case's summary.
     *
     * @param command The {@link AddCommand} containing the details of the case to be added.
     */
    public static void addCase(AddCommand command) {
        Case newCase = new Case(command.getCaseTitle(), command.getCaseDate(),
                command.getCaseInfo(), command.getCaseVictim(), command.getCaseOfficer());
        caseList.add(newCase);
        Display.printMessage("New case added:\n" + newCase.getDisplayLine());
    }

    /**
     * Edits an existing case in the case list using the details provided in the EditCommand.
     * The method first checks if the case number is valid, then updates the corresponding case
     * with the new field values.
     *
     * @param editCommand the {@link EditCommand} containing the case number and the new values to update
     */
    public static void editCase(EditCommand editCommand) {
        int caseNumber = editCommand.getCaseNumber();
        if  (caseNumber < 1 || caseNumber > caseList.size()) {
            Display.printMessage("Invalid case index, please try again.");
            return;
        }
        int caseIndex = caseNumber - 1;
        Case targetCase = caseList.get(caseIndex);

        targetCase.update(editCommand.getNewFlagValues());
        Display.printMessage("Case edited:\n" + targetCase.getDisplayLine());
    }
}
