package user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class User {
    
    // Instance variables
    protected String HospitalID;
    protected String password; 
    protected Role role;
    protected boolean isLoggedIn;

    // Constructor
    public User(String HospitalID, Role role) {
        this.HospitalID = HospitalID;
        this.password = "password";
        this.role = role;
        this.isLoggedIn = false;  // Default value
    }

    //Constructor to get from CSV File
    public User(String HospitalID, Role role, String password){
        this.HospitalID=HospitalID;
        this.password=password;
        this.role=role;
        this.isLoggedIn=false;
    }

    // Static counter map for each role
    private static final Map<Role, Integer> idCounters = new HashMap<>();

    static {
        // Initialize counters for each role
        idCounters.put(Role.PATIENT, 0);
        idCounters.put(Role.DOCTOR, 0);
        idCounters.put(Role.ADMINISTRATOR, 0);
        idCounters.put(Role.PHARMACIST, 0);
    }

    // Instance methods
    public abstract boolean login(String enteredPassword);
    public abstract void logout();
    public abstract boolean changePassword(String currentPassword, String newPassword);

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
        if (!isIDUniqueInFile("Data/Staff_List.csv", hospitalID)) {
            return false;  // ID already exists in Staff list
        }
        // Check Patient List
        if (!isIDUniqueInFile("Data/Patient_List.csv", hospitalID)) {
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

    public static String generateNewHospitalID(Role role) {
        String prefix;
        
        // Determine the prefix based on the role
        switch (role) {
            case PATIENT:
                prefix = "PA";
                break;
            case DOCTOR:
                prefix = "DC";
                break;
            case ADMINISTRATOR:
                prefix = "AD";
                break;
            case PHARMACIST:
                prefix = "PH";
                break;
            default:
                throw new IllegalArgumentException("Invalid role");
        }

        // Retrieve the current counter for the role
        int currentCount = idCounters.get(role);

        // Generate the initial ID
        String id;
        do {
            // Format the ID with the prefix and the incremented counter, padded to four digits
            id = prefix + String.format("%04d", currentCount);

            // Increment the counter for the role and update the map
            idCounters.put(role, currentCount + 1);
        } while (!isHospitalIDUnique(id)); // Ensure the generated ID is unique

        return id;
    }

    // Method to save a user to the appropriate CSV file (Staff or Patient)
    // public static void saveUser(String filePath, String hospitalID, String password, String role) {
    //     try (FileWriter writer = new FileWriter(filePath, true)) {
    //         writer.write(hospitalID + "," + password + "," + role + "\n");
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
}
