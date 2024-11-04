package inventory;

public class Medication {
    private String medicationId;
    private String name;
    private String description;

    public Medication(String medicationId, String name, String description) {
        this.medicationId = medicationId;
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    public String getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
