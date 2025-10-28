package seedu.sgsafe.domain.casefiles.type.traffic;

import java.util.List;
import java.util.Map;

import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.type.CaseCategory;
import seedu.sgsafe.utils.ui.Display;

import java.time.LocalDate;

public class SpeedingCase extends TrafficCase {
    private Integer speedLimit;
    private Integer exceededSpeed;

    public SpeedingCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.SPEEDING;
        this.categoryString = "Speeding";
    }

    public Integer getSpeedLimit() {
        return speedLimit;
    }

    public Integer getExceededSpeed() {
        return exceededSpeed;
    }

    @Override
    public String[] getReadCaseDisplay() {
        List<String> displayList = getBaseDisplayLines();

        displayList.add(Case.formatLineNoTruncate("Vehicle Type", this.getVehicleType()));
        displayList.add(Case.formatLineNoTruncate("Vehicle Plate", this.getVehiclePlate()));
        displayList.add(Case.formatLineNoTruncate("Road Name", this.getRoadName()));
        displayList.add(Case.formatLineNoTruncate("Speed Limit", this.speedLimit));
        displayList.add(Case.formatLineNoTruncate("Exceeded Speed", this.exceededSpeed));

        displayList.add(Display.formatIndentedText("Info :", getInfo()));

        return displayList.toArray(new String[0]);
    }

    @Override
    public List<String> getValidEditFlags() {
        return List.of("title", "date", "info", "victim", "officer",
                "vehicle-type", "vehicle-plate", "road-name",
                "speed-limit", "exceeded-speed");
    }

    @Override
    public void update(Map<String, Object> newValues) {
        super.update(newValues);
        if (newValues.containsKey("speed-limit") && newValues.get("speed-limit") != null) {
            this.speedLimit = (Integer) newValues.get("speed-limit");
        }
        if (newValues.containsKey("exceeded-speed") && newValues.get("exceeded-speed") != null) {
            this.exceededSpeed = (Integer) newValues.get("exceeded-speed");
        }
    }

    @Override
    public List<String> getAdditionalFields() {
        List<String> additionalFields = super.getAdditionalFields();
        additionalFields.add("speed-limit");
        additionalFields.add("exceeded-speed");
        return additionalFields;
    }

    @Override
    public String toSaveString() {
        return super.toSaveString()
                + "|speed-limit:" + (this.speedLimit == null ? "" : this.speedLimit)
                + "|exceeded-speed:" + (this.exceededSpeed == null ? "" : this.exceededSpeed);
    }
}
