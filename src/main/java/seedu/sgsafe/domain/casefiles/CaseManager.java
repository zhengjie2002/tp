package seedu.sgsafe.domain.casefiles;

import java.util.ArrayList;

import seedu.sgsafe.utils.command.AddCommand;
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
    public static ArrayList<Case> caseList = new ArrayList<>();

    /**
     * Displays all cases currently stored in {@link #caseList}.
     * Each case is printed with its index and display summary.
     * The output is wrapped using {@link Display#printMessage(String...)} for formatting.
     */
    public static void listCases() {
        int caseListSize = caseList.size();
        String[] caseDescriptions = new String[caseListSize];
        Case currentCase;
        for (int i = 0; i < caseListSize; i++) {
            currentCase = caseList.get(i);
            caseDescriptions[i] = (i + 1) + ". " + currentCase.getDisplayLine();
        }
        Display.printMessage(caseDescriptions);
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
}
