package appoinment;

import java.util.Date;

public interface Schedulable {
    boolean schedule(Appointment appointment);
    boolean reschedule(Appointment appointment, Date newDate, TimeSlot newTimeSlot);
    boolean cancel(Appointment appointment);
}
