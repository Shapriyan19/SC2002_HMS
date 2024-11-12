package inventory;
import java.util.Map;

public interface InventoryItem {
    public void addMedication(Medication medication);
    public void removeMedication(int medicationId);
    public void updateStockLevel(int medication, int newStockLevel);
    public Medication getMedication(int medicationId);
    public Map<Integer,Medication> getAllMedications();
    // public void checkLowStockLevel(Medication medication);
    public void viewLowStockMedications();
    
}


//shakthi
// package inventory;
// import java.util.Map;

// public interface InventoryItem {
//     void addMedication(Medication medication);
//     void removeMedication(int medicationId);
//     void updateStockLevel(int medicationId, int newStockLevel);
//     Medication getMedication(int medicationId);
//     Map<Integer, Medication> getAllMedications();
//     void viewLowStockMedications();
// }
