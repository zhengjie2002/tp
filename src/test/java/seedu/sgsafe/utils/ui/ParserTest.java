package seedu.sgsafe.utils.ui;

import org.junit.jupiter.api.Test;
import seedu.sgsafe.utils.command.Command;
import seedu.sgsafe.utils.command.CommandType;
import seedu.sgsafe.utils.exceptions.ListCommandException;
import seedu.sgsafe.utils.exceptions.UnknownCommandException;

import static org.junit.jupiter.api.Assertions.*;

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
}
