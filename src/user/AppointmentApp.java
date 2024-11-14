package user;

import appointment.*;
import java.util.List;

public class AppointmentApp {
    public static void main(String[] args) {
        // Create some sample doctors
        Doctor doctor1 = new Doctor(Role.DOCTOR, "Dr. John", "Male", 35);
        Doctor doctor2 = new Doctor(Role.DOCTOR, "Dr. Alice", "Female", 40);

        // Add available time slots to doctor's calendar
        TimeSlot slot1 = new TimeSlot("10:00 AM", "10:30 AM");
        TimeSlot slot2 = new TimeSlot("11:00 AM", "11:30 PM");
        // doctor1.getCalendar().addTimeSlot("November", slot1);
        // doctor1.getCalendar().addTimeSlot("November", slot2);

        // Create a patient
        Patient patient1 = new Patient(Role.PATIENT, "John Doe", "01/01/1985", "Male", "O+", 1234567890, "johndoe@example.com");

        // View available appointments
        System.out.println("Viewing available appointments for Dr. John:");
        patient1.viewAvailableAppointments(doctor1, "November");

        // Schedule an appointment
        patient1.scheduleAppointment(doctor1, "November", slot1);

        // View patient's scheduled appointments
        System.out.println("\nScheduled Appointments for " + patient1.getName() + ":");
        patient1.viewScheduledAppointments();

        // Try rescheduling an appointment
        TimeSlot newSlot = new TimeSlot("12:00 PM", "1:00 PM");
        patient1.rescheduleAppointment(patient1.getAppointments().get(0), "November", newSlot);

        // View updated appointments
        System.out.println("\nUpdated Appointments for " + patient1.getName() + ":");
        patient1.viewScheduledAppointments();

        // Cancel an appointment
        patient1.cancelAppointment(patient1.getAppointments().get(0));

        // View appointments after cancellation
        System.out.println("\nAppointments after cancellation for " + patient1.getName() + ":");
        patient1.viewScheduledAppointments();
    }
}
