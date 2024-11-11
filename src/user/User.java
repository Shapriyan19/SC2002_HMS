package user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public abstract class User {
    
    // Instance variables
    protected String HospitalID;
    protected String password; 
    protected Role role;
    protected boolean isLoggedIn;

    // Constructor
    public User(String HospitalID, String password, Role role) {
        this.HospitalID = HospitalID;
        this.password = password;
        this.role = role;
        this.isLoggedIn = false;  // Default value
    }

    // Instance methods
    public abstract boolean login(String enteredPassword);
    public abstract void logout();
    public abstract boolean changePassword(String currentPassword, String newPassword);
    public abstract void updateCSV();

    // Getters & Setters
    public String getHospitalID() {
        return HospitalID;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }

    // Static method to check if the HospitalID is unique across both Staff and Patient lists
    public static boolean isHospitalIDUnique(String hospitalID) {
        // Check Staff List
        if (!isIDUniqueInFile("Staff_List.csv", hospitalID)) {
            return false;  // ID already exists in Staff list
        }
        // Check Patient List
        if (!isIDUniqueInFile("Patient_List.csv", hospitalID)) {
            return false;  // ID already exists in Patient list
        }
        return true;  // ID is unique across both lists
    }

    // Helper method to check uniqueness in a specific CSV file
    private static boolean isIDUniqueInFile(String filePath, String hospitalID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length > 0 && columns[0].equals(hospitalID)) {
                    return false;  // ID found in the file
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;  // ID not found
    }

    // Static method to generate a new unique HospitalID (string-based)
    public static String generateNewHospitalID() {
        return "HOSP" + System.currentTimeMillis();  // Example: Unique ID based on current time in ms
    }

    // Method to save a user to the appropriate CSV file (Staff or Patient)
    public static void saveUser(String filePath, String hospitalID, String password, String role) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(hospitalID + "," + password + "," + role + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
