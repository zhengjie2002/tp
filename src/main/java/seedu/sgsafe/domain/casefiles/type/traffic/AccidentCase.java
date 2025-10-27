package seedu.sgsafe.domain.casefiles.type.traffic;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;

public class AccidentCase extends TrafficCase{
    public AccidentCase(String id, String title, String date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.ACCIDENT;
        this.categoryString = "Traffic accident";
    }
}
