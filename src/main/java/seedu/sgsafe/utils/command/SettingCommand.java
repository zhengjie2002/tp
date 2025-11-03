package seedu.sgsafe.utils.command;

import seedu.sgsafe.utils.settings.Settings;
import seedu.sgsafe.utils.ui.Display;

import java.util.function.Consumer;

/**
 * Represents a command to modify application settings.
 * Currently, supports changing date input and output formats.
 */
public class SettingCommand extends Command {

    private static final String SUCCESSFUL_UPDATE_MESSAGE = " successfully updated to: ";

    private static final String INVALID_FORMAT_MESSAGE = "Invalid date format. Reverted to previous format: ";

    // Attribute to hold the proposed new date format
    private final String newDateFormat;

    // Attribute to specify which setting is being changed
    private final SettingType settingType;

    /**
     * Constructs a SettingCommand with the specified setting type and new date format.
     *
     * @param settingType   The type of setting to modify (e.g., DATEINPUT, DATEOUTPUT)
     * @param newDateFormat The new date format string to apply
     */
    public SettingCommand(SettingType settingType, String newDateFormat) {
        assert newDateFormat != null;
        this.commandType = CommandType.SETTING;
        this.newDateFormat = newDateFormat;
        this.settingType = settingType;
    }

    public String getNewDateFormat() {
        return newDateFormat;
    }

    public SettingType getSettingType() {
        return settingType;
    }

    /**
     * Executes the setting command to update the specified date format.
     * Determines which date format setting to update based on the setting type,
     * then delegates to the updateDateFormat helper method.
     * If an invalid format is provided, the previous format is automatically restored.
     */
    @Override
    public void execute() {
        if (settingType == SettingType.DATEINPUT) {
            updateDateFormat(
                    Settings.getInputDateFormat(),
                    Settings::setInputDateFormat,
                    "Date input format"
            );
        } else if (settingType == SettingType.DATEOUTPUT) {
            updateDateFormat(
                    Settings.getOutputDateFormat(),
                    Settings::setOutputDateFormat,
                    "Date output format"
            );
        } else if(settingType == SettingType.TIMESTAMPOUTPUT) {
            updateDateFormat(
                    Settings.getDateTimeFormat(),
                    Settings::setDateTimeFormat,
                    "Timestamp output format"
            );
        }
    }

    /**
     * Updates a date format setting with rollback on error.
     *
     * @param previousFormat The current format to rollback to if update fails
     * @param setter         The method reference to set the new format
     * @param settingName    The human-readable name of the setting
     */
    private void updateDateFormat(String previousFormat,
                                  Consumer<String> setter,
                                  String settingName) {
        try {
            setter.accept(newDateFormat);
            Display.printMessage(settingName + SUCCESSFUL_UPDATE_MESSAGE + newDateFormat);
        } catch (Exception e) {
            setter.accept(previousFormat);
            Display.printMessage(INVALID_FORMAT_MESSAGE + previousFormat);
        }
    }
}
