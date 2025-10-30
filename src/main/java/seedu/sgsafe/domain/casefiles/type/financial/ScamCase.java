package seedu.sgsafe.domain.casefiles.type.financial;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;

import java.time.LocalDate;

/**
 * Represents a case involving a scam.
 */
public class ScamCase extends FinancialCase {
    public ScamCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.SCAM;
        this.categoryString = "Scam";
    }
}
