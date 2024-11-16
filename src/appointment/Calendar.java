package appointment;

import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import user.Doctor;
import java.time.LocalDate;

public class Calendar {
    private YearMonth yearMonth;
    private List<Appointment> appointments;
    private List<TimeSlot> availableTimeSlots;

    public Calendar(String monthString) {
        try {
            // Parse the provided month string to a Month enum
            Month month = Month.valueOf(monthString.toUpperCase());
            // Use the current year dynamically
            this.yearMonth = YearMonth.of(java.time.LocalDate.now().getYear(), month);
            this.appointments = new ArrayList<>();
            this.availableTimeSlots = new ArrayList<>();
            initializeTimeSlots();
        } catch (IllegalArgumentException e) {
            // Handle invalid month names gracefully
            throw new IllegalArgumentException("Invalid month name provided: " + monthString);
        }
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
        for (Appointment app : appointments) {
            // Ensure the date is formatted correctly
            String formattedDate = app.getDate();
            if (formattedDate.length() == 10 && formattedDate.charAt(5) == '-') {
                try {
                    // Try to parse the date with the proper format
                    LocalDate parsedDate = LocalDate.parse(formattedDate, formatter);
                    YearMonth appointmentYearMonth = YearMonth.from(parsedDate);
    
                    if (appointmentYearMonth.getYear() == this.yearMonth.getYear() && appointmentYearMonth.getMonth() == this.yearMonth.getMonth()) {
                        appointmentsInMonth.add(app);
                    }
                } catch (Exception e) {
                    System.out.println("Invalid date format for appointment: " + formattedDate);
                }
            } else {
                System.out.println("Invalid date format: " + formattedDate);
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

    public List<Appointment> getAppointmentsForDoctor(Doctor doctor) {
        List<Appointment> doctorAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctor().equals(doctor)) {
                doctorAppointments.add(appointment);
            }
        }
        return doctorAppointments;
    }

}