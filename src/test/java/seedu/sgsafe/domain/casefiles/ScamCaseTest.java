package seedu.sgsafe.domain.casefiles;

import org.junit.jupiter.api.Test;
import seedu.sgsafe.domain.casefiles.type.financial.ScamCase;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScamCaseTest {

    @Test
    void constructor_initializesFieldsCorrectly() {
        LocalDate date = LocalDate.of(2025, 10, 14);
        ScamCase c = new ScamCase("S001C2", "Phishing Scam", date,
                "Fake bank website", "Eve", "Officer Lee");

        assertEquals("S001C2", c.getId());
        assertEquals("Phishing Scam", c.getTitle());
        assertEquals("Eve", c.getVictim());
        assertEquals("Officer Lee", c.getOfficer());
        assertEquals("Scam", c.getCategoryString());
    }
}
