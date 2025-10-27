package seedu.sgsafe.domain.casefiles.type.traffic;

import java.util.List;
import java.util.Map;

import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.type.CaseType;
import seedu.sgsafe.utils.ui.Display;

import java.time.LocalDate;

public abstract class TrafficCase extends Case {
    private String vehicleType;
    private String vehiclePlate;
    private String roadName;

    public TrafficCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.type = CaseType.TRAFFIC;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getvehiclePlate() {
        return vehicleType;
    }

    public String getroadName() {
        return vehicleType;
    }

    @Override
    public String[] getReadCaseDisplay() {
        String header = "======== CASE ID " + this.getId() + " ========";
        String statusLine = "Status  : " + (this.isOpen() ? "Open" : "Closed");

        return new String[] {
            header,
            statusLine,
            "Title   : " + (this.getTitle() == null ? "" : this.getTitle()),
            "Category: " + (this.getCategory() == null ? "" : this.getCategory()),
            "Date    : " + (this.getDate() == null ? "" : this.getDate()),
            "Victim  : " + (this.getVictim() == null ? "" : this.getVictim()),
            "Officer : " + (this.getOfficer() == null ? "" : this.getOfficer()),
            "Vehicle Type  : " + (this.vehicleType == null ? "" : this.vehicleType),
            "Vehicle Plate : " + (this.vehiclePlate == null ? "" : this.vehiclePlate),
            "Road Name      : " + (this.roadName == null ? "" : this.roadName),
            Display.formatIndentedText("Info    : ", this.getInfo(), 80)
        };

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
