package seedu.sgsafe.utils.ui;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    @Test
    void haveValidFlags_allFlagsValid_returnsTrue() {
        Validator validator = new Validator();
        Map<String, String> flagValues = Map.of("title", "CaseTitle", "date", "2025-12-12");
        List<String> validFlags = List.of("title", "date", "info");

        assertEquals(true, validator.haveValidFlags(flagValues, validFlags));
    }

    @Test
    void haveValidFlags_someFlagsInvalid_returnsFalse() {
        Validator validator = new Validator();
        Map<String, String> flagValues = Map.of("title", "CaseTitle", "invalidFlag", "value");
        List<String> validFlags = List.of("title", "date", "info");

        assertEquals(false, validator.haveValidFlags(flagValues, validFlags));
    }

    @Test
    void haveAllRequiredFlags_allRequiredFlagsPresent_returnsTrue() {
        Validator validator = new Validator();
        Map<String, String> flagValues = Map.of("title", "CaseTitle", "date", "2025-12-12");
        List<String> requiredFlags = List.of("title", "date");

        assertEquals(true, validator.haveAllRequiredFlags(flagValues, requiredFlags));
    }

    @Test
    void haveAllRequiredFlags_missingRequiredFlags_returnsFalse() {
        Validator validator = new Validator();
        Map<String, String> flagValues = Map.of("title", "CaseTitle");
        List<String> requiredFlags = List.of("title", "date");

        assertEquals(false, validator.haveAllRequiredFlags(flagValues, requiredFlags));
    }

    @Test
    void inputIsEmpty_emptyInput_returnsTrue() {
        Validator validator = new Validator();
        String input = "";

        assertEquals(true, validator.inputIsEmpty(input));
    }

    @Test
    void inputIsEmpty_nonEmptyInput_returnsFalse() {
        Validator validator = new Validator();
        String input = "Some input";

        assertEquals(false, validator.inputIsEmpty(input));
    }

    @Test
    void containsOnlyEnglishCharacters_invalidInput_returnsFalse() {
        Validator validator = new Validator();
        String input = "Hello 世界";

        assertFalse(validator.containsOnlyEnglishCharacters(input));
    }

    @Test
    void containsOnlyEnglishCharacters_validInput_returnsTrue() {
        Validator validator = new Validator();
        String input = "Hello World 123!";

        assertTrue(validator.containsOnlyEnglishCharacters(input));
    }
}
