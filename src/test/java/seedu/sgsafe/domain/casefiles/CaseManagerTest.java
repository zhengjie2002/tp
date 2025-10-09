package seedu.sgsafe.domain.casefiles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaseManagerTest {

    @BeforeEach
    void setUp() throws Exception {
        Field caseListField = CaseManager.class.getDeclaredField("caseList");
        caseListField.setAccessible(true);
        ArrayList<Case> caseList = (ArrayList<Case>) caseListField.get(null);

        caseList.clear();
        caseList.add(new Case("Robbery",
                "2023-10-01",
                "Masked suspect",
                "John Doe",
                "Officer Tan"));
        caseList.add(new Case("Fraud",
                "2023-09-15",
                "Email scam",
                "Jane Doe",
                "Officer Lim"));
    }

    @Test
    void listCases_returnsFormattedCaseDescriptions() {
        String[] output = CaseManager.listCases();
        assertEquals(3, output.length); // includes header line
        assertEquals("You currently have 2 cases", output[0]);
        assertEquals("1. [O] 2023-10-01 Robbery", output[1]);
        assertEquals("2. [O] 2023-09-15 Fraud", output[2]);
    }
}