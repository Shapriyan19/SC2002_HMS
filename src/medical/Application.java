package medical;

import medical.*;
import user.Patient;
import user.Doctor;
import appointment.*;
import user.Role;

import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
    	
        // Create diagnoses for the patient
        Diagnosis diagnosis1 = new Diagnosis(1, "Flu", "2024-11-15");
        Diagnosis diagnosis2 = new Diagnosis(2, "Cough", "2024-11-10");
        Diagnosis diagnosis3 = new Diagnosis(3, "Fever", "2024-12-10");
        
        // Add diagnoses to ArrayLists
        ArrayList<Diagnosis> diagnoses = new ArrayList<>();
        diagnoses.add(diagnosis1);
        diagnoses.add(diagnosis2);

        ArrayList<Diagnosis> diagnoses2 = new ArrayList<>();
        diagnoses2.add(diagnosis3);

        // Create lab tests for the patient
        LabTest labTest1 = new LabTest(1, "Blood Test", "Normal", "2024-11-15");
        LabTest labTest2 = new LabTest(2, "X-ray", "No abnormalities", "2024-11-12");
        LabTest labTest3 = new LabTest(3, "CT-Scan", "No abnormalities", "2024-10-12");

        // Add lab tests to ArrayLists
        ArrayList<LabTest> labTests = new ArrayList<>();
        labTests.add(labTest1);
        labTests.add(labTest2);

        ArrayList<LabTest> labTests2 = new ArrayList<>();
        labTests2.add(labTest3);

        // Create prescriptions for the patient
        Prescription prescription1 = new Prescription(1, "Paracetamol", 500);
        Prescription prescription2 = new Prescription(2, "Cough Syrup", 10);
        Prescription prescription3 = new Prescription(3, "Fever Syrup", 10);

        // Add prescriptions to ArrayLists
        ArrayList<Prescription> prescriptions = new ArrayList<>();
        prescriptions.add(prescription1);
        prescriptions.add(prescription2);

        ArrayList<Prescription> prescriptions2 = new ArrayList<>();
        prescriptions2.add(prescription3);

        // Create treatments for the patient
        Treatment treatment1 = new Treatment("Rest and hydration", "2024-11-15", "2024-11-20");
        Treatment treatment2 = new Treatment("Cough suppressant", "2024-11-10", "2024-11-15");
        Treatment treatment3 = new Treatment("Fever suppressant", "2024-12-10", "2024-12-15");

        // Add treatments to ArrayLists
        ArrayList<Treatment> treatments = new ArrayList<>();
        treatments.add(treatment1);
        treatments.add(treatment2);

        ArrayList<Treatment> treatments2 = new ArrayList<>();
        treatments2.add(treatment3);

        // Create medical records for patients
        MedicalRecord medicalRecord = new MedicalRecord(diagnoses, labTests, prescriptions, treatments);
        MedicalRecord medicalRecord2 = new MedicalRecord(diagnoses2, labTests2, prescriptions2, treatments2);

        // Create patients with medical records
        Patient patient1 = new Patient(Role.PATIENT, "Jamie", "1993-01-01", "Male", "O-", 1234567880L, "hello@example.com", medicalRecord);
        Patient patient2 = new Patient(Role.PATIENT, "Muru", "1995-01-01", "Male", "B+", 1234567860L, "muru.doe@example.com", medicalRecord2);

        // Add patients to doctor's patient list
        ArrayList<Patient> patientsListNew = new ArrayList<>();
        patientsListNew.add(patient1);
        patientsListNew.add(patient2);
        
        
        // Test Cases

        // Test Case 1

        // patient1.viewMedicalRecord(patient1);

        // Test Case 2

        // patient1.viewMedicalRecord(patient1);
        // patient1.updateNumber(91119000);
        // patient1.updateDOB("261100");
        // patient1.updateEmail("yay@gmail.com");
        // patient1.viewMedicalRecord(patient1);

        // Tset Case 9

        //Create a doctor with a patient list
        Doctor doctor1 = new Doctor(Role.DOCTOR, "Roger", "Male", 30, patientsListNew);

        // Test viewing and updating patient records
        System.out.println("\nInitial Medical Records:");
        doctor1.viewAllPatientMedicalRecords();

        // // Test Case 10

        // // // Update a patient's medical record
        doctor1.updatePatientMedicalRecord(patient1, diagnoses2, prescriptions2, treatments2, labTests2);

        // View updated records
        System.out.println("\nUpdated Medical Records:");
        doctor1.viewAllPatientMedicalRecords();
    }
}