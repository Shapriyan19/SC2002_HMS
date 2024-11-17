package hmsApp;

import user.Administrator;
import user.Doctor;
import user.Patient;
import user.Pharmacist;
import user.Role;
import inventory.Medication;
import inventory.ReplenishmentRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdministratorUI {

    private Administrator admin;
    private Scanner scanner;

    public AdministratorUI(Administrator admin) {
        this.admin = admin;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n--- Administrator Menu ---");
            System.out.println("1. View and Manage Hospital Staff");
            System.out.println("2. View Appointments Details");
            System.out.println("3. View and Manage Medication Inventory");
            System.out.println("4. Approve Replenishment Requests");
            System.out.println("5. Change Password");
            System.out.println("6. Logout");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    viewAndManageHospitalStaff();
                    break;
                case 2:
                    viewAppointmentsDetails();
                    break;
                case 3:
                    viewAndManageMedicationInventory();
                    break;
                case 4:
                    approveReplenishmentRequests();
                    break;
                case 5:
                    changePassword();
                    break;
                case 6:
                    logout();
                    return; // exit the UI
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewAndManageHospitalStaff() {
        System.out.println("\n--- View and Manage Hospital Staff ---");
        admin.viewHospitalStaff();
        System.out.println("\nOptions:");
        System.out.println("1. Add Staff");
        System.out.println("2. Update Staff");
        System.out.println("3. Remove Staff");
        System.out.println("4. Back to Main Menu");
        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addStaff();
                break;
            case 2:
                updateStaff();
                break;
            case 3:
                removeStaff();
                break;
            case 4:
                return; // Back to main menu
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void addStaff() {
        System.out.print("Enter Role (doctor/pharmacist/administrator): ");
        String role = scanner.nextLine().toLowerCase();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
    
        if ("doctor".equals(role)) {
            System.out.println("Creating Doctor...");
            System.out.println("Adding Doctor...");
        } else if ("pharmacist".equals(role)) {
            Pharmacist newPharmacist = new Pharmacist(Role.PHARMACIST, name, gender, age);
            System.out.println("Adding Pharmacist...");
            admin.manageHospitalPharmacist("add", newPharmacist);
        } else if ("administrator".equals(role)) {
            Administrator newAdministrator = new Administrator(Role.ADMINISTRATOR, name, gender, age);
            System.out.println("Adding Administrator...");
            admin.manageHospitalAdministrator("add", newAdministrator);
        } else {
            System.out.println("Invalid role entered. Please try again.");
        }
    }

    private void updateStaff() {
        System.out.println("\n--- Update Staff ---");
        System.out.print("Enter Role of the Staff to Update (doctor/pharmacist/administrator): ");
        String role = scanner.nextLine().toLowerCase();
        System.out.print("Enter Staff ID: ");
        String staffID = scanner.nextLine();

        switch (role) {
            case "doctor":
                Doctor doctor = admin.findDoctorById(staffID);
                if (doctor != null) {
                    System.out.print("Enter New Name (leave blank to keep unchanged): ");
                    String newName = scanner.nextLine();
                    if (!newName.isBlank()) doctor.setName(newName);

                    System.out.print("Enter New Gender (leave blank to keep unchanged): ");
                    String newGender = scanner.nextLine();
                    if (!newGender.isBlank()) doctor.setGender(newGender);

                    System.out.print("Enter New Age (enter -1 to keep unchanged): ");
                    int newAge = scanner.nextInt();
                    scanner.nextLine();
                    if (newAge != -1) doctor.setAge(newAge);

                    admin.manageHospitalDoctor("update", doctor);
                    System.out.println("Doctor updated successfully.");
                } else {
                    System.out.println("Doctor with ID " + staffID + " not found.");
                }
                break;

            case "pharmacist":
                Pharmacist pharmacist = admin.findPharmacistById(staffID);
                if (pharmacist != null) {
                    System.out.print("Enter New Name (leave blank to keep unchanged): ");
                    String newName = scanner.nextLine();
                    if (!newName.isBlank()) pharmacist.setName(newName);

                    System.out.print("Enter New Gender (leave blank to keep unchanged): ");
                    String newGender = scanner.nextLine();
                    if (!newGender.isBlank()) pharmacist.setGender(newGender);

                    System.out.print("Enter New Age (enter -1 to keep unchanged): ");
                    int newAge = scanner.nextInt();
                    scanner.nextLine();
                    if (newAge != -1) pharmacist.setAge(newAge);

                    admin.manageHospitalPharmacist("update", pharmacist);
                    System.out.println("Pharmacist updated successfully.");
                } else {
                    System.out.println("Pharmacist with ID " + staffID + " not found.");
                }
                break;

            case "administrator":
                Administrator administrator = admin.findAdministratorById(staffID);
                if (administrator != null) {
                    System.out.print("Enter New Name (leave blank to keep unchanged): ");
                    String newName = scanner.nextLine();
                    if (!newName.isBlank()) administrator.setName(newName);

                    System.out.print("Enter New Gender (leave blank to keep unchanged): ");
                    String newGender = scanner.nextLine();
                    if (!newGender.isBlank()) administrator.setGender(newGender);

                    System.out.print("Enter New Age (enter -1 to keep unchanged): ");
                    int newAge = scanner.nextInt();
                    scanner.nextLine();
                    if (newAge != -1) administrator.setAge(newAge);

                    admin.manageHospitalAdministrator("update", administrator);
                    System.out.println("Administrator updated successfully.");
                } else {
                    System.out.println("Administrator with ID " + staffID + " not found.");
                }
                break;

            default:
                System.out.println("Invalid role entered.");
                break;
        }
    }

    private void removeStaff() {
        System.out.println("\n--- Remove Staff ---");
        System.out.print("Enter Role of the Staff to Remove (doctor/pharmacist/administrator): ");
        String role = scanner.nextLine().toLowerCase();
        System.out.print("Enter Staff ID: ");
        String staffID = scanner.nextLine();

        switch (role) {
            case "doctor":
                Doctor doctor = admin.findDoctorById(staffID);
                if (doctor != null) {
                    admin.manageHospitalDoctor("remove", doctor);
                    System.out.println("Doctor removed successfully.");
                } else {
                    System.out.println("Doctor with ID " + staffID + " not found.");
                }
                break;

            case "pharmacist":
                Pharmacist pharmacist = admin.findPharmacistById(staffID);
                if (pharmacist != null) {
                    admin.manageHospitalPharmacist("remove", pharmacist);
                    System.out.println("Pharmacist removed successfully.");
                } else {
                    System.out.println("Pharmacist with ID " + staffID + " not found.");
                }
                break;

            case "administrator":
                Administrator administrator = admin.findAdministratorById(staffID);
                if (administrator != null) {
                    admin.manageHospitalAdministrator("remove", administrator);
                    System.out.println("Administrator removed successfully.");
                } else {
                    System.out.println("Administrator with ID " + staffID + " not found.");
                }
                break;

            default:
                System.out.println("Invalid role entered.");
                break;
        }
    }

    private void viewAppointmentsDetails() {
        System.out.println("\n--- View Appointments Details ---");
        admin.displayAppointments(); // Call the Administrator's method to display all appointments
    }

    private void viewAndManageMedicationInventory() {
        System.out.println("\n--- View and Manage Medication Inventory ---");
        admin.viewMedicationInventory();

        System.out.println("\nOptions:");
        System.out.println("1. Add Medication");
        System.out.println("2. Update Stock Level");
        System.out.println("3. Update Low Stock Alert Level");
        System.out.println("4. Remove Medication");
        System.out.println("5. Back to Main Menu");
        System.out.print("Select an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addMedication();
                break;
            case 2:
                updateStockLevel();
                break;
            case 3:
                updateLowStockAlertLevel();
                break;
            case 4:
                removeMedication();
                break;
            case 5:
                return; // Back to main menu
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void approveReplenishmentRequests() {
        System.out.println("\n--- Approve Replenishment Requests ---");
    
        List<ReplenishmentRequest> requests = ReplenishmentRequest.getPendingRequests();
    
        if (requests.isEmpty()) {
            System.out.println("No replenishment requests to approve.");
            return;
        }
    
        for (int i = 0; i < requests.size(); i++) {
            ReplenishmentRequest request = requests.get(i);
            System.out.println((i + 1) + ". Medication Name: " + request.getMedicationName());
            System.out.println("   Requested Quantity: " + request.getRequestedQuantity());
            System.out.println("   Requested by Pharmacist: " + request.getPharmacistName());
            System.out.println("-----------------------------------");
        }
    
        System.out.print("Select a request to approve (1-" + requests.size() + ", or 0 to cancel): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
    
        if (choice == 0) {
            System.out.println("Operation canceled.");
            return;
        }
    
        if (choice < 1 || choice > requests.size()) {
            System.out.println("Invalid choice. Operation canceled.");
            return;
        }
    
        ReplenishmentRequest selectedRequest = requests.get(choice - 1);
    
        System.out.println("\nYou selected:");
        System.out.println("Medication Name: " + selectedRequest.getMedicationName());
        System.out.println("Requested Quantity: " + selectedRequest.getRequestedQuantity());
        System.out.println("Requested by Pharmacist: " + selectedRequest.getPharmacistName());
        System.out.print("Do you want to approve this request? (yes/no): ");
        String decision = scanner.nextLine().trim().toLowerCase();
    
        if ("yes".equals(decision)) {
            admin.approveReplenishmentRequest(selectedRequest); // Administrator approves
            ReplenishmentRequest.approveRequest(selectedRequest); // Remove the approved request
            System.out.println("Replenishment request approved.");
        } else {
            System.out.println("Replenishment request was not approved.");
        }
    }

    private void addMedication() {
        System.out.print("Enter Medication Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Stock Level: ");
        int stock = scanner.nextInt();
        System.out.print("Enter Low Stock Alert Level: ");
        int alertLevel = scanner.nextInt();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        admin.addMedication(name, stock, alertLevel, price);
    }

    private void updateStockLevel() {
        System.out.print("Enter Medication Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Stock Level: ");
        int stock = scanner.nextInt();
        scanner.nextLine();
        admin.updateMedicationStockLevel(name, stock);
    }

    private void updateLowStockAlertLevel() {
        System.out.print("Enter Medication Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Low Stock Alert Level: ");
        int alertLevel = scanner.nextInt();
        scanner.nextLine();
        admin.updateLowStockAlertLevel(name, alertLevel);
    }

    private void removeMedication() {
        System.out.print("Enter Medication Name: ");
        String name = scanner.nextLine();
        admin.removeMedication(name);
    }

    private void changePassword() {
        System.out.println("\n--- Change Password ---");
        System.out.print("Enter Current Password: ");
        String currentPassword = scanner.nextLine();
        System.out.print("Enter New Password: ");
        String newPassword = scanner.nextLine();

        boolean result = admin.changePassword(currentPassword, newPassword);
        if (result) {
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Password change failed. Please try again.");
        }
    }

    private void logout() {
        System.out.println("\nLogging out. Goodbye, " + admin.getName() + "!");
    }

    public static void main(String[] args) {
        Administrator admin = new Administrator(Role.ADMINISTRATOR, "Admin User", "Male", 40);
        AdministratorUI ui = new AdministratorUI(admin);
        ui.showMenu();
    }
}
