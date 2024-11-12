package appointment;

public class AppointmentScheduler implements Schedulable {
    private Appointment appointment;

    public AppointmentScheduler(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public void scheduleAppointment() {
        // Check if the time slot is available for scheduling
        if (appointment.getTimeSlot().isAvailable()) {
            // Set the appointment status to scheduled
            appointment.setStatus(AppointmentStatus.SCHEDULED);
            // Mark the time slot as not available anymore
            appointment.getTimeSlot().setAvailability(false);
            System.out.println("Appointment scheduled for patient: " + appointment.getPatient().getName() + " on " + appointment.getTimeSlot().toString());
        } else {
            System.out.println("The selected time slot is not available.");
        }
    }

    @Override
    public void rescheduleAppointment() {
        // Check if the new time slot is available for rescheduling
        if (appointment.getTimeSlot().isAvailable()) {
            // Release the old time slot
            appointment.getTimeSlot().setAvailability(true);
            // Set the new time slot
            TimeSlot newTimeSlot = appointment.getTimeSlot();
            // Mark the new time slot as not available
            newTimeSlot.setAvailability(false);
            appointment.rescheduleAppointment(appointment.getDate(), newTimeSlot); // Update the appointment
            System.out.println("Appointment rescheduled for patient: " + appointment.getPatient().getName() + " to " + newTimeSlot.toString());
        } else {
            System.out.println("The new time slot is not available.");
        }
    }

    @Override
    public void cancelAppointment() {
        // Set the status to canceled
        appointment.setStatus(AppointmentStatus.CANCELED);
        // Mark the time slot as available again
        appointment.getTimeSlot().setAvailability(true);
        System.out.println("Appointment canceled for patient: " + appointment.getPatient().getName() + " on " + appointment.getTimeSlot().toString());
    }
}



//old code
// public class AppointmentScheduler implements Schedulable {
//     private Calendar calendar;

//     public AppointmentScheduler(Calendar calendar) {
//         this.calendar = calendar;
//     }

//     @Override
//     public boolean schedule(Appointment appointment) {
//         if (!isTimeSlotAvailable(appointment.getDate(), appointment.getTimeSlot())) {
//             return false;
//         }
//         calendar.addAppointment(appointment);
//         appointment.setStatus(AppointmentStatus.CONFIRMED);
//         return true;
//     }

//     @Override
//     public boolean reschedule(Appointment appointment, Date newDate, TimeSlot newTimeSlot) {
//         if (!isTimeSlotAvailable(newDate, newTimeSlot)) {
//             return false;
//         }
//         calendar.removeAppointment(appointment);  // Remove the old appointment
//         appointment.setDate(newDate);             // Update the date and time slot
//         appointment.setTimeSlot(newTimeSlot);
//         calendar.addAppointment(appointment);     // Add the updated appointment
//         return true;
//     }

//     @Override
//     public boolean cancel(Appointment appointment) {
//         appointment.setStatus(AppointmentStatus.CANCELLED);
//         calendar.removeAppointment(appointment);
//         return true;
//     }

//     private boolean isTimeSlotAvailable(Date date, TimeSlot timeSlot) {
//         for (Appointment existingAppointment : calendar.getAppointmentsForDate(date)) {
//             if (existingAppointment.getTimeSlot().equals(timeSlot)) {
//                 return false;
//             }
//         }
//         return true;
//     }
// }
