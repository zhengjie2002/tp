package seedu.sgsafe.utils.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AddCommandTest {

    @Test
    void constructor_validParameters_createsCommandSuccessfully() {
        AddCommand command = new AddCommand("Case Title", "2023-10-01", "Case Info", "Victim Name", "Officer Name");

        assertEquals("Case Title", command.getCaseTitle());
        assertEquals("2023-10-01", command.getCaseDate());
        assertEquals("Case Info", command.getCaseInfo());
        assertEquals("Victim Name", command.getCaseVictim());
        assertEquals("Officer Name", command.getCaseOfficer());
    }

    @Test
    void constructor_withNullOptionalParameters_createsCommandSuccessfully() {
        AddCommand command = new AddCommand("Case Title", "2023-10-01", "Case Info", null, null);

        assertEquals("Case Title", command.getCaseTitle());
        assertEquals("2023-10-01", command.getCaseDate());
        assertEquals("Case Info", command.getCaseInfo());
        assertNull(command.getCaseVictim());
        assertNull(command.getCaseOfficer());
    }

    @Test
    void constructor_validParameters_setsCommandTypeToAdd() {
        AddCommand command = new AddCommand("Title", "2023-10-01", "Info", null, null);

        assertEquals(CommandType.ADD, command.getCommandType());
    }

    @Test
    void constructor_withSpecialCharacters_preservesCharacters() {
        AddCommand command = new AddCommand(
                "Case @#$%",
                "2023-10-01",
                "Info with symbols: !@#$%^&*()",
                "Victim's Name",
                "Officer-123"
        );

        assertEquals("Case @#$%", command.getCaseTitle());
        assertEquals("Info with symbols: !@#$%^&*()", command.getCaseInfo());
    }
}
