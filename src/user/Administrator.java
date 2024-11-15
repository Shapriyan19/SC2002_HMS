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
    private static Inventory inventory;
    

    // Constructor
    public Administrator(Role role, String name,String gender, int age) {
        super(generateNewHospitalID(role), role);
        // this.adminID = adminID;  to be removed
        this.name = name;
        // this.inventory=inventory;
        this.gender=gender;
        this.age=age;
        administratorList.add(this);
        if (inventory == null) {
            inventory = new Inventory();
        }
        updateCSV();
    }

    //Constructor to get from CSV File
    public Administrator(String hospitalID,String name,String password,Role role,String gender,int age){
        super(hospitalID, role,password);
        this.name=name;
        this.gender=gender;
        this.age=age;
        administratorList.add(this);
        if (inventory == null) {
            inventory = new Inventory();
        }
    }


    
    public void viewHospitalStaff() {
        System.out.println("Hospital Staff List:");

        // View doctors
        System.out.println("\nDoctors:");
        for (Doctor doctor : Doctor.getDoctorsList()) {
            System.out.println("ID: " + doctor.getHospitalID() + ", Name: " + doctor.getName() +
                               ", Gender: " + doctor.getGender() + ", Age: " + doctor.getAge());
        }

        // View administrators
        System.out.println("\nAdministrators:");
        for (Administrator admin : Administrator.getAdministratorsList()) {
            System.out.println("ID: " + admin.getHospitalID() + ", Name: " + admin.getName() +
                               ", Gender: " + admin.getGender() + ", Age: " + admin.getAge());
        }

        // View pharmacists
        System.out.println("\nPharmacists:");
        for (Pharmacist pharmacist : Pharmacist.getPharmacistsList()) {
            System.out.println("ID: " + pharmacist.getHospitalID() + ", Name: " + pharmacist.getName() +
                               ", Gender: " + pharmacist.getGender() + ", Age: " + pharmacist.getAge());
        }
    }

    // Method to manage Doctor staff (add, update, or remove)
    public void manageHospitalDoctor(String action, Doctor doctor) {
        switch (action.toLowerCase()) {
            case "add":
                addDoctor(doctor);
                break;
            case "update":
                updateDoctor(doctor);
                break;
            case "remove":
                removeDoctor(doctor.getHospitalID());
                break;
            default:
                System.out.println("Invalid action. Please use 'add', 'update', or 'remove'.");
        }
        updateCSV(); // Update CSV after any modification
    }

    // Method to manage Administrator staff (add, update, or remove)
    public void manageHospitalAdministrator(String action, Administrator administrator) {
        switch (action.toLowerCase()) {
            case "add":
                addAdministrator(administrator);
                break;
            case "update":
                updateAdministrator(administrator);
                break;
            case "remove":
                removeAdministrator(administrator.getHospitalID());
                break;
            default:
                System.out.println("Invalid action. Please use 'add', 'update', or 'remove'.");
        }
        updateCSV(); // Update CSV after any modification
    }

    // Method to manage Pharmacist staff (add, update, or remove)
    public void manageHospitalPharmacist(String action, Pharmacist pharmacist) {
        switch (action.toLowerCase()) {
            case "add":
                addPharmacist(pharmacist);
                break;
            case "update":
                updatePharmacist(pharmacist);
                break;
            case "remove":
                removePharmacist(pharmacist.getHospitalID());
                break;
            default:
                System.out.println("Invalid action. Please use 'add', 'update', or 'remove'.");
        }
        updateCSV(); // Update CSV after any modification
    }

    // Add, Update, and Remove methods for Doctor
    private void addDoctor(Doctor doctor) {
        if (!Doctor.getDoctorsList().contains(doctor)) {
            Doctor.getDoctorsList().add(doctor);
            System.out.println("Added Doctor: " + doctor.getName() + ", ID: " + doctor.getHospitalID());
        } else {
            System.out.println("Doctor already exists.");
        }
    }

    private void updateDoctor(Doctor updatedDoctor) {
        Doctor existingDoctor = findDoctorById(updatedDoctor.getHospitalID());
        if (existingDoctor != null) {
            existingDoctor.setName(updatedDoctor.getName());
            existingDoctor.setPassword(updatedDoctor.getPassword());
            existingDoctor.setGender(updatedDoctor.getGender());
            existingDoctor.setAge(updatedDoctor.getAge());
            System.out.println("Updated Doctor: " + existingDoctor.getName() + ", ID: " + existingDoctor.getHospitalID());
        } else {
            System.out.println("Doctor with ID " + updatedDoctor.getHospitalID() + " not found.");
        }
    }

    private void removeDoctor(String doctorID) {
        Doctor doctor = findDoctorById(doctorID);
        if (doctor != null) {
            Doctor.getDoctorsList().remove(doctor);
            System.out.println("Removed Doctor: " + doctor.getName() + ", ID: " + doctor.getHospitalID());
        } else {
            System.out.println("Doctor with ID " + doctorID + " not found.");
        }
    }

    // Add, Update, and Remove methods for Administrator
    private void addAdministrator(Administrator administrator) {
        if (!Administrator.getAdministratorsList().contains(administrator)) {
            Administrator.getAdministratorsList().add(administrator);
            System.out.println("Added Administrator: " + administrator.getName() + ", ID: " + administrator.getHospitalID());
        } else {
            System.out.println("Administrator already exists.");
        }
    }

    private void updateAdministrator(Administrator updatedAdministrator) {
        Administrator existingAdministrator = findAdministratorById(updatedAdministrator.getHospitalID());
        if (existingAdministrator != null) {
            existingAdministrator.setName(updatedAdministrator.getName());
            existingAdministrator.setPassword(updatedAdministrator.getPassword());
            existingAdministrator.setGender(updatedAdministrator.getGender());
            existingAdministrator.setAge(updatedAdministrator.getAge());
            System.out.println("Updated Administrator: " + existingAdministrator.getName() + ", ID: " + existingAdministrator.getHospitalID());
        } else {
            System.out.println("Administrator with ID " + updatedAdministrator.getHospitalID() + " not found.");
        }
    }

    private void removeAdministrator(String administratorID) {
        Administrator administrator = findAdministratorById(administratorID);
        if (administrator != null) {
            Administrator.getAdministratorsList().remove(administrator);
            System.out.println("Removed Administrator: " + administrator.getName() + ", ID: " + administrator.getHospitalID());
        } else {
            System.out.println("Administrator with ID " + administratorID + " not found.");
        }
    }

    // Add, Update, and Remove methods for Pharmacist
    private void addPharmacist(Pharmacist pharmacist) {
        if (!Pharmacist.getPharmacistsList().contains(pharmacist)) {
            Pharmacist.getPharmacistsList().add(pharmacist);
            System.out.println("Added Pharmacist: " + pharmacist.getName() + ", ID: " + pharmacist.getHospitalID());
        } else {
            System.out.println("Pharmacist already exists.");
        }
    }

    private void updatePharmacist(Pharmacist updatedPharmacist) {
        Pharmacist existingPharmacist = findPharmacistById(updatedPharmacist.getHospitalID());
        if (existingPharmacist != null) {
            existingPharmacist.setName(updatedPharmacist.getName());
            existingPharmacist.setPassword(updatedPharmacist.getPassword());
            existingPharmacist.setGender(updatedPharmacist.getGender());
            existingPharmacist.setAge(updatedPharmacist.getAge());
            System.out.println("Updated Pharmacist: " + existingPharmacist.getName() + ", ID: " + existingPharmacist.getHospitalID());
        } else {
            System.out.println("Pharmacist with ID " + updatedPharmacist.getHospitalID() + " not found.");
        }
    }

    private void removePharmacist(String pharmacistID) {
        Pharmacist pharmacist = findPharmacistById(pharmacistID);
        if (pharmacist != null) {
            Pharmacist.getPharmacistsList().remove(pharmacist);
            System.out.println("Removed Pharmacist: " + pharmacist.getName() + ", ID: " + pharmacist.getHospitalID());
        } else {
            System.out.println("Pharmacist with ID " + pharmacistID + " not found.");
        }
    }

    // Helper methods to find staff by ID in each category
    private Doctor findDoctorById(String id) {
        for (Doctor doctor : Doctor.getDoctorsList()) {
            if (doctor.getHospitalID().equals(id)) {
                return doctor;
            }
        }
        return null;
    }

    private Administrator findAdministratorById(String id) {
        for (Administrator admin : Administrator.getAdministratorsList()) {
            if (admin.getHospitalID().equals(id)) {
                return admin;
            }
        }
        return null;
    }

    private Pharmacist findPharmacistById(String id) {
        for (Pharmacist pharmacist : Pharmacist.getPharmacistsList()) {
            if (pharmacist.getHospitalID().equals(id)) {
                return pharmacist;
            }
        }
        return null;
    }

    // Update the Staff_List.csv file with the latest data from all staff lists
    private void updateCSV() {
        File file = new File("Data/Staff_List.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Staff ID,Name,Password,Role,Gender,Age");
            writer.newLine();

            for (Doctor doctor : Doctor.getDoctorsList()) {
                writer.write(formatDoctorData(doctor));
                writer.newLine();
            }
            for (Administrator admin : Administrator.getAdministratorsList()) {
                writer.write(formatadminData(admin));
                writer.newLine();
            }
            for (Pharmacist pharmacist : Pharmacist.getPharmacistsList()) {
                writer.write(formatpharmData(pharmacist));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating Staff_List.csv: " + e.getMessage());
        }
    }

    // Helper method to format staff data for CSV writing
    private String formatDoctorData(Doctor staff) {
        return String.join(",",
                staff.getHospitalID(),
                staff.getName(),
                staff.getPassword(),
                staff.getRole().toString(),
                staff.getGender(),
                String.valueOf(staff.getAge()));
    }

    private String formatadminData(Administrator staff) {
        return String.join(",",
                staff.getHospitalID(),
                staff.getName(),
                staff.getPassword(),
                staff.getRole().toString(),
                staff.getGender(),
                String.valueOf(staff.getAge()));
    }

    private String formatpharmData(Pharmacist staff) {
        return String.join(",",
                staff.getHospitalID(),
                staff.getName(),
                staff.getPassword(),
                staff.getRole().toString(),
                staff.getGender(),
                String.valueOf(staff.getAge()));
    }


    // Method to view appointment details
    // public void viewAppointmentDetails() {
    //     System.out.println("Viewing appointment details:");
    //     // Implement logic to retrieve and display appointment details
    // }

    // Metzhod to view medication inventory
    public void viewMedicationInventory() {
        System.out.println("Medication Inventory Details:");
        // Retrieve all medications from the inventory
        for (Medication medication : inventory.getAllMedications().values()) {
            System.out.println("Name: " + medication.getName());
            System.out.println("Stock Level: " + medication.getStockLevel());
            System.out.println("Low Stock Level Alert: " + medication.getLowStockLevelAlert());
            System.out.println("Price: $" + medication.getPrice());
            System.out.println("-----------------------------------");
        }
    }


    public void addMedication(String name, int stockLevel, int lowStockLevelAlert, double price) {
        Medication newMedication = new Medication(name, stockLevel, lowStockLevelAlert, price);

        if (!inventory.getAllMedications().containsKey(name)) {
            inventory.addMedication(newMedication);
            System.out.println("Added medication: " + name);
        } else {
            System.out.println("Medication " + name + " already exists in the inventory.");
        }
    }

    public void removeMedication(String name) {
        if (inventory.getAllMedications().containsKey(name)) {
            inventory.removeMedication(name);
            System.out.println("Removed medication: " + name);
        } else {
            System.out.println("Medication " + name + " does not exist in the inventory.");
        }
    }


    public void updateMedicationStockLevel(String name, int newStockLevel) {
        if (inventory.getAllMedications().containsKey(name)) {
            inventory.updateStockLevel(name, newStockLevel);
            System.out.println("Updated stock level of " + name + " to " + newStockLevel);
        } else {
            System.out.println("Medication " + name + " does not exist in the inventory.");
        }
    }


    public void updateLowStockAlertLevel(String name, int newLowStockLevelAlert) {
        if (inventory.getAllMedications().containsKey(name)) {
            inventory.updateLowStockLevelAlert(name, newLowStockLevelAlert);
            System.out.println("Updated low stock alert level of " + name + " to " + newLowStockLevelAlert);
        } else {
            System.out.println("Medication " + name + " does not exist in the inventory.");
        }
    }

    public void approveReplenishmentRequest(MedicationOrder order) {
        // Retrieve medication by name
        Medication medication = inventory.getAllMedications().get(order.getMedicationame());

        if (medication != null) {
            // Update the stock level by adding the requested quantity
            int newStockLevel = medication.getStockLevel() + order.getQuantity();
            inventory.updateStockLevel(order.getMedicationame(), newStockLevel);

            // Mark the order as approved
            order.setStatus("APPROVED");

            System.out.println("Replenishment request approved for " + order.getMedicationame() +
                    ". Quantity added: " + order.getQuantity() + ". New stock level: " + newStockLevel);
        } else {
            System.out.println("Medication " + order.getMedicationame() + " does not exist in the inventory.");
        }
    }

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

}

