package inventory;

public class InventoryAlert {
    private String alertID;
    private InventoryItem item;

    public InventoryAlert(String alertID, InventoryItem item) {
        this.alertID = alertID;
        this.item = item;
    }

    public void generateAlert() {
        if (item.isStockBelowAlert()) {
            System.out.println("Alert! Medication " + item.getName() + " has stock level below " + item.getLowStockLevelAlert());
        }
    }
}

// public class InventoryAlert {
//     private int alertID;
//     private int medicationID;
//     private String alertMessage;

//     public InventoryAlert(int alertID, int medicationID, String alertMessage) {
//         this.alertID = alertID;
//         this.medicationID = medicationID;
//         this.alertMessage = alertMessage;
//     }

//     public int getAlertID() {
//         return alertID;
//     }

//     public int getMedicationID() {
//         return medicationID;
//     }

//     public String getAlertMessage() {
//         return alertMessage;
//     }
// }


//shakthi
// package inventory;

// public class InventoryAlert {
//     private int alertID;
//     private int medicationID;
//     private String alertMessage;

//     public InventoryAlert(int alertID, int medicationID, String alertMessage) {
//         this.alertID = alertID;
//         this.medicationID = medicationID;
//         this.alertMessage = alertMessage;
//     }

//     public int getAlertID() {
//         return alertID;
//     }

//     public int getMedicationID() {
//         return medicationID;
//     }

//     public String getAlertMessage() {
//         return alertMessage;
//     }
// }


