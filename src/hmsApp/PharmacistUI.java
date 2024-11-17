package hmsApp;

import user.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import appointment.Appointment;
import appointment.AppointmentOutcomeRecord;
import appointment.AppointmentStatus;
import appointment.MedicationRecord;
import appointment.TimeSlot;
import inventory.Inventory;
import inventory.Medication;

public class PharmacistUI {

    private Pharmacist pharmacist;
    private Scanner scanner;

    List<Patient> patientsList = new ArrayList<>();

    public PharmacistUI(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n--- Pharmacist Menu ---");
            System.out.println("1. View Appointment Outcome Record");
            System.out.println("2. Update Prescription Status");
            System.out.println("3. View Medication Inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5. Change Password");
            System.out.println("6. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    viewAppointmentOutcomeRecord();
                    break;
                case 2:
                    viewAndUpdatePrescriptionStatus();
                    break;
                case 3:
                    viewMedicationInventory();
                    break;
                case 4:
                    submitReplenishmentRequest();
                    break;
                case 5:
                    changePassword();
                    break;
                case 6:
                    logout();
                    return; // exit the UI
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void viewAppointmentOutcomeRecord() {
        pharmacist.viewAppointmentOutcomeRecord();
    }

    public void viewAndUpdatePrescriptionStatus() {
        Inventory inventory = new Inventory(); // Ensure this instance is initialized properly
    
        List<Doctor> doctorsList = Doctor.getDoctorsList(); // Assuming this method exists to get all doctors
    
        if (doctorsList.isEmpty()) {
            System.out.println("No doctors available to view appointment outcomes.");
            return;
        }
    
        System.out.println("--- All Doctors' Appointment Outcome Records ---");
    
        for (Doctor doctor : doctorsList) {
            List<Appointment> doctorAppointments = doctor.getCalendar().getAppointmentsForDoctor(doctor); 
            System.out.println("Appointment Outcomes for Dr. " + doctor.getName() + ":");
    
            if (doctorAppointments.isEmpty()) {
                System.out.println("  No appointments scheduled for this doctor.");
            } else {
                List<Appointment> appointmentsWithOutcome = new ArrayList<>();
                for (Appointment app : doctorAppointments) {
                    AppointmentOutcomeRecord outcomeRecord = app.getOutcomeRecord();
    
                    if (outcomeRecord == null) {
                        System.out.println("  Appointment ID: " + app.getAppointmentID() + " | No outcome recorded yet.");
                    } else {
                        System.out.println("  Appointment ID: " + app.getAppointmentID() +
                                           " | Patient: " + app.getPatient().getName() +
                                           " | Date: " + app.getDate() +
                                           " | Outcome Service Type: " + outcomeRecord.getServiceType() +
                                           " | Prescription Status: " + outcomeRecord.getPrescriptionStatus());
                        System.out.println("    Prescribed Medications: ");
                        for (MedicationRecord medication : outcomeRecord.getPrescribedMedications()) {
                            System.out.println("      - " + medication.getMedicationName() + 
                                               " | Dosage: " + medication.getDosage() + 
                                               " | Price per unit: $" + medication.getPrice());
                        }
                        System.out.println("    Consultation Notes: " + outcomeRecord.getConsultationNotes());
                        appointmentsWithOutcome.add(app);
                    }
                }
    
                if (!appointmentsWithOutcome.isEmpty()) {
                    boolean keepUpdating = true;
                    while (keepUpdating) {
                        System.out.println("Options:");
                        System.out.println("1. Enter Appointment ID to update prescription status.");
                        System.out.println("2. Exit the program.");
                        System.out.print("Select an option: ");
                        int userChoice = scanner.nextInt();
                        scanner.nextLine(); 
    
                        if (userChoice == 2) {
                            System.out.println("Exiting prescription update process...");
                            return;
                        } else if (userChoice == 1) {
                            System.out.print("Enter Appointment ID: ");
                            int appointmentId = scanner.nextInt();
                            scanner.nextLine(); 
    
                            Appointment selectedAppointment = null;
                            for (Appointment app : appointmentsWithOutcome) {
                                if (app.getAppointmentID() == appointmentId) {
                                    selectedAppointment = app;
                                    break;
                                }
                            }
    
                            if (selectedAppointment != null) {
                                AppointmentOutcomeRecord selectedOutcomeRecord = selectedAppointment.getOutcomeRecord();
                                if (selectedOutcomeRecord.getPrescriptionStatus().equalsIgnoreCase("dispensed")) {
                                    System.out.println("Prescription is already marked as dispensed.");
                                    continue;
                                }
    
                                pharmacist.updatePrescriptionStatus(selectedOutcomeRecord);
    
                                for (MedicationRecord medication : selectedOutcomeRecord.getPrescribedMedications()) {
                                    Medication inventoryMedication = inventory.getMedicationByName(medication.getMedicationName());
                                    if (inventoryMedication != null) {
                                        int currentStock = inventoryMedication.getStockLevel();
                                        int newStock = currentStock - medication.getDosage();
                                        if (newStock >= 0) {
                                            inventoryMedication.setStockLevel(newStock);
                                            System.out.println("Stock level of " + medication.getMedicationName() + 
                                                               " reduced to " + newStock + 
                                                               " | Dosage deducted: " + medication.getDosage());
                                        } else {
                                            System.out.println("ALERT: Not enough stock for " + medication.getMedicationName() + ". Current stock: " + currentStock);
                                        }
                                    } else {
                                        System.out.println("Medication " + medication.getMedicationName() + " not found in inventory.");
                                    }
                                }
                                inventory.updateCSV();
                            } else {
                                System.out.println("Invalid Appointment ID.");
                            }
                        } else {
                            System.out.println("Invalid choice. Please try again.");
                        }
    
                        System.out.println("Do you want to update another appointment? (yes/no): ");
                        String continueResponse = scanner.nextLine().trim().toLowerCase();
                        if (!continueResponse.equals("yes")) {
                            keepUpdating = false;
                            System.out.println("Returning to the main menu...");
                        }
                    }
                } else {
                    System.out.println("No completed appointments with outcome records for this doctor.");
                }
            }
    
            System.out.println(); 
        }
    }

    private void viewMedicationInventory() {
        System.out.println("\n--- View Medication Inventory ---");
        pharmacist.viewMedicationInventory();
    }

    private void submitReplenishmentRequest() {
        System.out.println("\n--- Submit Replenishment Request ---");
        System.out.print("Enter Medication Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Quantity to Replenish: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        pharmacist.sendReplenishmentRequest(name, quantity);
        System.out.println("Replenishment request has been sent to the administrator.");
        System.out.println("The administrator can now view and process this request.");
    }

    private void changePassword() {
        System.out.println("\n--- Change Password ---");
        System.out.print("Enter Current Password: ");
        String currentPassword = scanner.nextLine();
        System.out.print("Enter New Password: ");
        String newPassword = scanner.nextLine();

        boolean result = pharmacist.changePassword(currentPassword, newPassword);
        if (result) {
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Password change failed. Please try again.");
        }
    }

    private void logout() {
        pharmacist.logout();
        System.out.println("\nLogging out. Goodbye, " + pharmacist.getName() + "!");
    }

    private Patient getPatientById(String patientId) {
        for (Patient patient : patientsList) {
            if (patient.getHospitalID().equals(patientId)) {
                return patient;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Pharmacist pharmacist = new Pharmacist(Role.PHARMACIST, "Pharmacist User", "Female", 30);
        PharmacistUI ui = new PharmacistUI(pharmacist);
        ui.showMenu();
    }
}
