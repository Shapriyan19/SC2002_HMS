package user;

import java.util.Map;
import java.util.HashMap;
import inventory.Inventory;
import inventory.Medication;
import medical.Prescription;
import medical.PrescriptionStatus;

public class Pharmacist extends User {

    // Instance variables
    private String pharmacistID;
    private String name;
    private Inventory inventory; // Inventory of medications with quantities
    private Map<Integer, Prescription> prescriptions; // Collection of prescriptions managed by this pharmacist

    // Constructor
    public Pharmacist(String userID, String password, Role role, String pharmacistID, String name, Inventory inventory) {
        super(userID, password, role);
        this.pharmacistID = pharmacistID;
        this.name = name;
        this.inventory = inventory; // Initialize inventory with the provided instance
        this.prescriptions = new HashMap<>(); // Initialize the prescriptions map
    }

    // Method to view the outcomes of appointments
    public void viewAppointmentOutcomeRecords() {
        System.out.println("Viewing appointment outcome records:");
        // Placeholder logic: Retrieve and display appointment outcomes that involve prescriptions
    }

    // Method to update the status of a prescription
    public void updatePrescriptionStatus(Prescription prescription, PrescriptionStatus newStatus) {
        System.out.println("Updating status of prescription ID: " + prescription.getPrescriptionID() + " to " + newStatus);
        if (prescription != null) {
            prescription.setStatus(newStatus);
            System.out.println("Prescription ID " + prescription.getPrescriptionID() + " status updated to: " + newStatus);
        } else {
            System.out.println("Prescription not found.");
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
            return false;
        }
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

    public Inventory getInventory() {
        return inventory;
    }

    // Method to add a prescription to the pharmacist's list
    public void addPrescription(Prescription prescription) {
        prescriptions.put(prescription.getPrescriptionID(), prescription);
        // System.out.println("Prescription added for patient: " + prescription.getPatientName()); need to add 
    }

    // Method to remove a prescription from the pharmacist's list
    public void removePrescription(int prescriptionID) {
        if (prescriptions.containsKey(prescriptionID)) {
            prescriptions.remove(prescriptionID);
            System.out.println("Prescription with ID " + prescriptionID + " removed.");
        } else {
            System.out.println("Prescription ID " + prescriptionID + " not found.");
        }
    }

    // Method to view all prescriptions managed by the pharmacist
    public void viewAllPrescriptions() {
        System.out.println("Viewing all prescriptions managed by Pharmacist " + name + ":");
        for (Prescription prescription : prescriptions.values()) {
            // System.out.println("Prescription ID: " + prescription.getPrescriptionID() + ", Patient: " + prescription.getPatientName() + ", Status: " + prescription.getStatus()); need to use this
            System.out.println("Prescription ID: " + prescription.getPrescriptionID() + ", Status: " + prescription.getStatus());
        }
    }

    // Method to get a specific prescription by ID
    public Prescription getPrescription(int prescriptionID) {
        return prescriptions.get(prescriptionID);
    }

    // Method to add medication to inventory
    public void addMedicationToInventory(String medicationName, int quantity) {
        inventory.addMedication(new Medication(medicationName, quantity)); // Assuming Medication constructor takes name and stock level
        System.out.println("Added medication to inventory: " + medicationName + " (Quantity: " + quantity + ")");
    }
}
