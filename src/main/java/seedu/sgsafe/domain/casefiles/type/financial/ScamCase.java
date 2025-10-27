package seedu.sgsafe.domain.casefiles.type.financial;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;

public class ScamCase extends FinancialCase {
    public ScamCase(String id, String title, String date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.SCAM;
        this.categoryString = "Scam";
    }
}
