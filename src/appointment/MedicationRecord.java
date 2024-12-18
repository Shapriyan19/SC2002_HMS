package appointment;

import inventory.Medication;
import inventory.Inventory;

public class MedicationRecord {
    private String medicationName;  // Changed to String type
    private String status;  // Default to "Pending"
    private int dosage;
    private double price;   // Automatically fetched from Medication inventory

    // Constructor accepts only medication name as String
    public MedicationRecord(String medicationName, int dosage) {
        this.medicationName = medicationName;
        this.status = "Pending";  // Default status
        this.dosage = dosage;
        this.price = fetchMedicationPrice(medicationName); // Automatically fetch price
    }

    // Fetch price from Medication inventory using medication name
    private double fetchMedicationPrice(String medicationName) {
        Medication medication = Inventory.getMedicationByName(medicationName); // Fetch medication from inventory
        if (medication != null) {
            return medication.getPrice();  // Return the price of the medication
        } else {
            System.err.println("Error: Medication '" + medicationName + "' not found in inventory.");
            return 0.0; // Default price if medication is not found
        }
    }

    // Getters and Setters
    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
        this.price = fetchMedicationPrice(medicationName); // Update price when name changes
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public double getPrice() {  // Getter for price
        return price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}







// package appointment;

// public class MedicationRecord {
//     private String medicationName;
//     private String status;  // Default to "Pending"
//     private String dosage; //add dosage

//     public MedicationRecord(String medicationName, String dosage) {
//         this.medicationName = medicationName;
//         this.status = "Pending";  // Default status
//         this.dosage= dosage;
//     }

//     // Getters and Setters
//     public String getMedicationName() {
//         return medicationName;
//     }

//     public void setMedicationName(String medicationName) {
//         this.medicationName = medicationName;
//     }

//     public String getDosage(){
//         return dosage;
//     }

//     public void setDosage(String dosage){
//         this.dosage=dosage;
//     }

//     public String getStatus() {
//         return status;
//     }

//     public void setStatus(String status) {
//         this.status = status;
//     }
// }

