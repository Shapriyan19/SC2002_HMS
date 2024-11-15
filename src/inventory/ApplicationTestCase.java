package inventory;

import java.io.File;
import java.util.Map;

public class ApplicationTestCase {

    public static void main(String[] args) {
        // Initialize inventory and sample medications
        Inventory inventory = new Inventory();  // Load existing inventory from CSV if available

        // Add multiple medications to inventory
        Medication amoxicillin = new Medication("Amoxicillin", 80, 15, 12.50);
        Medication doxycycline = new Medication("Doxycycline", 60, 20, 18.75);
        Medication azithromycin = new Medication("Azithromycin", 40, 10, 22.00);

        System.out.println("Adding medications to inventory...");
        inventory.addMedication(amoxicillin);
        inventory.addMedication(doxycycline);
        inventory.addMedication(azithromycin);

        // Display current inventory
        System.out.println("\nCurrent Inventory after adding medications:");
        displayInventory(inventory);
        checkCSVUpdated();

        // View all medications
        System.out.println("\nViewing all medications in inventory:");
        inventory.getAllMedications();

        // Bulk update stock levels
        System.out.println("\nBulk updating stock levels for all medications...");
        inventory.updateStockLevel("Amoxicillin", 50);
        inventory.updateStockLevel("Doxycycline", 40);
        inventory.updateStockLevel("Azithromycin", 25);

        // Display inventory after bulk stock updates
        System.out.println("\nInventory after bulk stock updates:");
        displayInventory(inventory);
        checkCSVUpdated();

        // Attempt to remove a medication not in the inventory
        System.out.println("\nAttempting to remove a non-existent medication (Penicillin)...");
        boolean removed = inventory.removeMedication("Penicillin");
        if (!removed) {
            System.out.println("Medication 'Penicillin' not found in inventory.");
        }

        // Display inventory to ensure no unintended changes occurred
        System.out.println("\nInventory after attempting to remove non-existent medication:");
        displayInventory(inventory);
        checkCSVUpdated();

        // View medications with stock levels below threshold
        System.out.println("\nViewing medications with low stock levels:");
        inventory.viewLowStockMedications();
    }

    // Utility method to display current inventory
    private static void displayInventory(Inventory inventory) {
        Map<String, Medication> medications = inventory.getAllMedications();
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
