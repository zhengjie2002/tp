package seedu.sgsafe.domain.casefiles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}