package inventory;

public class MedicationOrder {
    private String orderID;
    private int medicationID;
    private int quantity;
    private String status;

    public MedicationOrder(String orderID, int medicationID, int quantity, String status) {
        this.orderID = orderID;
        this.medicationID = medicationID;
        this.quantity = quantity;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getMedicationID() {
        return medicationID;
    }

    public void setMedicationID(int medicationID) {
        this.medicationID = medicationID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


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


//shakthi
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