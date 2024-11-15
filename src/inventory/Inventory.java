package inventory;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Inventory {
    private Map<String, Medication> medications;  // Store medications in a map with medication name as key
    private static final String CSV_FILE_PATH = "data/Medicine_list.csv";  // Path to the CSV file

    public Inventory() {
        this.medications = new HashMap<>();
        loadMedicationsFromCSV();
    }

    // Add a new medication to the inventory
    public void addMedication(Medication medication) {
        if (medication != null && !medications.containsKey(medication.getName())) {
            medications.put(medication.getName(), medication);
            System.out.println("Added medication: " + medication.getName());
            updateCSV();
        } else {
            System.out.println("Medication already exists or is invalid.");
        }
    }

    // Remove a medication from the inventory by its name
    public void removeMedication(String medicationName) {
        if (medications.containsKey(medicationName)) {
            medications.remove(medicationName);
            System.out.println("Removed medication with name: " + medicationName);
            updateCSV();
        } else {
            System.out.println("Medication with name " + medicationName + " not found.");
        }
    }

    // Update the stock level of a specific medication by its name
    public void updateStockLevel(String medicationName, int newStockLevel) {
        Medication medication = medications.get(medicationName);
        if (medication != null) {
            medication.setStockLevel(newStockLevel);
            System.out.println("Updated stock level of " + medication.getName() + " to " + newStockLevel);

            // Check if the stock level is below the low stock alert threshold
            checkLowStockLevel(medication);
            updateCSV();
        } else {
            System.out.println("Medication with name " + medicationName + " not found.");
        }
    }

    // Update the low stock alert level for a specific medication by its name
    public void updateLowStockLevelAlert(String medicationName, int newAlertLevel) {
        Medication medication = medications.get(medicationName);
        if (medication != null) {
            medication.setLowStockLevelAlert(newAlertLevel);
            System.out.println("Updated low stock level alert for " + medication.getName() + " to " + newAlertLevel);
            updateCSV();
        } else {
            System.out.println("Medication with name " + medicationName + " not found.");
        }
    }

    // // Approve replenishment requests for medications by its name
    // public void approveReplenishment(String medicationName, int quantity) {
    //     Medication medication = medications.get(medicationName);
    //     if (medication != null && medication.isReplenishmentRequested()) {
    //         medication.approveReplenishment(quantity);
    //         System.out.println("Replenishment of " + quantity + " units approved for " + medication.getName());
    //         updateCSV();
    //     } else {
    //         System.out.println("No replenishment request found for medication with name " + medicationName);
    //     }
    // }

    // View medications that are below their low stock alert level
    public void viewLowStockMedications() {
        System.out.println("Medications with Low Stock:");
        for (Medication medication : medications.values()) {
            if (medication.getStockLevel() < medication.getLowStockLevelAlert()) {
                System.out.println("Medication: " + medication.getName() + ", Stock Level: " + medication.getStockLevel() + ", Price: " + medication.getPrice());
            }
        }
    }

    // Get all medications from the inventory
    public Map<String, Medication> getAllMedications() {
        return medications;  // Return all medications in the inventory
    }

    // Load medications from the CSV file into the inventory
    private void loadMedicationsFromCSV() {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))) {
            String line;
            
            // Skip the header row
            br.readLine();  // Skip the first line (header)
    
            // Now read the medication data lines
            while ((line = br.readLine()) != null) {
                // Split each line by commas
                String[] data = line.split(",");
                if (data.length == 4) {  // Ensure we have 4 fields per line (name, stock level, low stock level alert, price)
                    String name = data[0];  // Medication name
                    int stockLevel = Integer.parseInt(data[1]);  // Stock level (parse as integer)
                    int lowStockLevelAlert = Integer.parseInt(data[2]);  // Low stock alert level (parse as integer)
                    double price = Double.parseDouble(data[3]);  // Price (parse as double)
                    
                    // Create a new Medication object and add it to the inventory map
                    Medication medication = new Medication(name, stockLevel, lowStockLevelAlert, price);
                    medications.put(medication.getName(), medication);  // Store medication by name
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading medications from CSV file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number from CSV: " + e.getMessage());
        }
    }
    
    // Update the CSV file with the current inventory data
    private void updateCSV() {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(CSV_FILE_PATH))) {
            // Writing the header to the CSV file
            String header = "medicationName,stockLevel,lowStockLevelAlert,price";
            bw.write(header);
            bw.newLine();  // Move to the next line after the header

            // Writing each medication's details
            for (Medication medication : medications.values()) {
                String line = String.join(",",
                    medication.getName(),
                    String.valueOf(medication.getStockLevel()),
                    String.valueOf(medication.getLowStockLevelAlert()),
                    String.valueOf(medication.getPrice()));  // Include price in CSV output
                bw.write(line);
                bw.newLine();  // Move to the next line for each medication
            }
        } catch (IOException e) {
            System.out.println("Error updating the CSV file: " + e.getMessage());
        }
    }

    // Check if the stock level is below the low stock alert threshold and trigger an alert
    private void checkLowStockLevel(Medication medication) {
        if (medication.getStockLevel() < medication.getLowStockLevelAlert()) {
            System.out.println("ALERT: Medication " + medication.getName() + " is below the low stock alert level! Current stock: " + medication.getStockLevel());
        }
    }
}




// public class Inventory {

//     // A map to hold medications with their medication ID as key
//     private Map<Integer, Medication> medications;
//     private static final String CSV_FILE_PATH = "data/Medicine_list.csv";

//     // Constructor to initialize the inventory with an empty list of medications
//     public Inventory() {
//         this.medications = new HashMap<>();
//         loadMedicationsFromCSV();
//     }

//     // Method to add a medication to the inventory
//     public void addMedication(Medication medication) {
//         // Check if the medication is not null and doesn't already exist in the inventory
//         if (medication != null && !medications.containsKey(medication.getMedicationID())) {
//             // Add the medication to the inventory
//             medications.put(medication.getMedicationID(), medication);
//             System.out.println("Added medication to inventory: " + medication.getName() + " (Quantity: " + medication.getStockLevel() + ")");
//             updateCSV();
//         } else {
//             // Print error if medication is invalid or already exists
//             System.out.println("Medication already exists or is invalid.");
//         }
//     }

//     // Method to remove a medication from the inventory by its ID
//     public void removeMedication(int medicationId) {
//         if (medications.containsKey(medicationId)) {
//             medications.remove(medicationId);
//             System.out.println("Removed medication with ID: " + medicationId);
//             updateCSV();
//         } else {
//             System.out.println("Medication with ID " + medicationId + " not found.");
//         }
//     }

//     // Method to update the stock level of a specific medication
//     public void updateStockLevel(int medicationId, int newStockLevel) {
//         Medication medication = medications.get(medicationId);
//         if (medication != null) {
//             medication.setStockLevel(newStockLevel);
//             System.out.println("Updated stock level of medication " + medication.getName() + " to " + newStockLevel);
            
//             // Check if the new stock level is below the low-stock alert threshold
//             checkLowStockLevel(medication);
//             updateCSV();
//         } else {
//             System.out.println("Medication with ID " + medicationId + " not found.");
//         }
//     }

//     // Method to get a specific medication by its ID
//     public Medication getMedication(int medicationId) {
//         return medications.get(medicationId);
//     }

//     // Method to retrieve all medications in the inventory
//     public Map<Integer, Medication> getAllMedications() {
//         return medications;
//     }

//     // Method to check if a medication's stock level is below its low stock alert level
//     private void checkLowStockLevel(Medication medication) {
//         if (medication.getStockLevel() < medication.getLowStockLevelAlert()) {
//             System.out.println("ALERT: Medication " + medication.getName() + " is below the low stock alert level! Current stock: " + medication.getStockLevel());
//         }
//     }

//     // Method to view all medications with low stock
//     public void viewLowStockMedications() {
//         System.out.println("Medications with Low Stock:");
//         for (Medication medication : medications.values()) {
//             if (medication.getStockLevel() < medication.getLowStockLevelAlert()) {
//                 System.out.println("Medication: " + medication.getName() + ", Stock Level: " + medication.getStockLevel());
//             }
//         }
//     }

//     // Method to load medications from the CSV file into the inventory
//     private void loadMedicationsFromCSV() {
//         try (BufferedReader br = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))) {
//             String line;
//             while ((line = br.readLine()) != null) {
//                 String[] data = line.split(",");
//                 if (data.length == 4) {
//                     String name = data[0];
//                     int stockLevel = Integer.parseInt(data[1]);
//                     int lowStockLevelAlert = Integer.parseInt(data[2]);
//                     Medication medication = new Medication(name, stockLevel, lowStockLevelAlert);
//                     medications.put(medication.getMedicationID(), medication);
//                 }
//             }
//         } catch (IOException e) {
//             System.out.println("Error loading medications from CSV file: " + e.getMessage());
//         }
//     }

//     // Method to update the CSV file with the current inventory
//     private void updateCSV() {
//         try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(CSV_FILE_PATH))) {
//             for (Medication medication : medications.values()) {
//                 String line = String.join(",", 
//                     medication.getName(), 
//                     String.valueOf(medication.getStockLevel()), 
//                     String.valueOf(medication.getLowStockLevelAlert()));
//                 bw.write(line);
//                 bw.newLine();
//             }
//         } catch (IOException e) {
//             System.out.println("Error updating the CSV file: " + e.getMessage());
//         }
//     }
// }

//Shakthi
// package inventory;

// import java.io.*;
// import java.nio.file.*;
// import java.util.*;

// public class Inventory implements InventoryItem {
//     private Map<Integer, Medication> medications;
//     private static final String CSV_FILE_PATH = "data/Medicine_list.csv";

//     public Inventory() {
//         this.medications = new HashMap<>();
//         loadMedicationsFromCSV();
//     }

//     public void addMedication(Medication medication) {
//         if (medication != null && !medications.containsKey(medication.getMedicationID())) {
//             medications.put(medication.getMedicationID(), medication);
//             updateCSV();
//         } else {
//             System.out.println("Medication already exists or is invalid.");
//         }
//     }

//     public void removeMedication(int medicationId) {
//         if (medications.containsKey(medicationId)) {
//             medications.remove(medicationId);
//             updateCSV();
//         } else {
//             System.out.println("Medication with ID " + medicationId + " not found.");
//         }
//     }

//     public void updateStockLevel(int medicationId, int newStockLevel) {
//         Medication medication = medications.get(medicationId);
//         if (medication != null) {
//             medication.setStockLevel(newStockLevel);
//             updateCSV();
//         } else {
//             System.out.println("Medication with ID " + medicationId + " not found.");
//         }
//     }

//     public Medication getMedication(int medicationId) {
//         return medications.get(medicationId);
//     }

//     public Map<Integer, Medication> getAllMedications() {
//         return medications;
//     }

//     public void viewLowStockMedications() {
//         boolean foundLowStock = false;
//         for (Medication medication : medications.values()) {
//             if (medication.getStockLevel() < medication.getLowStockLevelAlert()) {
//                 System.out.println("Low stock alert for Medication ID: " + medication.getMedicationID() + 
//                                    " - " + medication.getName() + 
//                                    " (Stock Level: " + medication.getStockLevel() + 
//                                    ", Alert Level: " + medication.getLowStockLevelAlert() + ")");
//                 foundLowStock = true;
//             }
//         }
//         if (!foundLowStock) {
//             System.out.println("No medications are below low stock levels.");
//         }
//     }

//     private void loadMedicationsFromCSV() {
//         try (BufferedReader br = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))) {
//             String line;
//             while ((line = br.readLine()) != null) {
//                 String[] data = line.split(",");
//                 if (data.length == 5) { // Updated to handle 5 elements per line
//                     int id = Integer.parseInt(data[0]);
//                     String name = data[1];
//                     int stockLevel = Integer.parseInt(data[2]);
//                     int lowStockLevelAlert = Integer.parseInt(data[3]);
//                     double price = Double.parseDouble(data[4]); // Parse price
//                     medications.put(id, new Medication(id, name, stockLevel, lowStockLevelAlert, price));
//                 }
//             }
//         } catch (IOException e) {
//             System.out.println("Error loading medications from CSV file: " + e.getMessage());
//         }
//     }

//     private void updateCSV() {
//         try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(CSV_FILE_PATH))) {
//             for (Medication medication : medications.values()) {
//                 String line = String.join(",",
//                     String.valueOf(medication.getMedicationID()),
//                     medication.getName(),
//                     String.valueOf(medication.getStockLevel()),
//                     String.valueOf(medication.getLowStockLevelAlert()),
//                     String.valueOf(medication.getPrice())); // Include price in CSV output
//                 bw.write(line);
//                 bw.newLine();
//             }
//         } catch (IOException e) {
//             System.out.println("Error updating the CSV file: " + e.getMessage());
//         }
//     }
// }