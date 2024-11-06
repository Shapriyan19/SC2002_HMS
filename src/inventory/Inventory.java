package inventory;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Integer, Medication> medications;

    public Inventory() {
        this.medications = new HashMap<>();
    }

    public void addMedication(Medication medication) {
        medications.put(medication.getMedicationID(), medication);
    }

    public void removeMedication(int medicationId) {
        medications.remove(medicationId);
    }

    public void updateStockLevel(int medicationId, int newStockLevel) {
        if (medications.containsKey(medicationId)) {
            medications.get(medicationId).setStockLevel(newStockLevel);
        }
    }

    public Medication getMedication(int medicationId) {
        return medications.get(medicationId);
    }

    public Map<Integer, Medication> getAllMedications() {
        return medications;
    }
}