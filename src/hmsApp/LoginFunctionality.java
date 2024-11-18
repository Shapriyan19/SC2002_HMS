package hmsApp;

import user.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import user.Patient;

public class LoginFunctionality {

    public static void main(String[] args) {

        // Load sample users or data for testing
        DataLoader.loadAllData();

        Scanner scanner = new Scanner(System.in);
        boolean continueLogin = true; // Control variable for the loop

        System.out.println("Welcome to the HMS Login System!");

        while (continueLogin) {
            // Main menu
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Login");
            System.out.println("2. Register as a new Patient");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Prompt user for login credentials
                    System.out.print("Enter your Staff/Patient ID: ");
                    String id = scanner.nextLine();

                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();

                    // Validate login
                    User loggedInUser = authenticateUser(id, password);

                    if (loggedInUser != null) {
                        System.out.println("Login successful!");
                        System.out.println("Welcome, " + loggedInUser.getRole() + " " + loggedInUser.getHospitalID() + "!");
                        navigateToRoleUI(loggedInUser);
                    } else {
                        System.out.println("Invalid credentials. Please try again.");
                    }
                    break;

                case "2":
                    registerNewPatient(scanner);
                    break;

                case "3":
                    continueLogin = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    // Authenticate user based on ID and password
    private static User authenticateUser(String id, String password) {
        List<Administrator> adminList = Administrator.getAdministratorsList();
        List<Doctor> doctorList = Doctor.getDoctorsList();
        List<Patient> patientList = Patient.getPatientsList();
        List<Pharmacist> pharmacistList = Pharmacist.getPharmacistsList();

        if (adminList != null) {
            for (Administrator admin : adminList) {
                if (admin.getHospitalID().equals(id) && admin.login(password)) {
                    return admin;
                }
            }
        }

        if (doctorList != null) {
            for (Doctor doctor : doctorList) {
                if (doctor.getHospitalID().equals(id) && doctor.login(password)) {
                    return doctor;
                }
            }
        }

        if (patientList != null) {
            for (Patient patient : patientList) {
                if (patient.getHospitalID().equals(id) && patient.login(password)) {
                    return patient;
                }
            }
        }

        if (pharmacistList != null) {
            for (Pharmacist pharmacist : pharmacistList) {
                if (pharmacist.getHospitalID().equals(id) && pharmacist.login(password)) {
                    return pharmacist;
                }
            }
        }

        return null; // No matching user found
    }

    // Navigate to the corresponding UI based on the user's role
    private static void navigateToRoleUI(User user) {
        Role role = user.getRole();

        switch (role) {
            case ADMINISTRATOR:
                new AdministratorUI((Administrator) user).showMenu();
                break;
            case DOCTOR:
                new DoctorUI((Doctor) user).showMenu();
                break;
            case PATIENT:
                new PatientUI((Patient) user).showMenu();
                break;
            case PHARMACIST:
                new PharmacistUI((Pharmacist) user).showMenu();
                break;
            default:
                System.out.println("Role not recognized. Cannot proceed to UI.");
                break;
        }
    }

    // Method for registering a new patient
    private static void registerNewPatient(Scanner scanner) {
        // System.out.println("\n--- New Patient Registration ---");

        // System.out.print("Enter your full name: ");
        // String name = scanner.nextLine();

        // System.out.print("Enter your age: ");
        // int age = Integer.parseInt(scanner.nextLine());

        // System.out.print("Enter your gender (M/F/Other): ");
        // String gender = scanner.nextLine();

        // System.out.print("Enter your contact number: ");
        // String contactNumber = scanner.nextLine();

        // System.out.print("Set a password for your account: ");
        // String password = scanner.nextLine();

        // // Generate a unique patient ID
        // String patientID = "P" + (Patient.getPatientsList().size() + 1);

        // // Create a new patient
        // Patient newPatient = new Patient(Role.PATIENT,name, dateOfBirth, gender , bloodType, contactNumber, email, NULL);

        // // Add the patient to the list
        // List<Patient> patientList = Patient.getPatientsList();
        // if (patientList == null) {
        //     patientList = new ArrayList<>();
        // }
        // patientList.add(newPatient);

        // // Confirm registration
        // System.out.println("\nRegistration successful!");
        // System.out.println("Your Patient ID is: " + patientID);
        // System.out.println("You can now log in using your Patient ID and password.");
     }
}
