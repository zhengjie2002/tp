package seedu.sgsafe.domain.casefiles.type.property;

import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.type.CaseType;

public abstract class PropertyCase extends Case {
    public PropertyCase(String id, String title, String date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.type = CaseType.PROPERTY;
    }
}
