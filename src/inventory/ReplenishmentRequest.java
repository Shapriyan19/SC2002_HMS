package inventory;

import java.util.ArrayList;
import java.util.List;

public class ReplenishmentRequest {
    private static List<ReplenishmentRequest> pendingRequests = new ArrayList<>();
    private String medicationName;
    private int requestedQuantity;
    private String pharmacistName;

    // Constructor
    public ReplenishmentRequest(String medicationName, int requestedQuantity, String pharmacistName) {
        this.medicationName = medicationName;
        this.requestedQuantity = requestedQuantity;
        this.pharmacistName = pharmacistName;
    }

    // Add a request to the shared list
    public static void addReplenishmentRequest(ReplenishmentRequest request) {
        pendingRequests.add(request);
        System.out.println("Replenishment request added to the system.");
    }

    // Get the list of pending requests
    public static List<ReplenishmentRequest> getPendingRequests() {
        return pendingRequests;
    }

    // Getters
    public String getMedicationName() {
        return medicationName;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public String getPharmacistName() {
        return pharmacistName;
    }

    // Approve the request (this logic can also be in the Administrator class)
    public static void approveRequest(ReplenishmentRequest request) {
        pendingRequests.remove(request);
    }
}
