package billing;

import appointment.AppointmentOutcomeRecord;
import appointment.MedicationRecord;
import java.util.List;
import java.util.Random;

public class Invoice {
    private String patientName;
    private String appointmentDate;
    private String serviceType;
    private double serviceCost; // Randomly generated value between 50 and 150
    private List<MedicationRecord> prescribedMedications;
    private double medicationsCost;
    private double totalCost;

    public Invoice(String patientName, AppointmentOutcomeRecord outcome) {
        this.patientName = patientName;
        this.appointmentDate = outcome.getAppointmentDate();
        this.serviceType = outcome.getServiceType();
        this.serviceCost = generateRandomServiceCost(); // Generate random service cost
        this.prescribedMedications = outcome.getPrescribedMedications();
        this.medicationsCost = calculateMedicationsCost(prescribedMedications);
        this.totalCost = this.serviceCost + this.medicationsCost;
    }

    // Generate a random service cost between 50 and 150
    private double generateRandomServiceCost() {
        Random random = new Random();
        return 50 + (100 * random.nextDouble()); // Random value between 50 and 150
    }

    // Calculate the total cost of prescribed medications
    private double calculateMedicationsCost(List<MedicationRecord> medications) {
        double total = 0.0;
        for (MedicationRecord medication : medications) {
            total += medication.getPrice() * medication.getDosage();
        }
        return total;
    }

    // Print the invoice details
    public void printInvoice() {
        System.out.println("\n--- Invoice ---");
        System.out.println("Patient Name: " + patientName);
        System.out.println("Appointment Date: " + appointmentDate);
        System.out.println("Service Type: " + serviceType);
        System.out.println("Service Cost: $" + String.format("%.2f", serviceCost));
        System.out.println("\nPrescribed Medications:");
        for (MedicationRecord medication : prescribedMedications) {
            System.out.println("- " + medication.getMedicationName() + " x" + medication.getDosage() +
                               " ($" + medication.getPrice() + " each)");
        }
        System.out.println("Medications Cost: $" + String.format("%.2f", medicationsCost));
        System.out.println("\nTotal Cost: $" + String.format("%.2f", totalCost));
    }

    // Getters
    public double getTotalCost() {
        return totalCost;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }
}




// package billing;

// import appointment.AppointmentOutcomeRecord;
// import inventory.Medication;
// import appointment.MedicationRecord;
// import java.util.List;

// public class Invoice {
//     private String patientName;
//     private String appointmentDate;
//     private String serviceType;
//     private double serviceCost;
//     private List<MedicationRecord> prescribedMedications;
//     private double medicationsCost;
//     private double totalCost;

//     public Invoice(String patientName, AppointmentOutcomeRecord outcome, double serviceCost) {
//         this.patientName = patientName;
//         this.appointmentDate = outcome.getAppointmentDate();
//         this.serviceType = outcome.getServiceType();
//         this.serviceCost = serviceCost;
//         this.prescribedMedications = outcome.getPrescribedMedications(); // List of Medication objects
//         this.medicationsCost = calculateMedicationsCost(prescribedMedications);
//         this.totalCost = this.serviceCost + this.medicationsCost;
//     }

//     private double calculateMedicationsCost(List<Medication> medications) {
//         double total = 0.0;
//         for (Medication medication : medications) {
//             total += medication.getPrice(); // Using Medication class's price
//         }
//         return total;
//     }

//     public void printInvoice() {
//         System.out.println("\n--- Invoice ---");
//         System.out.println("Patient Name: " + patientName);
//         System.out.println("Appointment Date: " + appointmentDate);
//         System.out.println("Service Type: " + serviceType);
//         System.out.println("Service Cost: $" + serviceCost);
//         System.out.println("\nPrescribed Medications:");
//         for (Medication medication : prescribedMedications) {
//             System.out.println("- " + medication.getName() + " ($" + medication.getPrice() + ")");
//         }
//         System.out.println("Medications Cost: $" + medicationsCost);
//         System.out.println("\nTotal Cost: $" + totalCost);
//     }

//     public double getTotalCost() {
//         return totalCost;
//     }
// }
































// // package billing;

// // import java.util.ArrayList;
// // import java.util.List;

// // public class Invoice {
// //     private List<InvoiceItem> items;

// //     public Invoice() {
// //         items = new ArrayList<>();
// //     }

// //     public void addItem(String description, double cost) {
// //         items.add(new InvoiceItem(description, cost));
// //     }

// //     public double calculateTotal() {
// //         double total = 0;
// //         for (InvoiceItem item : items) {
// //             total += item.getCost();
// //         }
// //         return total;
// //     }

// //     public void printInvoiceItems() {
// //         for (InvoiceItem item : items) {
// //             System.out.println(item.getDescription() + ": $" + item.getCost());
// //         }
// //     }

// //     private class InvoiceItem {
// //         private String description;
// //         private double cost;

// //         public InvoiceItem(String description, double cost) {
// //             this.description = description;
// //             this.cost = cost;
// //         }

// //         public String getDescription() {
// //             return description;
// //         }

// //         public double getCost() {
// //             return cost;
// //         }
// //     }
// }