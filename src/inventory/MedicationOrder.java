package inventory;

public class MedicationOrder {
    private String orderId;
    private String medicationId;
    private int quantityOrdered;

    public MedicationOrder(String orderId, String medicationId, int quantityOrdered) {
        this.orderId = orderId;
        this.medicationId = medicationId;
        this.quantityOrdered = quantityOrdered;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }
}
