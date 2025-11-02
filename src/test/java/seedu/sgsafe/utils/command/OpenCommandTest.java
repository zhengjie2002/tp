package seedu.sgsafe.utils.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.CaseManager;
import seedu.sgsafe.domain.casefiles.type.financial.TheftCase;
import seedu.sgsafe.utils.exceptions.CaseAlreadyOpenException;
import seedu.sgsafe.utils.exceptions.CaseNotFoundException;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenCommandTest {

    private ArrayList<Case> caseList;

    @BeforeEach
    void resetCaseList() throws Exception {
        Field caseListField = CaseManager.class.getDeclaredField("caseList");
        caseListField.setAccessible(true);
        caseList = (ArrayList<Case>) caseListField.get(null);
        caseList.clear();
    }

    @Test
    void constructor_validParameters_success() {
        OpenCommand openCommand = new OpenCommand("abc123");
        assertEquals(CommandType.OPEN, openCommand.getCommandType());
    }

    @Test
    void execute_withValidCase_marksCaseOpen() {
        LocalDate date = LocalDate.of(2020, 5, 5);
        TheftCase theftCase = new TheftCase("abc123", "Wallet Theft", date, "info", "victim", "officer");
        theftCase.setClosed();
        caseList.add(theftCase);

        OpenCommand openCommand = new OpenCommand("abc123");
        openCommand.execute();

        assertTrue(theftCase.isOpen());
    }

    @Test
    void execute_withInvalidCaseId_throwsCaseNotFoundException() {
        LocalDate date = LocalDate.of(2020, 5, 5);
        TheftCase theftCase = new TheftCase("abc123", "Wallet Theft", date, "info", "victim", "officer");
        theftCase.setClosed();
        caseList.add(theftCase);

        OpenCommand openCommand = new OpenCommand("invalid");
        openCommand.execute();

        assertFalse(theftCase.isOpen());
        assertThrows(CaseNotFoundException.class, () -> CaseManager.openCase("invalid"));
    }

    @Test
    void openCase_alreadyOpenCase_throwsCaseAlreadyOpenException() {
        LocalDate date = LocalDate.of(2020, 5, 5);
        TheftCase theftCase = new TheftCase("abc123", "Wallet Theft", date, "info", "victim", "officer");
        CaseManager.addCase(theftCase);

        assertThrows(CaseAlreadyOpenException.class, () -> CaseManager.openCase("abc123"));
    }
}
