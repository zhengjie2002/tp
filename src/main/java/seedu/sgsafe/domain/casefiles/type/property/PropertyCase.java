package seedu.sgsafe.domain.casefiles.type.property;

import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.type.CaseType;

import java.time.LocalDate;

/**
 * Represents a case under the Financial type.
 * <p>
 * This class serves as an abstract base for financial-related cases
 * such as {@link ArsonCase} and {@link VandalismCase}.
 */
public abstract class PropertyCase extends Case {
    public PropertyCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.type = CaseType.PROPERTY;
    }
}
