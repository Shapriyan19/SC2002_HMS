package billing;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private List<InvoiceItem> items;

    public Invoice() {
        items = new ArrayList<>();
    }

    public void addItem(String description, double cost) {
        items.add(new InvoiceItem(description, cost));
    }

    public double calculateTotal() {
        double total = 0;
        for (InvoiceItem item : items) {
            total += item.getCost();
        }
        return total;
    }

    public void printInvoiceItems() {
        for (InvoiceItem item : items) {
            System.out.println(item.getDescription() + ": $" + item.getCost());
        }
    }

    private class InvoiceItem {
        private String description;
        private double cost;

        public InvoiceItem(String description, double cost) {
            this.description = description;
            this.cost = cost;
        }

        public String getDescription() {
            return description;
        }

        public double getCost() {
            return cost;
        }
    }
}
