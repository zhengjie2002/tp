package seedu.sgsafe.domain.casefiles.type.property;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;

public class ArsonCase extends PropertyCase {
    public ArsonCase(String id, String title, String date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.ARSON;
        this.categoryString = "Arson";
    }
}
