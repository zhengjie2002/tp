package seedu.sgsafe.domain.casefiles;

import org.junit.jupiter.api.Test;
import seedu.sgsafe.domain.casefiles.type.sexual.RapeCase;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RapeCaseTest {

    @Test
    void constructor_initializesFieldsCorrectly() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        RapeCase c = new RapeCase("R001S1", "Park Assault", date,
                "Incident occurred near park", "Alice", "Officer Tan");

        assertEquals("R001S1", c.getId());
        assertEquals("Park Assault", c.getTitle());
        assertEquals("Alice", c.getVictim());
        assertEquals("Officer Tan", c.getOfficer());
        assertEquals("Rape", c.getCategoryString());
    }
}
