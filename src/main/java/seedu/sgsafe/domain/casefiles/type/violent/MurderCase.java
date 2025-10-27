package seedu.sgsafe.domain.casefiles.type.violent;

import java.util.List;
import java.util.Map;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;
import seedu.sgsafe.utils.ui.Display;

public class MurderCase extends ViolentCase {
    private String weapon;
    private String numberOfVictims;

    public MurderCase(String id, String title, String date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.MURDER;
        this.categoryString = "Murder";
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
            "Weapon  : " + (this.weapon == null ? "" : this.weapon),
            "Number of Victims: " + (this.numberOfVictims == 0 ? "" : this.numberOfVictims),
            Display.formatIndentedText("Info    : ", this.getInfo(), 80)
        };
      
    public List<String> getValidEditFlags() {
        return List.of("title", "date", "info", "victim", "officer", "weapon", "number-of-victims");
    }

    @Override
    public void update(Map<String, String> newValues) {
        super.update(newValues);
        if (newValues.containsKey("weapon")) {
            this.weapon = newValues.get("weapon");
        }
        if (newValues.containsKey("number-of-victims")) {
            this.numberOfVictims = newValues.get("numberOfVictims");
        }
    }
}
