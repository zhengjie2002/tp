package seedu.sgsafe.domain.casefiles.type.traffic;

import java.util.List;
import java.util.Map;

import seedu.sgsafe.domain.casefiles.type.CaseCategory;

import java.time.LocalDate;

public class SpeedingCase extends TrafficCase {
    private String speedLimit;
    private String exceededSpeed;

    public SpeedingCase(String id, String title, LocalDate date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
        this.category = CaseCategory.SPEEDING;
        this.categoryString = "Speeding";
    }

    @Override
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
