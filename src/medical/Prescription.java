package medical;

public class Prescription {
    private String medicationName;
    private PrescriptionStatus status;

    public Prescription(int prescriptionID,String medicationName, int dosage) {
        this.medicationName = medicationName;
        this.status = PrescriptionStatus.PENDING;
    }
    
    
    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public PrescriptionStatus getStatus() {
        return status;
    }

    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Prescription: " + medicationName + ", Dosage: " + dosage + " mg, Status: " + status;
    }
}
