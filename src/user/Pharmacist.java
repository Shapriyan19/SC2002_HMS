package user;

import java.io.*;
import java.util.List;

import appointment.Appointment;
import appointment.AppointmentOutcomeRecord;
import appointment.AppointmentStatus;
import appointment.MedicationRecord;
import inventory.Medication;
import inventory.Inventory;

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

    public void viewLatestAppointmentOutcomeRecord(Patient patient) {
            if (patient == null || patient.getAppointments().isEmpty()) {
                System.out.println("No appointments available for the patient.");
                return;
            }

            // Find the latest completed appointment
            Appointment latestCompletedAppointment = null;
            for (Appointment appointment : patient.getAppointments()) {
                if (appointment.getStatus() == AppointmentStatus.COMPLETED) {
                    if (latestCompletedAppointment == null || 
                        appointment.getDate().compareTo(latestCompletedAppointment.getDate()) > 0) {
                        latestCompletedAppointment = appointment;
                    }
                }
            }

            if (latestCompletedAppointment == null) {
                System.out.println("No completed appointments found for the patient.");
                return;
            }

            // Fetch the outcome record of the latest completed appointment
            AppointmentOutcomeRecord outcomeRecord = latestCompletedAppointment.getOutcomeRecord();
            if (outcomeRecord == null) {
                System.out.println("No outcome record available for the latest completed appointment.");
                return;
            }

            // Print the outcome record
            System.out.println("Latest Appointment Outcome Record:");
            System.out.println("Appointment Date: " + outcomeRecord.getAppointmentDate());
            System.out.println("Service Type: " + outcomeRecord.getServiceType());
            System.out.println("Consultation Notes: " + outcomeRecord.getConsultationNotes());
            System.out.println("Prescription Status: " + outcomeRecord.getPrescriptionStatus());

            // Print prescribed medications if available
            List<MedicationRecord> medications = outcomeRecord.getPrescribedMedications();
            if (medications == null || medications.isEmpty()) {
                System.out.println("No medications prescribed.");
            } else {
                System.out.println("Prescribed Medications:");
                for (MedicationRecord medication : medications) {
                    System.out.println(" - " + medication.getMedicationName() + " (" + medication.getDosage() + ")");
                }
            }
            System.out.println("-----------------------------------");
        }


    public void updatePrescriptionStatus(Patient patient) {
        if (patient == null || patient.getAppointments().isEmpty()) {
            System.out.println("No appointments available for the patient.");
            return;
        }

        // Find the latest appointment with a prescription
        Appointment latestCompletedAppointment = null;
        for (Appointment appointment : patient.getAppointments()) {
            if (appointment.getStatus() == AppointmentStatus.COMPLETED) {
                if (latestCompletedAppointment == null || 
                    appointment.getDate().compareTo(latestCompletedAppointment.getDate()) > 0) {
                    latestCompletedAppointment = appointment;
                }
            }
        }

        if (latestCompletedAppointment == null) {
            System.out.println("No completed appointments found with a prescription for the patient.");
            return;
        }

        // Update the prescription status
        AppointmentOutcomeRecord outcomeRecord = latestCompletedAppointment.getOutcomeRecord();
        if (outcomeRecord == null) {
            System.out.println("No outcome record available for the latest completed appointment.");
            return;
        }

        if ("pending".equals(outcomeRecord.getPrescriptionStatus())) {
            outcomeRecord.setPrescriptionStatus("approved");
            System.out.println("Prescription status updated to 'approved' for the latest completed appointment.");
        } else {
            System.out.println("Prescription status is already updated to: " + outcomeRecord.getPrescriptionStatus());
        }
    } 


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


    public void sendReplenishmentRequest(String medicationName, int requestedQuantity, Administrator administrator) {
        Inventory inventory = new Inventory(); // Initialize Inventory instance
        Medication medication = inventory.getAllMedications().get(medicationName);
    
        if (medication != null) {
            // Check if stock level is below the alert threshold
            if (medication.getStockLevel() < medication.getLowStockLevelAlert()) {
                System.out.println("Submitting replenishment request to Administrator...");
                System.out.println("Medication: " + medicationName);
                System.out.println("Requested Quantity: " + requestedQuantity);
    
                // Notify the administrator to handle the replenishment request
                administrator.approveReplenishmentRequest(medicationName, requestedQuantity);
            } else {
                System.out.println("Stock level for " + medicationName + " is sufficient. No replenishment needed.");
            }
        } else {
            System.out.println("Medication " + medicationName + " does not exist in the inventory.");
        }
    }
    
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


    public void updateCSV() {
        File file = new File("Data/Staff_List.csv");
        List<String> lines = new ArrayList<>();
        boolean isNew = true;
        boolean isHeaderWritten = false;
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if header is already written
                if (line.startsWith("Staff ID")) {
                    if (!isHeaderWritten) {
                        lines.add(line); // Write header only once
                        isHeaderWritten = true;
                    }
                } else if (line.startsWith(HospitalID + ",")) {
                    // Check if the record exists (by matching HospitalID)
                    isNew = false;
                    lines.add(toCSVFormat()); // Update the existing record
                } else {
                    lines.add(line); // Keep old records intact
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        // If the record doesn't exist, add it as a new one
        if (isNew) {
            lines.add(toCSVFormat());
        }
    
        // Write the updated data back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            if (!isHeaderWritten) {
                // Write header if it hasn't been written yet
                writer.write("Staff ID,Name,Password,Role,Specialty/Department,Gender,Age");
                writer.newLine();
            }
    
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
    
            System.out.println("Staff data updated in Staff_List.csv.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String toCSVFormat() {
        return HospitalID + "," + name + ","+ password + "," + role + "," + gender + "," + age;
    }

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
}
