package seedu.sgsafe.domain.casefiles.type.sexual;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;

import java.time.LocalDate;

/**
 * Represents a case involving rape.
 */
public class RapeCase extends SexualCase {
    public RapeCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.RAPE;
        this.categoryString = "Rape";
    }
}
