package appointment;

import user.Patient;
import user.Doctor;

import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Calendar {
    private YearMonth yearMonth;
    private List<Appointment> appointments;
    private List<TimeSlot> availableTimeSlots;

    public Calendar(String monthString) {
        Month month = Month.valueOf(monthString.toUpperCase());
        this.yearMonth = YearMonth.of(2023, month);
        this.appointments = new ArrayList<>();
        this.availableTimeSlots = new ArrayList<>();
        initializeTimeSlots();
    }

    private void initializeTimeSlots() {
        // Create time slots from 9:00 AM to 5:00 PM in 30-minute intervals
        for (int hour = 9; hour <= 17; hour++) {
            for (int minute = 0; minute < 60; minute += 30) {
                if (minute + 30 < 60) {
                    String startTime = String.format("%02d:%02d", hour, minute);
                    String endTime = String.format("%02d:%02d", hour, minute + 30);
                    TimeSlot timeSlot = new TimeSlot(startTime, endTime);
                    availableTimeSlots.add(timeSlot);
                }
            }
        }
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        // Remove the time slot from the available time slots
        for (TimeSlot timeSlot : availableTimeSlots) {
            if (timeSlot.equals(appointment.getTimeSlot())) {
                availableTimeSlots.remove(timeSlot);
                break;
            }
        }
    }

    public List<Appointment> getAppointmentsForDate(String date) {
        List<Appointment> appointmentsOnDate = new ArrayList<>();
        for (Appointment app : appointments) {
            if (app.getDate().equals(date)) {
                appointmentsOnDate.add(app);
            }
        }
        return appointmentsOnDate;
    }

    public List<TimeSlot> getAvailableTimeSlotsForDate(String date) {
        List<TimeSlot> availableSlots = new ArrayList<>(this.availableTimeSlots);
        // Check which time slots are still available on the given date
        for (Appointment app : appointments) {
            if (app.getDate().equals(date) && app.getStatus() == AppointmentStatus.PENDING) {
                availableSlots.remove(app.getTimeSlot());
            }
        }
        return availableSlots;
    }

    public List<Appointment> getAppointmentsForMonth() {
        List<Appointment> appointmentsInMonth = new ArrayList<>();
        for (Appointment app : appointments) {
            YearMonth appointmentYearMonth = YearMonth.parse(app.getDate(), DateTimeFormatter.ISO_DATE);
            if (appointmentYearMonth.getYear() == this.yearMonth.getYear() && appointmentYearMonth.getMonth() == this.yearMonth.getMonth()) {
                appointmentsInMonth.add(app);
            }
        }
        return appointmentsInMonth;
    }

    public void displayAppointments() {
        List<Appointment> appointmentsForMonth = getAppointmentsForMonth();
        if (appointmentsForMonth.isEmpty()) {
            System.out.println("No appointments scheduled for " + this.yearMonth.format(DateTimeFormatter.ofPattern("MMMM")));
        } else {
            for (Appointment app : appointmentsForMonth) {
                System.out.println("Appointment ID: " + app.getAppointmentID() + " | Status: " + app.getStatus());
            }
        }
    }
}