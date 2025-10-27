package seedu.sgsafe.domain.casefiles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.sgsafe.domain.casefiles.type.financial.TheftCase;
import seedu.sgsafe.utils.command.Command;
import seedu.sgsafe.utils.exceptions.CaseNotFoundException;
import seedu.sgsafe.utils.ui.Parser;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        Case newCase = new TheftCase("000000", "Burglary", "2023-10-05", "Broken window", "Alice", "Officer Lee");
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
        newCase = new TheftCase("000000", "Burglary", "2023-10-05", "Broken window", "Alice", "Officer Lee");
        CaseManager.addCase(newCase);

        newCase = new TheftCase("000001", "Burglary", "2023-10-05", "Broken window", null, null);
        CaseManager.addCase(newCase);

        newCase = new TheftCase("000002", "Burglary", "2023-10-05", "Broken window", "Alice", null);
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

    // ----------- TESTS FOR DELETE COMMANDS ----------- //

    @Test
    void deleteCase_withValidCommand_deletesCaseSuccessfully() throws Exception {
        caseList.add(new TheftCase("000000", "Burglary", "2023-10-05", "Broken window", "Alice", "Officer Lee"));
            CaseManager.deleteCase("000000");
        assertEquals(1, caseList.size());
        assertEquals("Burglary", caseList.get(0).getTitle());
        assertEquals("2023-10-05", caseList.get(0).getDate());
        assertEquals("Broken window", caseList.get(0).getInfo());
        assertEquals("Alice", caseList.get(0).getVictim());
        assertEquals("Officer Lee", caseList.get(0).getOfficer());
        assertTrue(caseList.get(0).isDeleted());
    }

    @Test
    void deleteCase_withInvalidCommand_throwsCaseNotFoundException() {
        caseList.add(new TheftCase("000000", "Burglary", "2023-10-05", "Broken window", "Alice", "Officer Lee"));
        assertThrows(CaseNotFoundException.class, () -> CaseManager.deleteCase("000001"));
    }

    @Test
    void deleteCase_withRepeatedDeletes_throwsCaseNotFoundException() throws Exception {
        caseList.add(new TheftCase("000000", "Burglary", "2023-10-05", "Broken window", "Alice", "Officer Lee"));
        CaseManager.deleteCase("000000");
        assertThrows(CaseNotFoundException.class, () -> CaseManager.deleteCase("000000"));
    }
}
