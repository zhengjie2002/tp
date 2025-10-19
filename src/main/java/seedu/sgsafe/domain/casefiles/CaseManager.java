package seedu.sgsafe.domain.casefiles;

import java.util.ArrayList;
import java.util.Map;

import seedu.sgsafe.utils.exceptions.CaseNotFoundException;
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

    public static int getCaseListSize() {
        return caseList.size();
    }

    public static ArrayList<Case> getCaseList() {
        return caseList;
    }

    /**
     * Adds a new case to the case list.
     *
     * @param newCase the {@link Case} object to be added
     */
    public static void addCase(Case newCase) {
        assert newCase != null : "newCase should not be null";
        caseList.add(newCase);
    }

    /**
     * Closes an existing case in the case list.
     *
     * @param caseToClose the case to be closed
     */
    public static void closeCase(Case caseToClose) {
        caseToClose.setClosed();
    }

    /**
     * Finds and returns a {@link Case} object from the case list using its unique ID.
     *
     * @param id the hexadecimal ID of the case to find
     * @return the Case with the matching ID, or null if not found
     */
    public static Case getCaseById(String id) {
        return caseList.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Updates an existing {@link Case} with new field values.
     * <p>
     * Finds the case by its {@code caseId} using {@link #getCaseById(String)} and applies
     * the updates from {@code newFlagValues} via {@link Case#update(Map)}.
     * Assumes the case exists (checked by assertion).
     *
     * @param caseId the 6-character hexadecimal case ID
     * @param newFlagValues map of field names to new values
     * @return the updated case’s display line
     */
    public static String editCase(String caseId, Map<String, String> newFlagValues)
            throws CaseNotFoundException {
        Case caseToEdit = getCaseById(caseId);
        if (caseToEdit == null) {
            throw new CaseNotFoundException(caseId);
        }
        caseToEdit.update(newFlagValues);
        return caseToEdit.getDisplayLine();
    }

    /**
     * Deletes an existing case in the case list using its index in the case list.
     * The method checks if the case number is valid, and throws an IndexOutOfBoundsException if
     * the case number is invalid
     *
     * @param caseNumber the index of the case to be deleted (1-indexed).
     */
    public static void deleteCase(int caseNumber) {
        if (caseNumber < 1 || caseNumber > caseList.size()) {
            throw new IndexOutOfBoundsException();
        }
        int caseIndex = caseNumber - 1;
        Case targetCase = caseList.get(caseIndex);
        Display.printMessage("Case deleted:\n" + targetCase.getDisplayLine());
        caseList.remove(caseIndex);
    }
}
