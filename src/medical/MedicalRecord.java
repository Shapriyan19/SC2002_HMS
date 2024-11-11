import java.util.ArrayList;

public class MedicalRecord {
    private String hospitalID;
    private String patientID;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String contactNumber;
    private String emailAddress;
    private String bloodType;
    private ArrayList<Diagnosis> diagnoses;
    private ArrayList<LabTest> labTests;
    private ArrayList<Prescription> prescriptions;
    private ArrayList<Treatment> treatments;

    public MedicalRecord(String hospitalID, String patientID, String name, String dateOfBirth, String gender, String contactNumber, String emailAddress, String bloodType) {
        this.hospitalID = hospitalID;
        this.patientID = patientID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.emailAddress = emailAddress;
        this.bloodType = bloodType;
        this.diagnoses = new ArrayList<>();
        this.labTests = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
        this.treatments = new ArrayList<>();
    }

    // Getters for Basic Information - Only Patient and Doctor should have access to this data
    public String getPatientID() { return patientID; }
    public String getName() { return name; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getGender() { return gender; }
    public String getContactNumber() { return contactNumber; }
    public String getEmailAddress() { return emailAddress; }
    public String getBloodType() { return bloodType; }

    // Setters for Non-Medical Information - Only accessible by Patient
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // Diagnosis Management - Only Doctor can add
    public ArrayList<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public void addDiagnosis(Diagnosis diagnosis) {
        this.diagnoses.add(diagnosis);
    }

    // Lab Test Management - Only Doctor can add
    public ArrayList<LabTest> getLabTests() {
        return labTests;
    }

    public void addLabTest(LabTest labTest) {
        this.labTests.add(labTest);
    }

    // Prescription Management - Only Doctor can add, Pharmacist can update status
    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void addPrescription(Prescription prescription) {
        this.prescriptions.add(prescription);
    }

    // Treatment Management - Only Doctor can add
    public ArrayList<Treatment> getTreatments() {
        return treatments;
    }

    public void addTreatment(Treatment treatment) {
        this.treatments.add(treatment);
    }

    @Override
    public String toString() {
        return "Medical Record for Hospital ID: " + hospitalID;
    }
}
