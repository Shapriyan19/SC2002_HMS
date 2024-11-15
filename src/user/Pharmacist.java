package user;

import java.io.*;
import java.util.Map;
import java.util.HashMap;
import inventory.Inventory;
import inventory.Medication;
import medical.Prescription;
import medical.PrescriptionStatus;
import java.util.List;
import java.util.ArrayList;

public class Pharmacist extends User {

    private static List<Pharmacist> pharmacistsList=new ArrayList<>();
    // Instance variables
    // private String pharmacistID; to be removed
    private String name;
    // private Inventory inventory; // Inventory of medications with quantities
    // private Map<Integer, Prescription> prescriptions; // Collection of prescriptions managed by this pharmacist
    private String gender;
    private int age;

    // Constructor
    public Pharmacist(Role role, String name, String gender, int age) {
        super(generateNewHospitalID(role), role);
        // this.pharmacistID = pharmacistID;    to be removed
        this.name = name;
        // this.inventory = inventory; // Initialize inventory with the provided instance
        // this.prescriptions = new HashMap<>(); // Initialize the prescriptions map
        this.gender=gender;
        this.age=age;
        pharmacistsList.add(this);
        updateCSV();
    }

    //Constructor to get from CSV files
    public Pharmacist(String hospitalID,String name,String password,Role role,String gender,int age){
        super(hospitalID, role,password);
        this.name=name;
        this.gender=gender;
        this.age=age;
        pharmacistsList.add(this);
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

    // public Inventory getInventory() {
    //     return inventory;
    // }

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
