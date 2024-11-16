package user;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//accessing appointment package
import appointment.*;
//accessing medical record (associating medical records with patient)
import medical.MedicalRecord;
import medical.Prescription;
import medical.Diagnosis;
import medical.LabTest;
import medical.Treatment;

public class Patient extends User {

    private static List<Patient> patientsList = new ArrayList<>(); // Store all patients
    // Attributes
    // private String patientID;    to be removed
    
    private String name;
    private String dateOfBirth;
    private String gender;
    private String bloodType;
    private long phoneNumber; // phone number
    private String email;
    private List<Appointment>appointments;
    private Calendar calendar;
    private MedicalRecord medicalRecord; // Past diagnoses & treatments //to be checked
    // private List<String> appointmentHistory; //to be checked

    // Constructor
    public Patient(Role role, String name, 
                   String dateOfBirth, String gender, String bloodType, long phoneNumber, String email, MedicalRecord medicalRecord) {
        super(generateNewHospitalID(role), role);
        // this.patientID = patientID; to be removed
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bloodType = bloodType;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.appointments=new ArrayList<>();
        this.calendar=new Calendar("November");  // Initialize with a default or specific month
        this.medicalRecord = medicalRecord; //medical records
        // this.appointmentHistory = new ArrayList<>();
        //this.medicalRecord = medicalRecord; //medical records
        patientsList.add(this); //add new patient to list
        updateCSV(); 
    }

    // Constructor to get from CSV files
    public Patient(String hospitalID, String name, String password, String dateOfBirth, String gender, 
                   String bloodType, long phoneNumber, String email, Role role) {
        super(hospitalID, role, password);
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bloodType = bloodType;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.appointments = new ArrayList<>();
        
        // Generate random appointments and medical records
        this.calendar = new Calendar("November");  // Initialize with a default or specific month
        this.medicalRecord = generateRandomMedicalRecord(); // Create a random medical record
        
        // Generate some random appointments
        // generateRandomAppointments();

        patientsList.add(this);
    }

    // Method to generate a random medical record
    private MedicalRecord generateRandomMedicalRecord() {
        // Create random data for medical records
        Random random = new Random();
    
        // Lists to hold random diagnoses, treatments, prescriptions, and lab tests
        ArrayList<Diagnosis> diagnoses = new ArrayList<>();
        ArrayList<Treatment> treatments = new ArrayList<>();
        ArrayList<Prescription> prescriptions = new ArrayList<>();
        ArrayList<LabTest> labTests = new ArrayList<>();
    
        // Add random diagnoses (between 1 and 3)
        for (int i = 0; i < random.nextInt(3) + 1; i++) {
            int diagnosisID = random.nextInt(1000) + 1;  // Random ID between 1 and 1000
            String description = "Diagnosis " + (i + 1);
            String date = "2024-11-" + (random.nextInt(30) + 1);  // Random date in November 2024
            Diagnosis diagnosis = new Diagnosis(diagnosisID, description, date);
            diagnoses.add(diagnosis);
        }
    
        // Add random treatments (between 1 and 2)
        for (int i = 0; i < random.nextInt(2) + 1; i++) {
            String treatmentName = "Treatment " + (i + 1);
            String startDate = "2024-11-" + (random.nextInt(30) + 1);  // Random start date in November 2024
            String endDate = "2024-11-" + (random.nextInt(30) + 1);  // Random end date in November 2024
            if (startDate.compareTo(endDate) > 0) {
                // Ensure end date is after start date
                endDate = startDate;
            }
            Treatment treatment = new Treatment(treatmentName, startDate, endDate);
            treatments.add(treatment);
        }
    
        // Add random prescriptions (between 1 and 2)
        for (int i = 0; i < random.nextInt(2) + 1; i++) {
            int prescriptionID = random.nextInt(1000) + 1;  // Random prescription ID
            String medicationName = "Medication " + (i + 1);
            int dosage = (random.nextInt(10) + 1) * 10;  // Random dosage (multiples of 10)
            Prescription prescription = new Prescription(prescriptionID, medicationName, dosage);
            prescriptions.add(prescription);
        }
    
        // Add random lab tests (between 1 and 2)
        for (int i = 0; i < random.nextInt(2) + 1; i++) {
            int testID = random.nextInt(1000) + 1;  // Random test ID
            String testName = "Lab Test " + (i + 1);
            String result = "Normal";  // You can randomize this if needed
            String date = "2024-11-" + (random.nextInt(30) + 1);  // Random test date in November 2024
            LabTest labTest = new LabTest(testID, testName, result, date);
            labTests.add(labTest);
        }
    
        // Create and return the MedicalRecord object
        return new MedicalRecord(diagnoses, labTests, prescriptions, treatments);
    }
    
    // Method to generate random appointments
    //  private void generateRandomAppointments() {
    //     Random random = new Random();
    //     for (int i = 0; i < random.nextInt(3) + 1; i++) {  // Random between 1 and 3 appointments
    //         // Assume Doctor class exists with available time slots and a method to schedule appointments
    //         Doctor doctor = new Doctor("Doctor " + (i + 1), "Specialty " + (i + 1));  // Create a random doctor
    //         String date = "2024-11-" + (random.nextInt(30) + 1);  // Random date in November 2024
    //         TimeSlot timeSlot = new TimeSlot("10:00 AM");  // Random time slot

    //         // Schedule a random appointment
    //         Appointment appointment = new Appointment(this, doctor, date, timeSlot);
    //         this.appointments.add(appointment);
    //         this.calendar.addAppointment(appointment);
    //     }
    // }

    // Getter method for medical record
    public MedicalRecord getMedicalRecord() {
        return this.medicalRecord;
    }
    

     // Method to view the full medical record details
     public void viewMedicalRecord(Patient patient) {
        if (medicalRecord != null) {
            medicalRecord.viewMedicalRecord(patient);
        } else {
            System.out.println("No medical record available for this patient.");
        }
    }
    

    public void updateDOB(String dateOfBirth){
        this.dateOfBirth=dateOfBirth;
        System.out.println("Patient Date of Birth updated");
        updateCSV();
    }

    public void updateNumber(long phoneNumber){
        this.phoneNumber=phoneNumber;
        System.out.println("Patient Phone Number updated.");
        updateCSV();
    }

    public void updateEmail(String email){
        this.email=email;
        System.out.println("Patient Email Address updated.");
        updateCSV();
    }

    public static void updateCSV() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Data/Patient_List.csv"));
            writer.write("Patient ID,Name,Password,Date of Birth,Gender,Blood Type,Phone Number,Email");
            writer.newLine();
            for (Patient patient : patientsList) {
                writer.write(patient.toCSVFormat());
                writer.newLine();
            }
            writer.close();
            System.out.println("Patient data updated in Patient_List.csv.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String toCSVFormat() {
        return HospitalID + "," + name + ","+ password + "," + dateOfBirth + "," + gender + "," + bloodType + ","
                + phoneNumber + "," + email;
    }

    //methods to book appointment
    // 1. View Available Appointment Slots with Doctors
    public void viewAvailableAppointments(Doctor doctor, String date) {
        List<TimeSlot> availableSlots = doctor.getAvailableTimeSlots(date);
        if (availableSlots.isEmpty()) {
            System.out.println("No available time slots for " + doctor.getName() + " on " + date);
        } else {
            System.out.println("Available time slots with " + doctor.getName() + " on " + date + ":");
            for (TimeSlot slot : availableSlots) {
                System.out.println(slot);
            }
        }
    }

    // 2. Schedule Appointments
    public void scheduleAppointment(Doctor doctor, String date, TimeSlot timeSlot) {
        if (doctor.isSlotAvailable(date, timeSlot)) {
            Appointment newAppointment = new Appointment(this, doctor, date, timeSlot);
            calendar.addAppointment(newAppointment); // Use calendar instance
            appointments.add(newAppointment); // Add to patient's appointments list
            System.out.println("Appointment scheduled with Dr. " + doctor.getName() + " on " + date + " at " + timeSlot);
        } else {
            System.out.println("The selected time slot is already taken. Please choose another slot.");
        }
    }

    // 3. Reschedule Appointments
    public void rescheduleAppointment(Appointment appointment, String newDate, TimeSlot newTimeSlot) {
        if (appointment.getStatus() != AppointmentStatus.PENDING) {
            System.out.println("Cannot reschedule. Appointment is already " + appointment.getStatus());
            return;
        }

        // Check if the new time slot is available with the doctor
        if (appointment.getDoctor().isSlotAvailable(newDate, newTimeSlot)) {
            // Remove the current appointment from the calendar
            calendar.getAppointmentsForDate(appointment.getDate()).remove(appointment);

            // Update the appointment details
            appointment.setDate(newDate);
            appointment.setTimeSlot(newTimeSlot);
            appointment.setStatus(AppointmentStatus.PENDING);  // Reset to pending if rescheduled

            // Add the updated appointment back to the calendar
            calendar.addAppointment(appointment);

            System.out.println("Appointment rescheduled to " + newDate + " at " + newTimeSlot);
        } else {
            System.out.println("The new time slot is unavailable. Please choose a different slot.");
        }
    }

    // 4. Cancel Appointments
    public void cancelAppointment(Appointment appointment) {
        if (appointment.getStatus() == AppointmentStatus.PENDING) {
            appointment.setStatus(AppointmentStatus.CANCELLED);

            // Remove the appointment from the calendar for the specific date
            calendar.getAppointmentsForDate(appointment.getDate()).remove(appointment);

            // Also remove from the patient's list of appointments
            appointments.remove(appointment);

            System.out.println("Appointment with Dr. " + appointment.getDoctor().getName() + " on " 
                            + appointment.getDate() + " has been canceled.");
        } else {
            System.out.println("Appointment cannot be canceled as it is already " + appointment.getStatus());
        }
    }

    // 5. View Appointment Status
    public void viewScheduledAppointments() {
        System.out.println("Scheduled appointments for " + name + ":");
        for (Appointment appointment : appointments) {
            System.out.println("Appointment ID: " + appointment.getAppointmentID() + " | Date: " 
                            + appointment.getDate() + " | Time Slot: " + appointment.getTimeSlot() 
                            + " | Status: " + appointment.getStatus());
        }
    }

    // 6. View appointments for a specific date
    public void viewAppointmentsForDate(String date) {
        List<Appointment> appointmentsOnDate = calendar.getAppointmentsForDate(date);
        if (appointmentsOnDate.isEmpty()) {
            System.out.println("No appointments scheduled for " + date);
        } else {
            for (Appointment app : appointmentsOnDate) {
                System.out.println("Appointment ID: " + app.getAppointmentID() + " | Status: " + app.getStatus());
            }
        }
    }

    // 7. View Appointment Outcome Records
    // public void viewAppointmentOutcomeRecords() {
    //     System.out.println("Appointment Outcome Records for " + name + ":");
    //     for (Appointment appointment : appointments) {
    //         if (appointment.getStatus() == AppointmentStatus.COMPLETED) {
    //             System.out.println("Appointment ID: " + appointment.getAppointmentID() + " | Outcome: " + appointment.getOutcome());
    //         }
    //     }
    // }

    // Override logout method from User class
    @Override
    public boolean login(String enteredPassword) {
        if (this.password.equals(enteredPassword)) {
            this.isLoggedIn = true;
            System.out.println("Login successful for user: " + HospitalID);
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
                updateCSV();
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
        this.isLoggedIn = false;
    }


    //getters
    public List<Appointment> getAppointments() {
        return appointments;
    }

    public static List<Patient> getPatientsList(){
        return patientsList;
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

}
    // Method to view full medical record details
    // public void viewMedicalRecord() {
    //     System.out.println("---- Medical Record for Patient ----");
    //     System.out.println("Patient ID: " + HospitalID);
    //     System.out.println("Name: " + name);
    //     System.out.println("Date of Birth: " + dateOfBirth);
    //     System.out.println("Gender: " + gender);
    //     System.out.println("Blood Type: " + bloodType);
    //     System.out.println("Contact Information:");
    //     System.out.println("    Phone Number: " + phoneNumber);
    //     System.out.println("    Email: " + email);

    //     // Display Diagnoses
    //     System.out.println("\nDiagnoses:");
    //     for (Diagnosis diagnosis : medicalRecord.getDiagnoses()) {
    //         System.out.println(diagnosis); // Assumes Diagnosis has a proper toString() method
    //     }

    //     // Display Lab Tests
    //     System.out.println("\nLab Tests:");
    //     for (LabTest labTest : medicalRecord.getLabTests()) {
    //         System.out.println(labTest); // remove if patient does not need to know lab
    //     }

    //     // Display Treatments
    //     System.out.println("\nTreatments:");
    //     for (Treatment treatment : medicalRecord.getTreatments()) {
    //         System.out.println(treatment); // Uses Treatment's toString() method
    //     }

    //     System.out.println("\nPrescriptions:");
    //     for (Prescription prescription : medicalRecord.getPrescriptions()) {
    //         System.out.println(prescription); // remove if patient does not need to know prescription
    //     }

    //     System.out.println("---- End of Medical Record ----");
    // }

    // // Method to view available appointments
    // public void viewAvailableAppointments() {
    //     // Placeholder: Logic to retrieve and display available appointments
    //     System.out.println("Displaying available appointments.");
    // }

    // // Method to schedule an appointment
    // public void scheduleAppointment(String appointmentDetails) {
    //     appointmentHistory.add(appointmentDetails);
    //     System.out.println("Appointment scheduled: " + appointmentDetails);
    // }

    // // Method to reschedule an appointment
    // public void rescheduleAppointment(String oldAppointment, String newAppointmentDetails) {
    //     appointmentHistory.remove(oldAppointment);
    //     appointmentHistory.add(newAppointmentDetails);
    //     System.out.println("Appointment rescheduled to: " + newAppointmentDetails);
    // }

    // // Method to cancel an appointment
    // public void cancelAppointment(String appointmentDetails) {
    //     appointmentHistory.remove(appointmentDetails);
    //     System.out.println("Appointment canceled: " + appointmentDetails);
    // }

    // // Method to view scheduled appointments
    // public void viewScheduledAppointments() {
    //     System.out.println("Scheduled Appointments:");
    //     for (String appointment : appointmentHistory) {
    //         System.out.println(appointment);
    //     }
    // }

    // // Method to view past appointment outcome records
    // public void viewPastAppointmentOutcomeRecords() {
    //     // Placeholder: Logic to retrieve and display past appointment outcomes
    //     System.out.println("Displaying past appointment outcome records.");
    // }

    

    // Getters and Setters (if needed)

    // public MedicalRecord getMedicalRecord() {
    //     return this.medicalRecord;
    // }

    // public List<String> getAppointmentHistory() {
    //     return appointmentHistory;
    // }
