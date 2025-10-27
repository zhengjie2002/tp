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

        displayList.add("\t" + Case.formatLineNoTruncate("Vehicle Type",
                this.getVehicleType() == null ? "" : this.getVehicleType()));
        displayList.add("\t" + Case.formatLineNoTruncate("Vehicle Plate",
                this.getVehiclePlate() == null ? "" : this.getVehiclePlate()));
        displayList.add("\t" + Case.formatLineNoTruncate("Road Name",
                this.getRoadName() == null ? "" : this.getRoadName()));
        displayList.add("\t" + Case.formatLineNoTruncate("Speed Limit",
                this.speedLimit == null ? "" : this.speedLimit.toString()));
        displayList.add("\t" + Case.formatLineNoTruncate("Exceeded Speed",
                this.exceededSpeed == null ? "" : this.exceededSpeed.toString()));

        displayList.add("\t" + Display.formatIndentedText("Info :", getInfo(), 55));

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
