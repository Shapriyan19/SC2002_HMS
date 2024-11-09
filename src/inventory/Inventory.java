package inventory;

import java.util.HashMap;
import java.util.Map;

// The Inventory class is responsible for managing medications in the inventory
public class Inventory {
    
    // A map to hold medications with their medication ID as key
    private Map<Integer, Medication> medications;

    // Constructor to initialize the inventory with an empty list of medications
    public Inventory() {
        this.medications = new HashMap<>();
    }

    // Method to add a medication to the inventory
    public void addMedication(Medication medication) {
        // Check if the medication is not null and doesn't already exist in the inventory
        if (medication != null && !medications.containsKey(medication.getMedicationID())) {
            // Add the medication to the inventory
            medications.put(medication.getMedicationID(), medication);
            System.out.println("Added medication to inventory: " + medication.getName() + " (Quantity: " + medication.getStockLevel() + ")");
        } else {
            // Print error if medication is invalid or already exists
            System.out.println("Medication already exists or is invalid.");
        }
    }
    

    // Method to remove a medication from the inventory by its ID
    public void removeMedication(int medicationId) {
        if (medications.containsKey(medicationId)) {
            medications.remove(medicationId);
            System.out.println("Removed medication with ID: " + medicationId);
        } else {
            System.out.println("Medication with ID " + medicationId + " not found.");
        }
    }

    // Method to update the stock level of a specific medication
    public void updateStockLevel(int medicationId, int newStockLevel) {
        Medication medication = medications.get(medicationId);
        if (medication != null) {
            medication.setStockLevel(newStockLevel);
            System.out.println("Updated stock level of medication " + medication.getName() + " to " + newStockLevel);
        } else {
            System.out.println("Medication with ID " + medicationId + " not found.");
        }
    }

    // Method to get a specific medication by its ID
    public Medication getMedication(int medicationId) {
        return medications.get(medicationId);
    }

    // Method to retrieve all medications in the inventory
    public Map<Integer, Medication> getAllMedications() {
        return medications;
    }
}
