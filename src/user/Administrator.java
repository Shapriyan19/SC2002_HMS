ppackage user;

import java.util.List;

public class Administrator extends User {

    // Instance variables
    private String adminID;
    private String name;

    // Constructor
    public Administrator(String userID, String password, Role role, String adminID, String name) {
        super(userID, password, role);
        this.adminID = adminID;
        this.name = name;
    }

    // Method to manage hospital staff
    public void manageHospitalStaff(String staffID, String action) {
        System.out.println("Managing hospital staff with ID: " + staffID);
        System.out.println("Action: " + action);
        // Implement logic to add, update, or remove hospital staff based on the action
    }

    // Method to view hospital staff details
    public void viewHospitalStaff() {
        System.out.println("Viewing all hospital staff details:");
        // Implement logic to retrieve and display hospital staff details
    }

    // Method to view appointment details
    public void viewAppointmentDetails() {
        System.out.println("Viewing appointment details:");
        // Implement logic to retrieve and display appointment details
    }

    // Method to view medication inventory
    public void viewMedicationInventory() {
        System.out.println("Viewing medication inventory:");
        // Implement logic to retrieve and display medication inventory
    }

    // Method to manage medication inventory
    public void manageMedicationInventory(String medicationID, String action, int quantity) {
        System.out.println("Managing medication inventory for Medication ID: " + medicationID);
        System.out.println("Action: " + action + ", Quantity: " + quantity);
        // Implement logic to add, update, or remove medication from the inventory based on the action
    }

    // Method to approve replenishment requests
    public void approveReplenishmentRequest(String requestID) {
        System.out.println("Approving replenishment request with ID: " + requestID);
        // Implement logic to approve a medication replenishment request
    }

    // Login method implementation
    @Override
    public boolean login(String enteredPassword) {
        if (this.password.equals(enteredPassword)) {
            this.isLoggedIn = true;
            System.out.println("Login successful for user: " + userID);
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
        System.out.println("Logging out patient: " + name);
    }

    // Getters
    public String getAdminID() {
        return adminID;
    }

    public String getName() {
        return name;
    }
}

