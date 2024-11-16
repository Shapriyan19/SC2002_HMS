import appointment.*;
import user.*;

public class AppointmentSchedulingApp {
    public static void main(String[] args) {
        //DataLoader.loadAllData(); // Load data from CSV files
        // Create a doctor
        Doctor doctor = new Doctor(Role.DOCTOR, "John Doe", "M", 45);
        
        // Create a patient
        Patient patient1 = new Patient(Role.PATIENT, "John Doe", "01/01/1985", "Male", "O+", 1234567890, "johndoe@example.com",null);
        Patient patient2 = new Patient(Role.PATIENT, "Jack", "01/01/1999", "Male", "O+", 1234567890, "johndoe@example.com",null);
        
        // Create a calendar for the doctor
        appointment.Calendar calendar = doctor.getCalendar();

        // Create time slots
        TimeSlot timeSlot1 = new TimeSlot("09:00", "09:30");
        TimeSlot timeSlot2 = new TimeSlot("10:00", "10:30");
        TimeSlot timeSlot3 = new TimeSlot("11:00", "11:30");

        // Create and add appointments to the calendar
        Appointment appointment1 = new Appointment(patient1, doctor, "2023-11-01", timeSlot1);
        Appointment appointment2 = new Appointment(patient2, doctor, "2023-11-01", timeSlot2);
        Appointment appointment3 = new Appointment(patient1, doctor, "2023-11-02", timeSlot3);

        calendar.addAppointment(appointment1);
        calendar.addAppointment(appointment2);
        calendar.addAppointment(appointment3);

        // Display the doctor's appointments
        System.out.println("Doctor's Appointments for November:");
        doctor.displayAppointments();
    }
}
