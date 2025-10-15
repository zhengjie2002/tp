package seedu.sgsafe.domain.casefiles;

import seedu.sgsafe.utils.command.AddCommand;
import seedu.sgsafe.utils.command.ListCommand;
import seedu.sgsafe.utils.command.CaseListingMode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CaseManagerTest {

    private ArrayList<Case> caseList;

    @BeforeEach
    void setUp() throws Exception {
        Field caseListField = CaseManager.class.getDeclaredField("caseList");
        caseListField.setAccessible(true);
        caseList = (ArrayList<Case>) caseListField.get(null);
        caseList.clear();
    }

    // ----------- TESTS FOR LIST COMMANDS ----------- //

    @Test
    void listCases_withNoCases_returnsHeaderOnly() {
        String[] output = CaseManager.listCases(new ListCommand(CaseListingMode.ALL));
        assertEquals(1, output.length);
        assertEquals("You currently have no total cases. Add some now!", output[0]);
    }

    @Test
    void listCases_withOneOpenCase_returnsCorrectHeaderAndLine() {
        CaseManager.addCase(new AddCommand("Robbery", "2025-10-01", "Masked suspect", "John Doe", "Officer Tan"));

        String[] output = CaseManager.listCases(new ListCommand(CaseListingMode.OPEN_ONLY));
        assertEquals(2, output.length);
        assertEquals("You currently have 1 open case", output[0]);
        assertTrue(output[1].contains("[O]"));
        assertTrue(output[1].contains("Robbery"));
    }

    @Test
    void listCases_withMultipleCases_filtersByStatusCorrectly() {
        CaseManager.addCase(new AddCommand("Robbery", "2025-10-01", "Masked suspect", "John Doe", "Officer Tan"));
        CaseManager.addCase(new AddCommand("Fraud", "2025-10-02", "Email scam", "Jane Doe", "Officer Lim"));
        Case closedCase = new Case("000002", "Trespass", "2025-10-03", "Unauthorized entry", "Jake Doe", "Officer Ong");
        closedCase.setClosed();
        caseList.add(closedCase);

        String[] openOutput = CaseManager.listCases(new ListCommand(CaseListingMode.OPEN_ONLY));
        assertEquals("You currently have 2 open cases", openOutput[0]);
        assertEquals(3, openOutput.length);
        assertTrue(openOutput[1].contains("Robbery"));
        assertTrue(openOutput[2].contains("Fraud"));

        String[] closedOutput = CaseManager.listCases(new ListCommand(CaseListingMode.CLOSED_ONLY));
        assertEquals("You currently have 1 closed case", closedOutput[0]);
        assertEquals(2, closedOutput.length);
        assertTrue(closedOutput[1].contains("Trespass"));
        assertTrue(closedOutput[1].contains("[C]"));
    }

    @Test
    void listCases_withAllMode_returnsAllCases() {
        CaseManager.addCase(new AddCommand("Robbery", "2025-10-01", "Masked suspect", "John Doe", "Officer Tan"));
        CaseManager.addCase(new AddCommand("Fraud", "2025-10-02", "Email scam", "Jane Doe", "Officer Lim"));
        Case closedCase = new Case("000002", "Trespass", "2025-10-03", "Unauthorized entry", "Jake Doe", "Officer Ong");
        closedCase.setClosed();
        caseList.add(closedCase);

        String[] output = CaseManager.listCases(new ListCommand(CaseListingMode.ALL));
        assertEquals("You currently have 3 total cases", output[0]);
        assertEquals(4, output.length);
        assertTrue(output[1].contains("Robbery"));
        assertTrue(output[2].contains("Fraud"));
        assertTrue(output[3].contains("Trespass"));
    }

    @Test
    void listCases_withDefaultMode_behavesLikeAll() {
        CaseManager.addCase(new AddCommand("Robbery", "2025-10-01", "Masked suspect", "John Doe", "Officer Tan"));
        CaseManager.addCase(new AddCommand("Fraud", "2025-10-02", "Email scam", "Jane Doe", "Officer Lim"));

        String[] output = CaseManager.listCases(new ListCommand(CaseListingMode.DEFAULT));
        assertEquals("You currently have 2 total cases", output[0]);
        assertEquals(3, output.length);
        assertTrue(output[1].contains("Robbery"));
        assertTrue(output[2].contains("Fraud"));
    }

    // ----------- TESTS FOR ADD COMMANDS ----------- //

    @Test
    void addCase_withOneValidCommand_addsCaseSuccessfully() {
        AddCommand command = new AddCommand("Burglary", "2023-10-05", "Broken window", "Alice", "Officer Lee");
        CaseManager.addCase(command);

        assertEquals(1, caseList.size());
        assertEquals("Burglary", caseList.get(0).getTitle());
        assertEquals("2023-10-05", caseList.get(0).getDate());
        assertEquals("Broken window", caseList.get(0).getInfo());
        assertEquals("Alice", caseList.get(0).getVictim());
        assertEquals("Officer Lee", caseList.get(0).getOfficer());
    }

    @Test
    void addCase_withThreeValidCommand_addsCaseSuccessfully() {
        AddCommand command;
        command = new AddCommand("Burglary", "2023-10-05", "Broken window", "Alice", "Officer Lee");
        CaseManager.addCase(command);

        command = new AddCommand("Burglary", "2023-10-05", "Broken window", null, null);
        CaseManager.addCase(command);

        command = new AddCommand("Burglary", "2023-10-05", "Broken window", "Alice", null);
        CaseManager.addCase(command);

        assertEquals(3, caseList.size());
        assertEquals("Burglary", caseList.get(0).getTitle());
        assertEquals("2023-10-05", caseList.get(0).getDate());
        assertEquals("Broken window", caseList.get(0).getInfo());
        assertEquals("Alice", caseList.get(0).getVictim());
        assertEquals("Officer Lee", caseList.get(0).getOfficer());

        assertEquals("Burglary", caseList.get(1).getTitle());
        assertEquals("2023-10-05", caseList.get(1).getDate());
        assertEquals("Broken window", caseList.get(1).getInfo());
        assertNull(caseList.get(1).getVictim());
        assertNull(caseList.get(1).getOfficer());

        assertEquals("Burglary", caseList.get(2).getTitle());
        assertEquals("2023-10-05", caseList.get(2).getDate());
        assertEquals("Broken window", caseList.get(2).getInfo());
        assertEquals("Alice", caseList.get(2).getVictim());
        assertNull(caseList.get(2).getOfficer());
    }

}
