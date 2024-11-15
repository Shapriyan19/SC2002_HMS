package medical;

import java.util.ArrayList;
import user.Patient;

public class MedicalRecord {
    private Patient patient;
    private ArrayList<Diagnosis> diagnoses;
    private ArrayList<LabTest> labTests;
    private ArrayList<Prescription> prescriptions;
    private ArrayList<Treatment> treatments;

    public MedicalRecord(Patient patient, ArrayList<Diagnosis> diagnoses, ArrayList<LabTest> labTests,
                         ArrayList<Prescription> prescriptions, ArrayList<Treatment> treatments) {
        this.patient = patient;
        this.diagnoses = diagnoses != null ? diagnoses : new ArrayList<>();
        this.labTests = labTests != null ? labTests : new ArrayList<>();
        this.prescriptions = prescriptions != null ? prescriptions : new ArrayList<>();
        this.treatments = treatments != null ? treatments : new ArrayList<>();
    }

    // Method to view the medical record details
    public void viewMedicalRecord() {
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

    @Override
    public String toString() {
        return "Medical Record for Patient: " + patient.getName() + " (Hospital ID: " + patient.getHospitalID() + ")";
    }
}







// package medical;
// import java.util.ArrayList;

// public class MedicalRecord {
//     private String hospitalID;
//     private String name;
//     private String dateOfBirth;
//     private String gender;
//     private String contactNumber;
//     private String emailAddress;
//     private String bloodType;
//     private ArrayList<Diagnosis> diagnoses;
//     private ArrayList<LabTest> labTests;
//     private ArrayList<Prescription> prescriptions;
//     private ArrayList<Treatment> treatments;

//     public MedicalRecord(String hospitalID) {
//         this.hospitalID = hospitalID;
//         this.name = name;
//         this.dateOfBirth = dateOfBirth;
//         this.gender = gender;
//         this.contactNumber = contactNumber;
//         this.emailAddress = emailAddress;
//         this.bloodType = bloodType;
//         this.diagnoses = new ArrayList<>();
//         this.labTests = new ArrayList<>();
//         this.prescriptions = new ArrayList<>();
//         this.treatments = new ArrayList<>();
//     }

//     // Getters for Basic Information - Only Patient and Doctor should have access to this data
//     public String getPatientID() { return hospitalID; }
//     // public String getName() { return name; }
//     // public String getDateOfBirth() { return dateOfBirth; }
//     // public String getGender() { return gender; }
//     // public String getContactNumber() { return contactNumber; }
//     // public String getEmailAddress() { return emailAddress; }
//     // public String getBloodType() { return bloodType; }

//     // Setters for Non-Medical Information - Only accessible by Patient
//     // public void setContactNumber(String contactNumber) {
//     //     this.contactNumber = contactNumber;
//     // }

//     // public void setEmailAddress(String emailAddress) {
//     //     this.emailAddress = emailAddress;
//     // }

//     // Diagnosis Management - Only Doctor can add
//     public ArrayList<Diagnosis> getDiagnoses() {
//         return diagnoses;
//     }

//     public void addDiagnosis(Diagnosis diagnosis) {
//         this.diagnoses.add(diagnosis);
//     }

//     // Lab Test Management - Only Doctor can add
//     public ArrayList<LabTest> getLabTests() {
//         return labTests;
//     }

//     public void addLabTest(LabTest labTest) {
//         this.labTests.add(labTest);
//     }

//     // Prescription Management - Only Doctor can add, Pharmacist can update status
//     public ArrayList<Prescription> getPrescriptions() {
//         return prescriptions;
//     }

//     public void addPrescription(Prescription prescription) {
//         this.prescriptions.add(prescription);
//     }

//     // Treatment Management - Only Doctor can add
//     public ArrayList<Treatment> getTreatments() {
//         return treatments;
//     }

//     public void addTreatment(Treatment treatment) {
//         this.treatments.add(treatment);
//     }

//     @Override
//     public String toString() {
//         return "Medical Record for Hospital ID: " + hospitalID;
//     }
// }
