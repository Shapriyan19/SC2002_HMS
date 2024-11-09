package medical;

public class Prescription {
    private int prescriptionID;
    private String medicationName;
    private int dosage;
    private PrescriptionStatus status;

    public Prescription(int prescriptionID,String medicationName, int dosage) {
        this.prescriptionID = prescriptionID;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.status = PrescriptionStatus.PENDING;
    }
    
    public int getPrescriptionID(){
        return prescriptionID;
    }
    
    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
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
