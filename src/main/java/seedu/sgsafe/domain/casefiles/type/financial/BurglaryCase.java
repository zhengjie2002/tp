package seedu.sgsafe.domain.casefiles.type.financial;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;

import java.time.LocalDate;

/**
 * Represents a case involving a burglary.
 */
public class BurglaryCase extends FinancialCase {
    public BurglaryCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.BURGLARY;
        this.categoryString = "Burglary";
    }
}
