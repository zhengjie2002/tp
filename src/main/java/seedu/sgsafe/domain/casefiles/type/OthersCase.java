package seedu.sgsafe.domain.casefiles.type;

import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.CaseFormatter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    public String getCustomCategory() {
        return customCategory;
    }


    @Override
    public String[] getReadCaseDisplay() {
        // Get base display lines from parent Case class
        List<String> displayList = getBaseDisplayLines();

        // Add TrafficCase specific fields
        CaseFormatter.addWrappedFieldForRead(displayList, "Custom Category", this.customCategory);

        // Display info
        CaseFormatter.addWrappedFieldForRead(displayList, "Info", getInfo());

        return displayList.toArray(new String[0]);
    }

    @Override
    public List<String> getValidEditFlags() {
        return List.of("title", "date", "info", "victim", "officer",
                "custom-category");
    }

    @Override
    public void update(Map<String, Object> newValues) {
        super.update(newValues);
        if (newValues.containsKey("custom-category")) {
            this.customCategory = (String) newValues.get("custom-category");
        }
    }

    @Override
    public List<String> getAdditionalFields() {
        List<String> additionalFields = super.getAdditionalFields();
        additionalFields.add("custom-category");
        return additionalFields;
    }

    @Override
    public String toSaveString() {
        return super.toSaveString()
                + "|custom-category:" + (this.customCategory  == null ? "" : this.customCategory);
    }
}
