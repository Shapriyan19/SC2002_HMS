package user;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//accessing medical record (associating medical records with patient)
import medical.MedicalRecord;
import medical.Prescription;
import medical.Diagnosis;
import medical.LabTest;
import medical.Treatment;

public class Patient extends User {

    // Attributes
    private String patientID;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String bloodType;
    private double phoneNumber; // phone number
    private String email;
    private MedicalRecord medicalRecord; // Past diagnoses & treatments
    private List<String> appointmentHistory;

    // Constructor
    public Patient(String userID, String password, Role role, String patientID, String name, 
                   String dateOfBirth, String gender, String bloodType, double phoneNumber, String email) {
        super(userID, password, role);
        this.patientID = patientID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bloodType = bloodType;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.medicalRecord = new MedicalRecord(patientID); //medical records
        this.appointmentHistory = new ArrayList<>();
    }

    // Method to update personal information and update the Patient_List.csv
    public void updatePersonalInformation(String name, double phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        System.out.println("Personal information updated.");
        
        // Update Patient_List.csv with the new information
        updatePatientListCSV();
    }

    // Method to update the Patient_List.csv
    private void updatePatientListCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/Patient_List.csv", true))) {
            // Write the updated patient information to the file
            String patientData = String.join(",", patientID, name, dateOfBirth, gender, bloodType,
                    String.valueOf(phoneNumber), email) + "\n";
            writer.append(patientData);
        } catch (IOException e) {
            System.out.println("Error updating the Patient_List.csv file: " + e.getMessage());
        }
    }

    // Method to view full medical record details
    public void viewMedicalRecord() {
        System.out.println("---- Medical Record for Patient ----");
        System.out.println("Patient ID: " + patientID);
        System.out.println("Name: " + name);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Gender: " + gender);
        System.out.println("Blood Type: " + bloodType);
        System.out.println("Contact Information:");
        System.out.println("    Phone Number: " + phoneNumber);
        System.out.println("    Email: " + email);

        // Display Diagnoses
        System.out.println("\nDiagnoses:");
        for (Diagnosis diagnosis : medicalRecord.getDiagnoses()) {
            System.out.println(diagnosis); // Assumes Diagnosis has a proper toString() method
        }

        // Display Lab Tests
        System.out.println("\nLab Tests:");
        for (LabTest labTest : medicalRecord.getLabTests()) {
            System.out.println(labTest); // remove if patient does not need to know lab
        }

        // Display Treatments
        System.out.println("\nTreatments:");
        for (Treatment treatment : medicalRecord.getTreatments()) {
            System.out.println(treatment); // Uses Treatment's toString() method
        }

        System.out.println("\nPrescriptions:");
        for (Prescription prescription : medicalRecord.getPrescriptions()) {
            System.out.println(prescription); // remove if patient does not need to know prescription
        }

        System.out.println("---- End of Medical Record ----");
    }

    // Method to view available appointments
    public void viewAvailableAppointments() {
        // Placeholder: Logic to retrieve and display available appointments
        System.out.println("Displaying available appointments.");
    }

    // Method to schedule an appointment
    public void scheduleAppointment(String appointmentDetails) {
        appointmentHistory.add(appointmentDetails);
        System.out.println("Appointment scheduled: " + appointmentDetails);
    }

    // Method to reschedule an appointment
    public void rescheduleAppointment(String oldAppointment, String newAppointmentDetails) {
        appointmentHistory.remove(oldAppointment);
        appointmentHistory.add(newAppointmentDetails);
        System.out.println("Appointment rescheduled to: " + newAppointmentDetails);
    }

    // Method to cancel an appointment
    public void cancelAppointment(String appointmentDetails) {
        appointmentHistory.remove(appointmentDetails);
        System.out.println("Appointment canceled: " + appointmentDetails);
    }

    // Method to view scheduled appointments
    public void viewScheduledAppointments() {
        System.out.println("Scheduled Appointments:");
        for (String appointment : appointmentHistory) {
            System.out.println(appointment);
        }
    }

    // Method to view past appointment outcome records
    public void viewPastAppointmentOutcomeRecords() {
        // Placeholder: Logic to retrieve and display past appointment outcomes
        System.out.println("Displaying past appointment outcome records.");
    }

    // Override logout method from User class
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
            System.out.println("Change password failed: Patient is not logged in.");
            return false;
        }

        if (this.getPassword().equals(currentPassword)) {
            if (newPassword.length() >= 8) { // Basic validation for password length
                setPassword(newPassword);
                System.out.println("Password changed successfully for Patient: " + name);
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
        System.out.println("Logging out patient: " + name);
    }

    // Getters and Setters (if needed)

    public MedicalRecord getMedicalRecord() {
        return this.medicalRecord;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getBloodType() {
        return bloodType;
    }

    public double getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getAppointmentHistory() {
        return appointmentHistory;
    }
}
