package inventory;

import java.io.File;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        // Initialize inventory and sample medications
        Inventory inventory = new Inventory();  // Load existing inventory from CSV if available

        // Add new medications to inventory
        Medication paracetamol = new Medication("Paracetamol", 100, 20, 9.99);
        Medication ibuprofen = new Medication("Ibuprofen", 50, 10, 15.50);
        Medication aspirin = new Medication("Aspirin", 200, 30, 5.99);

        System.out.println("Adding medications to inventory...");
        inventory.addMedication(paracetamol);
        inventory.addMedication(ibuprofen);
        inventory.addMedication(aspirin);

        // Display current inventory and check CSV update
        System.out.println("\nCurrent Inventory after adding medications:");
        displayInventory(inventory);
        checkCSVUpdated();

        // Update stock level for an existing medication (Ibuprofen)
        System.out.println("\nUpdating stock level of Ibuprofen...");
        inventory.updateStockLevel("Ibuprofen", 30);  // Reduce stock level of Ibuprofen to 30

        // Display inventory after updating stock level
        System.out.println("\nInventory after updating stock level for Ibuprofen:");
        displayInventory(inventory);
        checkCSVUpdated();

        // Remove a medication (Paracetamol)
        System.out.println("\nRemoving Paracetamol from inventory...");
        inventory.removeMedication("Paracetamol");

        // Display inventory after removing a medication
        System.out.println("\nInventory after removing Paracetamol:");
        displayInventory(inventory);
        checkCSVUpdated();

        // View medications with low stock levels
        System.out.println("\nViewing medications with low stock levels:");
        inventory.viewLowStockMedications();

        // Add back a medication (Paracetamol) and update its stock level
        System.out.println("\nAdding Paracetamol back to inventory...");
        Medication newParacetamol = new Medication("Paracetamol", 150, 20, 9.99);
        inventory.addMedication(newParacetamol);

        System.out.println("\nInventory after adding Paracetamol back:");
        displayInventory(inventory);
        checkCSVUpdated();
    }

    // Utility method to display current inventory
    private static void displayInventory(Inventory inventory) {
        Map<String, Medication> medications = inventory.getAllMedications();  // Use name-based map
        for (Medication medication : medications.values()) {
            System.out.println("Medication: " + medication.getName() +
                    ", Stock Level: " + medication.getStockLevel() +
                    ", Low Stock Level Alert: " + medication.getLowStockLevelAlert() +
                    ", Price: " + medication.getPrice());
        }
    }

    // Utility method to check if the CSV file was updated
    private static void checkCSVUpdated() {
        File file = new File("data/Medicine_list.csv");
        if (file.exists()) {
            System.out.println("CSV file exists and has been updated.");
        } else {
            System.out.println("CSV file not found or not updated.");
        }
    }
}
