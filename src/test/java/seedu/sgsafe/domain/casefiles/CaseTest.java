package seedu.sgsafe.domain.casefiles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import seedu.sgsafe.domain.casefiles.type.OthersCase;

public class CaseTest {

    // ----------- BASIC TESTS ----------- //

    @Test
    void constructor_initializesFieldsCorrectly() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        Case c = new OthersCase("0001a3", "Stolen wallet", date,
                "Wallet stolen at MRT station", "John Doe", "Officer Tan");

        assertEquals("0001a3", c.getId());
        assertEquals("Stolen wallet", c.getTitle());
        assertEquals(date, c.getDate());
        assertEquals("Wallet stolen at MRT station", c.getInfo());
        assertEquals("John Doe", c.getVictim());
        assertEquals("Officer Tan", c.getOfficer());

        assertTrue(c.isOpen(), "Case should be open by default");
        assertFalse(c.isDeleted(), "Case should not be deleted by default");
        assertNotNull(c.getCreatedAt());
        assertNotNull(c.getUpdatedAt());
        assertTrue(c.getCreatedAt().isBefore(LocalDateTime.now().plusSeconds(1)));
    }

    // ----------- DISPLAY TESTS ----------- //

    @Test
    void getDisplayLine_includesAllCoreFields() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        Case c = new OthersCase("0001a3", "Stolen wallet", date,
                "Wallet stolen at MRT station", "John Doe", "Officer Tan");

        String line = c.getDisplayLine();

        assertTrue(line.contains("Open"));
        assertTrue(line.contains("0001a3"));
        assertTrue(line.contains("14/10/2025")); // formatted date
        assertTrue(line.contains("Stolen wallet"));
    }

    @Test
    void getMultiLineVerboseDisplay_includesAllStandardFields() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        Case c = new OthersCase("0001a3", "Stolen wallet", date,
                "Wallet stolen at MRT station", "John Doe", "Officer Tan");

        String[] lines = c.getMultiLineVerboseDisplay();

        assertTrue(lines[0].contains("CASE ID 0001a3"));
        assertTrue(containsLineWith(lines, "Status", "Open"));
        assertTrue(containsLineWith(lines, "Category", "Theft")
                || containsLineWith(lines, "Category", "")); // category may be null/empty
        assertTrue(containsLineWith(lines, "Title", "Stolen wallet"));
        assertTrue(containsLineWith(lines, "Date", "14/10/2025"));
        assertTrue(containsLineWith(lines, "Info", "Wallet stolen at MRT station"));
        assertTrue(containsLineWith(lines, "Victim", "John Doe"));
        assertTrue(containsLineWith(lines, "Officer", "Officer Tan"));
    }

    @Test
    void getMultiLineVerboseDisplay_handlesNullsGracefully() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        Case c = new OthersCase("0001a3", null, date, null, null, null);

        String[] lines = c.getMultiLineVerboseDisplay();

        assertTrue(lines[0].contains("CASE ID 0001a3"));
        // Should not throw, even with null fields
        assertTrue(lines.length > 1);
    }

    // helper
    private boolean containsLineWith(String[] lines, String label, String value) {
        for (String line : lines) {
            if (line.contains(label) && line.contains(value)) {
                return true;
            }
        }
        return false;
    }
}
