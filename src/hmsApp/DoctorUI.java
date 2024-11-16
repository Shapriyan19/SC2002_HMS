package hmsApp;


import java.util.Scanner;
import medical.MedicalRecord;
import appointment.Appointment;
import appointment.AppointmentStatus;
import appointment.MedicationRecord;
import user.Doctor;
import user.Patient;

import java.util.ArrayList;
import java.util.List;

public class DoctorUI {

    private Doctor doctor;
    private Scanner scanner;

    public DoctorUI(Doctor doctor) {
        this.doctor = doctor;
        this.scanner = new Scanner(System.in);
    }

    // Main menu for Doctor's UI
    public void showMenu() {
        int choice;
        do {
            System.out.println("\n-- Doctor Menu --");
            System.out.println("1. View Patient Medical Records");
            System.out.println("2. Update Patient Medical Records");
            System.out.println("3. View Personal Schedule");
            System.out.println("4. Set Availability for Appointments");
            System.out.println("5. Accept or Decline Appointment Requests");
            System.out.println("6. View Upcoming Appointments");
            System.out.println("7. Record Appointment Outcome");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewPatientMedicalRecords();
                    break;
                case 2:
                    updatePatientMedicalRecords();
                    break;
                case 3:
                    viewPersonalSchedule();
                    break;
                case 4:
                    setAvailabilityForAppointments();
                    break;
                case 5:
                    acceptOrDeclineAppointments();
                    break;
                case 6:
                    viewUpcomingAppointments();
                    break;
                case 7:
                    recordAppointmentOutcome();
                    break;
                case 8:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
    }

    // 1. View Patient Medical Records
    private void viewPatientMedicalRecords() {
        System.out.print("Enter the Hospital ID of the patient: ");
        int hospitalID = scanner.nextInt();
        Patient patient = getPatientByHospitalID(hospitalID); // Assuming a method to get a patient by ID
        if (patient != null) {
            MedicalRecord medicalRecord = patient.getMedicalRecord();
            medicalRecord.viewMedicalRecord(patient);
        } else {
            System.out.println("Patient not found.");
        }
    }

    // 2. Update Patient Medical Records
    private void updatePatientMedicalRecords() {
        System.out.print("Enter the Hospital ID of the patient to update records: ");
        int hospitalID = scanner.nextInt();
        Patient patient = getPatientByHospitalID(hospitalID); // Assuming a method to get a patient by ID
        if (patient != null) {
            MedicalRecord medicalRecord = patient.getMedicalRecord();
            // Collect new data (e.g., diagnosis, lab tests) and update record
            System.out.println("Updating medical records for " + patient.getName());
            // Example: Collect new diagnoses and add to medical record
            // medicalRecord.updateMedicalRecord(newDiagnoses, newLabTests, newPrescriptions, newTreatments);
        } else {
            System.out.println("Patient not found.");
        }
    }

    // 3. View Personal Schedule
    private void viewPersonalSchedule() {
        doctor.displayAppointments();
    }

    // 4. Set Availability for Appointments
    private void setAvailabilityForAppointments() {
        System.out.println("Setting availability...");
        // Example: prompt doctor to choose time slots or set availability for specific dates
        System.out.println("Enter the start and end time of availability (HH:mm): ");
        String startTime = scanner.next();
        String endTime = scanner.next();
        // Update availability logic (perhaps modify doctor’s schedule)
    }

    // 5. Accept or Decline Appointment Requests
    private void acceptOrDeclineAppointments() {
        System.out.print("Enter the appointment ID to manage: ");
        int appointmentID = scanner.nextInt();
        Appointment appointment = getAppointmentByID(appointmentID); // Assuming method to fetch appointment
        if (appointment != null) {
            System.out.println("1. Confirm Appointment");
            System.out.println("2. Cancel Appointment");
            int decision = scanner.nextInt();
            if (decision == 1) {
                appointment.setStatus(AppointmentStatus.CONFIRMED); // Assuming AppointmentStatus enum
                System.out.println("Appointment accepted.");
            } else if (decision == 2) {
                appointment.setStatus(AppointmentStatus.CANCELLED);
                System.out.println("Appointment declined.");
            }
        } else {
            System.out.println("Appointment not found.");
        }
    }

    // 6. View Upcoming Appointments
    private void viewUpcomingAppointments() {
        List<Appointment> appointments = doctor.getAppointments(); // Assuming method to get doctor's appointments
        if (appointments.isEmpty()) {
            System.out.println("No upcoming appointments.");
        } else {
            System.out.println("-- Upcoming Appointments --");
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        }
    }

    // 7. Record Appointment Outcome
    private void recordAppointmentOutcome() {
        System.out.print("Enter the appointment ID to record outcome: ");
        int appointmentID = scanner.nextInt();
        Appointment appointment = getAppointmentByID(appointmentID); // Assuming method to fetch appointment
        if (appointment != null) {
            // Check if the appointment is completed before recording outcome
            if (appointment.getStatus() == AppointmentStatus.COMPLETED) {
                scanner.nextLine();  // Consume newline character

                // Input service type
                System.out.print("Enter the service type for this appointment: ");
                String serviceType = scanner.nextLine();

                // Input prescribed medications (this can be a list, so we will collect multiple medications)
                List<MedicationRecord> prescribedMedications = new ArrayList<>();
                System.out.println("Enter prescribed medications (type 'done' to finish):");
                while (true) {
                    System.out.print("Medication Name: ");
                    String medicationName = scanner.nextLine();
                    if (medicationName.equalsIgnoreCase("done")) {
                        break;
                    }
                    System.out.print("Dosage (mg): ");
                    String dosage = scanner.nextLine();
                    scanner.nextLine(); // Consume the newline character
                    prescribedMedications.add(new MedicationRecord(medicationName, dosage));
                }

                // Input consultation notes
                System.out.print("Enter consultation notes: ");
                String consultationNotes = scanner.nextLine();

                // Set the outcome record
                appointment.setOutcomeRecord(serviceType, prescribedMedications, consultationNotes);
                System.out.println("Outcome recorded successfully.");
            } else {
                System.out.println("Appointment must be completed before adding an outcome record.");
            }
        } else {
            System.out.println("Appointment not found.");
        }
    }


    // Helper methods to get Patient and Appointment (mock implementations)
    private Patient getPatientByHospitalID(int hospitalID) {
        // Implement logic to fetch patient by hospital ID from the database or list
        return null; // Mock return for now
    }

    private Appointment getAppointmentByID(int appointmentID) {
        // Implement logic to fetch appointment by ID from the database or list
        return null; // Mock return for now
    }
}


// import appointment.*;
// import user.*;

// import java.util.Scanner;
// import java.util.List;

// public class DoctorUI {

//     private Doctor doctor;
//     private Scanner scanner;

//     public DoctorUI(Doctor doctor) {
//         this.doctor = doctor;
//         this.scanner = new Scanner(System.in);
//     }

//     public void showMenu() {
//         while (true) {
//             System.out.println("\n--- Doctor's Dashboard ---");
//             System.out.println("1. View Personal Schedule");
//             System.out.println("2. Set Availability for Appointments");
//             System.out.println("3. Accept or Decline Appointment Requests");
//             System.out.println("4. View Upcoming Appointments");
//             System.out.println("5. Record Appointment Outcome");
//             System.out.println("6. Logout");
//             System.out.print("Select an option: ");
            
//             int choice = scanner.nextInt();
//             scanner.nextLine(); // consume the newline

//             switch (choice) {
//                 case 1:
//                     viewPersonalSchedule();
//                     break;
//                 case 2:
//                     setAvailability();
//                     break;
//                 case 3:
//                     manageAppointmentRequests();
//                     break;
//                 case 4:
//                     viewUpcomingAppointments();
//                     break;
//                 case 5:
//                     recordAppointmentOutcome();
//                     break;
//                 case 6:
//                     logout();
//                     return; // exit the UI
//                 default:
//                     System.out.println("Invalid choice. Please try again.");
//             }
//         }
//     }

//     private void viewPersonalSchedule() {
//         List<Appointment> appointments = doctor.getAppointments();
//         if (appointments.isEmpty()) {
//             System.out.println("No appointments in your schedule.");
//         } else {
//             System.out.println("Your Schedule:");
//             for (Appointment appointment : appointments) {
//                 System.out.println("Appointment ID: " + appointment.getAppointmentID() +
//                         " | Patient: " + appointment.getPatient().getName() +
//                         " | Date: " + appointment.getDate() +
//                         " | Time: " + appointment.getTimeSlot().toString());
//             }
//         }
//     }

//     private void setAvailability() {
//         System.out.print("Enter the date (yyyy-mm-dd) to set availability: ");
//         String date = scanner.nextLine();
//         System.out.print("Enter the start time (hh:mm): ");
//         String startTime = scanner.nextLine();
//         System.out.print("Enter the end time (hh:mm): ");
//         String endTime = scanner.nextLine();

//         // Assuming TimeSlot is set properly
//         TimeSlot timeSlot = new TimeSlot(startTime, endTime);

//         if (doctor.isSlotAvailable(date, timeSlot)) {
//             System.out.println("Slot available for " + date + " from " + startTime + " to " + endTime);
//             // Further logic to save this availability can be added here
//         } else {
//             System.out.println("Slot not available. Please try a different time.");
//         }
//     }

//     private void manageAppointmentRequests() {
//         System.out.println("Pending appointment requests:");
//         List<Appointment> appointments = doctor.getAppointments();
//         for (Appointment appointment : appointments) {
//             if (appointment.getStatus() == AppointmentStatus.PENDING) {
//                 System.out.println("Appointment ID: " + appointment.getAppointmentID() +
//                         " | Patient: " + appointment.getPatient().getName() +
//                         " | Date: " + appointment.getDate() +
//                         " | Time: " + appointment.getTimeSlot().toString());
//                 System.out.println("1. Accept");
//                 System.out.println("2. Decline");
//                 System.out.print("Select an option: ");
//                 int choice = scanner.nextInt();
//                 scanner.nextLine(); // consume newline

//                 if (choice == 1) {
//                     doctor.confirmAppointment(appointment);
//                 } else if (choice == 2) {
//                     doctor.cancelAppointment(appointment);
//                 } else {
//                     System.out.println("Invalid choice. Appointment not modified.");
//                 }
//             }
//         }
//     }

//     private void viewUpcomingAppointments() {
//         System.out.println("Upcoming Appointments:");
//         doctor.displayAppointments();
//     }

//     private void recordAppointmentOutcome() {
//         System.out.print("Enter the Appointment ID to record outcome: ");
//         int appointmentId = scanner.nextInt();
//         scanner.nextLine(); // consume newline

//         // Find the appointment with the given ID
//         Appointment appointment = null;
//         for (Appointment app : doctor.getAppointments()) {
//             if (app.getAppointmentID() == appointmentId) {
//                 appointment = app;
//                 break;
//             }
//         }

//         if (appointment != null) {
//             System.out.println("Recording outcome for appointment with " + appointment.getPatient().getName());
//             System.out.print("Enter the service provided (e.g., consultation, X-ray): ");
//             String service = scanner.nextLine();

//             System.out.print("Enter prescribed medications (comma-separated): ");
//             String medicationsInput = scanner.nextLine();
//             String[] medications = medicationsInput.split(",");

//             System.out.print("Enter consultation notes: ");
//             String notes = scanner.nextLine();

//             // Update the appointment outcome (this could involve more complex logic in real-world apps)
//             appointment.setOutcome(service, medications, notes);
//             System.out.println("Outcome recorded successfully.");
//         } else {
//             System.out.println("Appointment ID not found.");
//         }
//     }

//     private void logout() {
//         System.out.println("Logging out... Goodbye, Dr. " + doctor.getName() + "!");
//         doctor.logout();
//     }
// }

