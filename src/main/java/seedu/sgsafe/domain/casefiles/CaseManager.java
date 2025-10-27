package seedu.sgsafe.domain.casefiles;

import java.util.ArrayList;
import java.util.Map;

import seedu.sgsafe.utils.exceptions.CaseAlreadyClosedException;
import seedu.sgsafe.utils.exceptions.CaseAlreadyOpenException;
import seedu.sgsafe.utils.exceptions.CaseNotFoundException;

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
     * Finds and returns a {@link Case} object from the case list using its unique ID.
     *
     * @param id the hexadecimal ID of the case to find
     * @return the Case with the matching ID, or null if not found
     */
    public static Case getCaseById(String id) {
        return caseList.stream()
                .filter(c -> (c.getId().equals(id) && !c.isDeleted()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Closes an existing case in the case list.
     *
     * @param caseId the case to be closed
     */
    public static String closeCase(String caseId) throws CaseNotFoundException {
        Case caseToClose = getCaseById(caseId);
        if (caseToClose == null) {
            throw new CaseNotFoundException(caseId);
        }
        if (!caseToClose.isOpen()) {
            throw new CaseAlreadyClosedException(caseId);
        }
        caseToClose.setClosed();
        assert !caseToClose.isOpen() : "Case should be closed";
        return caseToClose.getDisplayLine();
    }

    /**
     * Reopens a closed case in the case list.
     *
     * @param caseId the case to be opened
     */
    public static String openCase(String caseId) throws CaseNotFoundException {
        Case caseToOpen = getCaseById(caseId);
        if (caseToOpen == null) {
            throw new CaseNotFoundException(caseId);
        }
        if (caseToOpen.isOpen()) {
            throw new CaseAlreadyOpenException(caseId);
        }
        caseToOpen.setOpen();
        assert caseToOpen.isOpen() : "Case should be open";
        return caseToOpen.getDisplayLine();
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
     * Deletes an existing case in the case list using its id.
     * Throws an {@link CaseNotFoundException} if the case does not exist or has been deleted.
     *
     * @param caseId the id of the case to be deleted.
     * @return the deleted case's display line.
     */
    public static String deleteCase(String caseId) throws CaseNotFoundException {
        Case caseToDelete = getCaseById(caseId);
        if (caseToDelete == null) {
            throw new CaseNotFoundException(caseId);
        }
        caseToDelete.setDeleted(true);
        return caseToDelete.getDisplayLine();
    }
}
