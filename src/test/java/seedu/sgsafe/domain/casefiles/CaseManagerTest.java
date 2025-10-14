package seedu.sgsafe.domain.casefiles;

import seedu.sgsafe.utils.command.AddCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CaseManagerTest {

    private ArrayList<Case> caseList;

    @BeforeEach
    void setUp() throws Exception {
        Field caseListField = CaseManager.class.getDeclaredField("caseList");
        caseListField.setAccessible(true);
        caseList = (ArrayList<Case>) caseListField.get(null);
        caseList.clear();
    }

    @Test
    void listCases_withNoCases_returnsHeaderOnly() {
        String[] output = CaseManager.listCases();
        assertEquals(1, output.length);
        assertEquals("You currently have no cases. Add some now!", output[0]);
    }

    @Test
    void listCases_withOneCase_returnsHeaderAndSingleCase() {
        caseList.add(new Case("Robbery", "2023-10-01", "Masked suspect", "John Doe", "Officer Tan"));

        String[] output = CaseManager.listCases();
        assertEquals(2, output.length);
        assertEquals("You currently have 1 case", output[0]);
        assertEquals("1. [O] 2023-10-01 Robbery", output[1]);
    }

    @Test
    void listCases_withMultipleCases_returnsHeaderAndAllCases() {
        caseList.add(new Case("Robbery", "2023-10-01", "Masked suspect", "John Doe", "Officer Tan"));
        caseList.add(new Case("Fraud", "2023-09-15", "Email scam", "Jane Doe", "Officer Lim"));

        String[] output = CaseManager.listCases();
        assertEquals(3, output.length);
        assertEquals("You currently have 2 cases", output[0]);
        assertEquals("1. [O] 2023-10-01 Robbery", output[1]);
        assertEquals("2. [O] 2023-09-15 Fraud", output[2]);
    }

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
