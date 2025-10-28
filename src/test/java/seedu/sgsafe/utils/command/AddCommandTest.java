package seedu.sgsafe.utils.command;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AddCommandTest {

    @Test
    void constructor_validParameters_createsCommandSuccessfully() {
        LocalDate date = LocalDate.of(2023, 10, 1);
        AddCommand command = new AddCommand("Theft", "Case Title", date,
                "Case Info", "Victim Name", "Officer Name");

        assertEquals("theft", command.getCaseCategory());
        assertEquals("Case Title", command.getCaseTitle());
        assertEquals(date, command.getCaseDate());
        assertEquals("Case Info", command.getCaseInfo());
        assertEquals("Victim Name", command.getCaseVictim());
        assertEquals("Officer Name", command.getCaseOfficer());
    }

    @Test
    void constructor_withNullOptionalParameters_createsCommandSuccessfully() {
        LocalDate date = LocalDate.of(2023, 10, 1);
        AddCommand command = new AddCommand("Theft", "Case Title", date, "Case Info", null, null);

        assertEquals("theft", command.getCaseCategory());
        assertEquals("Case Title", command.getCaseTitle());
        assertEquals(date, command.getCaseDate());
        assertEquals("Case Info", command.getCaseInfo());
        assertNull(command.getCaseVictim());
        assertNull(command.getCaseOfficer());
    }

    @Test
    void constructor_validParameters_setsCommandTypeToAdd() {
        LocalDate date = LocalDate.of(2023, 10, 1);
        AddCommand command = new AddCommand("Theft", "Title", date, "Info", null, null);

        assertEquals(CommandType.ADD, command.getCommandType());
    }

    @Test
    void constructor_withSpecialCharacters_preservesCharacters() {
        LocalDate date = LocalDate.of(2023, 10, 1);
        AddCommand command = new AddCommand(
                "Theft",
                "Case @#$%",
                date,
                "Info with symbols: !@#$%^&*()",
                "Victim's Name",
                "Officer-123"
        );

        assertEquals("Case @#$%", command.getCaseTitle());
        assertEquals("Info with symbols: !@#$%^&*()", command.getCaseInfo());
    }
}
