package appointment;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private String month;
    private List<Appointment> appointments;

    public Calendar(String month) {
        this.month = month;
        this.appointments = new ArrayList<>();
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    // Filter appointments by month
    public List<Appointment> getAppointmentsForMonth() {
        List<Appointment> appointmentsInMonth = new ArrayList<>();
        for (Appointment app : appointments) {
            // Assume Appointment has a getDate() method that returns the appointment's month (in "MM" format)
            if (app.getDate().startsWith(month)) {
                appointmentsInMonth.add(app);
            }
        }
        return appointmentsInMonth;
    }

    public List<TimeSlot> getAvailableTimeSlots() {
        List<TimeSlot> availableSlots = new ArrayList<>();
        for (Appointment app : appointments) {
            if (app.getStatus() == AppointmentStatus.PENDING) {
                availableSlots.add(app.getTimeSlot());
            }
        }
        return availableSlots;
    }

    public void displayAppointments() {
        List<Appointment> appointmentsForMonth = getAppointmentsForMonth();
        if (appointmentsForMonth.isEmpty()) {
            System.out.println("No appointments scheduled for " + month);
        } else {
            for (Appointment app : appointmentsForMonth) {
                System.out.println("Appointment ID: " + app.getAppointmentID() + " | Status: " + app.getStatus());
            }
        }
    }
}



//old code
// public class Calendar {

//     private List<Appointment> appointments;

//     public Calendar() {
//         this.appointments = new ArrayList<>();
//     }

//     public void addAppointment(Appointment appointment) {
//         appointments.add(appointment);
//     }

//     public void removeAppointment(Appointment appointment) {
//         appointments.remove(appointment);
//     }

//     public List<Appointment> getAppointments() {
//         return appointments;
//     }

//     public List<Appointment> getAppointmentsForDate(Date date) {
//         List<Appointment> result = new ArrayList<>();
//         for (Appointment appointment : appointments) {
//             if (appointment.getDate().equals(date)) {
//                 result.add(appointment);
//             }
//         }
//         return result;
//     }
// }
