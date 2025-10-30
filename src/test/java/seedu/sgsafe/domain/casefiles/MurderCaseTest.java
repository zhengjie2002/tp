package seedu.sgsafe.domain.casefiles;

import org.junit.jupiter.api.Test;
import seedu.sgsafe.domain.casefiles.type.violent.MurderCase;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MurderCaseTest {

    @Test
    void constructor_initializesFieldsCorrectly() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        MurderCase c = new MurderCase("M001V3", "Fatal Stabbing", date,
                "Victim found in alley", "Unknown", "Officer Koh");

        assertEquals("M001V3", c.getId());
        assertEquals("Fatal Stabbing", c.getTitle());
        assertEquals("Murder", c.getCategoryString());
        assertNull(c.getWeapon());
        assertNull(c.getNumberOfVictims());
    }

    @Test
    void update_updatesWeaponAndNumberOfVictims() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        MurderCase c = new MurderCase("M001V3", "Fatal Stabbing", date,
                "Victim found in alley", "Unknown", "Officer Koh");

        Map<String, Object> updates = new HashMap<>();
        updates.put("weapon", "Knife");
        updates.put("number-of-victims", 1);
        c.update(updates);

        assertEquals("Knife", c.getWeapon());
        assertEquals(1, c.getNumberOfVictims());
    }

    @Test
    void getReadCaseDisplay_includesWeaponAndNumberOfVictims() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        MurderCase c = new MurderCase("M001V3", "Fatal Stabbing", date,
                "Victim found in alley", "Unknown", "Officer Koh");

        Map<String, Object> updates = new HashMap<>();
        updates.put("weapon", "Knife");
        updates.put("number-of-victims", 1);
        c.update(updates);

        String[] lines = c.getReadCaseDisplay();
        assertTrue(containsLine(lines, "Weapon", "Knife"));
        assertTrue(containsLine(lines, "Number of Victims", "1"));
    }

    private boolean containsLine(String[] lines, String label, String value) {
        for (String line : lines) {
            if (line.contains(label) && line.contains(value)) {
                return true;
            }
        }
        return false;
    }
}
