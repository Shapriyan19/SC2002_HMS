package billing;

import java.util.Date;

public class Payment {
    private double amount;
    private Date date;

    public Payment(double amount, Date date) {
        this.amount = amount;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
