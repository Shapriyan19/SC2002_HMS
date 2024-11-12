package inventory;

public class Medication implements InventoryItem {
    private String name;
    private int stockLevel;
    private int lowStockLevelAlert;
    private boolean replenishmentRequested;
    private double price;

    public Medication(String name, int stockLevel, int lowStockLevelAlert, double price) {
        this.name = name;
        this.stockLevel = stockLevel;
        this.lowStockLevelAlert = lowStockLevelAlert;
        this.replenishmentRequested = false;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getStockLevel() {
        return stockLevel;
    }

    @Override
    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    @Override
    public int getLowStockLevelAlert() {
        return lowStockLevelAlert;
    }

    @Override
    public void setLowStockLevelAlert(int lowStockLevelAlert) {
        this.lowStockLevelAlert = lowStockLevelAlert;
    }

    @Override
    public boolean isStockBelowAlert() {
        return this.stockLevel <= this.lowStockLevelAlert;
    }

    @Override
    public void updateStock(int quantity) {
        this.stockLevel += quantity;
    }

    @Override
    public boolean isReplenishmentRequested() {
        return replenishmentRequested;
    }

    @Override
    public void requestReplenishment() {
        this.replenishmentRequested = true;
    }

    @Override
    public void approveReplenishment(int quantity) {
        if (this.replenishmentRequested) {
            this.stockLevel += quantity;
            this.replenishmentRequested = false;
        }
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }
}


// public class Medication {

//     private static int idCounter = 1; // Static counter for generating unique medication IDs
//     private int medicationID;
//     private String name;
//     private int stockLevel;
//     private int lowStockLevelAlert;

//     // Constructor that accepts only name and stockLevel
//     public Medication(String name, int stockLevel, int lowStockLevelAlert) {
//         this.medicationID = idCounter++; // Assign a unique ID and increment the counter
//         this.name = name;
//         this.stockLevel = stockLevel;
//         this.lowStockLevelAlert=lowStockLevelAlert;
//     }

//     public int getMedicationID() {
//         return medicationID;
//     }

//     public String getName() {
//         return name;
//     }

//     public int getStockLevel() {
//         return stockLevel;
//     }

//     public void setStockLevel(int stockLevel) {
//         this.stockLevel = stockLevel;
//     }

//     public int getLowStockLevelAlert(){
//         return lowStockLevelAlert;
//     }
// }

//shakthi
// package inventory;

// public class Medication {
//     private static int idCounter = 1; // Static counter for generating unique medication IDs
//     private int medicationID;
//     private String name;
//     private int stockLevel;
//     private int lowStockLevelAlert;
//     private double price; // Added price attribute

//     // Constructor for automatic ID assignment and including price
//     public Medication(String name, int stockLevel, int lowStockLevelAlert, double price) {
//         this.medicationID = idCounter++; // Assign a unique ID and increment the counter
//         this.name = name;
//         this.stockLevel = stockLevel;
//         this.lowStockLevelAlert = lowStockLevelAlert;
//         this.price = price; // Initialize price
//     }

//     // Overloaded constructor for manually setting IDs and including price
//     public Medication(int medicationID, String name, int stockLevel, int lowStockLevelAlert, double price) {
//         this.medicationID = medicationID;
//         this.name = name;
//         this.stockLevel = stockLevel;
//         this.lowStockLevelAlert = lowStockLevelAlert;
//         this.price = price;
//     }

//     public int getMedicationID() {
//         return medicationID;
//     }

//     public String getName() {
//         return name;
//     }

//     public int getStockLevel() {
//         return stockLevel;
//     }

//     public void setStockLevel(int stockLevel) {
//         this.stockLevel = stockLevel;
//     }

//     public int getLowStockLevelAlert() {
//         return lowStockLevelAlert;
//     }

//     public double getPrice() {
//         return price;
//     }

//     public void setPrice(double price) {
//         this.price = price;
//     }
// }