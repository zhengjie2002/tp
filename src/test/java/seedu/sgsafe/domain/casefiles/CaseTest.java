package seedu.sgsafe.domain.casefiles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;

import seedu.sgsafe.domain.casefiles.type.financial.TheftCase;
import seedu.sgsafe.domain.casefiles.type.violent.MurderCase;

public class CaseTest {

    @Test
    void getDisplayLine_formatsSummaryCorrectly() {
        Case c = new TheftCase("0001a3", "Theft",
                LocalDate.of(2025, 10, 14), "Stolen wallet", "", "");

        String line = c.getDisplayLine();

        // Should contain status, category, ID, date, and title
        assertTrue(line.contains("Open"));
        assertTrue(line.contains("Theft"));
        assertTrue(line.contains("0001a3"));
        assertTrue(line.contains("14/10/2025"));
    }

    @Test
    void getMultiLineVerboseDisplay_includesHeaderAndFields() {
        Case c = new MurderCase("0001a5", "Traffic accident",
                LocalDate.of(2025, 10, 16), "Minor collision", "", "") {
        };

        String[] lines = c.getMultiLineVerboseDisplay();

        assertTrue(lines[0].startsWith("======== CASE ID 0001a5 ========"));
        assertTrue(Arrays.stream(lines).anyMatch(l -> l.contains("Status")));
        assertTrue(Arrays.stream(lines).anyMatch(l -> l.contains("Category")));
        assertTrue(Arrays.stream(lines).anyMatch(l -> l.contains("Title")));
        assertTrue(Arrays.stream(lines).anyMatch(l -> l.contains("Date")));
    }
}
