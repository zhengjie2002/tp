package seedu.sgsafe.domain.casefiles.type.violent;

import java.util.List;
import java.util.Map;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;

import java.time.LocalDate;

public class MurderCase extends ViolentCase {
    private String weapon;
    private String numberOfVictims;

    public MurderCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.MURDER;
        this.categoryString = "Murder";
    }

    @Override
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
