package hmsApp;

import user.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import medical.MedicalRecord;

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

    private static void registerNewPatient(Scanner scanner) {
        // Prompt the user for patient details

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Date of Birth (dd-mm-yyyy): ");
        String dateOfBirth = scanner.nextLine();

        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter Blood Type: ");
        String bloodType = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        long phoneNumber = scanner.nextLong();

        scanner.nextLine();  // Consume the leftover newline character

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        // Create a new Patient object
        //Patient newPatient = new Patient(Role.PATIENT, name,dateOfBirth, gender, bloodType, phoneNumber, email, null);

        // Add patient to list and update CSV
        //Patient.getPatientsList().add(newPatient);
        //Patient.updateCSV();  // Updates the CSV file after registering a new patient
        
        // Output confirmation
        System.out.println("New patient registered successfully ");
    }
}
