package appointment;

public class MedicationRecord {
    private String medicationName;
    private String status;  // Default to "Pending"
    private String dosage; //add dosage

    public MedicationRecord(String medicationName, String dosage) {
        this.medicationName = medicationName;
        this.status = "Pending";  // Default status
        this.dosage= dosage;
    }

    // Getters and Setters
    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDosage(){
        return dosage;
    }

    public void setDosage(String dosage){
        this.dosage=dosage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

