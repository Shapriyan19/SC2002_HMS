package user;

public class Doctor {

    //instance variables 
    private String doctorID;
    private String name;
    private String specialisation;
    private String[] schedule; 
    private String[] patientList;   

    //methods

    public void viewPatientRecord();
    public void updatePatientRecord();
    public void viewPersonalSchedule();
    public void setAvailabilityforAppointment();
    public void acceptAppointment();
    public void declineAppointment();
    public void viewUpcomingAppointment();
    public void recordAppointmentoutcome(); //this should include date of appointment, type of service provided (consultation,x-ray, bloodtest), prescribed medication(medication name ,status(default is pending), consultation notes)
}
