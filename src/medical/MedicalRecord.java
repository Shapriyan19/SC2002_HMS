package medical;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {
    private String patientID;
    private ArrayList<Diagnosis> diagnoses;
    private ArrayList<LabTest> labTests;
    private List<Treatment> treatments; //add treatments
    private List<Prescription> prescriptions;//add prescriptions

    public MedicalRecord(String patientID) {
        this.patientID = patientID;
        this.diagnoses = new ArrayList<>();
        this.labTests = new ArrayList<>();
        this.treatments = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    public String getPatientID() {
        return patientID;
    }

    public ArrayList<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public void addDiagnosis(Diagnosis diagnosis) {
        this.diagnoses.add(diagnosis);
    }

    public ArrayList<LabTest> getLabTests() {
        return labTests;
    }

    public void addLabTest(LabTest labTest) {
        this.labTests.add(labTest);
    }

     // Methods to manage treatments
     public List<Treatment> getTreatments() {
        return treatments;
    }

    public void addTreatment(Treatment treatment) {
        this.treatments.add(treatment);
    }

      // Methods to manage prescriptions
      public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void addPrescription(Prescription prescription) {
        this.prescriptions.add(prescription);
    }

    @Override
    public String toString() {
        return "Medical Record for Patient ID: " + patientID;
    }
}
