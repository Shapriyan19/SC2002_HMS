package inventory;

public class InventoryAlert {
    private int alertID;
    private int medicationID;
    private String alertMessage;

    public InventoryAlert(int alertID, int medicationID, String alertMessage) {
        this.alertID = alertID;
        this.medicationID = medicationID;
        this.alertMessage = alertMessage;
    }

    public int getAlertID() {
        return alertID;
    }

    public int getMedicationID() {
        return medicationID;
    }

    public String getAlertMessage() {
        return alertMessage;
    }
}
