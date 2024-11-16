package user;

import user.Pharmacist;
import user.Patient;
import user.Role;
import appointment.Appointment;
import appointment.AppointmentOutcomeRecord;
import appointment.AppointmentStatus;
import appointment.MedicationRecord;

import java.util.ArrayList;
import java.util.List;

public class applicationadmin {
    public static void main(String[] args) {
        // Create a pharmacist
        Pharmacist pharmacist = new Pharmacist(Role.PHARMACIST, "John Doe", "Male", 35);

        // Create a patient with the updated constructor
        Patient patient = new Patient(
                Role.PATIENT,
                "Alice",
                "1995-06-15",
                "Female",
                "O+",
                1234567890L,
                "alice@example.com"
        );

        // Create a list of prescribed medications
        List<MedicationRecord> medications = new ArrayList<>();
        medications.add(new MedicationRecord("Paracetamol", "500mg"));
        medications.add(new MedicationRecord("Ibuprofen", "200mg"));

        // Create an appointment outcome record
        AppointmentOutcomeRecord outcomeRecord = new AppointmentOutcomeRecord(
                "2024-11-15",
                "General Consultation",
                medications,
                "Take medications twice a day"
        );

        // Create an appointment
        Appointment appointment = new Appointment(patient, null, "2024-11-15", null);
        appointment.setStatus(AppointmentStatus.COMPLETED);
        appointment.setOutcomeRecord("General Consultation", medications, "Take medications twice a day");

        // Add the appointment to the patient's list of appointments
        patient.getAppointments().add(appointment);

        // Test: View the latest Appointment Outcome Record
        System.out.println("Viewing the latest Appointment Outcome Record:");
        pharmacist.viewLatestAppointmentOutcomeRecord(patient);

        // Test: Update the prescription status in the Appointment Outcome Record
        System.out.println("\nUpdating the prescription status...");
        pharmacist.updatePrescriptionStatus(patient);

        // Test: View the updated Appointment Outcome Record
        System.out.println("\nViewing the updated Appointment Outcome Record:");
        pharmacist.viewLatestAppointmentOutcomeRecord(patient);
    }
}
