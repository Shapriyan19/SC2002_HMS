package appoinment;

import java.util.Date;

public class AppointmentScheduler implements Schedulable {
    private Calendar calendar;

    public AppointmentScheduler(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public boolean schedule(Appointment appointment) {
        if (!isTimeSlotAvailable(appointment.getDate(), appointment.getTimeSlot())) {
            return false;
        }
        calendar.addAppointment(appointment);
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        return true;
    }

    @Override
    public boolean reschedule(Appointment appointment, Date newDate, TimeSlot newTimeSlot) {
        if (!isTimeSlotAvailable(newDate, newTimeSlot)) {
            return false;
        }
        calendar.removeAppointment(appointment);  // Remove the old appointment
        appointment.setDate(newDate);             // Update the date and time slot
        appointment.setTimeSlot(newTimeSlot);
        calendar.addAppointment(appointment);     // Add the updated appointment
        return true;
    }

    @Override
    public boolean cancel(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.CANCELLED);
        calendar.removeAppointment(appointment);
        return true;
    }

    private boolean isTimeSlotAvailable(Date date, TimeSlot timeSlot) {
        for (Appointment existingAppointment : calendar.getAppointmentsForDate(date)) {
            if (existingAppointment.getTimeSlot().equals(timeSlot)) {
                return false;
            }
        }
        return true;
    }
}
