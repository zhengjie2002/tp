package seedu.sgsafe.utils.command;

import seedu.sgsafe.utils.ui.Display;

public class ByeCommand extends Command {

    public ByeCommand() {
        this.commandType = CommandType.BYE;
    }

    @Override
    public void execute() {
        Display.printGoodbyeMessage();
        System.exit(0);
    }
}
