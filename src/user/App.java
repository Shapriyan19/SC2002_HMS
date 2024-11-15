package user;
import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        // Creating patients with different roles
        System.out.println("Creating Patient 1:");
        Patient patient1 = new Patient(Role.PATIENT, "John Doe", 
                                      "1990-05-15", "Male", "O+", 1234567890L, "johndoe@example.com");
        
        System.out.println("Patient 1 details:");
        printPatientDetails(patient1);

        System.out.println("\nCreating Patient 2:");
        Patient patient2 = new Patient(Role.PATIENT, "Jane Smith", 
                                      "1985-08-25", "Female", "A+", 9876543210L, "janesmith@example.com");

        System.out.println("Patient 2 details:");
        printPatientDetails(patient2);

        // Test login and password change
        System.out.println("\nTesting login and password change for Patient 1:");
        patient1.login("password");
        patient1.changePassword("password", "newpassword456");

        // Print updated patient details after password change
        System.out.println("Patient 1 details after password change:");
        printPatientDetails(patient1);

        // Simulate logout
        patient1.logout();

        System.out.println("\nPatient 1 after logout:");
        printPatientDetails(patient1);

        // Creating Pharmacists with different roles
        System.out.println("Creating Pharmacist 1:");
        Pharmacist pharmacist1 = new Pharmacist(Role.PHARMACIST, "Dr. Adam Clark", "Male", 40);
        
        System.out.println("Pharmacist 1 details:");
        printPharmacistDetails(pharmacist1);

        System.out.println("\nCreating Pharmacist 2:");
        Pharmacist pharmacist2 = new Pharmacist(Role.PHARMACIST, "Dr. Emily Stone", "Female", 35);
        
        System.out.println("Pharmacist 2 details:");
        printPharmacistDetails(pharmacist2);

        // Test login and password change
        System.out.println("\nTesting login and password change for Pharmacist 1:");
        pharmacist1.login("password");
        pharmacist1.changePassword("password", "newpassword123");

        // Print updated pharmacist details after password change
        System.out.println("Pharmacist 1 details after password change:");
        printPharmacistDetails(pharmacist1);

        // Simulate logout
        pharmacist1.logout();

        System.out.println("\nPharmacist 1 after logout:");
        printPharmacistDetails(pharmacist1);

        // Create some administrators
        Administrator admin1 = new Administrator(Role.ADMINISTRATOR, "Alice", "Female", 35);
        Administrator admin2 = new Administrator(Role.ADMINISTRATOR, "Bob", "Male", 42);

        // Test login functionality
        admin1.setPassword("password123"); // Set password to test login
        System.out.println("Login Test for Alice (Correct Password): " + admin1.login("password123"));
        System.out.println("Login Test for Alice (Incorrect Password): " + admin1.login("wrongpassword"));

        // Test change password functionality
        System.out.println("Change Password for Alice: " + admin1.changePassword("password123", "newpassword123"));
        System.out.println("Login Test for Alice (New Password): " + admin1.login("newpassword123"));

        // Test logout functionality
        admin1.logout();

        // Test the updateCSV method which writes to the CSV file
        //admin1.updateCSV();

        // Add more administrators for testing
        Administrator admin3 = new Administrator(Role.ADMINISTRATOR, "Charlie", "Non-binary", 28);
        
        // Print out details for the administrators
        System.out.println("Administrator 1: " + admin1.getName() + ", " + admin1.getGender() + ", " + admin1.getAge());
        System.out.println("Administrator 2: " + admin2.getName() + ", " + admin2.getGender() + ", " + admin2.getAge());
        System.out.println("Administrator 3: " + admin3.getName() + ", " + admin3.getGender() + ", " + admin3.getAge());

        // Create Doctor instances
        Doctor doctor1 = new Doctor(Role.DOCTOR, "Dr. Alice", "Female", 35);
        Doctor doctor2 = new Doctor(Role.DOCTOR, "Dr. Bob", "Male", 42);
        
        // Test login functionality
        doctor1.setPassword("password123"); // Set password to test login
        System.out.println("Login Test for Dr. Alice (Correct Password): " + doctor1.login("password123"));
        System.out.println("Login Test for Dr. Alice (Incorrect Password): " + doctor1.login("wrongpassword"));

        // Test change password functionality
        System.out.println("Change Password for Dr. Alice: " + doctor1.changePassword("password123", "newpassword123"));
        System.out.println("Login Test for Dr. Alice (New Password): " + doctor1.login("newpassword123"));

        // Test logout functionality
        doctor1.logout();

        // Test the updateCSV method which writes to the CSV file
        doctor1.updateCSV();

        // Add more doctors for testing
        Doctor doctor3 = new Doctor(Role.DOCTOR, "Dr. Charlie", "Non-binary", 28);
        
        // Print out details for the doctors
        System.out.println("Doctor 1: " + doctor1.getName() + ", " + doctor1.getGender() + ", " + doctor1.getAge());
        System.out.println("Doctor 2: " + doctor2.getName() + ", " + doctor2.getGender() + ", " + doctor2.getAge());
        System.out.println("Doctor 3: " + doctor3.getName() + ", " + doctor3.getGender() + ", " + doctor3.getAge());
    }
    private static void printPatientDetails(Patient patient) {
        System.out.println("Hospital ID: " + patient.getHospitalID());
        System.out.println("Name: " + patient.getName());
        System.out.println("Date of Birth: " + patient.getDateOfBirth());
        System.out.println("Gender: " + patient.getGender());
        System.out.println("Blood Type: " + patient.getBloodType());
        System.out.println("Phone Number: " + patient.getPhoneNumber());
        System.out.println("Email: " + patient.getEmail());
    }

    private static void printPharmacistDetails(Pharmacist pharmacist) {
        System.out.println("Hospital ID: " + pharmacist.getHospitalID());
        System.out.println("Name: " + pharmacist.getName());
        System.out.println("Gender: " + pharmacist.getGender());
        System.out.println("Age: " + pharmacist.getAge());
    }
}
