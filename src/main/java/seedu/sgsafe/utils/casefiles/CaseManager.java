package seedu.sgsafe.utils.casefiles;
import java.util.ArrayList;

import seedu.sgsafe.utils.ui.Display;

public class CaseManager {
    public static ArrayList<Case> caseList = new ArrayList<>();

    public static void listCases() {
        int caseListSize = caseList.size();
        String[] caseDescriptions = new String[caseListSize];
        Case currentCase;
        for (int i = 0; i < caseListSize; i++) {
            currentCase = caseList.get(i);
            caseDescriptions[i] = (i + 1) + ". " + currentCase.getDisplayLine();
        }
        Display.printMessage(caseDescriptions);
    }
}
