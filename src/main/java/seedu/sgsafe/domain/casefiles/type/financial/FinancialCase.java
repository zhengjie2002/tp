package seedu.sgsafe.domain.casefiles.type.financial;

import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.type.CaseType;

public abstract class FinancialCase extends Case {
    public FinancialCase(String id, String title, String date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.type = CaseType.FINANCIAL;
    }
}
