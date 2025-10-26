import seedu.sgsafe.utils.settings.Settings;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class SettingsTest {

    @Test
    void setInputDateFormat_validFormat_updatesInputDateFormat() {
        Settings.setInputDateFormat("yyyy-MM-dd");
        assertEquals("yyyy-MM-dd", Settings.getInputDateFormat());
    }

    @Test
    void setInputDateFormat_invalidFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Settings.setInputDateFormat("invalid-format"));
    }

    @Test
    void setInputDateFormat_emptyFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Settings.setInputDateFormat(""));
    }

    @Test
    void setOutputDateFormat_validFormat_updatesOutputDateFormat() {
        Settings.setOutputDateFormat("MM/dd/yyyy");
        assertEquals("MM/dd/yyyy", Settings.getOutputDateFormat());
    }

    @Test
    void setOutputDateFormat_invalidFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Settings.setOutputDateFormat("invalid-format"));
    }

    @Test
    void setOutputDateFormat_emptyFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Settings.setOutputDateFormat(""));
    }

    @Test
    void setInputDateFormat_formatWithLiteralText_updatesInputDateFormat() {
        Settings.setInputDateFormat("yyyy 'Year' MM 'Month' dd");
        assertEquals("yyyy 'Year' MM 'Month' dd", Settings.getInputDateFormat());
    }

    @Test
    void setOutputDateFormat_formatWithLiteralText_updatesOutputDateFormat() {
        Settings.setOutputDateFormat("dd 'of' MMMM yyyy");
        assertEquals("dd 'of' MMMM yyyy", Settings.getOutputDateFormat());
    }

    @Test
    void setInputDateFormat_formatWithIncompletePattern_throwsIllegalArgumentException() {
        assertThrows(DateTimeParseException.class, () -> Settings.setInputDateFormat("yyyy-MM"));
    }

    @Test
    void setOutputDateFormat_formatWithIncompletePattern_throwsIllegalArgumentException() {
        assertThrows(DateTimeParseException.class, () -> Settings.setOutputDateFormat("MM-dd"));
    }

    @Test
    void setOutputDateFormat_formatWithSpecialCharacters_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Settings.setOutputDateFormat("dd-MM-yyyy#"));
    }

    @Test
    void setInputDateFormat_validFormatWithDifferentLocale_updatesInputDateFormat() {
        Settings.setInputDateFormat("dd-MMM-yyyy");
        assertEquals("dd-MMM-yyyy", Settings.getInputDateFormat());
    }

    @Test
    void setOutputDateFormat_validFormatWithDifferentLocale_updatesOutputDateFormat() {
        Settings.setOutputDateFormat("MMM dd, yyyy");
        assertEquals("MMM dd, yyyy", Settings.getOutputDateFormat());
    }
}
