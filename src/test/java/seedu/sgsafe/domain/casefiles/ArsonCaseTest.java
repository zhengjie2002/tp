package seedu.sgsafe.domain.casefiles;

import org.junit.jupiter.api.Test;
import seedu.sgsafe.domain.casefiles.type.property.ArsonCase;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArsonCaseTest {

    @Test
    void constructor_initializesFieldsCorrectly() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        ArsonCase c = new ArsonCase("A001F1", "Warehouse Fire", date,
                "Deliberate fire incident", "David", "Officer Ong");

        assertEquals("A001F1", c.getId());
        assertEquals("Warehouse Fire", c.getTitle());
        assertEquals("David", c.getVictim());
        assertEquals("Officer Ong", c.getOfficer());
        assertEquals("Arson", c.getCategoryString());
        assertNull(c.getLocation());
        assertNull(c.getMonetaryDamage());
    }

    @Test
    void getReadCaseDisplay_includesMonetaryDamageIfPresent() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        ArsonCase c = new ArsonCase("A001F1", "Warehouse Fire", date,
                "Deliberate fire incident", "David", "Officer Ong");

        Map<String, Object> updates = new HashMap<>();
        updates.put("monetary-damage", 10000);
        c.update(updates);

        String[] lines = c.getReadCaseDisplay();
        assertTrue(containsLine(lines, "Monetary Damage", "10000"));
    }

    @Test
    void update_updatesLocationAndMonetaryDamageFields() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        ArsonCase c = new ArsonCase("A001F1", "Warehouse Fire", date,
                "Deliberate fire incident", "David", "Officer Ong");

        Map<String, Object> updates = new HashMap<>();
        updates.put("location", "Jurong Industrial Park");
        updates.put("monetary-damage", 200000);

        c.update(updates);

        assertEquals("Jurong Industrial Park", c.getLocation());
        assertEquals(200000, c.getMonetaryDamage());
    }

    @Test
    void getReadCaseDisplay_includesLocationAndMonetaryDamage() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        ArsonCase c = new ArsonCase("A001F1", "Warehouse Fire", date,
                "Deliberate fire incident", "David", "Officer Ong");

        Map<String, Object> updates = new HashMap<>();
        updates.put("location", "Jurong Industrial Park");
        updates.put("monetary-damage", 200000);
        c.update(updates);

        String[] lines = c.getReadCaseDisplay();
        assertTrue(containsLine(lines, "Location", "Jurong Industrial Park"));
        assertTrue(containsLine(lines, "Monetary Damage", "200000"));
    }

    private boolean containsLine(String[] lines, String label, String value) {
        for (String l : lines) {
            if (l.contains(label) && l.contains(value)) {
                return true;
            }
        }
        return false;
    }
}
