package inventory;

public class Medication {

    private static int idCounter = 1; // Static counter for generating unique medication IDs
    private int medicationID;
    private String name;
    private int stockLevel;

    // Constructor that accepts only name and stockLevel
    public Medication(String name, int stockLevel) {
        this.medicationID = idCounter++; // Assign a unique ID and increment the counter
        this.name = name;
        this.stockLevel = stockLevel;
    }

    public int getMedicationID() {
        return medicationID;
    }

    public String getName() {
        return name;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }
}
