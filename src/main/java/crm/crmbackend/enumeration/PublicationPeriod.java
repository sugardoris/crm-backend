package crm.crmbackend.enumeration;

public enum PublicationPeriod {
    DAILY("Daily"),
    WEEKLY("Weekly"),
    MONTHLY("Monthly");

    private String label;

    PublicationPeriod(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.getLabel();
    }
}
