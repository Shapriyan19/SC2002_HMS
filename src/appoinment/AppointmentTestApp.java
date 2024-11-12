package appoinment;

import user.Patient;
import user.Doctor;
import user.Role;

public class AppointmentTestApp {
    public static void main(String[] args) {
        // Create sample patients
        Patient patient1 = new Patient(Role.PATIENT, "Jack","21-03-2004","Male","A+",1234567890L,"abc@gmail.com");
        Patient patient2 = new Patient(Role.PATIENT, "Dan","23-03-2004","Male","B+",12345672390L,"xyz@gmail.com");

        // Create sample doctors
        Doctor doctor1 = new Doctor(Role.DOCTOR,"Nick","Male",32);
        Doctor doctor2 = new Doctor(Role.DOCTOR,"Lim","Male",33);

        // Create some time slots
        TimeSlot timeSlot1 = new TimeSlot("09:00", "09:30");
        TimeSlot timeSlot2 = new TimeSlot("10:00", "10:30");
        // TimeSlot timeSlot3 = new TimeSlot("11:00", "11:30");

        // Create appointments
        Appointment appointment1 = new Appointment(patient1, doctor1, "2024-11-12", timeSlot1);
        Appointment appointment2 = new Appointment(patient2, doctor2, "2024-11-12", timeSlot2);

        // Create appointment scheduler
        AppointmentScheduler scheduler1 = new AppointmentScheduler(appointment1);
        AppointmentScheduler scheduler2 = new AppointmentScheduler(appointment2);

        // Schedule the appointments
        System.out.println("Scheduling appointments...");
        scheduler1.scheduleAppointment();
        scheduler2.scheduleAppointment();

        // Try scheduling the same time slot (should fail)
        Appointment appointment3 = new Appointment(patient1, doctor2, "2024-11-12", timeSlot1);
        AppointmentScheduler scheduler3 = new AppointmentScheduler(appointment3);
        scheduler3.scheduleAppointment();  // This should fail as the time slot is already booked

        // Reschedule an appointment
        System.out.println("\nRescheduling appointment...");
        scheduler1.rescheduleAppointment();

        // Cancel an appointment
        System.out.println("\nCanceling appointment...");
        scheduler2.cancelAppointment();

        // Try rescheduling again for the canceled appointment
        System.out.println("\nTrying to reschedule canceled appointment...");
        scheduler2.rescheduleAppointment();
    }
}

