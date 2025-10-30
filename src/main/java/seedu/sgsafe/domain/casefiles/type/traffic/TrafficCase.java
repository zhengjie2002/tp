package seedu.sgsafe.domain.casefiles.type.traffic;

import java.util.List;
import java.util.Map;

import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.type.CaseType;
import seedu.sgsafe.domain.casefiles.CaseFormatter;

import java.time.LocalDate;

/**
 * Represents a case under the Traffic type.
 * <p>
 * This class serves as an abstract base for traffic-related cases
 * such as {@link SpeedingCase} and {@link AccidentCase}.
 */
public abstract class TrafficCase extends Case {
    /** The type of vehicle (eg car, van, motorcycle) the driver was driving. */
    private String vehicleType;

    /** The vehicle plate number. */
    private String vehiclePlate;

    /** The name of the road where the incident occurred. */
    private String roadName;

    public TrafficCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.type = CaseType.TRAFFIC;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public String getRoadName() {
        return roadName;
    }

    //@@author shennontay
    @Override
    public String[] getReadCaseDisplay() {
        // Get base display lines from parent Case class
        List<String> displayList = getBaseDisplayLines();

        // Add TrafficCase specific fields
        CaseFormatter.addWrappedFieldForRead(displayList, "Vehicle Type", this.vehicleType);
        CaseFormatter.addWrappedFieldForRead(displayList, "Vehicle Plate", this.vehiclePlate);
        CaseFormatter.addWrappedFieldForRead(displayList, "Road Name", this.roadName);

        // Display info
        CaseFormatter.addWrappedFieldForRead(displayList, "Info", getInfo());

        return displayList.toArray(new String[0]);
    }
    //@@author

    @Override
    public List<String> getValidEditFlags() {
        return List.of("title", "date", "info", "victim", "officer",
                       "vehicle-type", "vehicle-plate", "road-name");
    }
    
    @Override
    public void update(Map<String, Object> newValues) {
        super.update(newValues);
        if (newValues.containsKey("vehicle-type")) {
            this.vehicleType = (String) newValues.get("vehicle-type");
        }
        if (newValues.containsKey("vehicle-plate")) {
            this.vehiclePlate = (String) newValues.get("vehicle-plate");
        }
        if (newValues.containsKey("road-name")) {
            this.roadName = (String) newValues.get("road-name");
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
