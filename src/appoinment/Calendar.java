package appoinment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Calendar {

    private List<Appointment> appointments;

    public Calendar() {
        this.appointments = new ArrayList<>();
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Appointment> getAppointmentsForDate(Date date) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date)) {
                result.add(appointment);
            }
        }
        return result;
    }
}

}
