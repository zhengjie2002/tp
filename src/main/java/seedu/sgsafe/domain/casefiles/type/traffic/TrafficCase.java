package seedu.sgsafe.domain.casefiles.type.traffic;

import java.util.List;
import java.util.Map;

import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.type.CaseType;

import java.time.LocalDate;

public abstract class TrafficCase extends Case {
    private String vehicleType;
    private String vehiclePlate;
    private String roadName;

    public TrafficCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.type = CaseType.TRAFFIC;
    }
    
    @Override
    public List<String> getValidEditFlags() {
        return List.of("title", "date", "info", "victim", "officer",
                       "vehicle-type", "vehicle-plate", "road-name");
    }
    
    @Override
    public void update(Map<String, String> newValues) {
        super.update(newValues);
        if (newValues.containsKey("vehicle-type")) {
            this.vehicleType = newValues.get("vehicleType");
        }
        if (newValues.containsKey("vehicle-plate")) {
            this.vehiclePlate = newValues.get("vehiclePlate");
        }
        if (newValues.containsKey("road-name")) {
            this.roadName = newValues.get("roadName");
        }
    }

    @Override
    public List<String> getAdditionalFields() {
        List<String> additionalFields = super.getAdditionalFields();
        additionalFields.add("vehicle-type");
        additionalFields.add("vehicle-plate");
        additionalFields.add("road-name");
        return additionalFields;
    }

    @Override
    public String toSaveString() {
        return super.toSaveString()
                + "|vehicle-type:" + (this.vehicleType  == null ? "" : this.vehicleType)
                + "|vehicle-plate:" + (this.vehiclePlate == null ? "" : this.vehiclePlate)
                + "|road-name:" + (this.roadName == null ? "" : this.roadName);
    }
}
