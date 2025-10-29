package seedu.sgsafe.domain.casefiles.type.financial;

import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.type.CaseType;

import java.time.LocalDate;

/**
 * Represents a case under the Financial type.
 * <p>
 * This class serves as an abstract base for financial-related cases
 * such as {@link BurglaryCase}, {@link ScamCase} and {@link TheftCase}.
 */
public abstract class FinancialCase extends Case {
    public FinancialCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.type = CaseType.FINANCIAL;
    }
}
