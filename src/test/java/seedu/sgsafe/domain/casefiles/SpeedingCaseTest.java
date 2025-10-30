package seedu.sgsafe.domain.casefiles;

import org.junit.jupiter.api.Test;
import seedu.sgsafe.domain.casefiles.type.traffic.SpeedingCase;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpeedingCaseTest {

    @Test
    void constructor_initializesFieldsCorrectly() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        SpeedingCase c = new SpeedingCase("T001T1", "Speeding at PIE", date,
                "Vehicle exceeded speed limit", "None", "Officer Wong");

        assertEquals("T001T1", c.getId());
        assertEquals("Speeding at PIE", c.getTitle());
        assertEquals("Speeding", c.getCategoryString());
        assertNull(c.getSpeedLimit());
        assertNull(c.getExceededSpeed());
        assertNull(c.getVehicleType());
        assertNull(c.getVehiclePlate());
        assertNull(c.getRoadName());
    }

    @Test
    void getValidEditFlags_includesTrafficAndSpeedingFlags() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        SpeedingCase c = new SpeedingCase("T001T1", "Speeding", date, "I", "V", "O");

        List<String> flags = c.getValidEditFlags();
        assertEquals(
                List.of("title", "date", "info", "victim", "officer",
                        "vehicle-type", "vehicle-plate", "road-name",
                        "speed-limit", "exceeded-speed"),
                flags
        );
    }

    @Test
    void update_updatesTrafficAndSpeedingFields() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        SpeedingCase c = new SpeedingCase("T001T1", "Speeding at PIE", date,
                "Vehicle exceeded speed limit", "None", "Officer Wong");

        Map<String, Object> updates = new HashMap<>();
        updates.put("vehicle-type", "Sedan");
        updates.put("vehicle-plate", "SFA1234X");
        updates.put("road-name", "PIE");
        updates.put("speed-limit", 90);
        updates.put("exceeded-speed", 120);
        c.update(updates);

        assertEquals("Sedan", c.getVehicleType());
        assertEquals("SFA1234X", c.getVehiclePlate());
        assertEquals("PIE", c.getRoadName());
        assertEquals(90, c.getSpeedLimit());
        assertEquals(120, c.getExceededSpeed());
    }

    @Test
    void getReadCaseDisplay_includesTrafficAndSpeedingFields() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        SpeedingCase c = new SpeedingCase("T001T1", "Speeding", date,
                "Vehicle exceeded speed limit", "None", "Officer Wong");

        Map<String, Object> updates = new HashMap<>();
        updates.put("vehicle-type", "SUV");
        updates.put("vehicle-plate", "SMC8888A");
        updates.put("road-name", "ECP");
        updates.put("speed-limit", 90);
        updates.put("exceeded-speed", 120);
        c.update(updates);

        String[] lines = c.getReadCaseDisplay();
        assertTrue(contains(lines, "Vehicle Type", "SUV"));
        assertTrue(contains(lines, "Vehicle Plate", "SMC8888A"));
        assertTrue(contains(lines, "Road Name", "ECP"));
        assertTrue(contains(lines, "Speed Limit", "90"));
        assertTrue(contains(lines, "Exceeded Speed", "120"));
        assertTrue(contains(lines, "Info", "Vehicle exceeded speed limit"));
    }

    @Test
    void getAdditionalFields_appendsTrafficAndSpeedingFields() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        SpeedingCase c = new SpeedingCase("T001T1", "Speeding", date, "I", "V", "O");

        List<String> fields = c.getAdditionalFields();
        assertTrue(fields.contains("vehicle-type"));
        assertTrue(fields.contains("vehicle-plate"));
        assertTrue(fields.contains("road-name"));
        assertTrue(fields.contains("speed-limit"));
        assertTrue(fields.contains("exceeded-speed"));

        int n = fields.size();
        assertEquals("speed-limit", fields.get(n - 2));
        assertEquals("exceeded-speed", fields.get(n - 1));
    }

    @Test
    void toSaveString_includesAllTrafficAndSpeedingKeys_withEmptyAndPopulatedValues() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        SpeedingCase c = new SpeedingCase("T001T1", "Speeding", date, "I", "V", "O");

        String empty = c.toSaveString();
        assertTrue(empty.contains("|vehicle-type:"));
        assertTrue(empty.contains("|vehicle-plate:"));
        assertTrue(empty.contains("|road-name:"));
        assertTrue(empty.contains("|speed-limit:"));
        assertTrue(empty.contains("|exceeded-speed:"));

        Map<String, Object> updates = new HashMap<>();
        updates.put("vehicle-type", "Van");
        updates.put("vehicle-plate", "SBA1234A");
        updates.put("road-name", "CTE");
        updates.put("speed-limit", 70);
        updates.put("exceeded-speed", 100);
        c.update(updates);

        String populated = c.toSaveString();
        assertTrue(populated.contains("|vehicle-type:Van"));
        assertTrue(populated.contains("|vehicle-plate:SBA1234A"));
        assertTrue(populated.contains("|road-name:CTE"));
        assertTrue(populated.contains("|speed-limit:70"));
        assertTrue(populated.contains("|exceeded-speed:100"));
    }

    private boolean contains(String[] lines, String label, String value) {
        for (String l : lines) {
            if (l.contains(label) && l.contains(value)) {
                return true;
            }
        }
        return false;
    }
}
