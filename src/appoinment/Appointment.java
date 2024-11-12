package appoinment;

import user.Patient;
import user.Doctor;

public class Appointment {
    private static int appointmentCounter = 0;
    private int appointmentID;
    private Patient patient;
    private Doctor doctor;
    private AppointmentStatus status;
    private String date;
    private TimeSlot timeSlot;

    public Appointment(Patient patient, Doctor doctor, String date, TimeSlot timeSlot) {
        this.appointmentID = ++appointmentCounter;
        this.patient = patient;
        this.doctor = doctor;
        this.status = AppointmentStatus.PENDING; // Default status
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
        this.status = AppointmentStatus.CANCELED;
        timeSlot.setAvailability(true); // Release the time slot
    }

    public void rescheduleAppointment(String newDate, TimeSlot newTimeSlot) {
        this.date = newDate;
        this.timeSlot.setAvailability(true); // Release the old slot
        this.timeSlot = newTimeSlot;
        this.status = AppointmentStatus.RESCHEDULED;
        newTimeSlot.setAvailability(false); // Book the new slot
    }

    // Getters and setters for the appointment fields
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

