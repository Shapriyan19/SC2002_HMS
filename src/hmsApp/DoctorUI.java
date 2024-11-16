package hmsApp;


import java.util.Scanner;

import medical.Diagnosis;
import medical.LabTest;
import medical.MedicalRecord;
import medical.Prescription;
import medical.Treatment;
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
        String hospitalID = scanner.next();
        
        // Call the method in Doctor class to view medical record by patient ID
        doctor.viewPatientMedicalRecordByID(hospitalID);  // Correct method call
    }


    // 2. Update Patient Medical Records
    private void updatePatientMedicalRecords() {
        System.out.print("Enter the Hospital ID of the patient to update records: ");
        String hospitalID = scanner.next();
        Patient patient = getPatientByHospitalID(hospitalID); // Assuming a method to get a patient by ID
    
        if (patient != null) {
            MedicalRecord medicalRecord = patient.getMedicalRecord();
            if (medicalRecord == null) {
                System.out.println("Medical record not found for patient: " + patient.getName());
                return;
            }
    
            System.out.println("Updating medical records for " + patient.getName());
    
            // Collect new diagnoses
            ArrayList<Diagnosis> newDiagnoses = new ArrayList<>();
            int existingDiagnoses = medicalRecord.getDiagnoses().size();
            System.out.println("Number of existing diagnoses: " + existingDiagnoses);
            System.out.print("Enter the number of new diagnoses to add: ");
            int numDiagnoses = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            for (int i = 0; i < numDiagnoses; i++) {
                System.out.println("Diagnosis ID: " + (existingDiagnoses + i + 1));
                System.out.print("Enter diagnosis detail: ");
                String diagnosisDetail = scanner.nextLine();
                System.out.print("Enter diagnosis date (YYYY-MM-DD): ");
                String date = scanner.nextLine();
                newDiagnoses.add(new Diagnosis((existingDiagnoses + i + 1), diagnosisDetail, date));
            }
    
            // Collect new lab tests
            ArrayList<LabTest> newLabTests = new ArrayList<>();
            int existingLabTests = medicalRecord.getLabTests().size();
            System.out.println("Number of existing lab tests: " + existingLabTests);
            System.out.print("Enter the number of new lab tests to add: ");
            int numLabTests = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            for (int i = 0; i < numLabTests; i++) {
                System.out.println("Lab test ID: " + (existingLabTests + i + 1));
                System.out.print("Enter test name: ");
                String testName = scanner.nextLine();
                System.out.print("Enter result: ");
                String result = scanner.nextLine();
                System.out.print("Enter test date (YYYY-MM-DD): ");
                String date = scanner.nextLine();
                newLabTests.add(new LabTest((existingLabTests + i + 1), testName, result, date));
            }
    
            // Collect new prescriptions
            ArrayList<Prescription> newPrescriptions = new ArrayList<>();
            int existingPrescriptions = medicalRecord.getPrescriptions().size();
            System.out.println("Number of existing prescriptions: " + existingPrescriptions);
            System.out.print("Enter the number of new prescriptions to add: ");
            int numPrescriptions = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            for (int i = 0; i < numPrescriptions; i++) {
                System.out.println("Prescription ID: " + (existingPrescriptions + i + 1));
                System.out.print("Enter medication name: ");
                String medicationName = scanner.nextLine();
                System.out.print("Enter dosage: ");
                int dosage = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                newPrescriptions.add(new Prescription((existingPrescriptions + i + 1), medicationName, dosage));
            }
    
            // Collect new treatments
            ArrayList<Treatment> newTreatments = new ArrayList<>();
            int existingTreatments = medicalRecord.getTreatments().size();
            System.out.println("Number of existing treatments: " + existingTreatments);
            System.out.print("Enter the number of new treatments to add: ");
            int numTreatments = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            for (int i = 0; i < numTreatments; i++) {
                System.out.println("Treatment ID: " + (existingTreatments + i + 1));
                System.out.print("Enter treatment name: ");
                String treatmentName = scanner.nextLine();
                System.out.print("Enter start date (YYYY-MM-DD): ");
                String startDate = scanner.nextLine();
                System.out.print("Enter end date (YYYY-MM-DD): ");
                String endDate = scanner.nextLine();
                if (startDate.compareTo(endDate) > 0) {
                    System.out.println("Start date is after end date; adjusting end date to match start date.");
                    endDate = startDate;
                }
                newTreatments.add(new Treatment(treatmentName, startDate, endDate));
            }
    
            // Update the medical record
            medicalRecord.updateMedicalRecord(newDiagnoses, newLabTests, newPrescriptions, newTreatments);
            System.out.println("Medical records updated successfully for " + patient.getName());
    
        } else {
            System.out.println("Patient with Hospital ID " + hospitalID + " not found.");
        }
    }
    
    // 3. View Personal Schedule
    private void viewPersonalSchedule() {
        List<Appointment> appointments = doctor.getAppointments(); // Assuming this returns all appointments for the doctor
        List<Appointment> confirmedAppointments = new ArrayList<>();
    
        // Filter out confirmed appointments
        for (Appointment appointment : appointments) {
            if (appointment.getStatus() == AppointmentStatus.CONFIRMED) { // Assuming AppointmentStatus is an enum with CONFIRMED status
                confirmedAppointments.add(appointment);
            }
        }
    
        if (confirmedAppointments.isEmpty()) {
            System.out.println("No confirmed upcoming appointments.");
        } else {
            System.out.println("-- Confirmed Upcoming Appointments --");
            for (Appointment appointment : confirmedAppointments) {
                System.out.println(appointment);
            }
        }
    }
    

    // 4. Set Availability for Appointments
    private void setAvailabilityForAppointments() {
        System.out.println("Setting availability...");
    
        // Prompt the doctor to enter the new availability range
        System.out.print("Enter the start time of availability (HH:mm): ");
        String startTime = scanner.next();
        System.out.print("Enter the end time of availability (HH:mm): ");
        String endTime = scanner.next();
    
        // Validate and update the time slots
        if (isValidTime(startTime) && isValidTime(endTime) && startTime.compareTo(endTime) < 0) {
            doctor.getCalendar().setAppointmentTime(startTime, endTime); // Update the calendar's time slots
            System.out.println("Availability updated successfully from " + startTime + " to " + endTime + ".");
        } else {
            System.out.println("Invalid time range. Please try again.");
        }
    }
    
    // Helper method to validate time format (HH:mm)
    private boolean isValidTime(String time) {
        try {
            String[] parts = time.split(":");
            if (parts.length != 2) return false;
    
            int hour = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);
            return hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59;
        } catch (NumberFormatException e) {
            return false;
        }
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
        doctor.displayAppointments();
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
                    int dosage = scanner.nextInt();
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
    private Patient getPatientByHospitalID(String HospitalID) {
        for (Patient p : doctor.getPatientList()) {
            if (p.getHospitalID().equals(HospitalID)) {
                return p; // Return the patient if found
            }
        }
        return null; // Return null if no patient is found
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

