package seedu.sgsafe.domain.casefiles.type;

import seedu.sgsafe.domain.casefiles.Case;

import java.time.LocalDate;

public class OthersCase extends Case {
    private String customCategory;

    public OthersCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.type = CaseType.OTHERS;
        this.category = CaseCategory.OTHERS;
        this.categoryString = "Others";
    }
}
