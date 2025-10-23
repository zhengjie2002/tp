package seedu.sgsafe.domain.casefiles.type;

import seedu.sgsafe.domain.casefiles.Case;

public class OthersCase extends Case {
    private String CustomCategory;

    public OthersCase(String id, String title, String date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.type = CaseType.OTHERS;
        this.category = CaseCategory.OTHERS;
        this.categoryString = "Others";
    }
}
