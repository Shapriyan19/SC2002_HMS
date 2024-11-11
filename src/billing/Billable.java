package billing;

public interface Billable {
    void generateBill();
    void processPayment(double amount);
    void issueRefund(double amount);
}
