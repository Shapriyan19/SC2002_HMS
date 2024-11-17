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
            System.out.println("5. Logout");
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
            // Doctor newDoctor = new Doctor(Role.DOCTOR, name, gender, new ArrayList<>(), age, 1);
            System.out.println("Adding Doctor...");
            // admin.manageHospitalDoctor("add", newDoctor);
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
    
    
    // private void approveReplenishmentRequests() {
    // System.out.println("\n--- Approve Replenishment Requests ---");

    // // Fetch the pending requests
    // List<ReplenishmentRequest> requests = ReplenishmentRequest.getPendingRequests();

    // if (requests.isEmpty()) {
    //     System.out.println("No replenishment requests to approve.");
    //     return;
    // }

    // // Display all pending requests
    // for (int i = 0; i < requests.size(); i++) {
    //     ReplenishmentRequest request = requests.get(i);
    //     System.out.println((i + 1) + ". Medication Name: " + request.getMedicationName());
    //     System.out.println("   Requested Quantity: " + request.getRequestedQuantity());
    //     System.out.println("   Requested by Pharmacist: " + request.getPharmacistName());
    //     System.out.println("-----------------------------------");
    // }

    // // Prompt the administrator to select a request
    // System.out.print("Select a request to approve (1-" + requests.size() + ", or 0 to cancel): ");
    // int choice = scanner.nextInt();
    // scanner.nextLine(); // Consume the newline

    // if (choice == 0) {
    //     System.out.println("Operation canceled.");
    //     return;
    // }

    // if (choice < 1 || choice > requests.size()) {
    //     System.out.println("Invalid choice. Operation canceled.");
    //     return;
    // }

    // // Get the selected request
    // ReplenishmentRequest selectedRequest = requests.get(choice - 1);

    // // Display the selected request for confirmation
    // System.out.println("\nYou selected:");
    // System.out.println("Medication Name: " + selectedRequest.getMedicationName());
    // System.out.println("Requested Quantity: " + selectedRequest.getRequestedQuantity());
    // System.out.println("Requested by Pharmacist: " + selectedRequest.getPharmacistName());
    // System.out.print("Do you want to approve this request? (yes/no): ");
    // String decision = scanner.nextLine().trim().toLowerCase();

    // if ("yes".equals(decision)) {
    //     // Call the administrator's method to approve the request
    //     admin.approveReplenishmentRequest(selectedRequest);
    //     System.out.println("Replenishment request approved.");
    // } else {
    //     System.out.println("Replenishment request was not approved.");
    // }
//}


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

    // private void approveReplenishmentRequests(List<ReplenishmentRequest> requests) {
    //     System.out.println("\n--- Approve Replenishment Requests ---");
    
    //     // Check if there are pending requests
    //     if (requests.isEmpty()) {
    //         System.out.println("No replenishment requests to approve.");
    //         return;
    //     }
    
    //     // Display all pending requests
    //     for (int i = 0; i < requests.size(); i++) {
    //         ReplenishmentRequest request = requests.get(i);
    //         System.out.println((i + 1) + ". Medication Name: " + request.getMedicationName());
    //         System.out.println("   Requested Quantity: " + request.getRequestedQuantity());
    //         System.out.println("   Requested by Pharmacist: " + request.getPharmacistName());
    //         System.out.println("-----------------------------------");
    //     }
    
    //     // Prompt the administrator to select a request
    //     System.out.print("Select a request to approve (1-" + requests.size() + ", or 0 to cancel): ");
    //     int choice = scanner.nextInt();
    //     scanner.nextLine(); // Consume the newline
    
    //     if (choice == 0) {
    //         System.out.println("Operation canceled.");
    //         return;
    //     }
    
    //     if (choice < 1 || choice > requests.size()) {
    //         System.out.println("Invalid choice. Operation canceled.");
    //         return;
    //     }
    
    //     // Get the selected request
    //     ReplenishmentRequest selectedRequest = requests.get(choice - 1);
    
    //     // Display the selected request for confirmation
    //     System.out.println("\nYou selected:");
    //     System.out.println("Medication Name: " + selectedRequest.getMedicationName());
    //     System.out.println("Requested Quantity: " + selectedRequest.getRequestedQuantity());
    //     System.out.println("Requested by Pharmacist: " + selectedRequest.getPharmacistName());
    //     System.out.print("Do you want to approve this request? (yes/no): ");
    //     String decision = scanner.nextLine().trim().toLowerCase();
    
    //     if ("yes".equals(decision)) {
    //         // Call the Administrator's method to approve the request
    //         admin.approveReplenishmentRequest(selectedRequest);
    //         requests.remove(selectedRequest); // Remove the approved request from the list
    //         System.out.println("Replenishment request approved.");
    //     } else {
    //         System.out.println("Replenishment request was not approved.");
    //     }
    // }
    // private void approveReplenishmentRequests() {
    //     System.out.println("\n--- Approve Replenishment Requests ---");
    
    //     List<ReplenishmentRequest> requests = Administrator.getPendingRequests(); // Fetch pending requests
    
    //     if (requests.isEmpty()) {
    //         System.out.println("No replenishment requests to approve.");
    //         return;
    //     }
    
    //     // Display all pending requests
    //     for (int i = 0; i < requests.size(); i++) {
    //         ReplenishmentRequest request = requests.get(i);
    //         System.out.println((i + 1) + ". Medication Name: " + request.getMedicationName());
    //         System.out.println("   Requested Quantity: " + request.getRequestedQuantity());
    //         System.out.println("   Requested by Pharmacist: " + request.getPharmacistName());
    //         System.out.println("-----------------------------------");
    //     }
    
    //     // Prompt the administrator to select a request
    //     System.out.print("Select a request to approve (1-" + requests.size() + ", or 0 to cancel): ");
    //     int choice = scanner.nextInt();
    //     scanner.nextLine(); // Consume the newline
    
    //     if (choice == 0) {
    //         System.out.println("Operation canceled.");
    //         return;
    //     }
    
    //     if (choice < 1 || choice > requests.size()) {
    //         System.out.println("Invalid choice. Operation canceled.");
    //         return;
    //     }
    
    //     // Get the selected request
    //     ReplenishmentRequest selectedRequest = requests.get(choice - 1);
    
    //     // Display the selected request for confirmation
    //     System.out.println("\nYou selected:");
    //     System.out.println("Medication Name: " + selectedRequest.getMedicationName());
    //     System.out.println("Requested Quantity: " + selectedRequest.getRequestedQuantity());
    //     System.out.println("Requested by Pharmacist: " + selectedRequest.getPharmacistName());
    //     System.out.print("Do you want to approve this request? (yes/no): ");
    //     String decision = scanner.nextLine().trim().toLowerCase();
    
    //     if ("yes".equals(decision)) {
    //         admin.approveReplenishmentRequest(selectedRequest); // Approve the request
    //         System.out.println("Replenishment request approved.");
    //     } else {
    //         System.out.println("Replenishment request was not approved.");
    //     }
    // }
    
    

    // private void approveReplenishmentRequests() {
    //     System.out.println("\n--- Approve Replenishment Requests ---");
    //     System.out.println("Feature not implemented for this demo.");
    // }

    // private void approveReplenishmentRequests() {
    //     System.out.println("\n--- Approve Replenishment Requests ---");
    
    //     System.out.print("Enter Medication Name: ");
    //     String medicationName = scanner.nextLine();
    
    //     System.out.print("Enter Requested Quantity: ");
    //     int requestedQuantity = scanner.nextInt();
    //     scanner.nextLine(); // Consume the newline
    
    //     admin.approveReplenishmentRequest(medicationName, requestedQuantity);
    // }

    // private void approveReplenishmentRequests(List<ReplenishmentRequest> requests) {
    //     System.out.println("\n--- Approve Replenishment Requests ---");
    
    //     if (requests.isEmpty()) {
    //         System.out.println("No replenishment requests to approve.");
    //         return;
    //     }
    
    //     for (int i = 0; i < requests.size(); i++) {
    //         ReplenishmentRequest request = requests.get(i);
    //         System.out.println((i + 1) + ". Medication Name: " + request.getMedicationName());
    //         System.out.println("   Requested Quantity: " + request.getRequestedQuantity());
    //         System.out.println("   Requested by Pharmacist: " + request.getPharmacistName());
    //         System.out.println("-----------------------------------");
    //     }
    
    //     System.out.print("Select a request to approve (1-" + requests.size() + ", or 0 to cancel): ");
    //     int choice = scanner.nextInt();
    //     scanner.nextLine(); // Consume the newline
    
    //     if (choice == 0) {
    //         System.out.println("Operation canceled.");
    //         return;
    //     }
    
    //     if (choice < 1 || choice > requests.size()) {
    //         System.out.println("Invalid choice. Operation canceled.");
    //         return;
    //     }
    
    //     ReplenishmentRequest selectedRequest = requests.get(choice - 1);
    
    //     System.out.println("\nYou selected:");
    //     System.out.println("Medication Name: " + selectedRequest.getMedicationName());
    //     System.out.println("Requested Quantity: " + selectedRequest.getRequestedQuantity());
    //     System.out.println("Requested by Pharmacist: " + selectedRequest.getPharmacistName());
    //     System.out.print("Do you want to approve this request? (yes/no): ");
    //     String decision = scanner.nextLine().trim().toLowerCase();
    
    //     if ("yes".equals(decision)) {
    //         admin.approveReplenishmentRequest(selectedRequest);
    //         requests.remove(selectedRequest); // Remove the approved request
    //     } else {
    //         System.out.println("Replenishment request was not approved.");
    //     }
    // }

    private void logout() {
        System.out.println("\nLogging out. Goodbye, " + admin.getName() + "!");
    }

    public static void main(String[] args) {
        Administrator admin = new Administrator(Role.ADMINISTRATOR, "Admin User", "Male", 40);
        AdministratorUI ui = new AdministratorUI(admin);
        ui.showMenu();
    }
}






















































//package hmsApp;

// import user.Administrator;
// import user.Doctor;
// import user.Pharmacist;
// import user.Role;
// import inventory.Medication;
// import java.util.Scanner;

// public class AdministratorUI {
//     private static Scanner scanner = new Scanner(System.in);

//     public static void main(String[] args) {
//         // Create a sample administrator
//         Administrator admin = new Administrator(Role.ADMINISTRATOR, "Admin User", "Male", 40);

//         System.out.println("Welcome, " + admin.getName() + " (Administrator)");
//         boolean isRunning = true;

//         while (isRunning) {
//             // Display menu options
//             System.out.println("\nAdministrator Menu:");
//             System.out.println("1. View and Manage Hospital Staff");
//             System.out.println("2. View Appointments Details");
//             System.out.println("3. View and Manage Medication Inventory");
//             System.out.println("4. Approve Replenishment Requests");
//             System.out.println("5. Logout");

//             // Get user choice
//             System.out.print("Enter your choice: ");
//             int choice = scanner.nextInt();
//             scanner.nextLine(); // Consume the newline character

//             switch (choice) {
//                 case 1:
//                     viewAndManageHospitalStaff(admin);
//                     break;
//                 case 2:
//                     viewAppointmentsDetails();
//                     break;
//                 case 3:
//                     viewAndManageMedicationInventory(admin);
//                     break;
//                 case 4:
//                     approveReplenishmentRequests(admin);
//                     break;
//                 case 5:
//                     logout();
//                     isRunning = false;
//                     break;
//                 default:
//                     System.out.println("Invalid choice. Please try again.");
//             }
//         }
//     }

//     private static void viewAndManageHospitalStaff(Administrator admin) {
//         System.out.println("\n--- View and Manage Hospital Staff ---");
//         admin.viewHospitalStaff();

//         System.out.println("\nOptions:");
//         System.out.println("1. Add Staff");
//         System.out.println("2. Update Staff");
//         System.out.println("3. Remove Staff");
//         System.out.println("4. Back to Main Menu");
//         System.out.print("Enter your choice: ");
//         int choice = scanner.nextInt();
//         scanner.nextLine();

//         switch (choice) {
//             case 1:
//                 System.out.println("Enter Role (doctor/pharmacist/administrator): ");
//                 String role = scanner.nextLine().toLowerCase();
//                 System.out.println("Enter Name: ");
//                 String name = scanner.nextLine();
//                 System.out.println("Enter Gender: ");
//                 String gender = scanner.nextLine();
//                 System.out.println("Enter Age: ");
//                 int age = scanner.nextInt();
//                 scanner.nextLine();

//                 if (role.equals("doctor")) {
//                     admin.manageHospitalDoctor("add", new Doctor(Role.DOCTOR, name, gender, age, "General"));
//                 } else if (role.equals("pharmacist")) {
//                     admin.manageHospitalPharmacist("add", new Pharmacist(Role.PHARMACIST, name, gender, age));
//                 } else if (role.equals("administrator")) {
//                     admin.manageHospitalAdministrator("add", new Administrator(Role.ADMINISTRATOR, name, gender, age));
//                 } else {
//                     System.out.println("Invalid role.");
//                 }
//                 break;
//             case 2:
//                 System.out.println("Feature not implemented for this demo.");
//                 break;
//             case 3:
//                 System.out.println("Feature not implemented for this demo.");
//                 break;
//             case 4:
//                 return;
//             default:
//                 System.out.println("Invalid choice.");
//         }
//     }

//     private static void viewAppointmentsDetails() {
//         System.out.println("\n--- View Appointments Details ---");
//         System.out.println("Feature not implemented for this demo.");
//     }

//     private static void viewAndManageMedicationInventory(Administrator admin) {
//         System.out.println("\n--- View and Manage Medication Inventory ---");
//         admin.viewMedicationInventory();

//         System.out.println("\nOptions:");
//         System.out.println("1. Add Medication");
//         System.out.println("2. Update Stock Level");
//         System.out.println("3. Update Low Stock Alert Level");
//         System.out.println("4. Remove Medication");
//         System.out.println("5. Back to Main Menu");
//         System.out.print("Enter your choice: ");
//         int choice = scanner.nextInt();
//         scanner.nextLine();

//         switch (choice) {
//             case 1:
//                 System.out.println("Enter Medication Name: ");
//                 String name = scanner.nextLine();
//                 System.out.println("Enter Stock Level: ");
//                 int stock = scanner.nextInt();
//                 System.out.println("Enter Low Stock Alert Level: ");
//                 int alertLevel = scanner.nextInt();
//                 System.out.println("Enter Price: ");
//                 double price = scanner.nextDouble();
//                 scanner.nextLine();
//                 admin.addMedication(name, stock, alertLevel, price);
//                 break;
//             case 2:
//                 System.out.println("Enter Medication Name: ");
//                 String updateName = scanner.nextLine();
//                 System.out.println("Enter New Stock Level: ");
//                 int newStock = scanner.nextInt();
//                 scanner.nextLine();
//                 admin.updateMedicationStockLevel(updateName, newStock);
//                 break;
//             case 3:
//                 System.out.println("Enter Medication Name: ");
//                 String alertName = scanner.nextLine();
//                 System.out.println("Enter New Low Stock Alert Level: ");
//                 int newAlert = scanner.nextInt();
//                 scanner.nextLine();
//                 admin.updateLowStockAlertLevel(alertName, newAlert);
//                 break;
//             case 4:
//                 System.out.println("Enter Medication Name: ");
//                 String removeName = scanner.nextLine();
//                 admin.removeMedication(removeName);
//                 break;
//             case 5:
//                 return;
//             default:
//                 System.out.println("Invalid choice.");
//         }
//     }

//     private static void approveReplenishmentRequests(Administrator admin) {
//         System.out.println("\n--- Approve Replenishment Requests ---");
//         System.out.println("Feature not implemented for this demo.");
//     }

//     private static void logout() {
//         System.out.println("\nLogging out. Goodbye!");
//     }
// }
