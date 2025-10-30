package seedu.sgsafe.domain.casefiles;

import org.junit.jupiter.api.Test;
import seedu.sgsafe.domain.casefiles.type.violent.AssaultCase;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssaultCaseTest {

    @Test
    void constructor_initializesFieldsCorrectly() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        AssaultCase c = new AssaultCase("A001V2", "Street Fight", date,
                "Altercation between two individuals", "Tom", "Officer Lim");

        assertEquals("A001V2", c.getId());
        assertEquals("Street Fight", c.getTitle());
        assertEquals("Assault", c.getCategoryString());
        assertNull(c.getWeapon());
        assertNull(c.getNumberOfVictims());
    }

    @Test
    void update_updatesWeaponAndNumberOfVictims() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        AssaultCase c = new AssaultCase("A001V2", "Street Fight", date,
                "Altercation between two individuals", "Tom", "Officer Lim");

        Map<String, Object> updates = new HashMap<>();
        updates.put("weapon", "Bottle");
        updates.put("number-of-victims", 1);
        c.update(updates);

        assertEquals("Bottle", c.getWeapon());
        assertEquals(1, c.getNumberOfVictims());
    }

    @Test
    void getReadCaseDisplay_includesWeaponAndNumberOfVictims() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        AssaultCase c = new AssaultCase("A001V2", "Street Fight", date,
                "Altercation between two individuals", "Tom", "Officer Lim");

        Map<String, Object> updates = new HashMap<>();
        updates.put("weapon", "Bottle");
        updates.put("number-of-victims", 1);
        c.update(updates);

        String[] lines = c.getReadCaseDisplay();
        assertTrue(containsLine(lines, "Weapon", "Bottle"));
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
