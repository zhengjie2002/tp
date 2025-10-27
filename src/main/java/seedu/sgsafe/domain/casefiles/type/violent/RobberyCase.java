package seedu.sgsafe.domain.casefiles.type.violent;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;

import java.time.LocalDate;

public class RobberyCase extends ViolentCase{
    public RobberyCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.ROBBERY;
        this.categoryString = "Robbery";
    }
}
