
package billing;

import appointment.AppointmentOutcomeRecord;
import appointment.MedicationRecord;
import java.util.List;

public class Invoice {
    private String patientName;
    private String appointmentDate;
    private String serviceType;
    private double serviceCost;
    private List<MedicationRecord> prescribedMedications; // Updated to MedicationRecord
    private double medicationsCost;
    private double totalCost;

    public Invoice(String patientName, AppointmentOutcomeRecord outcome, double serviceCost) {
        this.patientName = patientName;
        this.appointmentDate = outcome.getAppointmentDate();
        this.serviceType = outcome.getServiceType();
        this.serviceCost = serviceCost;
        this.prescribedMedications = outcome.getPrescribedMedications(); // Use MedicationRecord
        this.medicationsCost = calculateMedicationsCost(prescribedMedications);
        this.totalCost = this.serviceCost + this.medicationsCost;
    }

    private double calculateMedicationsCost(List<MedicationRecord> medications) {
        double total = 0.0;
        for (MedicationRecord medication : medications) {
            total += medication.getPrice()*medication.getDosage(); // Use MedicationRecord's price and quantity
        }
        return total;
    }

    public void printInvoice() {
        System.out.println("\n--- Invoice ---");
        System.out.println("Patient Name: " + patientName);
        System.out.println("Appointment Date: " + appointmentDate);
        System.out.println("Service Type: " + serviceType);
        System.out.println("Service Cost: $" + serviceCost);
        System.out.println("\nPrescribed Medications:");
        for (MedicationRecord medication : prescribedMedications) {
            System.out.println("- " + medication.getMedicationName() + " x" + medication.getDosage() +
                               " ($" + medication.getPrice() + " each)");
        }
        System.out.println("Medications Cost: $" + medicationsCost);
        System.out.println("\nTotal Cost: $" + totalCost);
    }

    public double getTotalCost() {
        return totalCost;
    }
    public String getServiceType() { // Getter for serviceType
        return serviceType;
    }

    public String getPatientName() { // Getter for serviceType
        return patientName;
    }
    public String getAppointmentDate() { // Getter for serviceType
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