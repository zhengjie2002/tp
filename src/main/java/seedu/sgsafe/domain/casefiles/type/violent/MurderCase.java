package seedu.sgsafe.domain.casefiles.type.violent;

import java.util.List;
import java.util.Map;

import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.type.CaseCategory;
import seedu.sgsafe.utils.ui.Display;

import java.time.LocalDate;

public class MurderCase extends ViolentCase {
    private String weapon;
    private Integer numberOfVictims;

    public MurderCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.MURDER;
        this.categoryString = "Murder";
    }

    public String getWeapon() {
        return weapon;
    }

    public Integer getNumberOfVictims() {
        return numberOfVictims;
    }

    @Override
    public String[] getReadCaseDisplay() {
        List<String> displayList = getBaseDisplayLines();

        displayList.add("\t" + Case.formatLineNoTruncate("Weapon", this.weapon == null ? "" : this.weapon));
        displayList.add("\t" + Case.formatLineNoTruncate("Number of Victims",
                this.numberOfVictims == null ? "" : this.numberOfVictims.toString()));

        displayList.add("\t" + Display.formatIndentedText("Info :", getInfo(), 55));

        return displayList.toArray(new String[0]);
    }

    @Override
    public List<String> getValidEditFlags() {
        return List.of("title", "date", "info", "victim", "officer", "weapon", "number-of-victims");
    }

    @Override
    public void update(Map<String, Object> newValues) {
        super.update(newValues);
        if (newValues.containsKey("weapon")) {
            this.weapon = (String) newValues.get("weapon");
        }
        if (newValues.containsKey("number-of-victims") && newValues.get("number-of-victims") != null) {
            this.numberOfVictims = (Integer) newValues.get("number-of-victims");
        }
    }

    @Override
    public List<String> getAdditionalFields() {
        List<String> additionalFields = super.getAdditionalFields();
        additionalFields.add("weapon");
        additionalFields.add("number-of-victims");
        return additionalFields;
    }

    @Override
    public String toSaveString() {
        return super.toSaveString()
                + "|number-of-victims:" + (this.numberOfVictims  == null ? "" : this.numberOfVictims)
                + "|weapon:" + (this.weapon == null ? "" : this.weapon);
    }
}
