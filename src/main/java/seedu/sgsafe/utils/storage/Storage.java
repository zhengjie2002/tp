package seedu.sgsafe.utils.storage;

import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.CaseManager;
import seedu.sgsafe.domain.casefiles.type.CaseCategory;
import seedu.sgsafe.domain.casefiles.type.OthersCase;
import seedu.sgsafe.domain.casefiles.type.financial.BurglaryCase;
import seedu.sgsafe.domain.casefiles.type.financial.ScamCase;
import seedu.sgsafe.domain.casefiles.type.financial.TheftCase;
import seedu.sgsafe.domain.casefiles.type.property.ArsonCase;
import seedu.sgsafe.domain.casefiles.type.property.VandalismCase;
import seedu.sgsafe.domain.casefiles.type.sexual.RapeCase;
import seedu.sgsafe.domain.casefiles.type.sexual.VoyeurismCase;
import seedu.sgsafe.domain.casefiles.type.traffic.AccidentCase;
import seedu.sgsafe.domain.casefiles.type.traffic.SpeedingCase;
import seedu.sgsafe.domain.casefiles.type.violent.AssaultCase;
import seedu.sgsafe.domain.casefiles.type.violent.MurderCase;
import seedu.sgsafe.domain.casefiles.type.violent.RobberyCase;
import seedu.sgsafe.utils.ui.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;

public class Storage {
    private static final String SAVE_DATE_PATTERN = "dd/MM/yyyy";
    private static final String SAVE_DATETIME_PATTERN = "dd/MM/yyyy HH:mm:ss";

    private final String filename;

    public Storage(String filename) {
        this.filename = filename;
    }

    public static String getSaveDatePattern() {
        return SAVE_DATE_PATTERN;
    }

    public static String getSaveDateTimePattern() {
        return SAVE_DATETIME_PATTERN;
    }

    private Map<String, String> getFields(String saveString) {
        Map<String, String> fields = new HashMap<>();
        for (String field : saveString.split("\\|")) {
            String[] splitField = field.split(":", 2);
            String key = splitField[0];
            String value = (splitField[1].isEmpty()) ? null : splitField[1];
            if(value != null) {
                fields.put(key, value);
            }
        }
        return fields;
    }

    private Case getCaseFromSaveString(String line) {
        Map<String, String> fields = getFields(line);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(getSaveDatePattern());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(getSaveDateTimePattern());

        String id = fields.get("id");
        String title = fields.get("title");
        LocalDate date = LocalDate.parse(fields.get("date"), dateFormatter);
        String info = fields.get("info");
        String victim = fields.get("victim");
        String officer = fields.get("officer");
        boolean isDeleted = fields.get("is-deleted").equals("1");
        boolean isOpen = fields.get("is-open").equals("1");
        String category = fields.get("category");
        LocalDateTime createdAt = LocalDateTime.parse(fields.get("created-at"), dateTimeFormatter);
        LocalDateTime updatedAt = LocalDateTime.parse(fields.get("updated-at"), dateTimeFormatter);

        Case newCase = switch (CaseCategory.valueOf(category)) {
        case BURGLARY -> new BurglaryCase(id, title, date, info, victim, officer);
        case SCAM -> new ScamCase(id, title, date, info, victim, officer);
        case THEFT -> new TheftCase(id, title, date, info, victim, officer);
        case ARSON -> new ArsonCase(id, title, date, info, victim, officer);
        case VANDALISM -> new VandalismCase(id, title, date, info, victim, officer);
        case RAPE -> new RapeCase(id, title, date, info, victim, officer);
        case VOYEURISM -> new VoyeurismCase(id, title, date, info, victim, officer);
        case ACCIDENT -> new AccidentCase(id, title, date, info, victim, officer);
        case SPEEDING -> new SpeedingCase(id, title, date, info, victim, officer);
        case ASSAULT -> new AssaultCase(id, title, date, info, victim, officer);
        case MURDER -> new MurderCase(id, title, date, info, victim, officer);
        case ROBBERY -> new RobberyCase(id, title, date, info, victim, officer);
        case OTHERS -> new OthersCase(id, title, date, info, victim, officer);
        };

        // only retrieve the fields that are not initialised in the constructor
        // update the case based on the additional fields found
        List<String> additionalFields = newCase.getAdditionalFields();
        fields.keySet().retainAll(additionalFields);

        //// Convert the raw string map (fields) to the typed map (typedFields)
        Map<String, Object> typedFields = Parser.convertFlagValueTypes(fields);
        newCase.update(typedFields);

        // update timestamps
        newCase.setCreatedAt(createdAt);
        newCase.setUpdatedAt(updatedAt);

        // update booleans
        newCase.setDeleted(isDeleted);
        if (isOpen) {
            newCase.setOpen();
        } else {
            newCase.setClosed();
        }

        return newCase;
    }

    public void loadCaseManager() {
        File file = new File(this.filename);
        if (file.exists()) {
            try (Scanner s = new Scanner(file)) {
                while (s.hasNextLine()) {
                    String line = s.nextLine();
                    if(!line.trim().isEmpty()) {
                        Case newCase = getCaseFromSaveString(line);
                        CaseManager.addCase(newCase);
                    }
                }
            } catch (Exception e) {
                System.out.println("Something went wrong while loading: " + e.getMessage());
            }
        }
    }

    public void saveToFile() {
        ArrayList<Case> cases = CaseManager.getCaseList();
        try (FileWriter fw = new FileWriter(this.filename)) {
            for(Case c : cases) {
                fw.append(c.toSaveString());
                fw.append(System.lineSeparator());
            }
            fw.flush();
        } catch (IOException e) {
            System.out.println("Something went wrong while saving: " + e.getMessage());
        }
    }
}
