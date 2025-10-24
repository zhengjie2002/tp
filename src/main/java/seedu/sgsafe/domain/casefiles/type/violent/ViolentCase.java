package seedu.sgsafe.domain.casefiles.type.violent;

import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.type.CaseType;

public abstract class ViolentCase extends Case {
    public ViolentCase(String id, String title, String date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.type = CaseType.VIOLENT;
    }
}
