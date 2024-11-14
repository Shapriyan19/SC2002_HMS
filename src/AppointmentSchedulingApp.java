import user.*;
import appointment.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AppointmentSchedulingApp {
    public static void main(String[] args) {
        // Create sample patients and doctors
        Patient patient1 = new Patient(Role.PATIENT, "John Doe", "1990-05-15", "Male", "A+", 1234567890, "john@example.com");
        Doctor doctor1 = new Doctor(Role.DOCTOR, "Dr. Jane Smith", "Female", 45);
        Doctor doctor2 = new Doctor(Role.DOCTOR, "Dr. Michael Johnson", "Male", 52);

        // Create sample appointments
        Appointment appointment1 = new Appointment(patient1, doctor1, "2023-05-01", new TimeSlot("10:00", "10:30"));
        Appointment appointment2 = new Appointment(patient1, doctor2, "2023-05-15", new TimeSlot("14:00", "14:30"));

        // Schedule the appointments
        AppointmentScheduler scheduler1 = new AppointmentScheduler(appointment1);
        scheduler1.scheduleAppointment();

        AppointmentScheduler scheduler2 = new AppointmentScheduler(appointment2);
        scheduler2.scheduleAppointment();

        // Display available appointments for the patient
        patient1.viewScheduledAppointments();

        // Display appointments for the doctors
        doctor1.viewAppointmentsForMonth();
        doctor2.viewAppointmentsForMonth();

        // Test rescheduling an appointment
        System.out.println("\nRescheduling appointment...");
        scheduler1.rescheduleAppointment();

        // Test canceling an appointment
        System.out.println("\nCanceling appointment...");
        scheduler2.cancelAppointment();

        // Display updated appointments
        patient1.viewScheduledAppointments();
        doctor1.viewAppointmentsForMonth();
        doctor2.viewAppointmentsForMonth();
    }
}
