package seedu.sgsafe.domain.casefiles.type.traffic;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;
import seedu.sgsafe.utils.ui.Display;

public class SpeedingCase extends TrafficCase {
    private int speedLimit;
    private int exceededSpeed;

    public SpeedingCase(String id, String title, String date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.SPEEDING;
        this.categoryString = "Speeding";
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
            "Vehicle Type  : " + (this.getVehicleType() == null ? "" : this.getVehicleType()),
            "Vehicle Plate : " + (this.getVehicleType() == null ? "" : this.getVehicleType()),
            "Road Name      : " + (this.getroadName() == null ? "" : this.getroadName()),
            "Speed Limit  : " + (this.speedLimit == 0 ? "" : this.speedLimit),
            "Exceeded Speed : " + (this.exceededSpeed == 0 ? "" : this.exceededSpeed),
            Display.formatIndentedText("Info    : ", this.getInfo(), 80)
        };
    }
}
