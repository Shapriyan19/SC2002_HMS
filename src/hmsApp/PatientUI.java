package hmsApp;

import appointment.*;
import user.Patient;
import user.Doctor;
import java.util.List;
import java.util.Scanner;

public class PatientUI {

    private Patient patient;
    private Scanner scanner;

    public PatientUI(Patient patient) {
        this.patient = patient;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n--- Patient Menu ---");
            System.out.println("1. View My Appointments");
            System.out.println("2. View My Medical Records");
            System.out.println("3. Update My Personal Details");
            System.out.println("4. Schedule New Appointment");
            System.out.println("5. Cancel An Appointment");
            System.out.println("6. Logout");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewConfirmedAppointments();
                    break;
                case 2:
                    patient.viewMedicalRecord(patient);
                    break;
                case 3:
                    updateMyPersonalDetails();
                    break;
                case 4:
                    scheduleNewAppointment();
                    break;
                case 5:
                    cancelAnAppointment();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    patient.logout();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private void viewConfirmedAppointments() {
        List<Appointment> appointments = patient.getAppointments();
        boolean hasConfirmed = false;
        System.out.println("-- My Confirmed Appointments --");
        for (Appointment appointment : appointments) {
            if (appointment.getStatus() == AppointmentStatus.CONFIRMED) {
                System.out.println(appointment);
                hasConfirmed = true;
            }
        }
        if (!hasConfirmed) {
            System.out.println("You have no confirmed appointments.");
        }
    }

    private void updateMyPersonalDetails() {
        boolean updated = false;
        System.out.println("Select detail to update:");
        System.out.println("1. Date of Birth");
        System.out.println("2. Phone Number");
        System.out.println("3. Email");
        System.out.println("4. Done Updating");
        while (true) {
            System.out.print("Enter your choice: ");
            int updateChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over
    
            switch (updateChoice) {
                case 1:
                    System.out.print("Enter new date of birth (YYYY-MM-DD): ");
                    String newDateOfBirth = scanner.nextLine();
                    patient.updateDOB(newDateOfBirth);
                    updated = true;
                    break;
                case 2:
                    System.out.print("Enter new phone number: ");
                    long newPhoneNumber = scanner.nextLong();
                    patient.updateNumber(newPhoneNumber);
                    updated = true;
                    break;
                case 3:
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    patient.updateEmail(newEmail);
                    updated = true;
                    break;
                case 4:
                    if (updated) {
                        Patient.updateCSV();  // Update the CSV only once after all changes
                        System.out.println("Details updated and saved.");
                    }
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
                }
            }
        }
    
        private void scheduleNewAppointment() {
            System.out.println("Enter the doctor's name you want to schedule with:");
            scanner.nextLine();  // Consume newline left-over
            String doctorName = scanner.nextLine();
    
            Doctor doctor = Doctor.getDoctorsList().stream()
                                  .filter(d -> d.getName().equals(doctorName))
                                  .findFirst()
                                  .orElse(null);
    
            if (doctor == null) {
                System.out.println("Doctor not found.");
                return;
            }
    
            System.out.print("Enter the date for the appointment (YYYY-MM-DD): ");
            String date = scanner.nextLine();
            List<TimeSlot> availableSlots = doctor.getAvailableTimeSlots(date);
    
            if (availableSlots.isEmpty()) {
                System.out.println("No available slots on " + date);
                return;
            }
    
            System.out.println("Available slots:");
            for (TimeSlot slot : availableSlots) {
                System.out.println(slot.getStartTime() + " to " + slot.getEndTime());
            }
    
            System.out.print("Select a time slot (enter the start time): ");
            String startTime = scanner.nextLine();
            TimeSlot selectedSlot = availableSlots.stream()
                                                  .filter(slot -> slot.getStartTime().equals(startTime))
                                                  .findFirst()
                                                  .orElse(null);
    
            if (selectedSlot == null) {
                System.out.println("Invalid time slot.");
                return;
            }
    
            patient.scheduleAppointment(doctor, date, selectedSlot);
        }
    
        private void cancelAnAppointment() {
            System.out.println("Select an appointment to cancel:");
            List<Appointment> appointments = patient.getAppointments();
            appointments.forEach(appointment -> System.out.println("ID: " + appointment.getAppointmentID() + " - " + appointment));
            
            System.out.print("Enter the appointment ID to cancel: ");
            int appointmentID = scanner.nextInt();
            Appointment appointmentToCancel = appointments.stream()
                                                          .filter(a -> a.getAppointmentID() == appointmentID)
                                                          .findFirst()
                                                          .orElse(null);
    
            if (appointmentToCancel != null && (appointmentToCancel.getStatus() == AppointmentStatus.CONFIRMED || 
                                                appointmentToCancel.getStatus() == AppointmentStatus.PENDING)) {
                patient.cancelAppointment(appointmentToCancel);
                System.out.println("Appointment canceled successfully.");
            } else {
                System.out.println("Appointment ID not found or cannot be canceled.");
            }
        }
}