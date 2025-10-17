package seedu.sgsafe.domain.casefiles;

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

    // ----------- TESTS FOR ADD COMMANDS ----------- //

    @Test
    void addCase_withOneValidCommand_addsCaseSuccessfully() {
        Case newCase = new Case("000000", "Burglary", "2023-10-05", "Broken window", "Alice", "Officer Lee");
        CaseManager.addCase(newCase);

        assertEquals(1, caseList.size());
        assertEquals("Burglary", caseList.get(0).getTitle());
        assertEquals("2023-10-05", caseList.get(0).getDate());
        assertEquals("Broken window", caseList.get(0).getInfo());
        assertEquals("Alice", caseList.get(0).getVictim());
        assertEquals("Officer Lee", caseList.get(0).getOfficer());
    }

    @Test
    void addCase_withThreeValidCommand_addsCaseSuccessfully() {
        Case newCase;
        newCase = new Case("000000", "Burglary", "2023-10-05", "Broken window", "Alice", "Officer Lee");
        CaseManager.addCase(newCase);

        newCase = new Case("000001", "Burglary", "2023-10-05", "Broken window", null, null);
        CaseManager.addCase(newCase);

        newCase = new Case("000002", "Burglary", "2023-10-05", "Broken window", "Alice", null);
        CaseManager.addCase(newCase);

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
