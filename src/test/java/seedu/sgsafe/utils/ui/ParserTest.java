package seedu.sgsafe.utils.ui;

import org.junit.jupiter.api.Test;
import seedu.sgsafe.utils.command.Command;
import seedu.sgsafe.utils.command.CommandType;
import seedu.sgsafe.utils.command.EditCommand;
import seedu.sgsafe.utils.exceptions.InvalidEditCommandException;
import seedu.sgsafe.utils.exceptions.ListCommandException;
import seedu.sgsafe.utils.exceptions.UnknownCommandException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

class ParserTest {

    // ----------- TESTS FOR GENERAL INVALID COMMANDS ----------- //

    @Test
    void parseInput_invalidKeyword_throwsUnknownCommandException() {
        assertThrows(UnknownCommandException.class, () -> Parser.parseInput("foobar"));
    }

    @Test
    void parseInput_fooList_throwsUnknownCommandException() {
        assertThrows(UnknownCommandException.class, () -> Parser.parseInput("foo list"));
    }

    // ----------- TESTS FOR LIST COMMANDS ----------- //

    @Test
    void parseInput_listWithWhitespace_returnsListCommand() {
        Command command = Parser.parseInput("  list  ");
        assertEquals(CommandType.LIST, command.getCommandType());
    }

    @Test
    void parseInput_listWithExtraArgAndWhitespace_throwsListCommandException() {
        assertThrows(ListCommandException.class, () -> Parser.parseInput("  list extra  "));
    }

    @Test
    void parseInput_foolistWithWhitespace_throwsUnknownCommandException() {
        assertThrows(UnknownCommandException.class, () -> Parser.parseInput("  foolist  "));
    }

    @Test
    void parseInput_listWithTabAndNewline_returnsListCommand() {
        Command command = Parser.parseInput("\tlist\n");
        assertEquals(CommandType.LIST, command.getCommandType());
    }

    // ----------- TESTS FOR EDIT COMMANDS ----------- //

    @Test
    void parseInput_validEditCommand_returnsEditCommand() {
        Command command = Parser.parseInput("edit 1 --title NewTitle --date 2025-10-10");

        assertEquals(CommandType.EDIT, command.getCommandType());

        EditCommand editCommand = (EditCommand) command;
        assertEquals(1, editCommand.getCaseNumber());

        Map<String, String> newValues = editCommand.getNewFlagValues();
        assertEquals("NewTitle", newValues.get("title"));
        assertEquals("2025-10-10", newValues.get("date"));
    }

    @Test
    void parseInput_missingFlagValue_throwsInvalidEditCommandException() {
        assertThrows(InvalidEditCommandException.class, () -> Parser.parseInput("edit 2 --title"));
    }

    @Test
    void parseInput_invalidFlag_throwsInvalidEditCommandException() {
        assertThrows(InvalidEditCommandException.class, () -> Parser.parseInput("edit 3 --invalidFlag newValue"));
    }

    @Test
    void parseInput_missingCaseNumber_throwsInvalidEditCommandException() {
        assertThrows(InvalidEditCommandException.class, () -> Parser.parseInput("edit --title newTitle"));
    }
}
