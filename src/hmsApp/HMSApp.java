package hmsApp;

import user.*;
import user.DataLoader;
import java.util.List;
import java.util.Scanner;

public class HMSApp {

    // Main method to run the application
    public static void main(String[] args) {
        HMSApp app = new HMSApp();
        app.run();
    }

    // Run the hospital management system application
    public void run() {
        DataLoader.loadAllData();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Welcome to the Hospital Management System (HMS) Application");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (option) {
                case 1:
                    System.out.print("Enter Hospital ID: ");
                    String hospitalID = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    User user = login(hospitalID, password);
                    if (user != null) {
                        System.out.println("Login successful!");
                        userMenu(user); // Redirect to a user-specific menu after login
                    } else {
                        System.out.println("Login failed. Please check your credentials and try again.");
                    }
                    break;
                case 2:
                    System.out.println("Exiting the application.");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
        scanner.close();
    }

    // Simulated login function
    private User login(String hospitalID, String password) {
        User foundUser = findUser(hospitalID);
        if (foundUser != null && foundUser.getPassword().equals(password)) {
            return foundUser;
        }
        return null;
    }

    // Find user by hospital ID from all user lists
    private User findUser(String hospitalID) {
        // Assuming ID prefixes indicate user type: AD for Administrators, DC for Doctors, PT for Patients, PH for Pharmacists
        if (hospitalID.startsWith("AD")) {
            return findInList(Administrator.getAdministratorsList(), hospitalID);
        } else if (hospitalID.startsWith("DC")) {
            return findInList(Doctor.getDoctorsList(), hospitalID);
        } else if (hospitalID.startsWith("PT")) {
            return findInList(Patient.getPatientsList(), hospitalID);
        } else if (hospitalID.startsWith("PH")) {
            return findInList(Pharmacist.getPharmacistsList(), hospitalID);
        }
        return null;
    }

    // Generic method to find a user in a given list
    private <T extends User> T findInList(List<T> userList, String hospitalID) {
        for (T user : userList) {
            if (user.getHospitalID().equals(hospitalID)) {
                return user;
            }
        }
        return null;
    }

    // User-specific menu after successful login
    private void userMenu(User user) {
        System.out.println("Logged in as: " + user.getRole());
        // Here you could have more complex logic based on user roles
        switch (user.getRole()) {
            case DOCTOR:
                System.out.println("Accessing doctor's dashboard...");
                // Implement doctor-specific functionalities
                break;
            case PATIENT:
                System.out.println("Accessing patient dashboard...");
                // Implement patient-specific functionalities
                break;
            case ADMINISTRATOR:
                System.out.println("Accessing administrator dashboard...");
                // Implement administrator-specific functionalities
                break;
            case PHARMACIST:
                System.out.println("Accessing pharmacist dashboard...");
                // Implement pharmacist-specific functionalities
                break;
            default:
                System.out.println("Role not recognized.");
                break;
        }
    }
}