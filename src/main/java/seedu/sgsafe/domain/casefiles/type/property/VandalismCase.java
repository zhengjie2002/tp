package seedu.sgsafe.domain.casefiles.type.property;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;

import java.time.LocalDate;

public class VandalismCase extends PropertyCase {
    public VandalismCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.VANDALISM;
        this.categoryString = "Vandalism";
    }
}
