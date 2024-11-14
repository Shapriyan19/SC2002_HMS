package appointment;

public class MedicationRecord {
    private String medicationName;
    private String status;  // Default to "Pending"

    public MedicationRecord(String medicationName) {
        this.medicationName = medicationName;
        this.status = "Pending";  // Default status
    }

    // Getters and Setters
    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

