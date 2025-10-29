package seedu.sgsafe.domain.casefiles.type.sexual;

import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.type.CaseType;

import java.time.LocalDate;

/**
 * Represents a case under the Financial type.
 * <p>
 * This class serves as an abstract base for financial-related cases
 * such as {@link RapeCase} and {@link VoyeurismCase}
 */
public abstract class SexualCase extends Case {
    public SexualCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.type = CaseType.SEXUAL;
    }
}
