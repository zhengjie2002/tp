package seedu.sgsafe.domain.casefiles.type.financial;

import java.util.List;
import java.util.Map;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;
import seedu.sgsafe.utils.ui.Display;

import java.time.LocalDate;

public class TheftCase extends FinancialCase {
    private String stolenObject;

    public TheftCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.THEFT;
        this.categoryString = "Theft";
    }

    public String getStolenObject () {
        return stolenObject;
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
    public List<String> getValidEditFlags() {
        return List.of("title", "date", "info", "victim", "officer", "stolen-object");
    }

    @Override
    public void update(Map<String, Object> newValues) {
        super.update(newValues);
        if (newValues.containsKey("stolen-object")) {
            this.stolenObject = (String) newValues.get("stolen-object");
        }
    }

    @Override
    public List<String> getAdditionalFields() {
        List<String> additionalFields = super.getAdditionalFields();
        additionalFields.add("stolen-object");
        return additionalFields;
    }

    @Override
    public String toSaveString() {
        return super.toSaveString()
                + "|stolen-object:" + (this.stolenObject == null ? "" : this.stolenObject);
    }
}
