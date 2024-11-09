package inventory;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Inventory {

    // A map to hold medications with their medication ID as key
    private Map<Integer, Medication> medications;
    private static final String CSV_FILE_PATH = "data/Medicine_list.csv";

    // Constructor to initialize the inventory with an empty list of medications
    public Inventory() {
        this.medications = new HashMap<>();
        loadMedicationsFromCSV();
    }

    // Method to add a medication to the inventory
    public void addMedication(Medication medication) {
        // Check if the medication is not null and doesn't already exist in the inventory
        if (medication != null && !medications.containsKey(medication.getMedicationID())) {
            // Add the medication to the inventory
            medications.put(medication.getMedicationID(), medication);
            System.out.println("Added medication to inventory: " + medication.getName() + " (Quantity: " + medication.getStockLevel() + ")");
            updateCSV();
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
            updateCSV();
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
            
            // Check if the new stock level is below the low-stock alert threshold
            checkLowStockLevel(medication);
            updateCSV();
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

    // Method to check if a medication's stock level is below its low stock alert level
    private void checkLowStockLevel(Medication medication) {
        if (medication.getStockLevel() < medication.getLowStockLevelAlert()) {
            System.out.println("ALERT: Medication " + medication.getName() + " is below the low stock alert level! Current stock: " + medication.getStockLevel());
        }
    }

    // Method to view all medications with low stock
    public void viewLowStockMedications() {
        System.out.println("Medications with Low Stock:");
        for (Medication medication : medications.values()) {
            if (medication.getStockLevel() < medication.getLowStockLevelAlert()) {
                System.out.println("Medication: " + medication.getName() + ", Stock Level: " + medication.getStockLevel());
            }
        }
    }

    // Method to load medications from the CSV file into the inventory
    private void loadMedicationsFromCSV() {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    String name = data[0];
                    int stockLevel = Integer.parseInt(data[1]);
                    int lowStockLevelAlert = Integer.parseInt(data[2]);
                    Medication medication = new Medication(name, stockLevel, lowStockLevelAlert);
                    medications.put(medication.getMedicationID(), medication);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading medications from CSV file: " + e.getMessage());
        }
    }

    // Method to update the CSV file with the current inventory
    private void updateCSV() {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(CSV_FILE_PATH))) {
            for (Medication medication : medications.values()) {
                String line = String.join(",", 
                    medication.getName(), 
                    String.valueOf(medication.getStockLevel()), 
                    String.valueOf(medication.getLowStockLevelAlert()));
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating the CSV file: " + e.getMessage());
        }
    }
}
