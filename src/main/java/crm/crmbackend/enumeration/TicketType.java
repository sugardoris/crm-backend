package crm.crmbackend.enumeration;

public enum TicketType {
    COMPLAINT("Complaint"),
    INFO("Subscription info"),
    PAYMENT("Payment info"),
    NEW("New subscription"),
    CANCELLATION("Cancellation of subscription");

    private String label;

    TicketType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.getLabel();
    }
}
