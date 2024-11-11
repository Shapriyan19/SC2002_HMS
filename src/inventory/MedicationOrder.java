// package inventory;

// public class MedicationOrder {
//     private int orderID;
//     private int medicationID;
//     private int quantity;
//     private String status; // e.g., PENDING, DISPENSED

//     public MedicationOrder(int orderID, int medicationID, int quantity, String status) {
//         this.orderID = orderID;
//         this.medicationID = medicationID;
//         this.quantity = quantity;
//         this.status = status;
//     }

//     public int getOrderID() {
//         return orderID;
//     }

//     public int getMedicationID() {
//         return medicationID;
//     }

//     public int getQuantity() {
//         return quantity;
//     }

//     public String getStatus() {
//         return status;
//     }

//     public void setStatus(String status) {
//         this.status = status;
//     }
// }

package inventory;

public class MedicationOrder {
    private int orderID;
    private int medicationID;
    private int quantity;
    private String status; // e.g., PENDING, DISPENSED

    public MedicationOrder(int orderID, int medicationID, int quantity, String status) {
        this.orderID = orderID;
        this.medicationID = medicationID;
        this.quantity = quantity;
        this.status = status;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getMedicationID() {
        return medicationID;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}