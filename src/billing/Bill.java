package billing;

public class Bill {
    private Invoice invoice;
    private boolean isPaid;

    public Bill(Invoice invoice) {
        this.invoice = invoice;
        this.isPaid = false; // Initially unpaid
    }

    public void payBill() {
        if (isPaid) {
            System.out.println("\nThe bill is already paid.");
        } else {
            System.out.println("\nProcessing payment for: $" + invoice.getTotalCost());
            this.isPaid = true; // Mark as paid
            generateReceipt();
        }
    }

    private void generateReceipt() {
        System.out.println("\n--- Payment Receipt ---");
        System.out.println("Patient Name: " + invoice.getPatientName());
        System.out.println("Appointment Date: " + invoice.getAppointmentDate());
        System.out.println("Service Type: " + invoice.getServiceType());
        System.out.println("Total Paid: $" + invoice.getTotalCost());
        System.out.println("Payment Status: Paid");
        System.out.println("Thank you for your payment!");
    }

    public boolean isPaid() {
        return isPaid;
    }
}
