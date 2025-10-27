package seedu.sgsafe.domain.casefiles.type.financial;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;

public class BurglaryCase extends FinancialCase {
    public BurglaryCase(String id, String title, String date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.BURGLARY;
        this.categoryString = "Burglary";
    }
}
