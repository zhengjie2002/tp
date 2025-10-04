package seedu.sgsafe.domain.casefiles;

import java.util.ArrayList;
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
}
