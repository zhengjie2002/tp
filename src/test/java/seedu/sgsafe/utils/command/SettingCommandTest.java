package seedu.sgsafe.utils.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.sgsafe.utils.settings.Settings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SettingCommandTest {

    @BeforeEach
    void setUp() {
        Settings.setInputDateFormat("yyyy-MM-dd");
        Settings.setOutputDateFormat("dd/MM/yyyy");
        Settings.setDateTimeFormat("dd-MM-yyyy HH:mm:ss");
    }

    @Test
    void execute_updatesDateInputFormatSuccessfully() {
        SettingCommand command = new SettingCommand(SettingType.DATEINPUT, "MM-dd-yyyy");
        command.execute();
        assertEquals("MM-dd-yyyy", Settings.getInputDateFormat());
    }

    @Test
    void execute_revertsDateInputFormatOnInvalidFormat() {
        SettingCommand command = new SettingCommand(SettingType.DATEINPUT, "invalid-format");
        command.execute();
        assertEquals("yyyy-MM-dd", Settings.getInputDateFormat());
    }

    @Test
    void execute_updatesDateOutputFormatSuccessfully() {
        SettingCommand command = new SettingCommand(SettingType.DATEOUTPUT, "MM/dd/yyyy");
        command.execute();
        assertEquals("MM/dd/yyyy", Settings.getOutputDateFormat());
    }

    @Test
    void execute_revertsDateOutputFormatOnInvalidFormat() {
        SettingCommand command = new SettingCommand(SettingType.DATEOUTPUT, "invalid-format");
        command.execute();
        assertEquals("dd/MM/yyyy", Settings.getOutputDateFormat());
    }

    @Test
    void execute_updatesTimestampOutputFormatSuccessfully() {
        SettingCommand command = new SettingCommand(SettingType.TIMESTAMPOUTPUT, "dd-MM-yyyy HH:mm:ss");
        command.execute();
        assertEquals("dd-MM-yyyy HH:mm:ss", Settings.getDateTimeFormat());
    }

    @Test
    void execute_revertsTimestampOutputFormatOnInvalidFormat() {
        SettingCommand command = new SettingCommand(SettingType.TIMESTAMPOUTPUT, "invalid-format");
        command.execute();
        assertEquals("dd-MM-yyyy HH:mm:ss", Settings.getDateTimeFormat());
    }

    @Test
    void setDateTimeFormat_validFormat_updatesDateTimeFormat() {
        Settings.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
        assertEquals("yyyy-MM-dd HH:mm:ss", Settings.getDateTimeFormat());
    }

    @Test
    void setDateTimeFormat_invalidFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Settings.setDateTimeFormat("invalid-format"));
    }

    @Test
    void setDateTimeFormat_emptyFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Settings.setDateTimeFormat(""));
    }

    @Test
    void setDateTimeFormat_formatWithLiteralText_updatesDateTimeFormat() {
        Settings.setDateTimeFormat("yyyy-MM-dd 'at' HH:mm:ss");
        assertEquals("yyyy-MM-dd 'at' HH:mm:ss", Settings.getDateTimeFormat());
    }

}
