package seedu.sgsafe.domain.casefiles;

import org.junit.jupiter.api.Test;
import seedu.sgsafe.domain.casefiles.type.sexual.VoyeurismCase;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VoyeurismCaseTest {

    @Test
    void constructor_initializesFieldsCorrectly() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        VoyeurismCase c = new VoyeurismCase("V001S2", "Hidden Camera in Toilet", date,
                "Discovered camera under sink", "Betty", "Officer Lee");

        assertEquals("V001S2", c.getId());
        assertEquals("Hidden Camera in Toilet", c.getTitle());
        assertEquals("Betty", c.getVictim());
        assertEquals("Officer Lee", c.getOfficer());
        assertEquals("Voyeurism", c.getCategoryString());
    }
}
