package hmsApp;

import appointment.*;
import user.*;

import java.util.Scanner;
import java.util.List;

public class DoctorUI {

    private Doctor doctor;
    private Scanner scanner;

    public DoctorUI(Doctor doctor) {
        this.doctor = doctor;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n--- Doctor's Dashboard ---");
            System.out.println("1. View Personal Schedule");
            System.out.println("2. Set Availability for Appointments");
            System.out.println("3. Accept or Decline Appointment Requests");
            System.out.println("4. View Upcoming Appointments");
            System.out.println("5. Record Appointment Outcome");
            System.out.println("6. Logout");
            System.out.print("Select an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    viewPersonalSchedule();
                    break;
                case 2:
                    setAvailability();
                    break;
                case 3:
                    manageAppointmentRequests();
                    break;
                case 4:
                    viewUpcomingAppointments();
                    break;
                case 5:
                    recordAppointmentOutcome();
                    break;
                case 6:
                    logout();
                    return; // exit the UI
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewPersonalSchedule() {
        List<Appointment> appointments = doctor.getAppointments();
        if (appointments.isEmpty()) {
            System.out.println("No appointments in your schedule.");
        } else {
            System.out.println("Your Schedule:");
            for (Appointment appointment : appointments) {
                System.out.println("Appointment ID: " + appointment.getAppointmentID() +
                        " | Patient: " + appointment.getPatient().getName() +
                        " | Date: " + appointment.getDate() +
                        " | Time: " + appointment.getTimeSlot().toString());
            }
        }
    }

    private void setAvailability() {
        System.out.print("Enter the date (yyyy-mm-dd) to set availability: ");
        String date = scanner.nextLine();
        System.out.print("Enter the start time (hh:mm): ");
        String startTime = scanner.nextLine();
        System.out.print("Enter the end time (hh:mm): ");
        String endTime = scanner.nextLine();

        // Assuming TimeSlot is set properly
        TimeSlot timeSlot = new TimeSlot(startTime, endTime);

        if (doctor.isSlotAvailable(date, timeSlot)) {
            System.out.println("Slot available for " + date + " from " + startTime + " to " + endTime);
            // Further logic to save this availability can be added here
        } else {
            System.out.println("Slot not available. Please try a different time.");
        }
    }

    private void manageAppointmentRequests() {
        System.out.println("Pending appointment requests:");
        List<Appointment> appointments = doctor.getAppointments();
        for (Appointment appointment : appointments) {
            if (appointment.getStatus() == AppointmentStatus.PENDING) {
                System.out.println("Appointment ID: " + appointment.getAppointmentID() +
                        " | Patient: " + appointment.getPatient().getName() +
                        " | Date: " + appointment.getDate() +
                        " | Time: " + appointment.getTimeSlot().toString());
                System.out.println("1. Accept");
                System.out.println("2. Decline");
                System.out.print("Select an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                if (choice == 1) {
                    doctor.confirmAppointment(appointment);
                } else if (choice == 2) {
                    doctor.cancelAppointment(appointment);
                } else {
                    System.out.println("Invalid choice. Appointment not modified.");
                }
            }
        }
    }

    private void viewUpcomingAppointments() {
        System.out.println("Upcoming Appointments:");
        doctor.displayAppointments();
    }

    private void recordAppointmentOutcome() {
        System.out.print("Enter the Appointment ID to record outcome: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Find the appointment with the given ID
        Appointment appointment = null;
        for (Appointment app : doctor.getAppointments()) {
            if (app.getAppointmentID() == appointmentId) {
                appointment = app;
                break;
            }
        }

        if (appointment != null) {
            System.out.println("Recording outcome for appointment with " + appointment.getPatient().getName());
            System.out.print("Enter the service provided (e.g., consultation, X-ray): ");
            String service = scanner.nextLine();

            System.out.print("Enter prescribed medications (comma-separated): ");
            String medicationsInput = scanner.nextLine();
            String[] medications = medicationsInput.split(",");

            System.out.print("Enter consultation notes: ");
            String notes = scanner.nextLine();

            // Update the appointment outcome (this could involve more complex logic in real-world apps)
            appointment.setOutcome(service, medications, notes);
            System.out.println("Outcome recorded successfully.");
        } else {
            System.out.println("Appointment ID not found.");
        }
    }

    private void logout() {
        System.out.println("Logging out... Goodbye, Dr. " + doctor.getName() + "!");
        doctor.logout();
    }
}

