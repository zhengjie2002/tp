package seedu.sgsafe.domain.casefiles.type.violent;

import java.util.List;
import java.util.Map;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;
import seedu.sgsafe.domain.casefiles.CaseFormatter;

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

    //@@author shennontay
    @Override
    public String[] getReadCaseDisplay() {
        List<String> displayList = getBaseDisplayLines();

        CaseFormatter.addWrappedFieldForRead(displayList, "Weapon", this.weapon);
        CaseFormatter.addWrappedFieldForRead(
                displayList, "Number of Victims", String.valueOf(this.numberOfVictims));

        CaseFormatter.addWrappedFieldForRead(displayList, "Info", getInfo());

        return displayList.toArray(new String[0]);
    }
    //@@author

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
