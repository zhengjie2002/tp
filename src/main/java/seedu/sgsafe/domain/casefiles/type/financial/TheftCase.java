package seedu.sgsafe.domain.casefiles;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;

public class TheftCase extends Case {
    private String stolenObject;

    public TheftCase(String id, String title, String date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.THEFT;
    }
}
