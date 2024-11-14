package appointment;

import java.util.List;

public class AppointmentOutcomeRecord {
    private String appointmentDate;
    private String serviceType;
    private List<MedicationRecord> prescribedMedications;
    private String consultationNotes;

    public AppointmentOutcomeRecord(String appointmentDate, String serviceType, List<MedicationRecord> prescribedMedications, String consultationNotes) {
        this.appointmentDate = appointmentDate;
        this.serviceType = serviceType;
        this.prescribedMedications = prescribedMedications;
        this.consultationNotes = consultationNotes;
    }

    // Getters and Setters
    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<MedicationRecord> getPrescribedMedications() {
        return prescribedMedications;
    }

    public void setPrescribedMedications(List<MedicationRecord> prescribedMedications) {
        this.prescribedMedications = prescribedMedications;
    }

    public String getConsultationNotes() {
        return consultationNotes;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }
}

