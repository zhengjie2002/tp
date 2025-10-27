package seedu.sgsafe.utils.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.CaseManager;
import seedu.sgsafe.domain.casefiles.type.financial.TheftCase;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteCommandTest {

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
        DeleteCommand deleteCommand = new DeleteCommand("000000");
        assertEquals("000000", deleteCommand.getCaseId());
    }

    @Test
    void execute_withValidParameters_deletesCase() {
        LocalDate date = LocalDate.of(1990, 2, 2);
        caseList.add(new TheftCase("000000", "Burglary", date, "info", "Alice", "john"));
        DeleteCommand deleteCommand = new DeleteCommand("000000");
        deleteCommand.execute();
        assertTrue(caseList.get(0).isDeleted());
    }
}
