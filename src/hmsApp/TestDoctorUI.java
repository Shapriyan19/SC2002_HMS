package hmsApp;

import appointment.*;
import user.*;

public class TestDoctorUI {
    public static void main(String[] args) {
        // Create some sample data
        // Patient patient1 = new Patient(Role.PATIENT, "John Doe", "01/01/1985", "Male", "O+", 1234567890L, "johndoe@example.com");
        // Patient patient2 = new Patient(Role.PATIENT, "Jack", "01/01/1999", "Male", "O+", 1234567890L, "jack@example.com");
        
        TimeSlot timeSlot1 = new TimeSlot("09:00", "09:30");
        TimeSlot timeSlot2 = new TimeSlot("10:00", "10:30");
        
        Doctor doctor = new Doctor(Role.DOCTOR, "Dr. Smith", "Male", 45);
        
        // Create appointments
        Appointment appointment1 = new Appointment(patient1, doctor, "2023-11-01", timeSlot1);
        Appointment appointment2 = new Appointment(patient2, doctor, "2023-11-01", timeSlot2);
        
        // Assign appointments to the doctor
        doctor.getAppointments().add(appointment1);
        doctor.getAppointments().add(appointment2);
        
        // Create Doctor UI and test the menu
        DoctorUI doctorUI = new DoctorUI(doctor);
        doctorUI.showMenu(); // This will allow interaction through the console
    }
}

