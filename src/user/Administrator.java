package user;

import inventory.Inventory;
import inventory.Medication;
import inventory.MedicationOrder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;


public class Administrator extends User {

    private static List<Administrator> administratorList=new ArrayList<>();
    // Instance variables
    // private String adminID;  to be removed
    private String name;
    // private Inventory inventory;
    private String gender;
    private int age;
    

    // Constructor
    public Administrator(Role role, String name,String gender, int age) {
        super(generateNewHospitalID(role), role);
        // this.adminID = adminID;  to be removed
        this.name = name;
        // this.inventory=inventory;
        this.gender=gender;
        this.age=age;
        administratorList.add(this);
        updateCSV();
    }

    //Constructor to get from CSV File
    public Administrator(String hospitalID,String name,String password,Role role,String gender,int age){
        super(hospitalID, role,password);
        this.name=name;
        this.gender=gender;
        this.age=age;
        administratorList.add(this);
    }

    // Method to manage hospital staff
    // public void manageHospitalStaff(String HospitalID, String action) {
    //     System.out.println("Managing hospital staff with ID: " + HospitalID);
    //     System.out.println("Action: " + action);
    //     // Implement logic to add, update, or remove hospital staff based on the action
    // }

    // Method to view hospital staff details
    // public void viewHospitalStaff() {
    //     System.out.println("Viewing all hospital staff details:");
    //     // Implement logic to retrieve and display hospital staff details
    // }

    // Method to view appointment details
    // public void viewAppointmentDetails() {
    //     System.out.println("Viewing appointment details:");
    //     // Implement logic to retrieve and display appointment details
    // }

    // Method to view medication inventory
    // public void viewMedicationInventory() {
    //     System.out.println("Viewing medication inventory:");
    //     Map<Integer, Medication> medications = inventory.getAllMedications();
    //     for (Medication medication : medications.values()) {
    //         System.out.println("Medication ID: " + medication.getMedicationID() + ", Name: " + medication.getName() +", Stock Level: " + medication.getStockLevel());
    //     }
    // }

    // Method to manage medication inventory
    // public void manageMedicationInventory(int medicationID, String action, int quantity) {
    //     Medication medication = inventory.getMedication(medicationID);
    //     if (medication == null) {
    //         System.out.println("Medication with ID " + medicationID + " not found.");
    //         return;
    //     }
    //     switch (action.toLowerCase()) {
    //         case "add":
    //             inventory.updateStockLevel(medicationID, medication.getStockLevel() + quantity);
    //             System.out.println("Added " + quantity + " units to Medication ID: " + medicationID);
    //             break;
    //         case "remove":
    //             int newStockLevel = medication.getStockLevel() - quantity;
    //             if (newStockLevel < 0) {
    //                 System.out.println("Cannot remove " + quantity + " units. Insufficient stock.");
    //             } else {
    //                 inventory.updateStockLevel(medicationID, newStockLevel);
    //                 System.out.println("Removed " + quantity + " units from Medication ID: " + medicationID);
    //             }
    //             break;
    //         case "update":
    //             inventory.updateStockLevel(medicationID, quantity);
    //             System.out.println("Updated stock level of Medication ID " + medicationID + " to " + quantity);
    //             break;
    //         default:
    //             System.out.println("Invalid action. Please choose 'add', 'remove', or 'update'.");
    //     }
    // }

    // Method to approve replenishment requests
    // public void approveReplenishmentRequest(MedicationOrder order) {
    //     if (order.getStatus().equalsIgnoreCase("PENDING")) {
    //         inventory.updateStockLevel(order.getMedicationID(), 
    //             inventory.getMedication(order.getMedicationID()).getStockLevel() + order.getQuantity());
    //         order.setStatus("DISPENSED");
    //         System.out.println("Approved and processed replenishment request ID: " + order.getOrderID());
    //     } else {
    //         System.out.println("Replenishment request ID: " + order.getOrderID() + " is already " + order.getStatus());
    //     }
    // }

    // Login method implementation
    @Override
    public boolean login(String enteredPassword) {
        if (this.password.equals(enteredPassword)) {
            this.isLoggedIn = true;
            System.out.println("Login successful for user: " + HospitalID);
            return true;
        } else {
            System.out.println("Login failed: Incorrect password.");
            return false;}
        }

    @Override
    public boolean changePassword(String currentPassword, String newPassword) {
        if (!isLoggedIn) {
            System.out.println("Change password failed: Patient is not logged in.");
            return false;
        }

        if (this.getPassword().equals(currentPassword)) {
            if (newPassword.length() >= 8) { // Basic validation for password length
                setPassword(newPassword);
                System.out.println("Password changed successfully for Patient: " + name);
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
    
    @Override
    public void logout() {
        System.out.println("Logging out patient: " + name);
    }
    

    public static List<Administrator> getAdministratorsList(){
        return administratorList;
    }
    // Getters

    public String getName() {
        return name;
    }

    public String getGender(){
        return gender;
    }

    public int getAge(){
        return age;
    }
}

