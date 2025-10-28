package seedu.sgsafe.domain.casefiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class CaseFormatterTest {

    @Test
    void convertStatusToString_returnsCorrectValues() {
        assertEquals("Open", CaseFormatter.convertStatusToString(true));
        assertEquals("Closed", CaseFormatter.convertStatusToString(false));
    }

    @Test
    void formatCaseSummaryLine_includesAllFields() {
        String line = CaseFormatter.formatCaseSummaryLine(
                true, "Theft", "0001a3", "14/10/2025", "Stolen wallet");

        assertTrue(line.contains("Open"));
        assertTrue(line.contains("Theft"));
        assertTrue(line.contains("0001a3"));
        assertTrue(line.contains("14/10/2025")); // dd/MM/yyyy
        assertTrue(line.contains("Stolen wallet"));
    }

    @Test
    void formatCaseSummaryLine_truncatesLongTitle() {
        String longTitle = "X".repeat(CaseFormatter.MAX_TITLE_WIDTH + 5);
        String line = CaseFormatter.formatCaseSummaryLine(
                true, "Scam", "0001a4", "15/10/2025", longTitle);

        assertTrue(line.contains("..."), "Long titles should be truncated with ellipses");
    }

    @Test
    void formatCaseIDHeader_formatsCorrectly() {
        String header = CaseFormatter.formatCaseIDHeader("0001a5");
        assertEquals("======== CASE ID 0001a5 ========", header);
    }

    @Test
    void truncateWithEllipses_handlesNullAndShortStrings() {
        assertEquals("", CaseFormatter.truncateWithEllipses(null, 5));
        assertEquals("Hi", CaseFormatter.truncateWithEllipses("Hi", 5));
        assertEquals("Hello...", CaseFormatter.truncateWithEllipses("HelloWorld", 5));
    }

    @Test
    void wrapField_wrapsAndAlignsReadMode() {
        List<String> lines = CaseFormatter.wrapField(false, "Title",
                "This is a short title", CaseFormatter.MAX_DISPLAY_WIDTH_CHARACTERS);

        assertFalse(lines.isEmpty());
        assertTrue(lines.get(0).contains("Title"));
    }

    @Test
    void wrapField_limitsVerboseLines() {
        String longValue = "word ".repeat(200); // very long text
        List<String> lines = CaseFormatter.wrapField(true, "Info", longValue,
                CaseFormatter.MAX_DISPLAY_WIDTH_CHARACTERS);

        assertTrue(lines.size() <= CaseFormatter.MAX_VERBOSE_LINES_PER_FIELD);
        assertTrue(lines.get(lines.size() - 1).endsWith("..."));
    }

    @Test
    void tryAddWord_wrapsWhenExceedingWidth() {
        List<String> lines = new ArrayList<>();
        StringBuilder line = new StringBuilder("Prefix: ");
        CaseFormatter.LineState state = CaseFormatter.tryAddWord(
                "word", line, 0, 5, "Prefix: ", lines);

        assertNotNull(state);
        assertTrue(state.line().toString().contains("word"));
    }
}
