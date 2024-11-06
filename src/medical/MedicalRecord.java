import java.util.ArrayList;

public class MedicalRecord {
    private String patientID;
    private ArrayList<Diagnosis> diagnoses;
    private ArrayList<LabTest> labTests;

    public MedicalRecord(String patientID) {
        this.patientID = patientID;
        this.diagnoses = new ArrayList<>();
        this.labTests = new ArrayList<>();
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

    @Override
    public String toString() {
        return "Medical Record for Patient ID: " + patientID;
    }
}
