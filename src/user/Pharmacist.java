package user;

import java.util.HashMap;
import java.util.Map;

import inventory.Inventory;
import inventory.Medication;
import inventory.InventoryAlert;
import inventory.MedicationOrder;
import medical.Prescription;
import medical.PrescriptionStatus;

public class Pharmacist extends User {

    // Instance variables
    private String pharmacistID;
    private String name;
    private Inventory inventory; // Inventory of medications with quantities

    // Constructor
    public Pharmacist(String userID, String password, Role role, String pharmacistID, String name) {
        super(userID, password, role);
        this.pharmacistID = pharmacistID;
        this.name = name;
        this.inventory = inventory; // Initialize inventory with an empty list
    }

    // Method to view the outcomes of appointments
    public void viewAppointmentOutcomeRecords() {
        System.out.println("Viewing appointment outcome records:");
        // Placeholder logic: Retrieve and display appointment outcomes that involve prescriptions
    }

    // Method to update the status of a prescription
    public void updatePrescriptionStatus(int prescriptionID, PrescriptionStatus newStatus) {
        System.out.println("Updating status of prescription ID: " + prescriptionID + " to " + newStatus);
        // Here, retrieve the Prescription from the inventory or other data source
        Prescription prescription = inventory.getPrescription(prescriptionID);
        if (prescription != null) {
            prescription.setStatus(newStatus);
            System.out.println("Prescription ID " + prescriptionID + " status updated to: " + newStatus);
        } else {
            System.out.println("Prescription ID " + prescriptionID + " not found.");
        }
    }

    // Method to view the current inventory of medications
    public void viewMedicationInventory() {
        System.out.println("Medication Inventory:");
        Map<Integer, Medication> medications = inventory.getAllMedications();
        for (Medication medication : medications.values()) {
            System.out.println("Medication: " + medication.getName() + ", ID: " + medication.getMedicationID() + ", Stock Level: " + medication.getStockLevel());
        }
    }

    // Method to submit a replenishment report
    public void submitReplenishmentReport(String medicationName, int quantityNeeded) {
        System.out.println("Replenishment report submitted for medication: " + medicationName + ", Quantity needed: " + quantityNeeded);
        // Implement logic to submit a replenishment report for low-stock medications
    }

    // Implementing login method
    @Override
    public boolean login(String enteredPassword) {
        if (this.password.equals(enteredPassword)) {
            this.isLoggedIn = true;
            System.out.println("Login successful for user: " + userID);
            return true;
        } else {
            System.out.println("Login failed: Incorrect password.");
            return false;}
        }

    @Override
    public boolean changePassword(String currentPassword, String newPassword) {
        if (!isLoggedIn) {
            System.out.println("Change password failed: Pharmacist is not logged in.");
            return false;
        }

        if (this.getPassword().equals(currentPassword)) {
            if (newPassword.length() >= 8) { // Basic validation for password length
                setPassword(newPassword);
                System.out.println("Password changed successfully for Pharmacist: " + name);
                return true;
            } else {
                System.out.println("New password must be at least 8 characters long.");
                return false;
            }
        } else {
            System.out.println("Change password failed: Current password is incorrect.");
            return false;
        }
    }

    
    @Override
    public void logout() {
        System.out.println("Logging out Pharmacist: " + name);
    }

    // Getters and Setters (if needed)
    public String getPharmacistID() {
        return pharmacistID;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    // Method to add medication to inventory
    public void addMedicationToInventory(String medicationName, int quantity) {
        inventory.put(medicationName, quantity);
        System.out.println("Added medication to inventory: " + medicationName + " (Quantity: " + quantity + ")");
    }
}

