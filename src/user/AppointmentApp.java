package user;

import appointment.*;
import java.util.List;

public class AppointmentApp {

    public static void main(String[] args) {
        // Create patients and doctors
        Patient patient1 = new Patient(Role.PATIENT, "John Doe", "01/01/1985", "Male", "O+", 1234567890, "johndoe@example.com",null);
        Patient patient2 = new Patient(Role.PATIENT, "Jack", "01/01/1999", "Male", "O+", 1234567890, "johndoe@example.com",null);
        Doctor doctor1 = new Doctor(Role.DOCTOR, "Dr. John", "Male", 35);
        Doctor doctor2 = new Doctor(Role.DOCTOR, "Dr. Alice", "Female", 40);

        // Create a calendar for a specific month
        Calendar calendar = new Calendar("November");

        // Create time slots for appointments
        TimeSlot timeSlot1 = new TimeSlot("09:00", "09:30");
        TimeSlot timeSlot2 = new TimeSlot("10:00", "10:30");

        // Create appointments
        Appointment appointment1 = new Appointment(patient1, doctor1, "2024-11-15", timeSlot1);
        Appointment appointment2 = new Appointment(patient2, doctor2, "2024-11-15", timeSlot2);

        // Add appointments to calendar
        calendar.addAppointment(appointment1);
        calendar.addAppointment(appointment2);

        // Schedule appointments
        AppointmentScheduler scheduler1 = new AppointmentScheduler(appointment1);
        AppointmentScheduler scheduler2 = new AppointmentScheduler(appointment2);

        // Schedule appointment for patient 1
        scheduler1.scheduleAppointment();

        // Schedule appointment for patient 2
        scheduler2.scheduleAppointment();

        // Display all appointments for the month
        System.out.println("Appointments for November:");
        calendar.displayAppointments();

        // Reschedule patient 1's appointment
        TimeSlot newTimeSlot = new TimeSlot("11:00", "11:30");
        scheduler1.rescheduleAppointment();

        // Cancel patient 2's appointment
        scheduler2.cancelAppointment();

        // Display updated appointments
        System.out.println("\nUpdated appointments for November:");
        calendar.displayAppointments();

        // Check available time slots for a specific date
        List<TimeSlot> availableSlots = calendar.getAvailableTimeSlotsForDate("2024-11-15");
        System.out.println("\nAvailable time slots for 2024-11-15:");
        for (TimeSlot slot : availableSlots) {
            System.out.println(slot);
        }
    } 
}
