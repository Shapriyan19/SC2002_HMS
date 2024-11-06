package inventory;

public class Medication implements InventoryItem {
    private int medicationID;
    private String name;
    private int stockLevel;

    public Medication(int medicationID, String name, int stockLevel) {
        this.medicationID = medicationID;
        this.name = name;
        this.stockLevel = stockLevel;
    }

    @Override
    public int getStockLevel() {
        return stockLevel;
    }

    @Override
    public void setStockLevel(int newStockLevel) {
        this.stockLevel = newStockLevel;
    }

    public int getMedicationID() {
        return medicationID;
    }

    public String getName() {
        return name;
    }
}