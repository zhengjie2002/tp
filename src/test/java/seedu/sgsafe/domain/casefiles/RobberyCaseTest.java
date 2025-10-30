package seedu.sgsafe.domain.casefiles;

import org.junit.jupiter.api.Test;
import seedu.sgsafe.domain.casefiles.type.violent.RobberyCase;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RobberyCaseTest {

    @Test
    void constructor_initializesFieldsCorrectly() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        RobberyCase c = new RobberyCase("R001V1", "Convenience Store Robbery", date,
                "Masked man took cash register", "Store Clerk", "Officer Tan");

        assertEquals("R001V1", c.getId());
        assertEquals("Convenience Store Robbery", c.getTitle());
        assertEquals("Robbery", c.getCategoryString());
        assertNull(c.getWeapon());
        assertNull(c.getNumberOfVictims());
    }

    @Test
    void validEditFlags_matchViolentCaseContract() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        RobberyCase c = new RobberyCase("R001V1", "Convenience Store Robbery", date,
                "Info", "Victim", "Officer");

        List<String> flags = c.getValidEditFlags();
        assertEquals(List.of("title", "date", "info", "victim", "officer", "weapon", "number-of-victims"), flags);
    }

    @Test
    void update_updatesWeaponAndNumberOfVictims() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        RobberyCase c = new RobberyCase("R001V1", "Convenience Store Robbery", date,
                "Masked man took cash register", "Store Clerk", "Officer Tan");

        Map<String, Object> updates = new HashMap<>();
        updates.put("weapon", "Knife");
        updates.put("number-of-victims", 2);
        c.update(updates);

        assertEquals("Knife", c.getWeapon());
        assertEquals(2, c.getNumberOfVictims());
    }

    @Test
    void update_ignoresNullNumberOfVictimsButAcceptsNonNull() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        RobberyCase c = new RobberyCase("R001V1", "Title", date,
                "Info", "Victim", "Officer");

        Map<String, Object> updatesNull = new HashMap<>();
        updatesNull.put("number-of-victims", null);
        c.update(updatesNull);
        assertNull(c.getNumberOfVictims());

        Map<String, Object> updatesVal = new HashMap<>();
        updatesVal.put("number-of-victims", 5);
        c.update(updatesVal);
        assertEquals(5, c.getNumberOfVictims());

        Map<String, Object> updatesNullAgain = new HashMap<>();
        updatesNullAgain.put("number-of-victims", null);
        c.update(updatesNullAgain);
        assertEquals(5, c.getNumberOfVictims());
    }

    @Test
    void update_propagatesToBaseFieldsViaSuperUpdate() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        RobberyCase c = new RobberyCase("R001V1", "Old Title", date,
                "Old Info", "Old Victim", "Old Officer");

        Map<String, Object> baseUpdates = new HashMap<>();
        baseUpdates.put("title", "New Title");
        baseUpdates.put("info", "New Info");
        baseUpdates.put("victim", "New Victim");
        baseUpdates.put("officer", "New Officer");
        baseUpdates.put("weapon", "Gun");
        baseUpdates.put("number-of-victims", 3);

        c.update(baseUpdates);

        assertEquals("New Title", c.getTitle());
        assertEquals("New Info", c.getInfo());
        assertEquals("New Victim", c.getVictim());
        assertEquals("New Officer", c.getOfficer());
        assertEquals("Gun", c.getWeapon());
        assertEquals(3, c.getNumberOfVictims());
    }

    @Test
    void getReadCaseDisplay_includesWeaponAndNumberOfVictims() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        RobberyCase c = new RobberyCase("R001V1", "Convenience Store Robbery", date,
                "Masked man took cash register", "Store Clerk", "Officer Tan");

        Map<String, Object> updates = new HashMap<>();
        updates.put("weapon", "Knife");
        updates.put("number-of-victims", 2);
        c.update(updates);

        String[] lines = c.getReadCaseDisplay();
        assertTrue(containsLine(lines, "Weapon", "Knife"));
        assertTrue(containsLine(lines, "Number of Victims", "2"));
        assertTrue(containsLine(lines, "Info", "Masked man took cash register"));
    }

    @Test
    void getReadCaseDisplay_handlesNullNumberOfVictimsGracefully() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        RobberyCase c = new RobberyCase("R001V1", "T", date, "I", "V", "O");

        String[] lines = c.getReadCaseDisplay();
        assertTrue(containsLabel(lines, "Number of Victims"));
    }

    @Test
    void getAdditionalFields_appendsViolentSpecificFieldsAtEnd() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        RobberyCase c = new RobberyCase("R001V1", "T", date, "I", "V", "O");

        List<String> fields = c.getAdditionalFields();
        assertTrue(fields.contains("weapon"));
        assertTrue(fields.contains("number-of-victims"));

        int n = fields.size();
        assertTrue(n >= 2, "Base fields list should have at least two after appending");
        assertEquals("weapon", fields.get(n - 2));
        assertEquals("number-of-victims", fields.get(n - 1));
    }

    @Test
    void toSaveString_containsViolentFieldsWithNullsAsEmpty() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        RobberyCase c = new RobberyCase("R001V1", "T", date, "I", "V", "O");

        String save = c.toSaveString();
        assertTrue(save.contains("|number-of-victims:"));
        assertTrue(save.contains("|weapon:"));

        assertTrue(save.contains("|number-of-victims:"));
        assertTrue(save.contains("|weapon:"));
    }

    @Test
    void toSaveString_containsViolentFieldsWithValues() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        RobberyCase c = new RobberyCase("R001V1", "T", date, "I", "V", "O");

        Map<String, Object> updates = new HashMap<>();
        updates.put("weapon", "Bat");
        updates.put("number-of-victims", 7);
        c.update(updates);

        String save = c.toSaveString();
        assertTrue(save.contains("|number-of-victims:7"));
        assertTrue(save.contains("|weapon:Bat"));
    }

    private boolean containsLine(String[] lines, String label, String value) {
        for (String line : lines) {
            if (line.contains(label) && line.contains(value)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsLabel(String[] lines, String label) {
        for (String line : lines) {
            if (line.contains(label)) {
                return true;
            }
        }
        return false;
    }
}
