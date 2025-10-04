package seedu.sgsafe.utils.casefiles;

public class Case {
    private String title;
    private String date;
    private String info;
    private String victim;
    private String officer;
    private boolean isOpen;

    Case (String title, String date, String info, String victim, String officer) {
        this.title = title;
        this.date = date;
        this.info = info;
        this.victim = victim;
        this.officer = officer;
        this.isOpen = false;
    }

    public String getDisplayLine() {
        String open = this.isOpen ? "[C]" : "[O]";
        return open + " " + this.date + " " + this.title;
    }
}
