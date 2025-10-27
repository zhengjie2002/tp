package seedu.sgsafe.domain.casefiles;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.sgsafe.domain.casefiles.type.OthersCase;
import java.time.LocalDate;

public class CaseTest {

    private static final int WIDTH = 100;

    @Test
    void testTruncate_nullInput() {
        OthersCase c = new OthersCase("000001", null, null, null, null, null);
        assertEquals("", c.truncate(null));
    }

    @Test
    void testTruncate_shortInput() {
        OthersCase c = new OthersCase("000001", null, null, null, null, null);
        assertEquals("Short", c.truncate("Short"));
    }

    @Test
    void testTruncate_longInput() {
        OthersCase c = new OthersCase("000001", null, null, null, null, null);
        String input = "X".repeat(150);
        String result = c.truncate(input);
        assertTrue(result.endsWith("..."));
        assertEquals(103, result.length()); // 100 + "..."
    }

    @Test
    void testFormatPrefix_alignment() {
        String result = Case.formatPrefix("Title");
        assertEquals("Title     : ", result);
        assertEquals(12, result.length()); // 10 + ": "
    }

    @Test
    void testWrapField_singleLine() {
        List<String> lines = Case.wrapField("Info", "Short description", WIDTH);
        assertEquals(1, lines.size());
        assertTrue(lines.get(0).startsWith("Info      : "));
    }

    @Test
    void testWrapField_multiLine() {
        String value = "This is a long 103 character description that should wrap across " +
                        "multiple lines for better readability.";
        List<String> lines = Case.wrapField("Info", value, WIDTH);
        assertTrue(lines.size() > 1);
        assertTrue(lines.get(1).startsWith(" ".repeat(12)));
    }

    @Test
    void testWrapField_longUnbreakableWord() {
        String value = "X".repeat(WIDTH + 20);
        List<String> lines = Case.wrapField("Info", value, WIDTH);
        assertTrue(lines.size() > 1);
        assertTrue(lines.get(0).contains("-"));
    }

    @Test
    void testGetDisplayLine_formatting() {
        OthersCase c = new OthersCase("000abc", "Robbery at DBS", LocalDate.of(2025, 10, 14), "Info", "Alice", "Tan");
        String line = c.getDisplayLine();
        assertTrue(line.startsWith("[Open]"));
        assertTrue(line.contains("Robbery"));
        assertTrue(line.contains("000abc"));
    }

    @Test
    void testGetMultiLineVerboseDisplay_containsAllFields() {
        OthersCase c = new OthersCase("000abc", "Robbery at DBS", LocalDate.of(2025, 10, 14),
                "Suspect fled toward MRT", "Alice", "Tan");
        String[] lines = c.getMultiLineVerboseDisplay();
        String joined = String.join("\n", lines);
        assertTrue(joined.contains("Title"));
        assertTrue(joined.contains("Info"));
        assertTrue(joined.contains("Victim"));
        assertTrue(joined.contains("Officer"));
        assertTrue(joined.contains("Created at"));
    }

    @Test
    void wrapField_truncatesAfterMaxLines() {
        String label = "Info";
        String value = "Word ".repeat(100); // forces > 5 lines
        List<String> lines = Case.wrapField(label, value, WIDTH);

        assertEquals(Case.MAX_VERBOSE_LINES_PER_FIELD, lines.size());
        assertTrue(lines.get(lines.size() - 1).endsWith("..."));
    }

    @Test
    void wrapField_emptyValue_returnsPrefixOnly() {
        String label = "Info";
        String value = "";
        List<String> lines = Case.wrapField(label, value, WIDTH);

        assertEquals(1, lines.size());
        assertEquals("Info      : ", lines.get(0));
    }

}
