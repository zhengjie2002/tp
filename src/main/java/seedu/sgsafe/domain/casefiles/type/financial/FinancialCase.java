package seedu.sgsafe.domain.casefiles.type.financial;

import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.CaseFormatter;
import seedu.sgsafe.domain.casefiles.type.CaseType;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public abstract class FinancialCase extends Case {
    /** The estimated financial value lost by the victim. */
    private Integer financialValue;

    public FinancialCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.type = CaseType.FINANCIAL;
    }

    public Integer getFinancialValue() {
        return financialValue;
    }

    @Override
    public String[] getReadCaseDisplay() {
        List<String> displayList = getBaseDisplayLines();

        CaseFormatter.addWrappedFieldForRead(displayList, "Financial Value", String.valueOf(this.financialValue));

        CaseFormatter.addWrappedFieldForRead(displayList, "Info", getInfo());

        return displayList.toArray(new String[0]);
    }

    @Override
    public List<String> getValidEditFlags() {
        return List.of("title", "date", "info", "victim", "officer",
                "financial-value");
    }

    @Override
    public void update(Map<String, Object> newValues) {
        super.update(newValues);
        if (newValues.containsKey("financial-value") && newValues.get("financial-value") != null) {
            this.financialValue = (Integer) newValues.get("financial-value");
        }
    }

    @Override
    public List<String> getAdditionalFields() {
        List<String> additionalFields = super.getAdditionalFields();
        additionalFields.add("financial-value");
        return additionalFields;
    }

    @Override
    public String toSaveString() {
        return super.toSaveString()
                + "|financial-value:" + (this.financialValue == null ? "" : this.financialValue);
    }
}
