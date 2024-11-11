package billing;

public class Billing implements Billable {
    private Invoice invoice;

    public Billing(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public void generateBill() {
        System.out.println("Generating bill for the following items:");
        invoice.printInvoiceItems();
        System.out.println("Total Amount: " + invoice.calculateTotal());
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of amount: $" + amount);
        // Implement logic for processing the payment
    }

    @Override
    public void issueRefund(double amount) {
        System.out.println("Issuing refund of amount: $" + amount);
        // Implement logic for issuing a refund
    }
}
