package seedu.sgsafe.domain.casefiles;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;

public class OthersCase extends Case {
    private String CustomCategory;

    public OthersCase(String id, String title, String date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.OTHERS;
    }
}
