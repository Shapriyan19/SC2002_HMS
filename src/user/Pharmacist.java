package user;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import appointment.Appointment;
import appointment.AppointmentOutcomeRecord;
import appointment.AppointmentStatus;
import appointment.MedicationRecord;
import inventory.Medication;
import inventory.ReplenishmentRequest;
import inventory.Inventory;
import hmsApp.AdministratorUI;

import java.util.ArrayList;

public class Pharmacist extends User {

    private static List<Pharmacist> pharmacistsList=new ArrayList<>();
    private String name;
    private String gender;
    private int age;
    private static Inventory inventory;

    // Constructor
    public Pharmacist(Role role, String name, String gender, int age) {
        super(generateNewHospitalID(role), role);
        this.name = name;
        this.gender=gender;
        this.age=age;
        pharmacistsList.add(this);
        if (inventory == null) {
            inventory = new Inventory();
        }
        updateCSV();
    }

    //Constructor to get from CSV files
    public Pharmacist(String hospitalID,String name,String password,Role role,String gender,int age){
        super(hospitalID, role,password);
        this.name=name;
        this.gender=gender;
        this.age=age;
        if (inventory == null) {
            inventory = new Inventory();
        }
        pharmacistsList.add(this);
    }


    public void viewAppointmentOutcomeRecord() {
        // Get the list of doctors to retrieve their appointments
        List<Doctor> doctorsList = Doctor.getDoctorsList(); // Assuming this method exists to get all doctors
        
        if (doctorsList.isEmpty()) {
            System.out.println("No doctors available to view appointment outcomes.");
            return;
        }

        System.out.println("--- All Doctors' Appointment Outcome Records ---");

        // Loop through each doctor to display the appointment outcome records
        for (Doctor doctor : doctorsList) {
            List<Appointment> doctorAppointments = doctor.getCalendar().getAppointmentsForDoctor(doctor); // Get appointments for each doctor
            System.out.println("Appointment Outcomes for Dr. " + doctor.getName() + ":");

            if (doctorAppointments.isEmpty()) {
                System.out.println("  No appointments scheduled for this doctor.");
            } else {
                for (Appointment app : doctorAppointments) {
                    AppointmentOutcomeRecord outcomeRecord = app.getOutcomeRecord(); // Get the outcome record for each appointment

                    if (outcomeRecord == null) {
                        System.out.println("  Appointment ID: " + app.getAppointmentID() + " | No outcome recorded yet.");
                    } else {
                        // Display outcome record details
                        System.out.println("  Appointment ID: " + app.getAppointmentID() +
                                           " | Patient: " + app.getPatient().getName() +
                                           " | Date: " + app.getDate() +
                                           " | Outcome Service Type: " + outcomeRecord.getServiceType() +
                                           " | Prescription Status: " + outcomeRecord.getPrescriptionStatus());
                        System.out.println("    Prescribed Medications: ");
                        for (MedicationRecord medication : outcomeRecord.getPrescribedMedications()) {
                            System.out.println("      - " + medication.getMedicationName());
                        }
                        System.out.println("    Consultation Notes: " + outcomeRecord.getConsultationNotes());
                    }
                }
            }
            System.out.println(); // Add a line break between doctors
        }
    }

    // newly added method to updatePrescriptionStatus with ApptOutcomeRecord
    public void updatePrescriptionStatus(AppointmentOutcomeRecord outcomeRecord) {
        if (outcomeRecord == null) {
            System.out.println("No outcome record provided.");
            return;
        }
    
        // Check the prescription status and update if necessary
        if ("pending".equals(outcomeRecord.getPrescriptionStatus())) {
            outcomeRecord.setPrescriptionStatus("DISPENSED");
            System.out.println("Prescription status updated to 'DISPENSED'.");
        } else {
            System.out.println("Prescription status is already updated to: " + outcomeRecord.getPrescriptionStatus());
        }
    }
    

    // public void updatePrescriptionStatus(Patient patient) {
    //     if (patient == null || patient.getAppointments().isEmpty()) {
    //         System.out.println("No appointments available for the patient.");
    //         return;
        

    //     // Find the latest appointment with a prescription
    //     Appointment latestCompletedAppointment = null;
    //     for (Appointment appointment : patient.getAppointments()) {
    //         if (appointment.getStatus() == AppointmentStatus.COMPLETED) {
    //             if (latestCompletedAppointment == null || 
    //                 appointment.getDate().compareTo(latestCompletedAppointment.getDate()) > 0) {
    //                 latestCompletedAppointment = appointment;
    //             }
    //         }
    //     }

    //     if (latestCompletedAppointment == null) {
    //         System.out.println("No completed appointments found with a prescription for the patient.");
    //         return;
    //     }

    //     // Update the prescription status
    //     AppointmentOutcomeRecord outcomeRecord = latestCompletedAppointment.getOutcomeRecord();
    //     if (outcomeRecord == null) {
    //         System.out.println("No outcome record available for the latest completed appointment.");
    //         return;
    //     }

    //     if ("pending".equals(outcomeRecord.getPrescriptionStatus())) {
    //         outcomeRecord.setPrescriptionStatus("approved");
    //         System.out.println("Prescription status updated to 'approved' for the latest completed appointment.");
    //     } else {
    //         System.out.println("Prescription status is already updated to: " + outcomeRecord.getPrescriptionStatus());
    //     }
    // } 


    public void viewMedicationInventory() {
    System.out.println("Current Inventory of Medications:");
    for (Medication medication : inventory.getAllMedications().values()) {
        System.out.println("Name: " + medication.getName());
        System.out.println("Stock Level: " + medication.getStockLevel());
        System.out.println("Low Stock Alert Level: " + medication.getLowStockLevelAlert());
        System.out.println("Price: $" + medication.getPrice());
        System.out.println("-----------------------------------");
    }
    }

    public void sendReplenishmentRequest(String medicationName, int requestedQuantity) {
        Medication medication = inventory.getAllMedications().get(medicationName);
    
        if (medication != null) {
            if (medication.getStockLevel() < medication.getLowStockLevelAlert()) {
                // Create a replenishment request object
                ReplenishmentRequest request = new ReplenishmentRequest(medicationName, requestedQuantity, this.getName());
    
                // Add the request to the shared list
                ReplenishmentRequest.addReplenishmentRequest(request);
    
                System.out.println("Replenishment request submitted for approval.");
                System.out.println("Medication: " + medicationName);
                System.out.println("Requested Quantity: " + requestedQuantity);
            } else {
                System.out.println("Stock level for " + medicationName + " is sufficient. No replenishment needed.");
            }
        } else {
            System.out.println("Medication " + medicationName + " does not exist in the inventory.");
        }
    }
    
    
    

//     public void sendReplenishmentRequest(String medicationName, int requestedQuantity, List<Administrator> administratorsList) {
//     Medication medication = inventory.getAllMedications().get(medicationName);

//     if (medication != null) {
//         // Check if stock level is below the alert threshold
//         if (medication.getStockLevel() < medication.getLowStockLevelAlert()) {
//             // Display available administrators
//             if (administratorsList.isEmpty()) {
//                 System.out.println("No administrators available to handle the replenishment request.");
//                 return;
//             }

//             System.out.println("Available Administrators:");
//             for (int i = 0; i < administratorsList.size(); i++) {
//                 System.out.println((i + 1) + ". " + administratorsList.get(i).getName());
//             }

//             // Prompt the pharmacist to select an administrator
//             System.out.print("Select an Administrator to send the replenishment request to (1-" + administratorsList.size() + "): ");
//             Scanner scanner = new Scanner(System.in);
//             int choice = scanner.nextInt();

//             if (choice < 1 || choice > administratorsList.size()) {
//                 System.out.println("Invalid choice. Replenishment request not sent.");
//                 return;
//             }

//             // Get the selected administrator
//             Administrator selectedAdministrator = administratorsList.get(choice - 1);

//             System.out.println("Submitting replenishment request to Administrator: " + selectedAdministrator.getName());
//             System.out.println("Medication: " + medicationName);
//             System.out.println("Requested Quantity: " + requestedQuantity);

//             // Create a replenishment request object
//             ReplenishmentRequest request = new ReplenishmentRequest(medicationName, requestedQuantity, this.getName());

//             // Notify the selected administrator to handle the replenishment request
//             selectedAdministrator.approveReplenishmentRequest(request);
//         } else {
//             System.out.println("Stock level for " + medicationName + " is sufficient. No replenishment needed.");
//         }
//     } else {
//         System.out.println("Medication " + medicationName + " does not exist in the inventory.");
//     }
// }

    // public void sendReplenishmentRequest(String medicationName, int requestedQuantity, Administrator administrator) {
    //     Inventory inventory = new Inventory(); // Initialize Inventory instance
    //     Medication medication = inventory.getAllMedications().get(medicationName);
    
    //     if (medication != null) {
    //         // Check if stock level is below the alert threshold
    //         if (medication.getStockLevel() < medication.getLowStockLevelAlert()) {
    //             System.out.println("Submitting replenishment request to Administrator...");
    //             System.out.println("Medication: " + medicationName);
    //             System.out.println("Requested Quantity: " + requestedQuantity);
    
    //             // Notify the administrator to handle the replenishment request
    //             administrator.approveReplenishmentRequest(medicationName, requestedQuantity);
    //         } else {
    //             System.out.println("Stock level for " + medicationName + " is sufficient. No replenishment needed.");
    //         }
    //     } else {
    //         System.out.println("Medication " + medicationName + " does not exist in the inventory.");
    //     }
    // }
    
    // Implementing login method
    @Override
    public boolean login(String enteredPassword) {
        if (this.password.equals(enteredPassword)) {
            this.isLoggedIn = true;
            System.out.println("Login successful for user: " + HospitalID);
            return true;
        } else {
            System.out.println("Login failed: Incorrect password.");
            return false;
        }
    }

    @Override
    public boolean changePassword(String currentPassword, String newPassword) {
        if (!isLoggedIn) {
            System.out.println("Change password failed: Pharmacist is not logged in.");
            return false;
        }

        if (this.getPassword().equals(currentPassword)) {
            if (newPassword.length() >= 8) { // Basic validation for password length
                setPassword(newPassword);
                System.out.println("Password changed successfully for Pharmacist: " + name);
                updateCSV();
                return true;
            } else {
                System.out.println("New password must be at least 8 characters long.");
                return false;
            }
        } else {
            System.out.println("Change password failed: Current password is incorrect.");
            return false;
        }
    }

    @Override
    public void logout() {
        System.out.println("Logging out Pharmacist: " + name);
    }

    // Getters and Setters (if needed)

    public String getName() {
        return name;
    }

    public static List<Pharmacist> getPharmacistsList(){
        return pharmacistsList;
    }

    public String getGender(){
        return gender;
    }

    public int getAge(){
        return age;
    }

        // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHospitalID(String HospitalID) {
        this.HospitalID = HospitalID;
    }


    private void updateCSV() {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("Data/Staff_List.csv"))) {
            // Writing the header to the CSV file
            String header = "Staff ID,Name,Password,Role,Gender,Age";
            bw.write(header);
            bw.newLine();  // Move to the next line after the header
    
            // Writing each staff member's details
            for (Pharmacist pharmacist : pharmacistsList) {  // assuming pharmacistsList is a collection of pharmacist objects
                String line = String.join(",",
                    pharmacist.getHospitalID(),
                    pharmacist.getName(),
                    pharmacist.getPassword(),
                    pharmacist.getRole().toString(),  // Convert Role to String
                    pharmacist.getGender(),
                    String.valueOf(pharmacist.getAge()));  // Convert Age to String
                bw.write(line);
                bw.newLine();  // Move to the next line for each staff member
            }
        } catch (IOException e) {
            System.out.println("Error updating the CSV file: " + e.getMessage());
        }
    }
    
}

    // Method to export inventory to CSV
    // public void exportInventoryToCSV(String filePath) {
    //     try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
    //         writer.println("MedicationID,Name,StockLevel,LowStockAlertLevel");
    //         for (Medication medication : inventory.getAllMedications().values()) {
    //             writer.println(medication.getMedicationID() + "," 
    //                            + medication.getName() + "," 
    //                            + medication.getStockLevel() + "," 
    //                            + medication.getLowStockLevelAlert());
    //         }
    //         System.out.println("Inventory successfully exported to CSV file: " + filePath);
    //     } catch (IOException e) {
    //         System.out.println("Error exporting inventory to CSV: " + e.getMessage());
    //     }
    // }

    // Method to view the outcomes of appointments
    // public void viewAppointmentOutcomeRecords() {
    //     System.out.println("Viewing appointment outcome records:");
    //     // Placeholder logic: Retrieve and display appointment outcomes that involve prescriptions
    // }

    // Method to update the status of a prescription
    // public void updatePrescriptionStatus(Prescription prescription, PrescriptionStatus newStatus) {
    //     System.out.println("Updating status of prescription ID: " + prescription.getPrescriptionID() + " to " + newStatus);
    //     if (prescription != null) {
    //         prescription.setStatus(newStatus);
    //         System.out.println("Prescription ID " + prescription.getPrescriptionID() + " status updated to: " + newStatus);
    //     } 
    //     else {
    //         System.out.println("Prescription not found.");
    //     }
    // }

    // Method to view the current inventory of medications
    // public void viewMedicationInventory() {
    //     System.out.println("Medication Inventory:");
    //     Map<Integer, Medication> medications = inventory.getAllMedications();
    //     for (Medication medication : medications.values()) {
    //         System.out.println("Medication: " + medication.getName() + ", ID: " + medication.getMedicationID() + ", Stock Level: " + medication.getStockLevel());
    //     }
    // }

    // Method to submit a replenishment report
    // public void submitReplenishmentReport(String medicationName, int quantityNeeded) {
    //     System.out.println("Replenishment report submitted for medication: " + medicationName + ", Quantity needed: " + quantityNeeded);
    //     // Implement logic to submit a replenishment report for low-stock medications
    // }

    // Method to add a prescription to the pharmacist's list
    // public void addPrescription(Prescription prescription) {
    //     prescriptions.put(prescription.getPrescriptionID(), prescription);
    //     // System.out.println("Prescription added for patient: " + prescription.getPatientName()); need to add 
    // }

    // Method to remove a prescription from the pharmacist's list
    // public void removePrescription(int prescriptionID) {
    //     if (prescriptions.containsKey(prescriptionID)) {
    //         prescriptions.remove(prescriptionID);
    //         System.out.println("Prescription with ID " + prescriptionID + " removed.");
    //     } else {
    //         System.out.println("Prescription ID " + prescriptionID + " not found.");
    //     }
    // }

    // Method to view all prescriptions managed by the pharmacist
    // public void viewAllPrescriptions() {
    //     System.out.println("Viewing all prescriptions managed by Pharmacist " + name + ":");
    //     for (Prescription prescription : prescriptions.values()) {
    //         // System.out.println("Prescription ID: " + prescription.getPrescriptionID() + ", Patient: " + prescription.getPatientName() + ", Status: " + prescription.getStatus()); need to use this
    //         System.out.println("Prescription ID: " + prescription.getPrescriptionID() + ", Status: " + prescription.getStatus());
    //     }
    // }

    // Method to get a specific prescription by ID
    // public Prescription getPrescription(int prescriptionID) {
    //     return prescriptions.get(prescriptionID);
    // }

    // Method to add medication to inventory
    // public void addMedicationToInventory(String medicationName, int quantity, int lowStockLevelAlert) {
    //     inventory.addMedication(new Medication(medicationName, quantity,lowStockLevelAlert)); // Assuming Medication constructor takes name and stock level
    //     System.out.println("Added medication to inventory: " + medicationName + " (Quantity: " + quantity + ")"+" (Low Stock Alert Level: "+lowStockLevelAlert+")");
    // }
