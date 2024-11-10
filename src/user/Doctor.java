package user;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
// import java.nio.file.*;
import medical.MedicalRecord;
import medical.Diagnosis;
import medical.LabTest;
import medical.Prescription;
import medical.Treatment;

public class Doctor extends User {

    // Instance variables
    private String doctorID;
    private String name;
    private String specialisation;
    private List<String> schedule;
    private List<Patient> patientList;
    private String gender;
    private int age;
    
    // Constructor
    public Doctor(String userID, String password, Role role, String doctorID, String name, 
                  String specialisation, String gender, int age) {
        super(userID, password, role);
        this.doctorID = doctorID;
        this.name = name;
        this.specialisation = specialisation;
        this.schedule = new ArrayList<>();
        this.patientList = new ArrayList<>();
        this.gender = gender;
        this.age = age;
    }

    public void addPatient(Patient patient) {
        patientList.add(patient);
        System.out.println("Added patient with ID: " + patient.getPatientID());
    }

    private Patient getPatientById(String patientID) {
        for (Patient patient : patientList) {
            if (patient.getPatientID().equals(patientID)) {
                return patient;
            }
        }
        System.out.println("Patient with ID " + patientID + " not found.");
        return null;
    }

    private MedicalRecord getMedicalRecordByPatient(Patient patient) {
        if (patient != null) {
            return patient.getMedicalRecord();
        }
        System.out.println("No medical record found for the provided patient.");
        return null;
    }

    public void viewPatientRecord(String patientID) {
        Patient patient = getPatientById(patientID);
        if (patient == null) return;

        MedicalRecord medicalRecord = getMedicalRecordByPatient(patient);
        System.out.println("---- Medical Record for Patient ----");
        System.out.println("Patient ID: " + patientID);
        System.out.println("Name: " + patient.getName());
        System.out.println("Date of Birth: " + patient.getDateOfBirth());
        System.out.println("Gender: " + patient.getGender());
        System.out.println("Blood Type: " + patient.getBloodType());
        System.out.println("Contact Information:");
        System.out.println("    Phone Number: " + patient.getPhoneNumber());
        System.out.println("    Email: " + patient.getEmail());

        // Display Diagnoses
        System.out.println("\nDiagnoses:");
        for (Diagnosis diagnosis : medicalRecord.getDiagnoses()) {
            System.out.println(diagnosis);
        }

        // Display Lab Tests
        System.out.println("\nLab Tests:");
        for (LabTest labTest : medicalRecord.getLabTests()) {
            System.out.println(labTest);
        }

        // Display Treatments
        System.out.println("\nTreatments:");
        for (Treatment treatment : medicalRecord.getTreatments()) {
            System.out.println(treatment);
        }

        // Display Prescriptions
        System.out.println("\nPrescriptions:");
        for (Prescription prescription : medicalRecord.getPrescriptions()) {
            System.out.println(prescription);
        }
        System.out.println("---- End of Medical Record ----");
    }

    public void updatePatientRecord(MedicalRecord medicalRecord, Diagnosis newDiagnosis, Treatment newTreatment, Prescription newPrescription) {
        System.out.println("Updating medical record for patient ID: " + medicalRecord.getPatientID());

        if (newDiagnosis != null) {
            medicalRecord.addDiagnosis(newDiagnosis);
            System.out.println("Added diagnosis: " + newDiagnosis);
        }

        if (newTreatment != null) {
            medicalRecord.addTreatment(newTreatment);
            System.out.println("Added treatment plan: " + newTreatment);
        }

        if (newPrescription != null) {
            medicalRecord.addPrescription(newPrescription);
            System.out.println("Added prescription: " + newPrescription);
        }
        System.out.println("Medical record updated successfully.");
    }

    public void viewPersonalSchedule() {
        System.out.println("Doctor's Schedule:");
        for (String slot : schedule) {
            System.out.println(slot);
        }
    }

    public void setAvailabilityForAppointment(String timeSlot) {
        schedule.add(timeSlot);
        System.out.println("Availability set for: " + timeSlot);
    }

    public void acceptAppointment(String appointmentID) {
        System.out.println("Accepted appointment with ID: " + appointmentID);
    }

    public void declineAppointment(String appointmentID) {
        System.out.println("Declined appointment with ID: " + appointmentID);
    }

    public void viewUpcomingAppointments() {
        System.out.println("Upcoming Appointments:");
    }

    public void recordAppointmentOutcome(String appointmentID, String date, String serviceType, String medicationName, String medicationStatus, String consultationNotes) {
        System.out.println("Recording outcome for appointment ID: " + appointmentID);
        System.out.println("Date: " + date);
        System.out.println("Service Type: " + serviceType);
        System.out.println("Prescribed Medication: " + medicationName + " (Status: " + medicationStatus + ")");
        System.out.println("Consultation Notes: " + consultationNotes);
    }

    @Override
    public boolean login(String enteredPassword) {
        if (this.password.equals(enteredPassword)) {
            this.isLoggedIn = true;
            System.out.println("Login successful for user: " + userID);
            return true;
        } else {
            System.out.println("Login failed: Incorrect password.");
            return false;
        }
    }

    @Override
    public boolean changePassword(String currentPassword, String newPassword) {
        if (!isLoggedIn) {
            System.out.println("Change password failed: Doctor is not logged in.");
            return false;
        }

        if (this.getPassword().equals(currentPassword)) {
            if (newPassword.length() >= 8) {
                setPassword(newPassword);
                updateCSV(); // Update CSV when password changes
                System.out.println("Password changed successfully for Doctor: " + name);
                return true;
            } else {
                System.out.println("New password must be at least 8 characters long.");
                return false;
            }
        } else {
            System.out.println("Change password failed: Current password is incorrect.");
            return false;
        }
    }

    @Override
    public void logout() {
        System.out.println("Logging out doctor: " + name);
    }

    public void updateCSV() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Staff_List.csv", true));
            writer.append("\n");
            writer.append(userID + "," + password + "," + role + "," + doctorID + "," + name + "," + specialisation + "," + gender + "," + age);
            writer.close();
            System.out.println("Doctor data updated in Staff_List.csv.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters and setters
    public String getDoctorID() {
        return doctorID; 
    }
    
    public String getName() { 
        return name; 
    }
    public String getSpecialisation() { 
        return specialisation; 
    }
    
    public List<String> getSchedule() { 
        return schedule; 
    }
    
    public List<Patient> getPatientList() { 
        return patientList; 
    }
    
    public String getGender() { 
        return gender; 
    }
    
    public int getAge() { 
        return age; 
    }

    // public void addPatient(int patientID) {
    //     patientList.add(patientID);
    //     System.out.println("Added patient with ID: " + patientID);
    // }
}
