package inventory;

public class InventoryTest {

    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        // Test adding medications
        System.out.println("Testing Adding Medications:");
        inventory.addMedication(new Medication("Aspirin", 100, 10, 9.99));
        inventory.addMedication(new Medication("Ibuprofen", 50, 15, 5.49));
        System.out.println("Expected: 2 medications added.");

        // Display medications
        for (Medication m : inventory.getAllMedications().values()) {
            System.out.println("Added: " + m.getName() + ", ID: " + m.getMedicationID() +
                               ", Stock: " + m.getStockLevel() + ", Price: $" + m.getPrice());
        }

        // Test updating stock level
        System.out.println("\nTesting Update Stock Level:");
        inventory.updateStockLevel(1, 80); // Assuming '1' is an ID that exists
        System.out.println("Expected stock level of Aspirin: 80, Actual: " +
                           inventory.getMedication(1).getStockLevel());

        // Test low stock alert
        System.out.println("\nTesting Low Stock Alert:");
        inventory.updateStockLevel(1, 5); // Update Aspirin below low stock level
        inventory.viewLowStockMedications(); // Should print low stock alert for Aspirin

        // Test removing a medication
        System.out.println("\nTesting Removing a Medication:");
        inventory.removeMedication(1); // Remove Aspirin
        if (inventory.getMedication(1) == null) {
            System.out.println("Expected: Aspirin removed.");
        } else {
            System.out.println("Error: Aspirin not removed.");
        }
    }
}
