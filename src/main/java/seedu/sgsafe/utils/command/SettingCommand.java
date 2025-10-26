package seedu.sgsafe.utils.command;

import seedu.sgsafe.utils.settings.Settings;
import seedu.sgsafe.utils.ui.Display;

public class SettingCommand extends Command {

    // Attribute to hold the proposed new date format
    private final String newDateFormat;

    private final SettingType settingType;

    public SettingCommand(SettingType settingType, String newDateFormat) {
        assert newDateFormat != null;
        this.commandType = CommandType.SETTING;
        this.newDateFormat = newDateFormat;
        this.settingType = settingType;
    }

    @Override
    public void execute() {
        if (settingType == SettingType.DATEINPUT) {
            // Variable to hold the previous date format for rollback if needed
            String previousDateFormat = Settings.getInputDateFormat();
            try {
                Settings.setInputDateFormat(newDateFormat);
                Display.printMessage("Date input format updated to: " + newDateFormat);
            } catch (IllegalArgumentException e) {
                // Rollback to previous date format
                Settings.setInputDateFormat(previousDateFormat);
                Display.printMessage("Invalid date format. Reverted to previous format: " + previousDateFormat);
            }
        } else if (settingType == SettingType.DATEOUTPUT) {
            // Variable to hold the previous date format for rollback if needed
            String previousDateFormat = Settings.getOutputDateFormat();
            try {
                Settings.setOutputDateFormat(newDateFormat);
                Display.printMessage("Date output format updated to: " + newDateFormat);
            } catch (IllegalArgumentException e) {
                // Rollback to previous date format
                Settings.setOutputDateFormat(previousDateFormat);
                Display.printMessage("Invalid date format. Reverted to previous format: " + previousDateFormat);
            }
        }
    }
}
