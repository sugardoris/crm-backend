package crm.crmbackend.enumeration;

public enum PublicationPeriod {
    DAILY("Daily"),
    WEEKLY("Weekly"),
    BIWEEKLY("Every two weeks"),
    MONTHLY("Monthly"),
    BIANNUALY("Every six months"),
    ANNUALY("Yearly");

    private String label;

    PublicationPeriod(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.getLabel();
    }
}
