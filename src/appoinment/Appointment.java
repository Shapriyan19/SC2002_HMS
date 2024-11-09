package appoinment;
import java.util.Date;

public class Appointment {

    private int appointmentId;
    private String doctorId;
    private String patientId;
    private Date date;
    private TimeSlot timeSlot;
    private AppointmentStatus status;

    public Appointment(int appointmentId, String doctorId, String patientId, Date date, TimeSlot timeSlot) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.status = AppointmentStatus.PENDING;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date){
        this.date=date;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot){
        this.timeSlot=timeSlot;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}

