package inventory;

public class InventoryItem {
    private String medicationId;
    private String name;
    private int quantity;
    private int lowStockAlert;

    public InventoryItem(String medicationId, String name, int quantity, int lowStockAlert) {
        this.medicationId = medicationId;
        this.name = name;
        this.quantity = quantity;
        this.lowStockAlert = lowStockAlert;
    }

    // Getters and Setters
    public String getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLowStockAlert() {
        return lowStockAlert;
    }

    public void setLowStockAlert(int lowStockAlert) {
        this.lowStockAlert = lowStockAlert;
    }

    // Method to check if inventory is low
    public boolean isLowOnStock() {
        return quantity <= lowStockAlert;
    }
}
