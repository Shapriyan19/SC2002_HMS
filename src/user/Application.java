
import user.Administrator;
import user.Doctor;
import user.Pharmacist;
import user.Role;

import java.util.Scanner;

public class Application {

    private Administrator admin;

    public Application() {
        // Initialize the application with a sample administrator
        admin = new Administrator(Role.ADMINISTRATOR, "Alice Smith", "Female", 45);
    }

    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Hospital Staff Management ---");
            System.out.println("1. View Hospital Staff");
            System.out.println("2. Add New Staff Member");
            System.out.println("3. Update Staff Information");
            System.out.println("4. Remove Staff Member");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    admin.viewHospitalStaff();
                    break;
                case 2:
                    addStaff(scanner);
                    break;
                case 3:
                    updateStaff(scanner);
                    break;
                case 4:
                    removeStaff(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the application.");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private void addStaff(Scanner scanner) {
        System.out.print("Enter role (DOCTOR/PHARMACIST/ADMINISTRATOR): ");
        Role role = Role.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (role == Role.DOCTOR) {
            Doctor newDoctor = new Doctor(role, name, gender, age);
            admin.manageHospitalDoctor("add", newDoctor);
        } else if (role == Role.PHARMACIST) {
            Pharmacist newPharmacist = new Pharmacist(role, name, gender, age);
            admin.manageHospitalPharmacist("add", newPharmacist);
        } else if (role == Role.ADMINISTRATOR) {
            Administrator newAdmin = new Administrator(role, name, gender, age);
            admin.manageHospitalAdministrator("add", newAdmin);
        }

        System.out.println("New staff member added successfully.");
    }

    private void updateStaff(Scanner scanner) {
        System.out.print("Enter the role of the staff to update (DOCTOR/PHARMACIST/ADMINISTRATOR): ");
        Role role = Role.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter staff ID to update: ");
        String id = scanner.nextLine();

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter new age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (role == Role.DOCTOR) {
            Doctor updatedDoctor = new Doctor(id, name, "password", role, gender, age);
            admin.manageHospitalDoctor("update", updatedDoctor);
        } else if (role == Role.PHARMACIST) {
            Pharmacist updatedPharmacist = new Pharmacist(id, name, "password", role, gender, age);
            admin.manageHospitalPharmacist("update", updatedPharmacist);
        } else if (role == Role.ADMINISTRATOR) {
            Administrator updatedAdmin = new Administrator(id, name, "password", role, gender, age);
            admin.manageHospitalAdministrator("update", updatedAdmin);
        }

        System.out.println("Staff information updated successfully.");
    }

    private void removeStaff(Scanner scanner) {
        System.out.print("Enter the role of the staff to remove (DOCTOR/PHARMACIST/ADMINISTRATOR): ");
        Role role = Role.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter staff ID to remove: ");
        String id = scanner.nextLine();

        if (role == Role.DOCTOR) {
            Doctor doctorToRemove = new Doctor(id, "temp", "password", role, "temp", 0); // Temporary object to pass ID
            admin.manageHospitalDoctor("remove", doctorToRemove);
        } else if (role == Role.PHARMACIST) {
            Pharmacist pharmacistToRemove = new Pharmacist(id, "temp", "password", role, "temp", 0);
            admin.manageHospitalPharmacist("remove", pharmacistToRemove);
        } else if (role == Role.ADMINISTRATOR) {
            Administrator adminToRemove = new Administrator(id, "temp", "password", role, "temp", 0);
            admin.manageHospitalAdministrator("remove", adminToRemove);
        }

        System.out.println("Staff member removed successfully.");
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.mainMenu();
    }
}
 {
    
}
