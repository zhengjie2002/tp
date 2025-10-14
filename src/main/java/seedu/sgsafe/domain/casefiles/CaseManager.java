package seedu.sgsafe.domain.casefiles;

import java.util.ArrayList;

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
     * Displays the current list of cases filtered by the specified {@link CaseListingMode}.
     * <p>
     * This method delegates to {@link #getCaseDescriptions(CaseListingMode)} for formatting,
     * and passes the result to the {@code Display} class for output.
     * <p>
     * Supported listing modes:
     * <ul>
     *   <li>{@code DEFAULT} — same as {@code ALL}</li>
     *   <li>{@code OPEN_ONLY} — shows only open cases</li>
     *   <li>{@code CLOSED_ONLY} — shows only closed cases</li>
     *   <li>{@code ALL} — shows all cases</li>
     * </ul>
     *
     * @param command the {@link ListCommand} containing the desired listing mode
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
     * Case indices reflect their original position in the full {@code caseList}, even after filtering.
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
            Case currentCase = matchingCases.get(i);
            descriptions[i + 1] = currentCase.getDisplayLine();
        }

        return descriptions;
    }

    /**
     * Filters the full {@code caseList} and returns only the cases that match the given mode.
     *
     * @param mode the {@link CaseListingMode} to filter by
     * @return a new {@link ArrayList} containing only the matching cases
     */
    private static ArrayList<Case> filterCasesByMode(CaseListingMode mode) {
        return new ArrayList<>(
                caseList.stream()
                        .filter(caseRecord -> isCaseVisible(caseRecord, mode))
                        .toList()
        );
    }

    /**
     * Determines whether a given case should be visible under the specified listing mode.
     *
     * @param caseRecord the {@link Case} to evaluate
     * @param mode       the {@link CaseListingMode} to apply
     * @return {@code true} if the case should be included; {@code false} otherwise
     */
    private static boolean isCaseVisible(Case caseRecord, CaseListingMode mode) {
        return switch (mode) {
            case OPEN_ONLY -> caseRecord.isOpen();
            case CLOSED_ONLY -> !caseRecord.isOpen();
            case ALL, DEFAULT -> true;
        };
    }

    /**
     * Generates a header message based on the number of cases and the listing mode.
     * <p>
     * The message varies depending on the count and mode:
     * <ul>
     *   <li>0 cases: {@code "You currently have no [status] cases. Add some now!"}</li>
     *   <li>1 case: {@code "You currently have 1 [status] case"}</li>
     *   <li>n > 1: {@code "You currently have n [status] cases"}</li>
     * </ul>
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
     * Generates a unique 6-character hexadecimal ID for a new case.
     * <p>
     * The ID is derived from the current size of {@code caseList}, formatted as a
     * zero-padded lowercase hexadecimal string. This ensures compact, readable,
     * and collision-free identifiers as long as cases are not removed or reordered.
     * <p>
     * Example outputs:
     * <ul>
     *   <li>{@code 000000} — first case</li>
     *   <li>{@code 00000a} — tenth case</li>
     *   <li>{@code 0000ff} — 256th case</li>
     * </ul>
     *
     * @return a 6-character hexadecimal string representing the new case ID
     */
    private static String generateHexId() {
        int raw = caseList.size();
        return String.format("%06x", raw); // zero-padded 6-digit hex
    }

    /**
     * Adds a new case to the {@link #caseList} based on the details provided in the {@link AddCommand}.
     * After adding the case, a confirmation message is displayed with the case's summary.
     *
     * @param command The {@link AddCommand} containing the details of the case to be added.
     */
    public static void addCase(AddCommand command) {
        assert command != null : "AddCommand should not be null";
        String id = generateHexId();
        Case newCase = new Case(id, command.getCaseTitle(),
                command.getCaseDate(), command.getCaseInfo(), command.getCaseVictim(), command.getCaseOfficer());
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
