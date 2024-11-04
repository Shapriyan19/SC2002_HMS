package inventory;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<InventoryItem> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    // Method to add an item to inventory
    public void addItem(InventoryItem item) {
        items.add(item);
    }

    // Method to update inventory quantity
    public void updateItemQuantity(String medicationId, int quantity) {
        for (InventoryItem item : items) {
            if (item.getMedicationId().equals(medicationId)) {
                item.setQuantity(quantity);
                break;
            }
        }
    }

    // Method to check and generate alerts
    public List<String> checkInventoryAlerts() {
        List<String> alerts = new ArrayList<>();
        for (InventoryItem item : items) {
            if (item.isLowOnStock()) {
                alerts.add("Low stock alert for " + item.getName() + ": " + item.getQuantity() + " remaining.");
            }
        }
        return alerts;
    }
}