package seedu.sgsafe.domain.casefiles;

import java.util.ArrayList;
import java.util.stream.Collectors;

import seedu.sgsafe.utils.command.AddCommand;
import seedu.sgsafe.utils.command.CaseListingMode;
import seedu.sgsafe.utils.command.CloseCommand;
import seedu.sgsafe.utils.command.EditCommand;
import seedu.sgsafe.utils.command.ListCommand;
import seedu.sgsafe.utils.ui.Display;

import seedu.sgsafe.utils.exceptions.IndexOutOfBoundsException;

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
    /**
     * Displays the current list of cases filtered by the specified {@link CaseListingMode}.
     * <p>
     * Supported modes:
     * <ul>
     *   <li>{@code DEFAULT} — same as {@code ALL}</li>
     *   <li>{@code OPEN_ONLY} — shows only open cases</li>
     *   <li>{@code CLOSED_ONLY} — shows only closed cases</li>
     *   <li>{@code ALL} — shows all cases</li>
     * </ul>
     *
     * @param mode the listing mode to apply
     * @return the array of case descriptions that were printed
     */
    public static String[] listCases(ListCommand command) {
        CaseListingMode mode = command.getListingMode();
        String[] caseDescriptions = getCaseDescriptions(mode);
        Display.printMessage(caseDescriptions);
        return caseDescriptions;
    }

    /**
     * Builds and returns a formatted list of case descriptions based on the given mode.
     * <p>
     * The first line indicates the total number of matching cases, followed by each case's display line.
     *
     * @param mode the listing mode to apply
     * @return an array of formatted case description strings
     */
    private static String[] getCaseDescriptions(CaseListingMode mode) {
        ArrayList<Case> matchingCases = filterCasesByMode(mode);
        int count = matchingCases.size();

        String[] descriptions = new String[count + 1];
        descriptions[0] = generateCaseHeaderMessage(count, mode);

        for (int i = 0; i < count; i++) {
            descriptions[i + 1] = formatCaseLine(i, matchingCases.get(i));
        }

        return descriptions;
    }

    private static ArrayList<Case> filterCasesByMode(CaseListingMode mode) {
        return new ArrayList<>(
                caseList.stream()
                        .filter(caseRecord -> isCaseVisible(caseRecord, mode))
                        .toList()
        );
    }

    private static boolean isCaseVisible(Case caseRecord, CaseListingMode mode) {
        return switch (mode) {
            case OPEN_ONLY -> caseRecord.isOpen();
            case CLOSED_ONLY -> !caseRecord.isOpen();
            case ALL, DEFAULT -> true;
        };
    }

    /**
     * Generates a header message based on the number of cases and the listing mode.
     *
     * @param caseCount the number of cases in the filtered list
     * @param mode      the {@link CaseListingMode} used to filter the cases
     * @return a formatted header message string
     */
    private static String generateCaseHeaderMessage(int caseCount, CaseListingMode mode) {
        String statusLabel = switch (mode) {
            case OPEN_ONLY -> "open";
            case CLOSED_ONLY -> "closed";
            case ALL, DEFAULT -> "total";
        };

        if (caseCount == 0) {
            return "You currently have no " + statusLabel + " cases. Add some now!";
        } else if (caseCount == 1) {
            return "You currently have 1 " + statusLabel + " case";
        } else {
            return "You currently have " + caseCount + " " + statusLabel + " cases";
        }
    }

    /**
     * Formats a single case line for display.
     *
     * @param index       the index of the case in the filtered list (1-based)
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
        assert command != null : "AddCommand should not be null";
        Case newCase = new Case(command.getCaseTitle(), command.getCaseDate(),
                command.getCaseInfo(), command.getCaseVictim(), command.getCaseOfficer());
        caseList.add(newCase);
        Display.printMessage("New case added:\n" + newCase.getDisplayLine());
    }

    public static void closeCase(CloseCommand command) {
        int caseNumber = command.getCaseNumber();
        if (caseNumber < 1 || caseNumber > caseList.size()) {
            throw new IndexOutOfBoundsException();
        }
        int caseIndex = caseNumber - 1;
        Case caseToClose = caseList.get(caseIndex);
        caseToClose.setClosed();
        Display.printMessage("Case closed:\n" + caseToClose.getDisplayLine());
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
