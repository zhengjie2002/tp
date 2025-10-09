package seedu.sgsafe.domain.casefiles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CaseManagerTest {

    @BeforeEach
    void setUp() {
        CaseManager.caseList.clear();
        CaseManager.caseList.add(new Case("Robbery", "2023-10-01", "Masked suspect", "John Doe", "Officer Tan"));
        CaseManager.caseList.add(new Case("Fraud", "2023-09-15", "Email scam", "Jane Doe", "Officer Lim"));
    }

    @Test
    void listCases_returnsFormattedCaseDescriptions() {
        String[] output = CaseManager.listCases();
        assertEquals(2, output.length);
        assertEquals("1. [O] 2023-10-01 Robbery", output[0]);
        assertEquals("2. [O] 2023-09-15 Fraud", output[1]);
    }
}
