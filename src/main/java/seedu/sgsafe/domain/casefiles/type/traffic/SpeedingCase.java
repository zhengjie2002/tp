package seedu.sgsafe.domain.casefiles.type.traffic;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;

import java.time.LocalDate;

public class SpeedingCase extends TrafficCase {
    private int speedLimit;
    private int exceededSpeed;

    public SpeedingCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.SPEEDING;
        this.categoryString = "Speeding";
    }
}
