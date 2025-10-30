package seedu.sgsafe.domain.casefiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class CaseFormatterTest {

    String expectedOpenString = "Open";
    String expectedClosedString = "Closed";

    // ----------- TESTS FOR CONVERTING STATUS TO STRING ----------- //

    @Test
    void convertStatusToString_returnsCorrectValues() {
        assertEquals(expectedOpenString, CaseFormatter.convertStatusToString(true));
        assertEquals(expectedClosedString, CaseFormatter.convertStatusToString(false));
    }

    // ----------- TESTS FOR FORMATTING THE CASE SUMMARY LINE ----------- //

    // Happy path
    @Test
    void formatCaseSummaryLine_includesAllFields_multipleCases() {
        Object[][] cases = {
                {true,  "Theft",            "0001a3", "14/10/2025", "Stolen wallet"},
                {false, "Scam",             "0001a4", "15/10/2025", "Loan fraud"},
                {false, "Traffic accident", "0001a5", "16/10/2025", "Minor collision"}
        };

        for (Object[] c : cases) {
            boolean isOpen = (boolean) c[0];
            String category = (String) c[1];
            String id = (String) c[2];
            String date = (String) c[3];
            String title = (String) c[4];

            String line = CaseFormatter.formatCaseSummaryLine(isOpen, category, id, date, title);

            assertTrue(line.contains(CaseFormatter.convertStatusToString(isOpen)));
            assertTrue(line.contains(category));
            assertTrue(line.contains(id));
            assertTrue(line.contains(date));
            assertTrue(line.contains(title));
        }
    }

    // way over the limit should be truncated
    @Test
    void formatCaseSummaryLine_truncatesLongTitle() {
        String longTitle = "X".repeat(CaseFormatter.MAX_TITLE_WIDTH + 5);
        String line = CaseFormatter.formatCaseSummaryLine(
                true, "Scam", "0001a4", "15/10/2025", longTitle);

        assertTrue(line.contains("..."), "Long titles should be truncated with ellipses");
    }

    // even one over should also truncate
    @Test
    void formatCaseSummaryLine_truncatesLongTitle2() {
        String longTitle = "X".repeat(CaseFormatter.MAX_TITLE_WIDTH + 1);
        String line = CaseFormatter.formatCaseSummaryLine(
                true, "Scam", "0001a4", "15/10/2025", longTitle);

        assertTrue(line.contains("..."), "Long titles should be truncated with ellipses");
    }

    @Test
    void formatCaseSummaryLine_doesNotTruncateAtExactMaxWidth() {
        String exactTitle = "X".repeat(CaseFormatter.MAX_TITLE_WIDTH);
        String line = CaseFormatter.formatCaseSummaryLine(
                true, "Fraud", "0001a5", "16/10/2025", exactTitle);

        assertFalse(line.contains("..."), "Title at exact max width should not be truncated");
        assertTrue(line.contains(exactTitle));
    }

    @Test
    void formatCaseSummaryLine_doesNotTruncateBelowMaxWidth() {
        String shortTitle = "Short";
        String line = CaseFormatter.formatCaseSummaryLine(
                true, "Accident", "0001a6", "17/10/2025", shortTitle);

        assertFalse(line.contains("..."), "Title shorter than max width should not be truncated");
        assertTrue(line.contains(shortTitle));
    }

    @Test
    void formatCaseSummaryLine_handlesLongId() {
        String longId = "123456789ABCDEF";
        String line = CaseFormatter.formatCaseSummaryLine(
                false, "Fraud", longId, "16/10/2025", "Fake loan");

        // ID is longer than 6 chars, but should still appear
        assertTrue(line.contains(longId));
    }

    @Test
    void formatCaseSummaryLine_handlesLongDate() {
        String longDate = "16/10/2025-Extra";
        String line = CaseFormatter.formatCaseSummaryLine(
                false, "Accident", "0001a5", longDate, "Minor collision");

        // Date string is longer than 10 chars, but should still appear
        assertTrue(line.contains(longDate));
    }

    @Test
    void formatCaseSummaryLine_handlesNullTitle() {
        String line = CaseFormatter.formatCaseSummaryLine(
                true, "Theft", "0001a3", "14/10/2025", null);

        assertTrue(line.contains(expectedOpenString));
        assertTrue(line.contains("Theft"));
        assertTrue(line.contains("0001a3"));
        assertTrue(line.contains("14/10/2025"));
        // Title is null → truncateWithEllipses returns ""
        assertTrue(line.endsWith(""), "Null title should be empty");
    }

    @Test
    void formatCaseSummaryLine_handlesNullCategory() {
        String line = CaseFormatter.formatCaseSummaryLine(
                true, null, "0001a3", "14/10/2025", "Wallet theft");

        assertTrue(line.contains(expectedOpenString));
        // String.format will render null as "null"
        assertTrue(line.contains("null"));
        assertTrue(line.contains("0001a3"));
        assertTrue(line.contains("14/10/2025"));
        assertTrue(line.contains("Wallet theft"));
    }

    @Test
    void formatCaseSummaryLine_handlesNullId() {
        String line = CaseFormatter.formatCaseSummaryLine(
                true, "Theft", null, "14/10/2025", "Wallet theft");

        assertTrue(line.contains(expectedOpenString));
        assertTrue(line.contains("Theft"));
        assertTrue(line.contains("null")); // id is null
        assertTrue(line.contains("14/10/2025"));
        assertTrue(line.contains("Wallet theft"));
    }

    @Test
    void formatCaseSummaryLine_handlesNullDate() {
        String line = CaseFormatter.formatCaseSummaryLine(
                true, "Theft", "0001a3", null, "Wallet theft");

        assertTrue(line.contains(expectedOpenString));
        assertTrue(line.contains("Theft"));
        assertTrue(line.contains("0001a3"));
        assertTrue(line.contains("null")); // date is null
        assertTrue(line.contains("Wallet theft"));
    }

    @Test
    void formatCaseSummaryLine_handlesAllNulls() {
        String line = CaseFormatter.formatCaseSummaryLine(
                true, null, null, null, null);

        assertTrue(line.contains(expectedOpenString));
        // All other fields null → "null" or "" for title
        assertTrue(line.contains("null")); // category
        assertTrue(line.contains("null")); // id
        assertTrue(line.contains("null")); // date
        assertTrue(line.endsWith(""));     // title empty
    }

    // ----------- TESTS FOR FORMATTING THE CASE ID HEADER ----------- //

    @Test
    void formatCaseIDHeader_containsCaseId() {
        String header = CaseFormatter.formatCaseIDHeader("0001a5");

        // Just check that the important parts are present
        assertTrue(header.contains("CASE ID"));
        assertTrue(header.contains("0001a5"));
        assertTrue(header.startsWith("="));
        assertTrue(header.endsWith("="));
    }

    // ----------- TESTS FOR TRUNCATING WITH ELLIPSES ----------- //

    @Test
    void truncateWithEllipses_returnsEmptyForNull() {
        assertEquals("", CaseFormatter.truncateWithEllipses(null, 5));
    }

    @Test
    void truncateWithEllipses_returnsInputWhenShorterThanMax() {
        assertEquals("Hi", CaseFormatter.truncateWithEllipses("Hi", 5));
    }

    @Test
    void truncateWithEllipses_returnsInputWhenExactlyMax() {
        String exact = "Hello"; // length 5
        assertEquals("Hello", CaseFormatter.truncateWithEllipses(exact, 5));
    }

    @Test
    void truncateWithEllipses_truncatesWhenLongerThanMax() {
        String result = CaseFormatter.truncateWithEllipses("HelloWorld", 5);
        assertEquals("Hello...", result);
    }

    @Test
    void truncateWithEllipses_handlesZeroMaxLength() {
        // substring(0,0) + "..." → "..."
        assertEquals("...", CaseFormatter.truncateWithEllipses("Hello", 0));
        assertEquals("", CaseFormatter.truncateWithEllipses(null, 0));
    }

    @Test
    void truncateWithEllipses_returnsEllipsesForNegativeMaxLength() {
        // Any non-null input with negative max length should return "..."
        assertEquals("...", CaseFormatter.truncateWithEllipses("Hello", -1));
        assertEquals("...", CaseFormatter.truncateWithEllipses("HelloWorld", -10));

        // Null input still returns empty string, regardless of max length
        assertEquals("", CaseFormatter.truncateWithEllipses(null, -5));
    }

    @Test
    void truncateWithEllipses_handlesEmptyString() {
        // Empty string with positive max length should remain empty
        assertEquals("", CaseFormatter.truncateWithEllipses("", 5));

        // Empty string with max length exactly 0 or negative still returns "..."
        assertEquals("...", CaseFormatter.truncateWithEllipses("", 0));
        assertEquals("...", CaseFormatter.truncateWithEllipses("", -3));
    }

    // ----------- TESTS FOR ADDING WRAPPED FIELD IN MULTI-LINE PRINTING ----------- //

    @Test
    void addWrappedFieldForRead_addsWrappedFieldWhenValuePresent() {
        List<String> lines = new ArrayList<>();
        String value = "This is a short title";

        CaseFormatter.addWrappedFieldForRead(lines, "Title", value);

        assertFalse(lines.isEmpty(), "Lines should contain wrapped output");
        assertTrue(lines.get(0).contains("Title"), "First line should include the label");
        assertTrue(lines.get(0).contains("This is a short title"), "Value should appear in the output");
    }

    @Test
    void addWrappedFieldForVerbose_addsWrappedFieldWhenValuePresent() {
        List<String> lines = new ArrayList<>();
        String value = "This is a verbose description that might wrap across lines";

        CaseFormatter.addWrappedFieldForVerbose(lines, "Description", value);

        assertFalse(lines.isEmpty(), "Lines should contain wrapped output");
        assertTrue(lines.get(0).contains("Description"), "First line should include the label");
        assertTrue(lines.stream().anyMatch(l -> l.contains("verbose")),
                "Wrapped lines should include the value text");
    }

    @Test
    void addWrappedFieldForRead_handlesNullsGracefully() {
        List<String> lines = new ArrayList<>();

        // Null lines → should not throw
        assertDoesNotThrow(() -> CaseFormatter.addWrappedFieldForRead(null, "Label", "Value"));

        // Null label → should not throw
        assertDoesNotThrow(() -> CaseFormatter.addWrappedFieldForRead(new ArrayList<>(), null, "Value"));
    }

    @Test
    void addWrappedFieldForVerbose_handlesNullsGracefully() {
        List<String> lines = new ArrayList<>();

        CaseFormatter.addWrappedFieldForVerbose(lines, "Label", null);
        assertTrue(lines.isEmpty());

        CaseFormatter.addWrappedFieldForVerbose(lines, "Label", "");
        assertTrue(lines.isEmpty());

        assertDoesNotThrow(() -> CaseFormatter.addWrappedFieldForVerbose(null, "Label", "Value"));
        assertDoesNotThrow(() -> CaseFormatter.addWrappedFieldForVerbose(new ArrayList<>(), null, "Value"));
    }

    // ----------- TESTS FOR WRAPPING FIELDS IN MULTI-LINE PRINTING ----------- //

    // happy path
    @Test
    void wrapField_wrapsAndAlignsReadMode() {
        List<String> lines = CaseFormatter.wrapField(false, "Title",
                "This is a short title", CaseFormatter.MAX_DISPLAY_WIDTH_CHARACTERS);

        assertFalse(lines.isEmpty());
        assertTrue(lines.get(0).contains("Title"));
    }

    @Test
    void wrapField_wrapsLongValueAcrossMultipleLines() {
        String longValue = "word ".repeat(50); // definitely longer than 100 chars
        List<String> lines = CaseFormatter.wrapField(false, "Info", longValue,
                CaseFormatter.MAX_DISPLAY_WIDTH_CHARACTERS);

        assertTrue(lines.size() > 1, "Expected wrapping to produce multiple lines");
        assertTrue(lines.get(0).contains("Info"), "First line should contain the label prefix");
        // Subsequent lines should be indented (no label, just spaces)
        assertTrue(lines.get(1).startsWith(" ".repeat("Info".length() + 3))); // at least label width + " : "
    }

    @Test
    void wrapField_limitsVerboseLines() {
        String longValue = "word ".repeat(200); // very long
        List<String> lines = CaseFormatter.wrapField(true, "Info", longValue,
                CaseFormatter.MAX_DISPLAY_WIDTH_CHARACTERS);

        assertTrue(lines.size() <= CaseFormatter.MAX_VERBOSE_LINES_PER_FIELD,
                "Verbose mode should limit lines to MAX_VERBOSE_LINES_PER_FIELD");
        assertTrue(lines.get(lines.size() - 1).endsWith("..."),
                "Last line should end with ellipses when truncated");
    }

    @Test
    void wrapField_splitsLongSingleWord() {
        String longWord = "X".repeat(CaseFormatter.MAX_DISPLAY_WIDTH_CHARACTERS + 20);
        List<String> lines = CaseFormatter.wrapField(false, "Data", longWord,
                CaseFormatter.MAX_DISPLAY_WIDTH_CHARACTERS);

        assertTrue(lines.size() > 1, "Long single word should be split across lines");
        assertTrue(lines.get(0).endsWith("-"), "First chunk should end with a dash");
    }

    // ----------- TESTS FOR TRY ADDING WORD TO LINE ----------- //

    @Test
    void tryAddWord_doesNotWrapWhenWithinWidth() {
        List<String> lines = new ArrayList<>();
        StringBuilder line = new StringBuilder("Prefix: ");
        // Remaining width is large enough to hold "word"
        CaseFormatter.LineState state = CaseFormatter.tryAddWord(
                "word", line, 0, 20, "Prefix: ", lines);

        assertNotNull(state);
        // Word should be appended to the current line
        assertTrue(state.line().toString().contains("Prefix: word"));
        // No new line should have been added to lines
        assertTrue(lines.isEmpty(), "Lines list should remain empty when no wrap occurs");
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
