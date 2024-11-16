package hmsApp;

import user.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import appointment.Appointment;
import appointment.AppointmentOutcomeRecord;
import appointment.AppointmentStatus;
import appointment.MedicationRecord;
import appointment.TimeSlot;

public class PharmacistUI {

    private Pharmacist pharmacist;
    private Scanner scanner;

    List<Patient> patientsList = new ArrayList<>();

    public PharmacistUI(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n--- Pharmacist Menu ---");
            System.out.println("1. View Appointment Outcome Record");
            System.out.println("2. Update Prescription Status");
            System.out.println("3. View Medication Inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    viewAppointmentOutcomeRecord();
                    break;
                case 2:
                    viewAndUpdatePrescriptionStatus();
                    break;
                case 3:
                    viewMedicationInventory();
                    break;
                case 4:
                    submitReplenishmentRequest();
                    break;
                case 5:
                    logout();
                    return; // exit the UI
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void viewAppointmentOutcomeRecord() {
        // Delegate the responsibility to the Pharmacist class
        pharmacist.viewAppointmentOutcomeRecord();
    }
    

//     private void viewAppointmentOutcomeRecord() {
//     // Create example patients with appointment data
//         Patient patient1 = new Patient("PA0000", "John Doe", "password", "1/1/1985", "Male", "O+", 1234567890L, "johndoe@example.com", Role.PATIENT);
//         Patient patient2 = new Patient("PA0001", "Jack", "password", "1/1/1999", "Male", "O+", 1234567890L, "johndoe@example.com", Role.PATIENT);

//      // Add patients to the list
//         patientsList.add(patient1);
//         patientsList.add(patient2);

//     // Create a completed appointment for patient1
//         TimeSlot timeSlot = new TimeSlot("10:00 AM", "11:00 AM"); // Example times
//         Doctor doctor = new Doctor(Role.DOCTOR, "DR JOE", "MALE", 30,patientsList);
//         Appointment appointment = new Appointment(patient1, doctor, "2024-11-17", timeSlot);
//         appointment.confirmAppointment(); // Confirm the appointment
//         appointment.completeAppointment("General Checkup", new ArrayList<>(), "Routine examination completed."); // Complete the appointment

//         patient1.scheduleAppointment(doctor, "2024-11-17", timeSlot); // Assuming a method addAppointment() exists in the Patient class

   

//         System.out.println("\n--- View Appointment Outcome Record ---");
//         System.out.print("Enter Patient ID: ");
//         String patientId = scanner.nextLine();
//         Patient patient = getPatientById(patientId); // Method to retrieve the Patient object

//         if (patient != null) {
//         // Find the latest completed appointment with an outcome record
//             Appointment latestCompletedAppointment = null;
//             for (Appointment appt : patient.getAppointments()) { // Assuming getAppointments() exists in Patient class
//                 if (appt.getStatus() == AppointmentStatus.COMPLETED && appt.getOutcomeRecord() != null) {
//                     latestCompletedAppointment = appt;
//                     break; // Get the most recent one
//                 }
//             }

//         if (latestCompletedAppointment != null) {
//             System.out.println("Appointment Outcome Record:");
//             System.out.println(latestCompletedAppointment.getOutcomeRecord()); // Print outcome details
//         } else {
//             System.out.println("No completed appointments with an outcome record for this patient.");
//         }
//     } else {
//         System.out.println("Patient not found.");
//     }
// }


    public void viewAndUpdatePrescriptionStatus() {
    // Get the list of doctors to retrieve their appointments
    List<Doctor> doctorsList = Doctor.getDoctorsList(); // Assuming this method exists to get all doctors
    
    if (doctorsList.isEmpty()) {
        System.out.println("No doctors available to view appointment outcomes.");
        return;
    }

    System.out.println("--- All Doctors' Appointment Outcome Records ---");

    // Loop through each doctor to display the appointment outcome records
    for (Doctor doctor : doctorsList) {
        List<Appointment> doctorAppointments = doctor.getCalendar().getAppointmentsForDoctor(doctor); // Get appointments for each doctor
        System.out.println("Appointment Outcomes for Dr. " + doctor.getName() + ":");

        if (doctorAppointments.isEmpty()) {
            System.out.println("  No appointments scheduled for this doctor.");
        } else {
            // Create a list to hold appointments with outcome records for easier selection
            List<Appointment> appointmentsWithOutcome = new ArrayList<>();
            for (Appointment app : doctorAppointments) {
                AppointmentOutcomeRecord outcomeRecord = app.getOutcomeRecord(); // Get the outcome record for each appointment

                if (outcomeRecord == null) {
                    System.out.println("  Appointment ID: " + app.getAppointmentID() + " | No outcome recorded yet.");
                } else {
                    // Display outcome record details
                    System.out.println("  Appointment ID: " + app.getAppointmentID() +
                                       " | Patient: " + app.getPatient().getName() +
                                       " | Date: " + app.getDate() +
                                       " | Outcome Service Type: " + outcomeRecord.getServiceType() +
                                       " | Prescription Status: " + outcomeRecord.getPrescriptionStatus());
                    System.out.println("    Prescribed Medications: ");
                    for (MedicationRecord medication : outcomeRecord.getPrescribedMedications()) {
                        System.out.println("      - " + medication.getMedicationName());
                    }
                    System.out.println("    Consultation Notes: " + outcomeRecord.getConsultationNotes());

                    // Add the appointment to the list for further selection
                    appointmentsWithOutcome.add(app);
                }
            }

            // If there are appointments with outcome records, let the pharmacist select one to update
            if (!appointmentsWithOutcome.isEmpty()) {
                System.out.print("Enter Appointment ID to update prescription status: ");
                int appointmentId = scanner.nextInt();
                
                // Find the selected appointment
                Appointment selectedAppointment = null;
                for (Appointment app : appointmentsWithOutcome) {
                    if (app.getAppointmentID()==appointmentId) {
                        selectedAppointment = app;
                        break;
                    }
                }

                if (selectedAppointment != null) {
                    // Get the appointment outcome record and update prescription status
                    AppointmentOutcomeRecord selectedOutcomeRecord = selectedAppointment.getOutcomeRecord();
                    pharmacist.updatePrescriptionStatus(selectedOutcomeRecord);
                } else {
                    System.out.println("Invalid Appointment ID.");
                }
            } else {
                System.out.println("No completed appointments with outcome records for this doctor.");
            }
        }
        System.out.println(); // Add a line break between doctors
    }
}


    private void viewMedicationInventory() {
        System.out.println("\n--- View Medication Inventory ---");
        pharmacist.viewMedicationInventory();
    }

    private void submitReplenishmentRequest() {
        System.out.println("\n--- Submit Replenishment Request ---");
        System.out.print("Enter Medication Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Quantity to Replenish: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        Administrator admin = new Administrator(Role.ADMINISTRATOR, "John Doe", "Male", 35);// Placeholder; replace with actual administrator reference
        pharmacist.sendReplenishmentRequest(name, quantity);
    }

    private void logout() {
        pharmacist.logout();
        System.out.println("\nLogging out. Goodbye, " + pharmacist.getName() + "!");
    }

    private Patient getPatientById(String patientId) {
        
        
        // Loop through the list and find a patient with the matching hospital ID
        for (Patient patient : patientsList) {
            if (patient.getHospitalID().equals(patientId)) {
                return patient; // Return the matching patient
            }
        }
        
        // Return null if no matching patient is found
        return null;
    }
    

    public static void main(String[] args) {
        Pharmacist pharmacist = new Pharmacist(Role.PHARMACIST, "Pharmacist User", "Female", 30);
        PharmacistUI ui = new PharmacistUI(pharmacist);
        ui.showMenu();
    }
}
