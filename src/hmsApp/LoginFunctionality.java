package hmsApp;

import user.*;
import java.util.List;
import java.util.Scanner;

public class LoginFunctionality {

    public static void main(String[] args) {
        // Sample users for testing
        DataLoader.loadAllData();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the HMS Login System!");

        // Prompt user for login
        System.out.print("Enter your Staff/Patient ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Validate login
        User loggedInUser = authenticateUser(id, password);

        if (loggedInUser != null) {
            System.out.println("Login successful!");
            System.out.println("Welcome, " + loggedInUser.getRole() + " " + loggedInUser.getHospitalID() + "!");
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }

        // Debugging: Create a new pharmacist and add to CSV
        Pharmacist pharmacist1 = new Pharmacist(Role.PHARMACIST, "abc", "M", 29);
        System.out.println("Pharmacist object created and added to CSV.");
        
        scanner.close();
    }

    // Authenticate user based on ID and password
    private static User authenticateUser(String id, String password) {
        List<Administrator> adminList = Administrator.getAdministratorsList();
        List<Doctor> doctorList = Doctor.getDoctorsList();
        List<Patient> patientList = Patient.getPatientsList();
        List<Pharmacist> pharmacistList = Pharmacist.getPharmacistsList();

        // Check each list for the given credentials
        for (Administrator admin : adminList) {
            if (admin.getHospitalID().equals(id) && admin.login(password)) {
                return admin;
            }
        }

        for (Doctor doctor : doctorList) {
            if (doctor.getHospitalID().equals(id) && doctor.login(password)) {
                return doctor;
            }
        }

        for (Patient patient : patientList) {
            if (patient.getHospitalID().equals(id) && patient.login(password)) {
                return patient;
            }
        }

        for (Pharmacist pharmacist : pharmacistList) {
            if (pharmacist.getHospitalID().equals(id) && pharmacist.login(password)) {
                return pharmacist;
            }
        }

        return null; // No matching user found
    }
}
