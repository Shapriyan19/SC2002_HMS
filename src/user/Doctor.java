package user;

import java.util.ArrayList;
import java.util.List;

import medical.MedicalRecord;
import medical.Diagnosis;
import medical.LabTest;
import medical.Prescription;
import medical.Treatment;
import medical.PrescriptionStatus;

public class Doctor extends User {

    // Instance variables
    private String doctorID;
    private String name;
    private String specialisation;
    private List<String> schedule; // List of available time slots
    private List<Patient> patientList;// List of patient IDs

    // Constructor
    public Doctor(String userID, String password, Role role, String doctorID, String name, 
                  String specialisation) {
        super(userID, password, role);
        this.doctorID = doctorID;
        this.name = name;
        this.specialisation = specialisation;
        this.schedule = new ArrayList<>();
        this.patientList = new ArrayList<>();
    }
        // Method to add a patient to the doctor's patient list (for setup)
        public void addPatient(Patient patient) {
            patientList.add(patient);
            System.out.println("Added patient with ID: " + patient.getPatientID());
        }
    
        // Expanded getPatientById method to retrieve a Patient by their ID
        private Patient getPatientById(String patientID) {
            for (Patient patient : patientList) {
                if (patient.getPatientID().equals(patientID)) {
                    return patient;
                }
            }
            System.out.println("Patient with ID " + patientID + " not found.");
            return null; // Returns null if patient is not found
        }
    
        // Expanded getMedicalRecordByPatient method
        private MedicalRecord getMedicalRecordByPatient(Patient patient) {
            if (patient != null) {
                return patient.getMedicalRecord(); // Access the patient's MedicalRecord
            }
            System.out.println("No medical record found for the provided patient.");
            return null;
        }

    // Method to view a patient's medical record
    public void viewPatientRecord(String patientID) { //only using PatientID
        Patient patient = getPatientById(patientID); // Retrieve Patient object by ID

        if (patient == null) {
            System.out.println("No patient found with ID: " + patientID);
            return;
        }
    
        MedicalRecord medicalRecord = getMedicalRecordByPatient(patient);
    
        System.out.println("---- Medical Record for Patient ----");
        System.out.println("Patient ID: " + patientID);
        
        // Display patient details from the Patient object
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
            System.out.println(diagnosis); // Assumes Diagnosis has a proper toString() method
        }

        // Display Lab Tests
        System.out.println("\nLab Tests:");
        for (LabTest labTest : medicalRecord.getLabTests()) {
            System.out.println(labTest); // Assumes LabTest has a proper toString() method
        }

        // Display Treatments
        System.out.println("\nTreatments:");
        for (Treatment treatment : medicalRecord.getTreatments()) {
            System.out.println(treatment); // Uses Treatment's toString() method
        }

        // Display Prescriptions
        System.out.println("\nPrescriptions:")
        for (Prescription prescription : medicalRecord.getPrescriptions()) {
            System.out.println(prescription); // Assumes Prescription has a proper toString() method
        }

        System.out.println("---- End of Medical Record ----");
    }

    

    // Method to update a patient's medical record
    public void updatePatientRecord(String patientID, String diagnosis, String treatment) {
        System.out.println("Updating medical record for patient ID: " + patientID);
        // Implement logic to update patient's medical record with diagnosis and treatment
    }

    // Method to view the doctor's personal schedule
    public void viewPersonalSchedule() {
        System.out.println("Doctor's Schedule:");
        for (String slot : schedule) {
            System.out.println(slot);
        }
    }

    // Method to set availability for appointments
    public void setAvailabilityForAppointment(String timeSlot) {
        schedule.add(timeSlot);
        System.out.println("Availability set for: " + timeSlot);
    }

    // Method to accept an appointment
    public void acceptAppointment(String appointmentID) {
        System.out.println("Accepted appointment with ID: " + appointmentID);
        // Implement logic to accept the appointment
    }

    // Method to decline an appointment
    public void declineAppointment(String appointmentID) {
        System.out.println("Declined appointment with ID: " + appointmentID);
        // Implement logic to decline the appointment
    }

    // Method to view upcoming appointments
    public void viewUpcomingAppointments() {
        System.out.println("Upcoming Appointments:");
        // Implement logic to retrieve and display upcoming appointments
    }

    // Method to record the outcome of an appointment
    public void recordAppointmentOutcome(String appointmentID, String date, String serviceType,String medicationName, String medicationStatus, String consultationNotes) {
        System.out.println("Recording outcome for appointment ID: " + appointmentID);
        System.out.println("Date: " + date);
        System.out.println("Service Type: " + serviceType);
        System.out.println("Prescribed Medication: " + medicationName + " (Status: " + medicationStatus + ")");
        System.out.println("Consultation Notes: " + consultationNotes);
        // Implement logic to record appointment outcome
    }

    // Login method implementation
    @Override
    public boolean login(String enteredPassword) {
        if (this.password.equals(enteredPassword)) {
            this.isLoggedIn = true;
            System.out.println("Login successful for user: " + userID);
            return true;
        } else {
            System.out.println("Login failed: Incorrect password.");
            return false;}
        }

    // Change password method implementation
   
    @Override
    public boolean changePassword(String currentPassword, String newPassword) {
        if (!isLoggedIn) {
            System.out.println("Change password failed: Doctor is not logged in.");
            return false;
        }

        if (this.getPassword().equals(currentPassword)) {
            if (newPassword.length() >= 8) { // Basic validation for password length
                setPassword(newPassword);
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

    // Logout method implementation
        
    @Override
    public void logout() {
        System.out.println("Logging out doctor: " + name);
    }

    // Getters and Setters (if needed)
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

    // public void addPatient(String patientID) {
    //     patientList.add(patientID);
    //     System.out.println("Added patient with ID: " + patientID);
    // }
}
