package medical;

public class Diagnosis {

    private int diagnosisID;
    private String description;
    private String date;

    // Constructor
    public Diagnosis(int diagnosisID, String description, String date) {
        this.diagnosisID = diagnosisID;
        this.description = description;
        this.date = date;
    }

    // Getters
    public int getDiagnosisID() {
        return diagnosisID;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    // Setters
    public void setDiagnosisID(int diagnosisID) {
        this.diagnosisID = diagnosisID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Diagnosis ID: " + diagnosisID + ", Description: " + description + ", Date: " + date;
    }

}
