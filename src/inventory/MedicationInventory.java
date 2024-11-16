package inventory;

import java.util.HashMap;
import java.util.Map;

public class MedicationInventory {
    private static Map<String, Medication> inventory = new HashMap<>();

    // Add medication to the inventory
    public static void addMedication(Medication medication) {
        inventory.put(medication.getName(), medication);
    }

    // Get medication by name
    public static Medication getMedicationByName(String name) {
        return inventory.get(name);
    }

    // List all medications
    public static void listInventory() {
        System.out.println("\n--- Medication Inventory ---");
        for (Medication medication : inventory.values()) {
            System.out.println("Name: " + medication.getName() + ", Price: $" + medication.getPrice() +
                               ", Stock: " + medication.getStockLevel());
        }
    }
}
