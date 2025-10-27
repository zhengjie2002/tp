package seedu.sgsafe.utils.command;

import seedu.sgsafe.utils.ui.Display;

/**
 * Displays manual for using all available commands.
 * <p>
 * The {@code HelpCommand} prints a summary of every command keyword, its syntax,
 * and example usages to assist the user in navigating SGSafe.
 */
public class HelpCommand extends Command {
    private static final String HELP_TEXT =
            "\n" +
                    "\tSGSAFE HELP MENU\n" +
                    "\tHere is a list of supported commands and their formats. Use this as a quick reference to add,\n" +
                    "\tmanage and view cases.\n" +
                    "\t_________________________________________________________________________________________________\n" +
                    "\n" +
                    "\tADD — Create a new case\n" +
                    "\n" +
                    "\tDescription:\n" +
                    "\t\tAdds a new case record to the system. Each case must belong to one of the predefined\n " +
                    "\t\tcategories (e.g. theft, arson, accident, etc.) and include basic information such as\n" +
                    "\t\t title, date, and information.\n" +
                    "\n" +
                    "\tUsage:\n" +
                    "\t\tadd --category <CATEGORY> --title <TITLE> --date <DATE> --info <INFO>\n" +
                    "\t\t\t[--victim <NAME>] [--officer <NAME>]\n" +
                    "\n" +
                    "\tExamples:\n" +
                    "\t\tadd --category robbery --title Bank Heist --date 2025-05-04 --info Masked suspects escaped.\n" +
                    "\t\tadd --category arson --title Factory Fire --date 2024-12-10 --info Caused by electrical\n" +
                    "\t\tmalfunction. --officer Jane Tan\n" +
                    "\n" +
                    "\tValid categories include:\n" +
                    "\t\tfinancial: scam, theft\n" +
                    "\t\tproperty: arson, vandalism\n" +
                    "\t\tsexual: rape, voyeurism\n" +
                    "\t\ttraffic: accident, speeding\n" +
                    "\t\tviolent: assault, murder, robbery\n" +
                    "\t\tothers: others\n" +
                    "\n" +
                    "\t\tExample:\n" +
                    "\t\t\tadd --category theft --title Shoplifting --date 2024-05-01 --info Stolen items from store.\n" +
                    "\n" +
                    "\tNotes:\n" +
                    "\t\t• The case ID is automatically generated.\n" +
                    "\t\t• Flags (--flag) can be entered in any order, but must always be followed right after by\n" +
                    "\t\ttheir values <VALUE>.\n" +
                    "\t\t• Parameters in [brackets] are optional.\n" +
                    "\t_________________________________________________________________________________________________\n" +
                    "\n" +
                    "\tLIST — View existing cases\n" +
                    "\n" +
                    "\tDescription:\n" +
                    "\t\tDisplays case files in the system. You can filter by case status (open, closed, all) and\n" +
                    "\t\tcontrol output detail using the mode flag.\n" +
                    "\n" +
                    "\tUsage:\n" +
                    "\t\tlist\n" +
                    "\t\tlist [--status <open|closed|all>] [--mode <summary|verbose>]\n" +
                    "\n" +
                    "\tExamples:\n" +
                    "\t\tlist\n" +
                    "\t\t\tLists all cases in summary mode (default).\n" +
                    "\t\tlist --status closed\n" +
                    "\t\t\tLists only closed cases in summary mode.\n" +
                    "\t\tlist --status all --mode verbose\n" +
                    "\t\t\tShows all cases with full details.\n" +
                    "\n" +
                    "\tFlags:\n" +
                    "\t\t--status <value>    Filters which cases to show.\n" +
                    "\t\t\topen   → Only open cases\n" +
                    "\t\t\tclosed → Only closed cases\n" +
                    "\t\t\tall    → All cases (default)\n" +
                    "\n" +
                    "\t\t--mode <value>    Controls display format.\n" +
                    "\t\t\tsummary → One line per case (default)\n" +
                    "\t\t\tverbose → Detailed multi-line view\n" +
                    "\t_________________________________________________________________________________________________\n" +
                    "\n" +
                    "\tEDIT — Modify an existing case\n" +
                    "\n" +
                    "\tDescription:\n" +
                    "\t\tEdits the details of an existing case by specifying the case ID and one or more flags with\n" +
                    "\t\tnew values.\n" +
                    "\t\tYou can also view editable fields first.\n" +
                    "\n" +
                    "\tUsage:\n" +
                    "\t\tedit <CASE_ID>\n" +
                    "\t\tedit <CASE_ID> --flag <VALUE> [--flag <VALUE> ...]\n" +
                    "\n" +
                    "\tExamples:\n" +
                    "\t\tedit 000001\n" +
                    "\t\t\tDisplays a list of editable fields for the case.\n" +
                    "\t\tedit 000001 --title Updated Title --date 2024-02-10\n" +
                    "\t\t\tUpdates title and date for case 000001.\n" +
                    "\n" +
                    "\tNotes:\n" +
                    "\t\t• All flags must match the case type’s valid editable fields.\n" +
                    "\t\t• Invalid flags will cancel the update and show an error message.\n" +
                    "\t\t• Use 'list' first to find a case ID.\n" +
                    "\t_________________________________________________________________________________________________\n" +
                    "\n" +
                    "\tCLOSE — Mark a case as closed\n" +
                    "\n" +
                    "\tDescription:\n" +
                    "\t\tChanges the status of an existing open case to 'closed'.\n" +
                    "\n" +
                    "\tUsage:\n" +
                    "\t\tclose <CASE_ID>\n" +
                    "\n" +
                    "\tExamples:\n" +
                    "\t\tclose 000002\n" +
                    "\t_________________________________________________________________________________________________\n" +
                    "\n" +
                    "\tOPEN — Reopen a previously closed case\n" +
                    "\n" +
                    "\tDescription:\n" +
                    "\t\tReopens a case that was previously marked as closed. The case becomes visible again in the\n" +
                    "\t\topen mode when listing.\n" +
                    "\n" +
                    "\tUsage:\n" +
                    "\t\topen <CASE_ID>\n" +
                    "\n" +
                    "\tExamples:\n" +
                    "\t\topen 000002\n" +
                    "\t_________________________________________________________________________________________________\n" +
                    "\n" +
                    "\tDELETE — Remove a case permanently\n" +
                    "\n" +
                    "\tDescription:\n" +
                    "\t\tDeletes a case from the system. Deleted cases cannot be recovered. Make sure to double-check\n" +
                    "\t\tbefore using this command.\n" +
                    "\n" +
                    "\tUsage:\n" +
                    "\t\tdelete <CASE_ID>\n" +
                    "\n" +
                    "\tExamples:\n" +
                    "\t\tdelete 000004\n" +
                    "\t_________________________________________________________________________________________________\n" +
                    "\n" +
                    "\tHELP — Display this help message\n" +
                    "\n" +
                    "\tDescription:\n" +
                    "\t\tShows a list of all available commands, their formats, and usage examples.\n" +
                    "\n" +
                    "\tUsage:\n" +
                    "\t\thelp\n" +
                    "\t_________________________________________________________________________________________________\n" +
                    "\n" +
                    "\tBYE — Exit the program\n" +
                    "\n" +
                    "\tDescription:\n" +
                    "\t\tTerminates the application safely. No further input will be accepted.\n" +
                    "\n" +
                    "\tUsage:\n" +
                    "\t\tbye\n" +
                    "\t_________________________________________________________________________________________________\n" +
                    "\tEnd of help.\n";





    public HelpCommand() {
        this.commandType = CommandType.HELP;
    }

    @Override
    public void execute() {
        Display.printMessage(HELP_TEXT);
    }
}
