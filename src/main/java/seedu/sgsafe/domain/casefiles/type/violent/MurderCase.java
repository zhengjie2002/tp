package seedu.sgsafe.domain.casefiles.type.violent;

import java.util.List;
import java.util.Map;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;

import java.time.LocalDate;

public class MurderCase extends ViolentCase {
    private String weapon;
    private int numberOfVictims;

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
    public void update(Map<String, Object> newValues) {
        super.update(newValues);
        if (newValues.containsKey("weapon")) {
            this.weapon = (String) newValues.get("weapon");
        }
        if (newValues.containsKey("number-of-victims") && newValues.get("number-of-victims") != null) {
            this.numberOfVictims = (int) newValues.get("number-of-victims");
        }
    }
}
