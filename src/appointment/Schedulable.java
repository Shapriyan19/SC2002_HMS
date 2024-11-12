package appointment;


public interface Schedulable {
    void scheduleAppointment();
    void rescheduleAppointment();
    void cancelAppointment();
}

//old code
// public interface Schedulable {
//     boolean schedule(Appointment appointment);
//     boolean reschedule(Appointment appointment, Date newDate, TimeSlot newTimeSlot);
//     boolean cancel(Appointment appointment);
// }
