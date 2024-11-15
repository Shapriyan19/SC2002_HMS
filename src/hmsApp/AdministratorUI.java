package hmsApp;

import user.Administrator;
import user.Doctor;
import user.Pharmacist;
import user.Role;
import inventory.Medication;
import java.util.Scanner;

public class AdministratorUI {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Create a sample administrator
        Administrator admin = new Administrator(Role.ADMINISTRATOR, "Admin User", "Male", 40);

        System.out.println("Welcome, " + admin.getName() + " (Administrator)");
        boolean isRunning = true;

        while (isRunning) {
            // Display menu options
            System.out.println("\nAdministrator Menu:");
            System.out.println("1. View and Manage Hospital Staff");
            System.out.println("2. View Appointments Details");
            System.out.println("3. View and Manage Medication Inventory");
            System.out.println("4. Approve Replenishment Requests");
            System.out.println("5. Logout");

            // Get user choice
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    viewAndManageHospitalStaff(admin);
                    break;
                case 2:
                    viewAppointmentsDetails();
                    break;
                case 3:
                    viewAndManageMedicationInventory(admin);
                    break;
                case 4:
                    approveReplenishmentRequests(admin);
                    break;
                case 5:
                    logout();
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewAndManageHospitalStaff(Administrator admin) {
        System.out.println("\n--- View and Manage Hospital Staff ---");
        admin.viewHospitalStaff();

        System.out.println("\nOptions:");
        System.out.println("1. Add Staff");
        System.out.println("2. Update Staff");
        System.out.println("3. Remove Staff");
        System.out.println("4. Back to Main Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter Role (doctor/pharmacist/administrator): ");
                String role = scanner.nextLine().toLowerCase();
                System.out.println("Enter Name: ");
                String name = scanner.nextLine();
                System.out.println("Enter Gender: ");
                String gender = scanner.nextLine();
                System.out.println("Enter Age: ");
                int age = scanner.nextInt();
                scanner.nextLine();

                if (role.equals("doctor")) {
                    admin.manageHospitalDoctor("add", new Doctor(Role.DOCTOR, name, gender, age, "General"));
                } else if (role.equals("pharmacist")) {
                    admin.manageHospitalPharmacist("add", new Pharmacist(Role.PHARMACIST, name, gender, age));
                } else if (role.equals("administrator")) {
                    admin.manageHospitalAdministrator("add", new Administrator(Role.ADMINISTRATOR, name, gender, age));
                } else {
                    System.out.println("Invalid role.");
                }
                break;
            case 2:
                System.out.println("Feature not implemented for this demo.");
                break;
            case 3:
                System.out.println("Feature not implemented for this demo.");
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void viewAppointmentsDetails() {
        System.out.println("\n--- View Appointments Details ---");
        System.out.println("Feature not implemented for this demo.");
    }

    private static void viewAndManageMedicationInventory(Administrator admin) {
        System.out.println("\n--- View and Manage Medication Inventory ---");
        admin.viewMedicationInventory();

        System.out.println("\nOptions:");
        System.out.println("1. Add Medication");
        System.out.println("2. Update Stock Level");
        System.out.println("3. Update Low Stock Alert Level");
        System.out.println("4. Remove Medication");
        System.out.println("5. Back to Main Menu");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter Medication Name: ");
                String name = scanner.nextLine();
                System.out.println("Enter Stock Level: ");
                int stock = scanner.nextInt();
                System.out.println("Enter Low Stock Alert Level: ");
                int alertLevel = scanner.nextInt();
                System.out.println("Enter Price: ");
                double price = scanner.nextDouble();
                scanner.nextLine();
                admin.addMedication(name, stock, alertLevel, price);
                break;
            case 2:
                System.out.println("Enter Medication Name: ");
                String updateName = scanner.nextLine();
                System.out.println("Enter New Stock Level: ");
                int newStock = scanner.nextInt();
                scanner.nextLine();
                admin.updateMedicationStockLevel(updateName, newStock);
                break;
            case 3:
                System.out.println("Enter Medication Name: ");
                String alertName = scanner.nextLine();
                System.out.println("Enter New Low Stock Alert Level: ");
                int newAlert = scanner.nextInt();
                scanner.nextLine();
                admin.updateLowStockAlertLevel(alertName, newAlert);
                break;
            case 4:
                System.out.println("Enter Medication Name: ");
                String removeName = scanner.nextLine();
                admin.removeMedication(removeName);
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void approveReplenishmentRequests(Administrator admin) {
        System.out.println("\n--- Approve Replenishment Requests ---");
        System.out.println("Feature not implemented for this demo.");
    }

    private static void logout() {
        System.out.println("\nLogging out. Goodbye!");
    }
}
