package crm.crmbackend.enumeration;

public enum SubscriptionPeriod {
    MONTHLY("Monthly"),
    TRIMONTHLY("Every three months"),
    BIANNUALY("Every six months"),
    ANNUALY("Yearly");

    private String label;

    SubscriptionPeriod(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.getLabel();
    }
}
