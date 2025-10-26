package seedu.sgsafe.domain.casefiles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.sgsafe.domain.casefiles.type.financial.TheftCase;

import java.lang.reflect.Field;
import java.time.LocalDate;
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
        LocalDate date = LocalDate.of(2023, 10, 5);
        Case newCase = new TheftCase("000000", "Burglary", date, "Broken window", "Alice", "Officer Lee");
        CaseManager.addCase(newCase);

        assertEquals(1, caseList.size());
        assertEquals("Burglary", caseList.get(0).getTitle());
        assertEquals(date, caseList.get(0).getDate());
        assertEquals("Broken window", caseList.get(0).getInfo());
        assertEquals("Alice", caseList.get(0).getVictim());
        assertEquals("Officer Lee", caseList.get(0).getOfficer());
    }

    @Test
    void addCase_withThreeValidCommand_addsCaseSuccessfully() {
        LocalDate date = LocalDate.of(2023, 10, 5);
        Case newCase;
        newCase = new TheftCase("000000", "Burglary", date, "Broken window", "Alice", "Officer Lee");
        CaseManager.addCase(newCase);

        newCase = new TheftCase("000001", "Burglary", date, "Broken window", null, null);
        CaseManager.addCase(newCase);

        newCase = new TheftCase("000002", "Burglary", date, "Broken window", "Alice", null);
        CaseManager.addCase(newCase);

        assertEquals(3, caseList.size());
        assertEquals("Burglary", caseList.get(0).getTitle());
        assertEquals(date, caseList.get(0).getDate());
        assertEquals("Broken window", caseList.get(0).getInfo());
        assertEquals("Alice", caseList.get(0).getVictim());
        assertEquals("Officer Lee", caseList.get(0).getOfficer());

        assertEquals("Burglary", caseList.get(1).getTitle());
        assertEquals(date, caseList.get(1).getDate());
        assertEquals("Broken window", caseList.get(1).getInfo());
        assertNull(caseList.get(1).getVictim());
        assertNull(caseList.get(1).getOfficer());

        assertEquals("Burglary", caseList.get(2).getTitle());
        assertEquals(date, caseList.get(2).getDate());
        assertEquals("Broken window", caseList.get(2).getInfo());
        assertEquals("Alice", caseList.get(2).getVictim());
        assertNull(caseList.get(2).getOfficer());
    }
}
