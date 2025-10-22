package seedu.sgsafe.domain.casefiles;

public class AccidentCase extends Case {
    public AccidentCase(String id, String title, String date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
    }

    @Override
    public String getCategory() {
        return "Accident";
    }
}
