package seedu.sgsafe.domain.casefiles;

public class TheftCase extends Case {
    public TheftCase(String id, String title, String date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
    }

    @Override
    public String getCategory() {
        return "Theft";
    }
}
