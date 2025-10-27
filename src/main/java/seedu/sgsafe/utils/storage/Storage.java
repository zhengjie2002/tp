package seedu.sgsafe.utils.storage;

import seedu.sgsafe.domain.casefiles.Case;
import seedu.sgsafe.domain.casefiles.CaseManager;
import seedu.sgsafe.domain.casefiles.type.CaseCategory;
import seedu.sgsafe.domain.casefiles.type.OthersCase;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Storage {
    private final String filename;

    public Storage(String filename) {
        this.filename = filename;
    }

    private Map<String, String> getFields(String saveString) {
        Map<String, String> fields = new HashMap<>();
        for (String field : saveString.split("\\|")) {
            String[] splitField = field.split(":", 2);
            String key = splitField[0];
            String value = (splitField[1].isEmpty()) ? null : splitField[1];
            fields.put(key, value);
        }
        return fields;
    }

    private Case getCaseFromSaveString(String line) {
        Map<String, String> fields = getFields(line);

        String id = fields.get("id");
        String title = fields.get("title");
        String date = fields.get("date");
        String info = fields.get("info");
        String victim = fields.get("victim");
        String officer = fields.get("officer");
        boolean isDeleted = fields.get("isDeleted").equals("1");
        boolean isOpen = fields.get("isOpen").equals("1");
        String category = fields.get("category");

        Case newCase = switch (CaseCategory.valueOf(category)) {
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
        default -> throw new IllegalStateException("Unexpected value given as category: " + category);
        };

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
        } catch (IOException e) {
            System.out.println("Something went wrong while saving: " + e.getMessage());
        }
    }
}
