package seedu.sgsafe.domain.casefiles.type.traffic;

import java.util.List;
import java.util.Map;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;
import seedu.sgsafe.utils.ui.Display;

public class SpeedingCase extends TrafficCase {
    private String speedLimit;
    private String exceededSpeed;

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
    public List<String> getValidEditFlags() {
        return List.of("title", "date", "info", "victim", "officer",
                "vehicle-type", "vehicle-plate", "road-name",
                "speed-limit", "exceeded-speed");
    }

    @Override
    public void update(Map<String, String> newValues) {
        super.update(newValues);

        if (newValues.containsKey("speed-limit")) {
            this.speedLimit = newValues.get("speedLimit");
        }
        if (newValues.containsKey("exceeded-speed")) {
            this.exceededSpeed = newValues.get("exceededSpeed");
        }
    }
}
