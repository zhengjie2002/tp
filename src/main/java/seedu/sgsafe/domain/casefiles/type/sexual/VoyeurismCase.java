package seedu.sgsafe.domain.casefiles.type.sexual;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;

public class VoyeurismCase extends SexualCase {
    public VoyeurismCase(String id, String title, String date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.VOYEURISM;
        this.categoryString = "Voyeurism";
    }
}
