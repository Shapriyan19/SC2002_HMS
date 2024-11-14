package appointment;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private String month;
    private List<Appointment> appointments;
    private List<TimeSlot> availableTimeSlots;

    public Calendar(String month) {
        this.month = month;
        this.appointments = new ArrayList<>();
        this.availableTimeSlots = new ArrayList<>();
        initializeTimeSlots(); // Initialize default time slots
    }

    private void initializeTimeSlots() {
        // Create time slots from 9:00 AM to 9:00 PM in 30-minute intervals
        for (int hour = 9; hour <= 21; hour++) {
            for (int minute = 0; minute < 60; minute += 30) {
                String startTime = String.format("%02d:%02d", hour, minute);
                String endTime = String.format("%02d:%02d", hour, minute + 30);
                TimeSlot timeSlot = new TimeSlot(startTime, endTime);
                availableTimeSlots.add(timeSlot);
            }
        }
    }

    
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    //Method to get appointments for a specific date
    public List<Appointment> getAppointmentsForDate(String date) {
        List<Appointment> appointmentsOnDate = new ArrayList<>();
        for (Appointment app : appointments) {
            if (app.getDate().equals(date)) {
                appointmentsOnDate.add(app);
            }
        }
        return appointmentsOnDate;
    }

    // Method to get available time slots for a specific date
    public List<TimeSlot> getAvailableTimeSlotsForDate(String date) {
        List<TimeSlot> availableSlots = new ArrayList<>();
        // Check which time slots are still available on the given date
        for (TimeSlot timeSlot : availableTimeSlots) {
            boolean isBooked = false;
            for (Appointment app : appointments) {
                if (app.getDate().equals(date) && app.getTimeSlot().equals(timeSlot) && app.getStatus() == AppointmentStatus.PENDING) {
                    isBooked = true;
                    break;
                }
            }
            if (!isBooked) {
                availableSlots.add(timeSlot);
            }
        }
        return availableSlots;
    }


    // Existing method to get appointments by month
    public List<Appointment> getAppointmentsForMonth() {
        List<Appointment> appointmentsInMonth = new ArrayList<>();
        for (Appointment app : appointments) {
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
