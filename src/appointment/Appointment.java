package appointment;

import user.Patient;

import java.util.ArrayList;
import java.util.List;

import user.Doctor;

public class Appointment {
    private static int appointmentCounter = 0;
    private int appointmentID;
    private Patient patient;
    private Doctor doctor;
    private AppointmentStatus status;
    private String date;
    private TimeSlot timeSlot;
    private AppointmentOutcomeRecord outcomeRecord;

    public Appointment(Patient patient, Doctor doctor, String date, TimeSlot timeSlot) {
        this.appointmentID = ++appointmentCounter;
        this.patient = patient;
        this.doctor = doctor;
        this.status = AppointmentStatus.PENDING;
        this.date = date;
        this.timeSlot = timeSlot;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void confirmAppointment() {
        this.status = AppointmentStatus.CONFIRMED;
    }

    public void cancelAppointment() {
        this.status = AppointmentStatus.CANCELLED;
        timeSlot.setAvailability(true);
    }

    public void rescheduleAppointment(String newDate, TimeSlot newTimeSlot) {
        this.date = newDate;
        this.timeSlot.setAvailability(true);
        this.timeSlot = newTimeSlot;
        this.status = AppointmentStatus.RESCHEDULED;
        newTimeSlot.setAvailability(false);
    }

    // New setters for date and timeSlot
    public void setDate(String date) {
        this.date = date;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    // Getters
    public int getAppointmentID() {
        return appointmentID;
    }

    public String getDate() {
        return date;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void completeAppointment(String serviceType, List<MedicationRecord> prescribedMedications, String consultationNotes) {
        if (this.status == AppointmentStatus.CONFIRMED) {
            this.status = AppointmentStatus.COMPLETED;
            setOutcomeRecord(serviceType, prescribedMedications, consultationNotes);
            System.out.println("Appointment completed and outcome recorded.");
        } else {
            System.out.println("Appointment must be confirmed before completing.");
        }
    }

    public void setOutcomeRecord(String serviceType, List<MedicationRecord> prescribedMedications, String consultationNotes) {
        if (this.status == AppointmentStatus.COMPLETED) {
            this.outcomeRecord = new AppointmentOutcomeRecord(this.date, serviceType, prescribedMedications, consultationNotes);
        } else {
            System.out.println("Appointment must be completed before adding an outcome record.");
        }
    }

    public AppointmentOutcomeRecord getOutcomeRecord() {
        return outcomeRecord;
    }
}




//old code
// public class Appointment {

//     private int appointmentId;
//     private String doctorId;
//     private String patientId;
//     private Date date;
//     private TimeSlot timeSlot;
//     private AppointmentStatus status;

//     public Appointment(int appointmentId, String doctorId, String patientId, Date date, TimeSlot timeSlot) {
//         this.appointmentId = appointmentId;
//         this.doctorId = doctorId;
//         this.patientId = patientId;
//         this.date = date;
//         this.timeSlot = timeSlot;
//         this.status = AppointmentStatus.PENDING;
//     }

//     public int getAppointmentId() {
//         return appointmentId;
//     }

//     public String getDoctorId() {
//         return doctorId;
//     }

//     public String getPatientId() {
//         return patientId;
//     }

//     public Date getDate() {
//         return date;
//     }

//     public void setDate(Date date){
//         this.date=date;
//     }

//     public TimeSlot getTimeSlot() {
//         return timeSlot;
//     }

//     public void setTimeSlot(TimeSlot timeSlot){
//         this.timeSlot=timeSlot;
//     }

//     public AppointmentStatus getStatus() {
//         return status;
//     }

//     public void setStatus(AppointmentStatus status) {
//         this.status = status;
//     }
// }

