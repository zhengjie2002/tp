package seedu.sgsafe.domain.casefiles.type.financial;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;
import seedu.sgsafe.utils.ui.Display;

public class TheftCase extends FinancialCase {
    private String stolenObject;

    public TheftCase(String id, String title, String date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.THEFT;
        this.categoryString = "Theft";
    }

    @Override
    public String[] getReadCaseDisplay() {
        String header = "======== CASE ID " + this.getId() + " ========";
        String statusLine = "Status  : " + (this.isOpen() ? "Open" : "Closed");

        return new String[] {
            header,
            statusLine,
            "Title   : " + (this.getTitle() == null ? "" : this.getTitle()),
            "Category: " + (this.getCategory() == null ? "" : this.getCategory()),
            "Date    : " + (this.getDate() == null ? "" : this.getDate()),
            "Victim  : " + (this.getVictim() == null ? "" : this.getVictim()),
            "Officer : " + (this.getOfficer() == null ? "" : this.getOfficer()),
            "Stolen Object : " + (this.stolenObject == null ? "" : this.stolenObject),
            Display.formatIndentedText("Info    : ", this.getInfo(), 80)
        };
    }
}
