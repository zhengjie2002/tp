package seedu.sgsafe.utils.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.CaseManager;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Unit tests for {@link ListCommand}, verifying correct filtering and output formatting
 * across all {@link CaseListingMode} variants.
 */
class ListCommandTest {

    private ArrayList<Case> caseList;

    @BeforeEach
    void resetCaseList() throws Exception {
        Field caseListField = CaseManager.class.getDeclaredField("caseList");
        caseListField.setAccessible(true);
        caseList = (ArrayList<Case>) caseListField.get(null);
        caseList.clear();
    }

    /**
     * Verifies that listing with no cases returns only the header line.
     */
    @Test
    void list_withNoCases_returnsHeaderOnly() {
        ListCommand command = new ListCommand(CaseListingMode.ALL);
        String[] output = command.getCaseDescriptions(caseList);

        assertEquals(1, output.length);
        assertEquals("You currently have no cases in total. Add some now!", output[0]);
    }

    /**
     * Verifies that a single open case is listed correctly with appropriate header and display line.
     */
    @Test
    void list_withOneOpenCase_returnsCorrectHeaderAndLine() {
        caseList.add(new Case("000001", "Robbery", "2025-10-01", "Masked suspect", "John Doe", "Officer Tan"));

        ListCommand command = new ListCommand(CaseListingMode.OPEN_ONLY);
        String[] output = command.getCaseDescriptions(caseList);

        assertEquals(2, output.length);
        assertEquals("You currently have 1 case open", output[0]);
        assertTrue(output[1].contains("[O]"));
        assertTrue(output[1].contains("Robbery"));
    }

    /**
     * Verifies that open and closed cases are filtered correctly when mixed.
     */
    @Test
    void list_withMixedCases_filtersByStatusCorrectly() {
        caseList.add(new Case("000001", "Robbery", "2025-10-01", "Masked suspect", "John Doe", "Officer Tan"));
        caseList.add(new Case("000002", "Fraud", "2025-10-02", "Email scam", "Jane Doe", "Officer Lim"));

        Case closedCase = new Case("000003", "Trespass", "2025-10-03", "Unauthorized entry", "Jake Doe", "Officer Ong");
        closedCase.setClosed();
        caseList.add(closedCase);

        String[] openOutput = new ListCommand(CaseListingMode.OPEN_ONLY).getCaseDescriptions(caseList);
        assertEquals("You currently have 2 cases open", openOutput[0]);
        assertEquals(3, openOutput.length);
        assertTrue(openOutput[1].contains("Robbery"));
        assertTrue(openOutput[2].contains("Fraud"));

        String[] closedOutput = new ListCommand(CaseListingMode.CLOSED_ONLY).getCaseDescriptions(caseList);
        assertEquals("You currently have 1 case closed", closedOutput[0]);
        assertEquals(2, closedOutput.length);
        assertTrue(closedOutput[1].contains("Trespass"));
        assertTrue(closedOutput[1].contains("[C]"));
    }

    /**
     * Verifies that ALL mode includes both open and closed cases.
     */
    @Test
    void list_withAllMode_returnsAllCases() {
        caseList.add(new Case("000001", "Robbery", "2025-10-01", "Masked suspect", "John Doe", "Officer Tan"));
        caseList.add(new Case("000002", "Fraud", "2025-10-02", "Email scam", "Jane Doe", "Officer Lim"));

        Case closedCase = new Case("000003", "Trespass", "2025-10-03", "Unauthorized entry", "Jake Doe", "Officer Ong");
        closedCase.setClosed();
        caseList.add(closedCase);

        String[] output = new ListCommand(CaseListingMode.ALL).getCaseDescriptions(caseList);
        assertEquals("You currently have 3 cases in total", output[0]);
        assertEquals(4, output.length);
        assertTrue(output[1].contains("Robbery"));
        assertTrue(output[2].contains("Fraud"));
        assertTrue(output[3].contains("Trespass"));
    }

    /**
     * Verifies that DEFAULT mode behaves identically to ALL mode.
     */
    @Test
    void list_withDefaultMode_behavesLikeAll() {
        caseList.add(new Case("000001", "Robbery", "2025-10-01", "Masked suspect", "John Doe", "Officer Tan"));
        caseList.add(new Case("000002", "Fraud", "2025-10-02", "Email scam", "Jane Doe", "Officer Lim"));

        String[] output = new ListCommand(CaseListingMode.DEFAULT).getCaseDescriptions(caseList);
        assertEquals("You currently have 2 cases in total", output[0]);
        assertEquals(3, output.length);
        assertTrue(output[1].contains("Robbery"));
        assertTrue(output[2].contains("Fraud"));
    }
}
