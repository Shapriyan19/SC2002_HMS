package medical;

import java.util.ArrayList;
import user.Patient;

public class MedicalRecord {
    private Patient patient;
    private ArrayList<Diagnosis> diagnoses;
    private ArrayList<LabTest> labTests;
    private ArrayList<Prescription> prescriptions;
    private ArrayList<Treatment> treatments;

    public MedicalRecord(ArrayList<Diagnosis> diagnoses, ArrayList<LabTest> labTests,
                         ArrayList<Prescription> prescriptions, ArrayList<Treatment> treatments) {
        //this.patient = patient;
        this.diagnoses = diagnoses != null ? diagnoses : new ArrayList<>();
        this.labTests = labTests != null ? labTests : new ArrayList<>();
        this.prescriptions = prescriptions != null ? prescriptions : new ArrayList<>();
        this.treatments = treatments != null ? treatments : new ArrayList<>();
    }

    // Method to view the medical record details
    public void viewMedicalRecord(Patient patient) {
        System.out.println("Patient Details:");
        System.out.println("Hospital ID: " + patient.getHospitalID());
        System.out.println("Name: " + patient.getName());
        System.out.println("Date of Birth: " + patient.getDateOfBirth());
        System.out.println("Gender: " + patient.getGender());
        System.out.println("Contact Number: " + patient.getPhoneNumber());
        System.out.println("Email Address: " + patient.getEmail());
        System.out.println("Blood Type: " + patient.getBloodType());

        System.out.println("\nDiagnoses:");
        for (Diagnosis diagnosis : diagnoses) {
            System.out.println(diagnosis);
        }

        System.out.println("\nLab Tests:");
        for (LabTest labTest : labTests) {
            System.out.println(labTest);
        }

        System.out.println("\nPrescriptions:");
        for (Prescription prescription : prescriptions) {
            System.out.println(prescription);
        }

        System.out.println("\nTreatments:");
        for (Treatment treatment : treatments) {
            System.out.println(treatment);
        }
    }

    // Method to update medical record details
    public void updateMedicalRecord(ArrayList<Diagnosis> newDiagnoses, ArrayList<LabTest> newLabTests,
                                    ArrayList<Prescription> newPrescriptions, ArrayList<Treatment> newTreatments) {
        if (newDiagnoses != null) {
            this.diagnoses.addAll(newDiagnoses);
        }
        if (newLabTests != null) {
            this.labTests.addAll(newLabTests);
        }
        if (newPrescriptions != null) {
            this.prescriptions.addAll(newPrescriptions);
        }
        if (newTreatments != null) {
            this.treatments.addAll(newTreatments);
        }
    }

    // Method to update medical record details
    public void updateMedicalRecord(ArrayList<Diagnosis> newDiagnoses, ArrayList<LabTest> newLabTests,
                                    ArrayList<Prescription> newPrescriptions, ArrayList<Treatment> newTreatments) {
        if (newDiagnoses != null) {
            this.diagnoses.addAll(newDiagnoses);
        }
        if (newLabTests != null) {
            this.labTests.addAll(newLabTests);
        }
        if (newPrescriptions != null) {
            this.prescriptions.addAll(newPrescriptions);
        }
        if (newTreatments != null) {
            this.treatments.addAll(newTreatments);
        }
    }

    public ArrayList<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public ArrayList<LabTest> getLabTests() {
        return labTests;
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public ArrayList<Treatment> getTreatments() {
        return treatments;
    }

    // Method to only get MedicalRecord Details 
    public String getMedicalRecordSummary() {

        StringBuilder summary = new StringBuilder();
        //summary.append("Medical Record for Hospital ID: ").append(patient.hospitalID).append("\n\n");



        summary.append("Diagnoses:\n");
        for (Diagnosis diagnosis : diagnoses) {
            summary.append(diagnosis.toString()).append("\n"); // Assumes Diagnosis has a toString() method
        }

        summary.append("\nLab Tests:\n");
        for (LabTest labTest : labTests) {
            summary.append(labTest.toString()).append("\n"); // Assumes LabTest has a toString() method
        }

        summary.append("\nPrescriptions:\n");
        for (Prescription prescription : prescriptions) {
            summary.append(prescription.toString()).append("\n"); // Assumes Prescription has a toString() method
        }

        summary.append("\nTreatments:\n");
        for (Treatment treatment : treatments) {
            summary.append(treatment.toString()).append("\n"); // Assumes Treatment has a toString() method
        }

        return summary.toString();
    }

    @Override
    public String toString() {
        return "Medical Record for Hospital ID: " + hospitalID;
    }
}
