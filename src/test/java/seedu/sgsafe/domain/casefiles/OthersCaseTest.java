package seedu.sgsafe.domain.casefiles;

import org.junit.jupiter.api.Test;
import seedu.sgsafe.domain.casefiles.type.OthersCase;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OthersCaseTest {

    @Test
    void constructor_initializesFieldsCorrectly() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        OthersCase c = new OthersCase("O001X1", "Unclassified Incident", date,
                "Unknown event reported", "Kelly", "Officer Tan");

        assertEquals("O001X1", c.getId());
        assertEquals("Unclassified Incident", c.getTitle());
        assertEquals(date, c.getDate());
        assertEquals("Unknown event reported", c.getInfo());
        assertEquals("Kelly", c.getVictim());
        assertEquals("Officer Tan", c.getOfficer());
        assertEquals("Others", c.getCategoryString());
        assertNull(c.getCustomCategory(), "Custom category should initially be null");
    }

    @Test
    void update_setsCustomCategory_whenKeyPresent() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        OthersCase c = new OthersCase("O001X1", "Unclassified Incident", date,
                "Unknown event reported", "Kelly", "Officer Tan");

        Map<String, Object> updates = new HashMap<>();
        updates.put("custom-category", "Cybercrime");
        updates.put("info", "Reclassified as potential cyber incident");
        c.update(updates);

        assertEquals("Cybercrime", c.getCustomCategory());
        assertEquals("Reclassified as potential cyber incident", c.getInfo());
    }

    @Test
    void update_doesNotChangeCustomCategory_whenKeyAbsent() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        OthersCase c = new OthersCase("O001X1", "Unclassified Incident", date,
                "Unknown event reported", "Kelly", "Officer Tan");

        Map<String, Object> first = new HashMap<>();
        first.put("custom-category", "Cybercrime");
        c.update(first);
        assertEquals("Cybercrime", c.getCustomCategory());

        Map<String, Object> second = new HashMap<>();
        second.put("title", "Updated Title Only");
        c.update(second);

        assertEquals("Cybercrime", c.getCustomCategory(), "Custom category should remain unchanged when key is absent");
        assertEquals("Updated Title Only", c.getTitle());
    }

    @Test
    void getReadCaseDisplay_handlesNullsGracefully_andIncludesCustomCategoryWhenSet() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        OthersCase c = new OthersCase("O001X1", "Unclassified Incident", date,
                "Unknown event reported", "Kelly", "Officer Tan");

        String[] initial = c.getReadCaseDisplay();
        assertNotNull(initial);
        assertTrue(initial.length > 0);

        Map<String, Object> updates = new HashMap<>();
        updates.put("custom-category", "Cybercrime");
        c.update(updates);

        String[] lines = c.getReadCaseDisplay();
        assertTrue(containsLine(lines, "Custom Category", "Cybercrime"));
        assertTrue(containsLine(lines, "Info", "Unknown event reported")
                || containsLine(lines, "Info", "Unknown event reported".substring(0, 5)));
    }

    @Test
    void getValidEditFlags_returnsExactExpectedList() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        OthersCase c = new OthersCase("O001X1", "Unclassified Incident", date,
                "Unknown event reported", "Kelly", "Officer Tan");

        List<String> flags = c.getValidEditFlags();
        assertEquals(List.of("title", "date", "info", "victim", "officer", "custom-category"), flags);
    }

    @Test
    void getAdditionalFields_containsCustomCategory() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        OthersCase c = new OthersCase("O001X1", "Unclassified Incident", date,
                "Unknown event reported", "Kelly", "Officer Tan");

        List<String> extra = c.getAdditionalFields();
        assertNotNull(extra);
        assertTrue(extra.contains("custom-category"), "additional fields should include custom-category");
    }

    @Test
    void toSaveString_includesCustomCategory_emptyWhenNull() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        OthersCase c = new OthersCase("O001X1", "Unclassified Incident", date,
                "Unknown event reported", "Kelly", "Officer Tan");

        String s1 = c.toSaveString();
        assertTrue(s1.contains("|custom-category:"), "save string should include custom-category field");
        assertTrue(s1.matches(".*\\|custom-category:$") || s1.matches(".*\\|custom-category:\\|.*"));

        Map<String, Object> updates = new HashMap<>();
        updates.put("custom-category", "Cybercrime");
        c.update(updates);

        String s2 = c.toSaveString();
        assertTrue(s2.contains("|custom-category:Cybercrime"),
                "save string should include the set custom-category value");
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
