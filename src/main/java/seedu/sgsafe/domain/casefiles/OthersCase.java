package seedu.sgsafe.domain.casefiles;

public class OthersCase extends Case {
    public OthersCase(String id, String title, String date, String info, String victim, String officer) {
        super(id, title, date, info, victim, officer);
    }

    @Override
    public String getCategory() {
        return "Others";
    }
}
