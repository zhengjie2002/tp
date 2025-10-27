package seedu.sgsafe.domain.casefiles.type.traffic;

import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.type.CaseType;
import seedu.sgsafe.utils.ui.Display;

public abstract class TrafficCase extends Case {
    private String vehicleType;
    private String vehiclePlate;
    private String roadName;

    public TrafficCase(String id, String title, String date, String info, String victim, String officer) {
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
    }
}
