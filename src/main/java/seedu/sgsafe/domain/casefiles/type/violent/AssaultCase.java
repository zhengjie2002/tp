package seedu.sgsafe.domain.casefiles.type.violent;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;

public class AssaultCase extends ViolentCase{
    public AssaultCase(String id, String title, String date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.ASSAULT;
        this.categoryString = "Assault";
    }
}
