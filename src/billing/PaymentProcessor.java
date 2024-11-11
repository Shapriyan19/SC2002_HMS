package billing;

public class PaymentProcessor implements Billable {

    @Override
    public void generateBill() {
        System.out.println("Generating bill via PaymentProcessor...");
        // Implement logic to generate a bill through PaymentProcessor
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of amount: $" + amount + " via PaymentProcessor");
        // Implement logic to process the payment
    }

    @Override
    public void issueRefund(double amount) {
        System.out.println("Issuing refund of amount: $" + amount + " via PaymentProcessor");
        // Implement logic to issue a refund
    }
}
