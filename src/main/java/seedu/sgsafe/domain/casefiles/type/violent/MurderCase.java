package seedu.sgsafe.domain.casefiles.type.violent;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;
import java.time.LocalDate;

/**
 * Represents a case involving murder.
 */
public class MurderCase extends ViolentCase {
    public MurderCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.MURDER;
        this.categoryString = "Murder";
    }
}
