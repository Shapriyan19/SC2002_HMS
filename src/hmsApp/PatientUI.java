package hmsApp;

import appointment.*;
import user.Patient;
import user.Doctor;
import java.util.List;
import java.util.Scanner;
import billing.Invoice; 
import billing.Bill;


public class PatientUI {

    private Patient patient;
    private Scanner scanner;

    public PatientUI(Patient patient) {
        this.patient = patient;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n--- Patient Menu ---");
            System.out.println("1. View My Appointments");
            System.out.println("2. View My Medical Records");
            System.out.println("3. Update My Personal Details");
            System.out.println("4. Schedule New Appointment");
            System.out.println("5. Cancel An Appointment");
            System.out.println("6. View and Pay for Appointment");
            System.out.println("7. View Past Appointment Outcome Records");
            System.out.println("8. Logout");
    
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
    
            switch (choice) {
                case 1:
                    viewConfirmedAppointments();
                    break;
                case 2:
                    patient.viewMedicalRecord(patient);
                    break;
                case 3:
                    updateMyPersonalDetails();
                    break;
                case 4:
                    scheduleNewAppointment();
                    break;
                case 5:
                    cancelAnAppointment();
                    break;
                case 6:
                    viewAndPayForAppointment();
                    break;
                case 7:
                    viewPastAppointmentOutcomeRecords();
                case 8:
                    System.out.println("Logging out...");
                    patient.logout();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
    }
    

    private void viewConfirmedAppointments() {
        List<Appointment> appointments = patient.getAppointments();
        boolean hasConfirmed = false;
        System.out.println("-- My Confirmed Appointments --");
        for (Appointment appointment : appointments) {
            if (appointment.getStatus() == AppointmentStatus.CONFIRMED) {
                System.out.println(appointment);
                hasConfirmed = true;
            }
        }
        if (!hasConfirmed) {
            System.out.println("You have no confirmed appointments.");
        }
    }

    private void updateMyPersonalDetails() {
        boolean updated = false;
        System.out.println("Select detail to update:");
        System.out.println("1. Date of Birth");
        System.out.println("2. Phone Number");
        System.out.println("3. Email");
        System.out.println("4. Done Updating");
        while (true) {
            System.out.print("Enter your choice: ");
            int updateChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over
    
            switch (updateChoice) {
                case 1:
                    System.out.print("Enter new date of birth (YYYY-MM-DD): ");
                    String newDateOfBirth = scanner.nextLine();
                    patient.updateDOB(newDateOfBirth);
                    updated = true;
                    break;
                case 2:
                    System.out.print("Enter new phone number: ");
                    long newPhoneNumber = scanner.nextLong();
                    patient.updateNumber(newPhoneNumber);
                    updated = true;
                    break;
                case 3:
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    patient.updateEmail(newEmail);
                    updated = true;
                    break;
                case 4:
                    if (updated) {
                        Patient.updateCSV();  // Update the CSV only once after all changes
                        System.out.println("Details updated and saved.");
                    }
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
                }
            }
        }
    
        private void scheduleNewAppointment() {
            System.out.println("Enter the doctor's name you want to schedule with:");
            scanner.nextLine();  // Consume newline left-over
            String doctorName = scanner.nextLine();
    
            Doctor doctor = Doctor.getDoctorsList().stream()
                                  .filter(d -> d.getName().equals(doctorName))
                                  .findFirst()
                                  .orElse(null);
    
            if (doctor == null) {
                System.out.println("Doctor not found.");
                return;
            }
    
            System.out.print("Enter the date for the appointment (YYYY-MM-DD): ");
            String date = scanner.nextLine();
            List<TimeSlot> availableSlots = doctor.getAvailableTimeSlots(date);
    
            if (availableSlots.isEmpty()) {
                System.out.println("No available slots on " + date);
                return;
            }
    
            System.out.println("Available slots:");
            for (TimeSlot slot : availableSlots) {
                System.out.println(slot.getStartTime() + " to " + slot.getEndTime());
            }
    
            System.out.print("Select a time slot (enter the start time): ");
            String startTime = scanner.nextLine();
            TimeSlot selectedSlot = availableSlots.stream()
                                                  .filter(slot -> slot.getStartTime().equals(startTime))
                                                  .findFirst()
                                                  .orElse(null);
    
            if (selectedSlot == null) {
                System.out.println("Invalid time slot.");
                return;
            }
    
            patient.scheduleAppointment(doctor, date, selectedSlot);
        }
    
        private void cancelAnAppointment() {
            System.out.println("Select an appointment to cancel:");
            List<Appointment> appointments = patient.getAppointments();
            appointments.forEach(appointment -> System.out.println("ID: " + appointment.getAppointmentID() + " - " + appointment));
            
            System.out.print("Enter the appointment ID to cancel: ");
            int appointmentID = scanner.nextInt();
            Appointment appointmentToCancel = appointments.stream()
                                                          .filter(a -> a.getAppointmentID() == appointmentID)
                                                          .findFirst()
                                                          .orElse(null);
    
            if (appointmentToCancel != null && (appointmentToCancel.getStatus() == AppointmentStatus.CONFIRMED || 
                                                appointmentToCancel.getStatus() == AppointmentStatus.PENDING)) {
                patient.cancelAppointment(appointmentToCancel);
                System.out.println("Appointment canceled successfully.");
            } else {
                System.out.println("Appointment ID not found or cannot be canceled.");
            }
        }


        public void viewAndPayForAppointment() {
            // Get the list of appointments from the patient
            List<Appointment> appointments = patient.getAppointments();
        
            // Check if the patient has any appointments
            if (appointments.isEmpty()) {
                System.out.println("No appointments available to view or pay for.");
                return;
            }
        
            // Display all appointments for the patient
            System.out.println("\n--- Your Appointments ---");
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        
            // Prompt the patient to enter the AppointmentID to pay for
            System.out.print("\nEnter the Appointment ID you want to pay for: ");
            int appointmentID = scanner.nextInt();
        
            // Find the appointment with the given AppointmentID
            Appointment selectedAppointment = null;
            for (Appointment appointment : appointments) {
                if (appointment.getAppointmentID() == appointmentID) {
                    selectedAppointment = appointment;
                    break;
                }
            }
        
            if (selectedAppointment == null) {
                System.out.println("Invalid Appointment ID. Please try again.");
                return;
            }
        
            // Ensure the appointment is completed
            if (selectedAppointment.getStatus() != AppointmentStatus.COMPLETED) {
                System.out.println("The selected appointment is not completed and cannot be invoiced.");
                return;
            }
        
            // Process the invoice and payment
            processInvoiceAndPayment(selectedAppointment);
        }
        

        private void processInvoiceAndPayment(Appointment appointment) {
            // Retrieve the appointment outcome record
            AppointmentOutcomeRecord outcome = appointment.getOutcomeRecord();
            if (outcome == null) {
                System.out.println("No outcome record available for the selected appointment.");
                return;
            }
        
            // Generate the invoice
            Invoice invoice = new Invoice(patient.getName(), outcome); // No serviceCost parameter needed
        
            // Display the invoice to the patient
            invoice.printInvoice();
        
            // Prompt the patient to confirm payment
            System.out.print("\nDo you want to pay this bill? (yes/no): ");
            scanner.nextLine(); // Consume newline
            String decision = scanner.nextLine().trim().toLowerCase();
        
            if ("yes".equals(decision)) {
                // Process the bill payment
                Bill bill = new Bill(invoice);
                bill.payBill();
            } else {
                System.out.println("Payment cancelled. Invoice remains unpaid.");
            }
        }
        
        private void viewPastAppointmentOutcomeRecords() {
            List<Appointment> appointments = patient.getAppointments();
            boolean hasPastAppointments = false;
        
            System.out.println("-- My Past Appointments --");
        
            // Header for the table of past appointments
            System.out.printf("%-25s %-15s %-15s %-20s %-20s\n", "Doctor", "Date", "Time", "Services Provided", "Consultation Notes");
            System.out.println("-------------------------------------------------------------------");
        
            // Iterate through the list of appointments
            for (Appointment appointment : appointments) {
                // Check if the appointment has a completed status
                if (appointment.getStatus() == AppointmentStatus.COMPLETED) {
                    AppointmentOutcomeRecord outcome = appointment.getOutcomeRecord();
        
                    if (outcome != null) {
                        String doctorName = appointment.getDoctor().getName();
                        String date = appointment.getDate();
                        String time = appointment.getTimeSlot().getStartTime() + " to " + appointment.getTimeSlot().getEndTime(); // Get the time range
                        String servicesProvided = outcome.getServiceType(); // The service type (e.g., General Checkup)
                        String consultationNotes = outcome.getConsultationNotes(); // Notes from the consultation
                        String prescribedMedications = formatPrescribedMedications(outcome.getPrescribedMedications()); // Format medications
        
                        // Print the appointment details along with outcome
                        System.out.printf("%-25s %-15s %-15s %-20s %-20s\n", doctorName, date, time, servicesProvided, consultationNotes);
                        System.out.println("Prescribed Medications: " + prescribedMedications);
                        hasPastAppointments = true;
                    }
                }
            }
        
            if (!hasPastAppointments) {
                System.out.println("You have no past completed appointments.");
            }
        }
        
        // Helper method to format prescribed medications
        private String formatPrescribedMedications(List<MedicationRecord> medications) {
            if (medications == null || medications.isEmpty()) {
                return "No medications prescribed.";
            }
            StringBuilder meds = new StringBuilder();
            for (MedicationRecord medication : medications) {
                meds.append(medication.getMedicationName()).append(" (").append(medication.getDosage()).append("), ");
            }
            return meds.toString().replaceAll(", $", ""); // Remove trailing comma
        }
        
        
}