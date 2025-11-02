package seedu.sgsafe.utils.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.CaseManager;
import seedu.sgsafe.domain.casefiles.type.financial.TheftCase;
import seedu.sgsafe.utils.exceptions.CaseAlreadyClosedException;
import seedu.sgsafe.utils.exceptions.CaseNotFoundException;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CloseCommandTest {

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
        CloseCommand closeCommand = new CloseCommand("abc123");
        assertEquals(CommandType.CLOSE, closeCommand.getCommandType());
    }

    @Test
    void execute_withValidCase_marksCaseClosed() {
        LocalDate date = LocalDate.of(2020, 5, 5);
        TheftCase theftCase = new TheftCase("abc123", "Wallet Theft", date, "info", "victim", "officer");
        caseList.add(theftCase);

        CloseCommand closeCommand = new CloseCommand("abc123");
        closeCommand.execute();

        assertFalse(theftCase.isOpen());
    }

    @Test
    void execute_withInvalidCaseId_throwsCaseNotFoundException() {
        LocalDate date = LocalDate.of(2020, 5, 5);
        TheftCase theftCase = new TheftCase("abc123", "Wallet Theft", date, "info", "victim", "officer");
        caseList.add(theftCase);

        CloseCommand closeCommand = new CloseCommand("invalid");
        closeCommand.execute();

        assertTrue(theftCase.isOpen());
        assertThrows(CaseNotFoundException.class, () -> CaseManager.closeCase("invalid"));
    }

    @Test
    void closeCase_alreadyClosedCase_throwsCaseAlreadyClosedException() {
        LocalDate date = LocalDate.of(2020, 5, 5);
        TheftCase theftCase = new TheftCase("abc123", "Wallet Theft", date, "info", "victim", "officer");
        theftCase.setClosed();
        CaseManager.addCase(theftCase);

        assertThrows(CaseAlreadyClosedException.class, () -> CaseManager.closeCase("abc123"));
    }
}
