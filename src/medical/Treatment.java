public class Treatment {
    private String treatmentName;
    private String startDate;
    private String endDate;

    public Treatment(String treatmentName, String startDate, String endDate) {
        this.treatmentName = treatmentName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Treatment: " + treatmentName + " from " + startDate + " to " + endDate;
    }
}
