package seedu.sgsafe.domain.casefiles.type;

import seedu.sgsafe.domain.casefiles.Case;

import java.time.LocalDate;

/**
 * Represents a case that does not fall under any predefined category.
 * <p>
 * The {@code OthersCase} class is used for general or uncategorized cases
 * that do not match specific domains such as Financial, Traffic, or Violent cases.
 * It inherits all common fields and behaviors from {@link Case} and sets
 * its category and type to {@code OTHERS}.
 */
public class OthersCase extends Case {
    private String customCategory;

    public OthersCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.type = CaseType.OTHERS;
        this.category = CaseCategory.OTHERS;
        this.categoryString = "Others";
    }
}
