package seedu.sgsafe.domain.casefiles;

import org.junit.jupiter.api.Test;
import seedu.sgsafe.domain.casefiles.type.traffic.AccidentCase;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccidentCaseTest {

    @Test
    void constructor_initializesFieldsCorrectly() {
        LocalDate date = LocalDate.of(2025, 10, 20);
        AccidentCase c = new AccidentCase("A001T1", "Highway Crash", date,
                "Two cars collided", "Driver A", "Officer Lim");

        assertEquals("A001T1", c.getId());
        assertEquals("Highway Crash", c.getTitle());
        assertEquals("Traffic accident", c.getCategoryString());
        assertNull(c.getNumberOfCasualties());
    }

    @Test
    void getValidEditFlags_returnsExpectedList() {
        LocalDate date = LocalDate.of(2025, 10, 20);
        AccidentCase c = new AccidentCase("A001T1", "Crash", date, "Info", "V", "O");

        List<String> expected = List.of(
                "title", "date", "info", "victim", "officer",
                "vehicle-type", "vehicle-plate", "road-name",
                "number-of-casualties"
        );
        assertEquals(expected, c.getValidEditFlags());
    }

    @Test
    void update_updatesNumberOfCasualties_andBaseFields() {
        LocalDate date = LocalDate.of(2025, 10, 20);
        AccidentCase c = new AccidentCase("A001T1", "Crash", date,
                "Initial info", "Victim", "Officer");

        Map<String, Object> updates = new HashMap<>();
        updates.put("number-of-casualties", 3);
        updates.put("vehicle-type", "Motorcycle");
        updates.put("vehicle-plate", "SGH1234J");
        updates.put("road-name", "CTE");
        updates.put("info", "Updated info");
        updates.put("title", "Updated title");

        c.update(updates);

        assertEquals(3, c.getNumberOfCasualties());
        assertEquals("Motorcycle", c.getVehicleType());
        assertEquals("SGH1234J", c.getVehiclePlate());
        assertEquals("CTE", c.getRoadName());
        assertEquals("Updated info", c.getInfo());
        assertEquals("Updated title", c.getTitle());
    }

    @Test
    void update_ignoresNullNumberOfCasualtiesAndKeepsPreviousValue() {
        LocalDate date = LocalDate.of(2025, 10, 20);
        AccidentCase c = new AccidentCase("A001T1", "Crash", date,
                "Initial", "V", "O");

        Map<String, Object> first = new HashMap<>();
        first.put("number-of-casualties", 2);
        c.update(first);
        assertEquals(2, c.getNumberOfCasualties());

        Map<String, Object> nullUpdate = new HashMap<>();
        nullUpdate.put("number-of-casualties", null);
        c.update(nullUpdate);
        assertEquals(2, c.getNumberOfCasualties());
    }

    @Test
    void getReadCaseDisplay_includesAllRelevantFields() {
        LocalDate date = LocalDate.of(2025, 10, 20);
        AccidentCase c = new AccidentCase("A001T1", "Crash", date,
                "Collision occurred", "Victim", "Officer");

        Map<String, Object> updates = new HashMap<>();
        updates.put("number-of-casualties", 4);
        updates.put("vehicle-type", "Truck");
        updates.put("vehicle-plate", "SBA4567Z");
        updates.put("road-name", "PIE");
        c.update(updates);

        String[] lines = c.getReadCaseDisplay();
        assertTrue(contains(lines, "Vehicle Type", "Truck"));
        assertTrue(contains(lines, "Vehicle Plate", "SBA4567Z"));
        assertTrue(contains(lines, "Road Name", "PIE"));
        assertTrue(contains(lines, "Number of Casualties", "4"));
        assertTrue(contains(lines, "Info", "Collision occurred"));
    }

    @Test
    void getReadCaseDisplay_handlesNullCasualtiesGracefully() {
        LocalDate date = LocalDate.of(2025, 10, 20);
        AccidentCase c = new AccidentCase("A001T1", "Crash", date,
                "Info", "V", "O");

        String[] lines = c.getReadCaseDisplay();
        assertTrue(containsLabel(lines, "Number of Casualties"));
        assertTrue(contains(lines, "Info", "Info"));
    }

    @Test
    void getAdditionalFields_appendsAccidentSpecificFields() {
        LocalDate date = LocalDate.of(2025, 10, 20);
        AccidentCase c = new AccidentCase("A001T1", "Crash", date,
                "Info", "V", "O");

        List<String> fields = c.getAdditionalFields();
        assertTrue(fields.contains("number-of-casualties"));
        assertEquals("number-of-casualties", fields.get(fields.size() - 1));
    }

    @Test
    void toSaveString_includesEmptyAndPopulatedValuesCorrectly() {
        LocalDate date = LocalDate.of(2025, 10, 20);
        AccidentCase c = new AccidentCase("A001T1", "Crash", date,
                "Info", "V", "O");

        String emptySave = c.toSaveString();
        assertTrue(emptySave.contains("|number-of-casualties:"));

        Map<String, Object> updates = new HashMap<>();
        updates.put("number-of-casualties", 5);
        c.update(updates);

        String save = c.toSaveString();
        assertTrue(save.contains("|number-of-casualties:5"));
    }

    private boolean contains(String[] lines, String label, String value) {
        for (String l : lines) {
            if (l.contains(label) && l.contains(value)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsLabel(String[] lines, String label) {
        for (String l : lines) {
            if (l.contains(label)) {
                return true;
            }
        }
        return false;
    }
}
